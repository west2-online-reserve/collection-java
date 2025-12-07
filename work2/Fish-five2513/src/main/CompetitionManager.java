package main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CompetitionManager {
    private Map<String, List<CompetitionDetail>> competitionDetails;
    private AthleteManager athleteManager;

    public CompetitionManager(AthleteManager athleteManager) {
        this.competitionDetails = new HashMap<>();
        this.athleteManager = athleteManager;
    }

    public void loadCompetitionData(String directory) throws IOException {
        try {
            Files.list(Paths.get(directory))
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(this::processCompetitionFile);
        } catch (IOException e) {
            System.err.println("Failed to read competition directory: " + directory);
        }
    }

    private void processCompetitionFile(Path filePath) {
        try {
            String json = new String(Files.readAllBytes(filePath));
            JSONObject competitionData = JSON.parseObject(json);
            String eventName = competitionData.getString("DisciplineName").toLowerCase();

            JSONArray heats = competitionData.getJSONArray("Heats");
            for (int i = 0; i < heats.size(); i++) {
                JSONObject heat = heats.getJSONObject(i);
                String level = heat.getString("PhaseName");

                JSONArray results = heat.getJSONArray("Results");
                for (int j = 0; j < results.size(); j++) {
                    JSONObject result = results.getJSONObject(j);
                    String personId = result.getString("PersonId");

                    Athlete athlete = athleteManager.findAthleteById(personId);
                    if (athlete != null) {
                        CompetitionDetail detail = new CompetitionDetail();
                        detail.setEventName(eventName);
                        detail.setLevel(level);
                        detail.setScore(Double.parseDouble(result.getString("TotalPoints")));
                        detail.setRank(result.getInteger("Rank"));

                        if (detail.isPreliminary()) {
                            detail.setPreliminaryDiveScores(parseDiveScores(result));
                        } else if (detail.isSemifinal()) {
                            detail.setSemifinalDiveScores(parseDiveScores(result));
                        } else if (detail.isFinal()) {
                            detail.setFinalDiveScores(parseDiveScores(result));
                        }

                        competitionDetails.computeIfAbsent(athlete.getId(), k -> new ArrayList<>())
                                .add(detail);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing competition file: " + filePath);
        }
    }


    private List<Double> parseDiveScores(JSONObject result) {
        List<Double> scores = new ArrayList<>();

        JSONArray divesArray = result.getJSONArray("Dives");
        if (divesArray != null) {
            for (int i = 0; i < divesArray.size(); i++) {
                try {
                    JSONObject dive = divesArray.getJSONObject(i);
                    if (dive.containsKey("DivePoints")) {
                        scores.add(dive.getDouble("DivePoints"));
                    }
                } catch (Exception e) {
                    // 忽略解析错误
                }
            }
        }

        return scores;
    }

    public Map<String, List<CompetitionDetail>> getCompetitionDetails() {
        return competitionDetails;
    }
}
