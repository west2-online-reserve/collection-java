package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DetailedResultManager {
    private AthleteManager athleteManager;
    private CompetitionManager competitionManager;

    public DetailedResultManager(AthleteManager athleteManager, CompetitionManager competitionManager) {
        this.athleteManager = athleteManager;
        this.competitionManager = competitionManager;
    }

    // 详细结果条目类
    public static class DetailedResultEntry {
        private String fullName;
        private Integer preliminaryRank;
        private Integer semifinalRank;
        private Integer finalRank;
        private Double preliminaryScore;
        private Double semifinalScore;
        private Double finalScore;
        private List<Double> preliminaryDiveScores;
        private List<Double> semifinalDiveScores;
        private List<Double> finalDiveScores;

        public DetailedResultEntry(String fullName,
                                   Integer preliminaryRank, Integer semifinalRank, Integer finalRank,
                                   Double preliminaryScore, Double semifinalScore, Double finalScore,
                                   List<Double> preliminaryDiveScores, List<Double> semifinalDiveScores, List<Double> finalDiveScores) {
            this.fullName = fullName;
            this.preliminaryRank = preliminaryRank;
            this.semifinalRank = semifinalRank;
            this.finalRank = finalRank;
            this.preliminaryScore = preliminaryScore;
            this.semifinalScore = semifinalScore;
            this.finalScore = finalScore;
            this.preliminaryDiveScores = preliminaryDiveScores;
            this.semifinalDiveScores = semifinalDiveScores;
            this.finalDiveScores = finalDiveScores;
        }

        // Getters
        public String getFullName() { return fullName; }
        public Integer getPreliminaryRank() { return preliminaryRank; }
        public Integer getSemifinalRank() { return semifinalRank; }
        public Integer getFinalRank() { return finalRank; }
        public List<Double> getPreliminaryDiveScores() { return preliminaryDiveScores; }
        public List<Double> getSemifinalDiveScores() { return semifinalDiveScores; }
        public List<Double> getFinalDiveScores() { return finalDiveScores; }

        // 格式化方法
        public String getFormattedPreliminaryRank() {
            return preliminaryRank != null ? preliminaryRank.toString() : "*";
        }

        public String getFormattedSemifinalRank() {
            return semifinalRank != null ? semifinalRank.toString() : "*";
        }

        public String getFormattedFinalRank() {
            return finalRank != null ? finalRank.toString() : "*";
        }

        public String getFormattedPreliminaryDiveScores() {
            return formatDiveScores(preliminaryDiveScores);
        }

        public String getFormattedSemifinalDiveScores() {
            return formatDiveScores(semifinalDiveScores);
        }

        public String getFormattedFinalDiveScores() {
            return formatDiveScores(finalDiveScores);
        }

        private String formatDiveScores(List<Double> scores) {
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

    public void displayDetailedResults(BufferedWriter writer, String eventName) throws IOException {
        List<DetailedResultEntry> detailedResults = getDetailedResults(eventName);

        for (DetailedResultEntry entry : detailedResults) {
            writer.write("Full Name:" + entry.getFullName() + "\n");
            writer.write("Rank:" + entry.getFormattedPreliminaryRank() + "|" +
                    entry.getFormattedSemifinalRank() + "|" +
                    entry.getFormattedFinalRank() + "\n");
            writer.write("Preliminary Score:" + entry.getFormattedPreliminaryDiveScores() + "\n");
            writer.write("Semifinal Score:" + entry.getFormattedSemifinalDiveScores() + "\n");
            writer.write("Final Score:" + entry.getFormattedFinalDiveScores() + "\n");
            writer.write("-----\n");
        }
    }

    private List<DetailedResultEntry> getDetailedResults(String eventName) {
        List<DetailedResultEntry> results = new ArrayList<>();
        Map<String, List<CompetitionDetail>> competitionDetails = competitionManager.getCompetitionDetails();

        for (Map.Entry<String, List<CompetitionDetail>> entry : competitionDetails.entrySet()) {
            String athleteId = entry.getKey();
            List<CompetitionDetail> details = entry.getValue();

            Athlete athlete = athleteManager.findAthleteById(athleteId);
            if (athlete == null) continue;

            Double[] scores = new Double[3]; // preliminaries, semifinals, finals
            Integer[] ranks = new Integer[3]; // preliminaries, semifinals, finals
            List<Double>[] diveScoresList = new List[3]; // preliminaries, semifinals, finals

            for (CompetitionDetail detail : details) {
                if (!eventName.equalsIgnoreCase(detail.getEventName())) continue;

                if (detail.isPreliminary()) {
                    scores[0] = detail.getScore();
                    ranks[0] = detail.getRank();
                    diveScoresList[0] = detail.getPreliminaryDiveScores();
                } else if (detail.isSemifinal()) {
                    scores[1] = detail.getScore();
                    ranks[1] = detail.getRank();
                    diveScoresList[1] = detail.getSemifinalDiveScores();
                } else if (detail.isFinal()) {
                    scores[2] = detail.getScore();
                    ranks[2] = detail.getRank();
                    diveScoresList[2] = detail.getFinalDiveScores();
                }
            }

            if (Arrays.stream(scores).anyMatch(Objects::nonNull)) {
                results.add(new DetailedResultEntry(
                        athlete.getFullName(),
                        ranks[0], ranks[1], ranks[2],
                        scores[0], scores[1], scores[2],
                        diveScoresList[0], diveScoresList[1], diveScoresList[2]
                ));
            }
        }

        return results.stream()
                .sorted(Comparator.comparing(DetailedResultEntry::getFinalRank,
                        Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());
    }
}
