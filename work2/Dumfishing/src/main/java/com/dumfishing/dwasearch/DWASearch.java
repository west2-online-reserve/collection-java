package com.dumfishing.dwasearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DWASearch {
    private static final Map<String, String> KEYWORD_TO_FILE = Map.of(
            "women 3m springboard",   "Women's 3m Springboard.json",
            "women 10m platform",     "Women's 10m Platform.json",
            "women 3m synchronised",  "Women's 3m Synchronised.json",
            "women 10m synchronised", "Women's 10m Synchronised.json",
            "men 3m springboard",     "Men's 3m Springboard.json",
            "men 10m platform",       "Men's 10m Platform.json",
            "men 3m synchronised",    "Men's 3m Synchronised.json",
            "men 10m synchronised",   "Men's 10m Synchronised.json"
    );
    enum Command {
        PLAYERS,
        RESULT,
        RESULT_NO_NAME,
        UNKNOWN
    }
    static String requireText(JsonNode node, String field, String context) {
        JsonNode v = node.get(field);
        if (v == null) {
            throw new IllegalStateException("missing '" + field + "' in " + context);
        }
        return v.asText();
    }
    static String formatPlayers(List<Player> players) {
        StringBuilder sb = new StringBuilder();
        for (Player player: players) {
            sb.append("Full Name:").append(player.lastname()).append(" ").append(player.firstname()).append("\n");
            sb.append("Gender:").append(player.gender()).append("\n");
            sb.append("Country:").append(player.country()).append("\n");
            sb.append("-----\n");
        }
        return sb.toString();
    }

    static List<Player> loadPlayers(ObjectMapper om) throws Exception {
        JsonNode root = om.readTree(new File("data/athletes.json"));
        List<Player> players = new ArrayList<>();
        for (JsonNode country : root) {
            String countryName = country.get("CountryName").asText();
            for (JsonNode p : country.get("Participations")) {
                String lastname = p.get("PreferredLastName").asText();
                String firstname = p.get("PreferredFirstName").asText();
                String gender;
                if(p.get("Gender").asInt() == 1) {
                    gender = "Female";
                }else {
                    gender = "Male";
                }
                players.add(new Player(lastname, firstname, gender, countryName));
            }
        }
        players.sort(Comparator.comparing(Player::country).thenComparing(Player::lastname));
        return players;
    }

    static String formatEvent(ObjectMapper om, String file) throws Exception{
        StringBuilder sb = new StringBuilder();
        JsonNode root = om.readTree(new File("data/" + file));
        JsonNode heats = root.get("Heats");
        if (heats == null || !heats.isArray()) {
            throw new IllegalStateException("missing 'Heats' in " + file);
        }
        for (JsonNode heat: heats) {
            if ("Finals".equals(heat.path("Name").asText())) {
                JsonNode result = heat.get("Results");
                for (JsonNode playerResult: result) {
                    List<String> Scores= new ArrayList<>();
                    JsonNode dives = playerResult.get("Dives");
                    String allTotalPoints = new BigDecimal(requireText(playerResult, "TotalPoints", file)).setScale(2, RoundingMode.HALF_UP).toPlainString();
                    String fullName = requireText(playerResult, "FullName", file);
                    String rank = requireText(playerResult, "Rank", file);
                    for (JsonNode scores: dives) {
                        String DivePoints = new BigDecimal(scores.get("DivePoints").asText()).setScale(2, RoundingMode.HALF_UP).toPlainString();
                        Scores.add(DivePoints);
                    }
                    String Score = String.join(" + ", Scores);
                    sb.append("Full Name:").append(fullName).append("\n");
                    sb.append("Rank:").append(rank).append("\n");
                    sb.append("Score:").append(Score).append(" = ").append(allTotalPoints).append("\n");
                    sb.append("-----\n");
                }
            }
        }
        return sb.toString();
    }

    static Command classify(String line) {
        if ("players".equals(line)) {
            return Command.PLAYERS;
        }
        if (line.startsWith("result ")) {
            return Command.RESULT;
        }
        if ("result".equals(line)) {
            return Command.RESULT_NO_NAME;
        }
        return Command.UNKNOWN;
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar DWASearch.jar <input.txt> <output.txt>");
            System.exit(2);
        }
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(args[0]), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error: cannot read input file: " + args[0]);
            System.err.println("Reason: " + e.getMessage());
            System.exit(3);
            return;
        }

        StringBuilder sb = new StringBuilder();
        ObjectMapper om = new ObjectMapper();
        List<Player> players;
        try {
            players = loadPlayers(om);
        }catch (Exception e) {
            System.err.println("Error: cannot load athlete data from data/athletes.json");
            System.err.println("Reason: " + e.getMessage());
            System.exit(4);
            return;
        }

        for (String line: lines) {
            if (line.isBlank()) continue;
            switch (classify(line)) {
                case PLAYERS -> sb.append(formatPlayers(players));
                case RESULT_NO_NAME -> sb.append("N/A\n-----\n");
                case RESULT -> {
                    String name = line.substring("result ".length());
                    String file = KEYWORD_TO_FILE.get(name);
                    if (file == null) {
                        sb.append("N/A\n-----\n");
                    }else {
                        try {
                            sb.append(formatEvent(om, file));
                        }catch (Exception e) {
                            System.err.println("Error: cannot load event data: " + file);
                            System.err.println("Reason: " + e.getMessage());
                            System.exit(4);
                            return;
                        }
                    }
                }
                case UNKNOWN -> sb.append("Error\n-----\n");
            }
        }
        try {
            Files.writeString(Path.of(args[1]), sb.toString(), StandardCharsets.UTF_8);
        }catch (IOException e) {
            System.err.println("Error: cannot write output file: " + args[1]);
            System.err.println("Reason: " + e.getMessage());
            System.exit(5);
        }

    }
}

