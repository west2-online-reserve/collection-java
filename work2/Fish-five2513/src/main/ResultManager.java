package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ResultManager {
    private AthleteManager athleteManager;
    private CompetitionManager competitionManager;

    public ResultManager(AthleteManager athleteManager, CompetitionManager competitionManager) {
        this.athleteManager = athleteManager;
        this.competitionManager = competitionManager;
    }

    // 结果条目类
    // 结果条目类
    public static class ResultEntry {
        private String fullName;
        private Integer rank;
        private Double score;
        private List<Double> diveScores;

        public ResultEntry(String fullName, Integer rank, Double score, List<Double> diveScores) {
            this.fullName = fullName;
            this.rank = rank;
            this.score = score;
            this.diveScores = diveScores;
        }

        public String getFullName() { return fullName; }
        public Integer getRank() { return rank; }
        public List<Double> getFinalDiveScores() { return diveScores; }
    }

    public void displayFinalResults(BufferedWriter writer, String eventName) throws IOException {
        List<ResultEntry> finalResults = getFinalResults(eventName);

        for (ResultEntry entry : finalResults) {
            writer.write("Full Name:" + entry.getFullName() + "\n");
            writer.write("Rank:" + entry.getRank() + "\n");
            writer.write("Score:" + formatScore(entry.getFinalDiveScores()) + "\n");
            writer.write("-----\n");
        }
    }


    private List<ResultEntry> getFinalResults(String eventName) {
        List<ResultEntry> results = new ArrayList<>();
        Map<String, List<CompetitionDetail>> competitionDetails = competitionManager.getCompetitionDetails();

        for (Map.Entry<String, List<CompetitionDetail>> entry : competitionDetails.entrySet()) {
            String athleteId = entry.getKey();
            List<CompetitionDetail> details = entry.getValue();

            Optional<CompetitionDetail> finalDetailOpt = details.stream()
                    .filter(d -> eventName.equalsIgnoreCase(d.getEventName()) &&
                            (d.getLevel().equalsIgnoreCase("Finals")))
                    .findFirst();

            if (finalDetailOpt.isPresent()) {
                CompetitionDetail detail = finalDetailOpt.get();
                Athlete athlete = athleteManager.findAthleteById(athleteId);
                if (athlete != null) {
                    results.add(new ResultEntry(
                            athlete.getFullName(),
                            detail.getRank(),
                            detail.getScore(),
                            detail.getFinalDiveScores()
                    ));
                }
            }
        }

        return results.stream()
                .sorted(Comparator.comparing(ResultEntry::getRank, Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());
    }
    public void displayAllPlayers(BufferedWriter writer) throws IOException {
        List<Athlete> sortedAthletes = athleteManager.getAthletes().stream()
                .sorted((a1, a2) -> {
                    int countryCompare = a1.getCountry().compareTo(a2.getCountry());
                    if (countryCompare != 0) return countryCompare;
                    return a1.getFullName().compareTo(a2.getFullName());
                })
                .collect(Collectors.toList());

        for (Athlete athlete : sortedAthletes) {
            writer.write("Full Name:" + athlete.getFullName() + "\n");
            writer.write("Gender:" + athlete.getGender() + "\n");
            writer.write("Country:" + athlete.getCountry() + "\n");
            writer.write("-----\n");
        }
    }
    private String formatScore(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return "*";
        }

        StringBuilder sb = new StringBuilder();
        double sum = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (i > 0) sb.append(" + ");
            sb.append(String.format("%.2f", scores.get(i)));
            sum += scores.get(i);
        }
        sb.append(" = ").append(String.format("%.2f", sum));
        return sb.toString();
    }



}
