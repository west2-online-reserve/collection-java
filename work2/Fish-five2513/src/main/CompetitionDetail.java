package main;
import java.util.List;

public class CompetitionDetail {
    private String eventName;
    private String level;
    private double score;
    private int rank;
    private List<Double> DiveScores;

    // Getters and Setters
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public void setDiveScores(List<Double> DiveScores) { this.DiveScores = DiveScores; }

    // 添加级别判断方法
    public boolean isPreliminary() {
        return level != null && (level.toLowerCase().contains("preliminaries") ||
                level.toLowerCase().contains("prelim"));
    }

    public boolean isSemifinal() {
        return level != null && (level.toLowerCase().contains("semifinal") ||
                level.toLowerCase().contains("semi-final"));
    }

    public boolean isFinal() {
        return level != null && level.toLowerCase().contains("finals") &&
                !level.toLowerCase().contains("semifinals");
    }
    // 在 CompetitionDetail.java 中添加以下字段和方法
    private List<Double> preliminaryDiveScores;
    private List<Double> semifinalDiveScores;
    private List<Double> finalDiveScores;

    // 添加相应的 getter 和 setter 方法
    public List<Double> getPreliminaryDiveScores() { return preliminaryDiveScores; } // Changed from preliminaryJudgeScores
    public void setPreliminaryDiveScores(List<Double> preliminaryDiveScores) { this.preliminaryDiveScores = preliminaryDiveScores; }

    public List<Double> getSemifinalDiveScores() { return semifinalDiveScores; }
    public void setSemifinalDiveScores(List<Double> semifinalDiveScores) { this.semifinalDiveScores = semifinalDiveScores; }

    public List<Double> getFinalDiveScores() { return finalDiveScores; }
    public void setFinalDiveScores(List<Double> finalDiveScores) { this.finalDiveScores = finalDiveScores; }

}
