package com.org.Lskar.Swimming.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.org.Lskar.Swimming.lib.Contest.AthleteResult;
import com.org.Lskar.Swimming.lib.Contest.Event;
import com.org.Lskar.Swimming.lib.Contest.Heat;
import com.org.Lskar.Swimming.lib.Contest.Result;
import com.org.Lskar.Swimming.lib.Country;
import com.org.Lskar.Swimming.lib.Participation;
import com.org.Lskar.Swimming.lib.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static com.org.Lskar.Swimming.utils.OutputData.*;

public class CoreModule {
    private static final String DATA_PATH = "src\\main\\java\\com\\org.Lskar.Swimming\\data";
    private static final String PLAYER_PATH = DATA_PATH + "\\players.json";
    private static final String PATH = "src\\main\\java\\com\\org.Lskar.Swimming\\data\\competition\\";

    private List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        try (FileReader fileReader = new FileReader(PLAYER_PATH);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){

            Gson gson = new Gson();
            List<Country> countries = gson.fromJson(bufferedReader, new TypeToken<List<Country>>() {
            }.getType());
            for (Country country : countries) {
                String countryName = country.getCountryName();
                for (Participation participation : country.getParticipations()) {
                    Player player = new Player(
                            countryName,
                            participation.getGender(),
                            participation.getPreferredFirstName(),
                            participation.getPreferredLastName()
                    );
                    players.add(player);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }


    private int getPhasePriority(String phase) {
        return switch (phase) {
            case "Preliminary" -> 0;
            case "Semifinal" -> 1;
            case "Final" -> 2;
            default -> 3;
        };
    }


    private Map<String, AthleteResult> getAthletesInformation(List<Heat> heats) {

        Map<String, AthleteResult> athletes = new HashMap<>();

        List<Heat> orderedHeats = heats.stream()
                .sorted(Comparator.comparingInt(h -> getPhasePriority(h.getName())))
                .toList();

        for (Heat heat : orderedHeats) {
            String phase = heat.getName();
            for (Result result : heat.getResults()) {
                String name = formatName(result.getFullName());//处理双人项目

                AthleteResult athlete = athletes.computeIfAbsent(name, k -> new AthleteResult());
                athlete.setName(name);
                if (athlete.getFirstAppearanceRank() == Integer.MAX_VALUE) {
                    athlete.setFirstAppearanceRank(result.getRank());
                    athlete.setFirstAppearancePhase(phase);
                }

                switch (phase) {
                    case "Preliminary":
                        athlete.setPrelimRank(String.valueOf(result.getRank()));
                        athlete.setPrelimScores(buildScoreString(result.getDives(), result.getTotalPoints()));
                        break;
                    case "Semifinal":
                        athlete.setSemiRank(String.valueOf(result.getRank()));
                        athlete.setSemiScores(buildScoreString(result.getDives(), result.getTotalPoints()));
                        break;
                    case "Final":
                        athlete.setFinalRank(String.valueOf(result.getRank()));
                        athlete.setFinalScores(buildScoreString(result.getDives(), result.getTotalPoints()));
                        break;
                }
            }
        }

        return athletes;
    }


    private Map<String, AthleteResult> getAthleteSimpleInformation(List<Heat> heats) {
        Map<String, AthleteResult> athletes = new HashMap<>();
        for (Heat heat : heats) {
            String phase = heat.getName();
            if (phase.equals("Final")) {
                for (Result result : heat.getResults()) {
                    String name = formatName(result.getFullName());
                    AthleteResult athlete = athletes.computeIfAbsent(name, k -> new AthleteResult());
                    athlete.setName(name);
                    athlete.setFinalRank(String.valueOf(result.getRank()));
                    athlete.setFinalScores(OutputData.buildScoreString(result.getDives(), result.getTotalPoints()));
                }
            }
        }
        return athletes;
    }


    private List<AthleteResult> getSortedResults(String path) {

        List<AthleteResult> sortedResults = new ArrayList<>();

        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){

            Gson gson = new Gson();

            Event event = gson.fromJson(bufferedReader, Event.class);
            Map<String, AthleteResult> athletes = getAthletesInformation(event.getHeats());
            sortedResults = athletes.values().stream()
                    .sorted(Comparator.comparingInt((AthleteResult a) -> getPhasePriority(a.getFirstAppearancePhase()))
                            .thenComparingInt(AthleteResult::getFirstAppearanceRank))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedResults;
    }


    private List<AthleteResult> getSimpleResults(String path) {
        List<AthleteResult> simpleResults = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){

            Gson gson = new Gson();
            Event event = gson.fromJson(bufferedReader, Event.class);
            Map<String, AthleteResult> athletes = getAthleteSimpleInformation(event.getHeats());

            simpleResults = athletes.values().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return simpleResults;
    }


    //处理命令
    public String ProcessingCommands(String command) {
        String lowerCommand = command.toLowerCase();
        if (lowerCommand.equals("player")) {
            if (command.equals(lowerCommand)) {
                List<Player> players = getAllPlayers();
                players.sort(Comparator.comparing(Player::getCountryName).thenComparing(Player::getPreferredLastName));
                return outputPlayersData(players);
            } else return "Error";
        } else if (lowerCommand.startsWith("result")) {

            List<String> detailContests = new ArrayList<>();
            Collections.addAll(detailContests,
                    "women 1m springboard",
                    "women 3m springboard", "women 10m platform",
                    "women 3m synchronised", "women 10m synchronised",
                    "men 1m springboard", "men 3m springboard",
                    "men 10m platform", "men 3m synchronised",
                    "men 10m synchronised"
            );
            String[] parts = command.split(" ");

            //指令长度不对，无法读取指令
            if (parts.length != 4 && parts.length != 5) {
                return "Error";
            }

            String contestName = parts[1] + " " + parts[2] + " " + parts[3];

            if (parts.length == 5) {
                String detail = parts[4];
                if (!"detail".equals(detail)) {
                    return "N/A";
                }
            }
            //比赛名字错误
            if (!detailContests.contains(contestName)) {
                return "N/A";
            }
            //判断detail是否正确
            if (parts.length == 4) {
                List<AthleteResult> simpleResults = getSimpleResults(PATH + contestName + ".json");
                return outputAthleteSimpleResults(simpleResults);
            } else {
                List<AthleteResult> sortedResults = getSortedResults(PATH + contestName + ".json");
                return outputAthleteResults(sortedResults);
            }
        }

        //其他情况指令无法识别
        return "Error";
    }

}
