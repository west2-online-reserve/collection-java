import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Offline, reusable API for Doha 2024 diving entrants and results. */
public final class CoreModule {
    private static final Map<String, String> EVENTS;
    static {
        Map<String, String> events = new LinkedHashMap<>();
        events.put("women 1m springboard", "women-1m");
        events.put("women 3m springboard", "women-3m");
        events.put("women 10m platform", "women-10m");
        events.put("women 3m synchronised", "women-3m-synchronised");
        events.put("women 10m synchronised", "women-10m-synchronised");
        events.put("men 1m springboard", "men-1m");
        events.put("men 3m springboard", "men-3m");
        events.put("men 10m platform", "men-10m");
        events.put("men 3m synchronised", "men-3m-synchronised");
        events.put("men 10m synchronised", "men-10m-synchronised");
        EVENTS = Collections.unmodifiableMap(events);
    }

    private record Result(String rank, String name, String score) { }
    private static final class AthleteResult {
        final String name;
        final String[] ranks = {"*", "*", "*"};
        final String[] scores = {"*", "*", "*"};
        AthleteResult(String name) { this.name = name; }
        void set(int stage, Result result) {
            ranks[stage] = result.rank;
            scores[stage] = result.score;
        }
    }

    private final String allPlayers;
    private final Map<String, String> finalResults;
    private final Map<String, String> detailedResults;

    public CoreModule(InputStream playerData, InputStream resultData) throws IOException {
        if (playerData == null || resultData == null) throw new IOException("Missing bundled data files");
        allPlayers = loadPlayers(playerData);
        Map<String, Map<String, List<Result>>> stages = loadResults(resultData);
        Map<String, String> finals = new LinkedHashMap<>();
        Map<String, String> details = new LinkedHashMap<>();
        for (var event : EVENTS.entrySet()) {
            Map<String, List<Result>> byStage = stages.get(event.getValue());
            if (byStage == null || !byStage.containsKey("final")) {
                throw new IOException("Missing final results for " + event.getKey());
            }
            finals.put(event.getKey(), formatFinal(byStage.get("final")));
            details.put(event.getKey(), formatDetail(byStage));
        }
        finalResults = Collections.unmodifiableMap(finals);
        detailedResults = Collections.unmodifiableMap(details);
    }

    public static CoreModule fromBundledData() throws IOException {
        try (InputStream players = CoreModule.class.getResourceAsStream("/data/players.tsv");
             InputStream results = CoreModule.class.getResourceAsStream("/data/results.tsv")) {
            return new CoreModule(players, results);
        }
    }

    public static List<String> supportedEvents() {
        return List.copyOf(EVENTS.keySet());
    }

    public boolean supportsEvent(String event) {
        return EVENTS.containsKey(event);
    }

    public String displayAllPlayersInfo() {
        return allPlayers;
    }

    public String displayResultsForEachEvent(String event, boolean detail) {
        String result = detail ? detailedResults.get(event) : finalResults.get(event);
        if (result == null) throw new IllegalArgumentException("Unknown event: " + event);
        return result;
    }

    private static String loadPlayers(InputStream input) throws IOException {
        StringBuilder out = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\t", -1);
                if (p.length != 3 || p[0].isBlank() || p[1].isBlank() || p[2].isBlank()) {
                    throw new IOException("Malformed player row: " + line);
                }
                out.append(Lib.block("Full Name:" + p[0], "Gender:" + p[1], "Country:" + p[2]));
            }
        }
        if (out.length() == 0) throw new IOException("Empty player data");
        return out.toString();
    }

    private static Map<String, Map<String, List<Result>>> loadResults(InputStream input) throws IOException {
        Map<String, Map<String, List<Result>>> stages = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\t", -1);
                if (p.length != 6 || p[2].isBlank() || p[3].isBlank() || p[4].isBlank() || p[5].isBlank()) {
                    throw new IOException("Malformed result row: " + line);
                }
                if (!EVENTS.containsValue(p[0]) || stageIndex(p[1]) < 0) {
                    throw new IOException("Unknown event or stage: " + line);
                }
                stages.computeIfAbsent(p[0], k -> new LinkedHashMap<>())
                        .computeIfAbsent(p[1], k -> new ArrayList<>())
                        .add(new Result(p[2], p[3], Lib.score(p[4], p[5])));
            }
        }
        return stages;
    }

    private static String formatFinal(List<Result> results) {
        StringBuilder out = new StringBuilder();
        for (Result result : results) {
            out.append(Lib.block("Full Name:" + result.name,
                    "Rank:" + result.rank, "Score:" + result.score));
        }
        return out.toString();
    }

    private static String formatDetail(Map<String, List<Result>> stages) {
        Map<String, AthleteResult> athletes = new LinkedHashMap<>();
        for (String stage : List.of("preliminary", "semifinal", "final")) {
            for (Result result : stages.getOrDefault(stage, List.of())) {
                AthleteResult athlete = athletes.computeIfAbsent(result.name, AthleteResult::new);
                athlete.set(stageIndex(stage), result);
            }
        }
        StringBuilder out = new StringBuilder();
        for (AthleteResult athlete : athletes.values()) {
            out.append(Lib.block("Full Name:" + athlete.name,
                    "Rank:" + Lib.rank(athlete.ranks[0], athlete.ranks[1], athlete.ranks[2]),
                    "Preliminary Score:" + athlete.scores[0],
                    "Semifinal Score:" + athlete.scores[1],
                    "Final Score:" + athlete.scores[2]));
        }
        return out.toString();
    }

    private static int stageIndex(String stage) {
        return switch (stage) {
            case "preliminary" -> 0;
            case "semifinal" -> 1;
            case "final" -> 2;
            default -> -1;
        };
    }
}
