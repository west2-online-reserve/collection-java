import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Tools {
    private static final String BASE_URL = "https://api.worldaquatics.com/fina/competitions/3337/athletes?gender=&countryId=";
    private static final String JSON_PATH = "src/JsonData/";
    private static final Gson GSON = new Gson();

    public static void fetchAndWriteAthletes(String outputFile) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URL);
            request.setHeader("Accept", "application/json");

            try (CloseableHttpResponse response = client.execute(request)) {
                String jsonString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                JsonArray countries = GSON.fromJson(jsonString, JsonArray.class);

                List<Athlete> athletes = parseAthletes(countries);
                athletes.sort(createAthleteComparator());

                writeAthletesToFile(athletes, outputFile);
            }
        } catch (Exception e) {
            throw new IOException("Failed to fetch and write athletes", e);
        }
    }

    private static List<Athlete> parseAthletes(JsonArray countries) {
        List<Athlete> athletes = new ArrayList<>();
        countries.forEach(country -> {
            JsonObject countryObj = country.getAsJsonObject();
            String countryName = countryObj.get("CountryName").getAsString();
            countryObj.getAsJsonArray("Participations").forEach(athlete -> {
                JsonObject athleteObj = athlete.getAsJsonObject();
                String fullName = athleteObj.get("PreferredLastName").getAsString() + " " +
                        athleteObj.get("PreferredFirstName").getAsString();
                String gender = athleteObj.get("Gender").getAsInt() == 0 ? "Male" : "Female";
                athletes.add(new Athlete(fullName, gender, countryName));
            });
        });
        return athletes;
    }

    private static Comparator<Athlete> createAthleteComparator() {
        return Comparator.comparing(Athlete::getCountry)
                .thenComparing(a -> a.getFullName().split(" ")[0]);
    }

    private static void writeAthletesToFile(List<Athlete> athletes, String outputFile) throws IOException {
        StringBuilder output = new StringBuilder();
        athletes.forEach(athlete -> {
            output.append("Full Name:").append(reverseName(athlete.getFullName())).append("\n")
                    .append("Gender:").append(athlete.getGender()).append("\n")
                    .append("Country:").append(athlete.getCountry()).append("\n")
                    .append("-----\n");
        });
        FileUtils.writeStringToFile(new File(outputFile), output.toString(), StandardCharsets.UTF_8, true);
    }

    public static ArrayList<String> WriteCompetition(String output, String fileName) throws IOException {
        String jsonString = FileUtils.readFileToString(new File(JSON_PATH + fileName + ".json"), StandardCharsets.UTF_8);
        JsonArray competition = GSON.fromJson(jsonString, JsonArray.class);

        List<Competition> competitions = new ArrayList<>();
        ArrayList<String> testExample = new ArrayList<>();

        competition.forEach(comp -> {
            JsonObject competitor = comp.getAsJsonObject();
            if ("Finals".equals(competitor.get("PhaseName").getAsString())) {
                String fullName = competitor.get("FullName").getAsString();
                String rank = competitor.get("Rank").getAsString();
                String score = formatScore(competitor);

                competitions.add(new Competition(fullName, rank, score));
                testExample.add(competitions.get(competitions.size() - 1).toString());
            }
        });

        writeCompetitionsToFile(competitions, output);
        return testExample;
    }

    private static String formatScore(JsonObject competitor) {
        StringBuilder sb = new StringBuilder();
        JsonArray scores = competitor.get("Scores").getAsJsonArray();
        String totalScore = competitor.get("TotalScore").getAsString();

        scores.forEach(score -> {
            if (sb.length() > 0) sb.append("+");
            sb.append(score.getAsString());
        });
        return sb.append("=").append(totalScore).toString();
    }

    private static void writeCompetitionsToFile(List<Competition> competitions, String outputFile) throws IOException {
        StringBuilder output = new StringBuilder();
        competitions.forEach(comp -> {
            output.append("Full Name:").append(reverseName(comp.getFullName())).append("\n")
                    .append("Rank:").append(comp.getRank()).append("\n")
                    .append("Score:").append(comp.getScore()).append("\n")
                    .append("-----\n");
        });
        FileUtils.writeStringToFile(new File(outputFile), output.toString(), StandardCharsets.UTF_8, true);
    }

    public static ArrayList<String> writeCompetitionDetails(String outputFile, String event) throws IOException {
        String jsonString = FileUtils.readFileToString(new File(JSON_PATH + event + ".json"), StandardCharsets.UTF_8);
        JsonArray competition = GSON.fromJson(jsonString, JsonArray.class);

        Map<String, CompetitionDetail> athleteMap = new HashMap<>();
        ArrayList<String> testExample = new ArrayList<>();

        competition.forEach(comp -> {
            JsonObject competitor = comp.getAsJsonObject();
            String fullName = competitor.get("FullName").getAsString();
            String phaseName = competitor.get("PhaseName").getAsString();
            String rank = competitor.get("Rank").getAsString();
            String score = formatScore(competitor);

            CompetitionDetail detail = athleteMap.computeIfAbsent(fullName,
                    k -> new CompetitionDetail(fullName, "*", "*", "*", "*", "*", "*"));

            updatePhaseScores(detail, phaseName, rank, score);
        });

        List<CompetitionDetail> competitions = new ArrayList<>(athleteMap.values());
        competitions.sort(createDetailComparator());

        writeDetailsToFile(competitions, outputFile);
        competitions.forEach(comp -> testExample.add(comp.toString()));

        return testExample;
    }

    private static void updatePhaseScores(CompetitionDetail detail, String phaseName, String rank, String score) {
        switch (phaseName) {
            case "Preliminaries":
                detail.setPreliminaryScore(score);
                detail.setPreliminaryRank(rank);
                break;
            case "Semifinals":
                detail.setSemifinalScore(score);
                detail.setSemifinalRank(rank);
                break;
            case "Finals":
                detail.setFinalScore(score);
                detail.setFinalsRank(rank);
                break;
        }
    }

    private static Comparator<CompetitionDetail> createDetailComparator() {
        return Comparator.comparingInt((CompetitionDetail c) -> {
            try {
                return !c.getPreliminaryRank().equals("*") ?
                        (!c.getFinalsRank().equals("*") ? Integer.parseInt(c.getPreliminaryRank()) :
                                Integer.parseInt(c.getSemifinalRank())) :
                        Integer.parseInt(c.getFinalsRank());
            } catch (NumberFormatException e) {
                return Integer.MAX_VALUE;
            }
        });
    }

    private static void writeDetailsToFile(List<CompetitionDetail> competitions, String outputFile) throws IOException {
        StringBuilder output = new StringBuilder();
        competitions.forEach(comp -> {
            output.append("Full Name:").append(reverseName(comp.getFullName())).append("\n")
                    .append("Rank:").append(comp.getPreliminaryRank()).append(" | ")
                    .append(comp.getSemifinalRank()).append(" | ")
                    .append(comp.getFinalsRank()).append("\n")
                    .append("Preliminary Score:").append(comp.getPreliminaryScore()).append("\n")
                    .append("Semifinal Score:").append(comp.getSemifinalScore()).append("\n")
                    .append("Final Score:").append(comp.getFinalScore()).append("\n")
                    .append("-----\n");
        });
        FileUtils.writeStringToFile(new File(outputFile), output.toString(), StandardCharsets.UTF_8, true);
    }

    public static void choose(List<String> commands, String outputFile) throws IOException {
        commands.forEach(command -> {
            command = command.trim();
            try {
                if ("players".equals(command)) {
                    fetchAndWriteAthletes(outputFile);
                } else if (command.startsWith("result ")) {
                    handleResultCommand(command, outputFile);
                } else {
                    printError(outputFile);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to process command: " + command, e);
            }
        });
        System.out.println("处理完成，结果已保存至 " + outputFile);
    }

    private static void handleResultCommand(String command, String outputFile) throws IOException {
        if (!checkSpaces(command)) {
            printError(outputFile);
            return;
        }

        String event = command.substring(7).trim();
        if (command.endsWith(" detail")) {
            event = event.substring(0, event.length() - 7).trim();
            if (check(event)) {
                writeCompetitionDetails(outputFile, event);
            } else {
                printError(outputFile);
            }
        } else {
            if (check(event)) {
                WriteCompetition(outputFile, event);
            } else {
                printError(outputFile);
            }
        }
    }

    private static void printError(String outputFile) throws IOException {
        FileUtils.writeStringToFile(new File(outputFile), "N/A\n-----\n", StandardCharsets.UTF_8, true);
    }

    public static boolean check(String competition) {
        return Const.EVENTS.contains(competition);
    }

    public static boolean checkSpaces(String str) {
        if (str.length() <= 13) return false;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) == ' ') {
                return false;
            }
        }
        return true;
    }

    public static String reverseName(String input) {
        if (input == null || input.trim().isEmpty()) return input;

        String trimmedInput = input.trim();
        if (trimmedInput.contains("&")) {
            return handleCompoundName(trimmedInput);
        }
        return handleSingleName(trimmedInput);
    }

    private static String handleCompoundName(String input) {
        String[] sides = input.split("&", 2);
        if (sides.length != 2) return input;

        String leftReversed = handleSingleName(sides[0].trim());
        String rightReversed = handleSingleName(sides[1].trim());

        return leftReversed + " & " + rightReversed;
    }

    private static String handleSingleName(String input) {
        String[] parts = input.split("\\s+");
        return parts.length == 2 ? parts[1] + " " + parts[0] : input;
    }
}
