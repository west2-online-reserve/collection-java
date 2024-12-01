package lib;

public class EventResult {
    private String eventName;
    private String athleteName;
    private int rank;
    private String scoreString;
    private String rankString;
    private String preliminaryScore;
    private String semifinalScore;
    private String finalScore;
    private String preliminaryRank;
    private String semifinalRank;
    private String finalRank;

    // 全参数构造函数
    public EventResult(String eventName, String athleteName, int rank, String scoreString, String rankString, String preliminaryScore, String semifinalScore, String finalScore) {
        this.eventName = eventName;
        this.athleteName = athleteName;
        this.rank = rank;
        this.scoreString = scoreString;
        this.rankString = rankString;
        this.preliminaryScore = preliminaryScore;
        this.semifinalScore = semifinalScore;
        this.finalScore = finalScore;
    }

    // 新增的构造函数，仅包含事件名称和选手姓名
    public EventResult(String eventName, String athleteName) {
        this.eventName = eventName;
        this.athleteName = athleteName;
        this.rank = -1;
        this.scoreString = "*";
        this.rankString = "* | * | *";
        this.preliminaryScore = "*";
        this.semifinalScore = "*";
        this.finalScore = "*";
        this.preliminaryRank = "*";
        this.semifinalRank = "*";
        this.finalRank = "*";
    }

    // Getter 方法
    public String getEventName() {
        return eventName;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public int getRank() {
        return rank;
    }

    public String getScoreString() {
        return scoreString;
    }

    public String getRankString() {
        return rankString;
    }

    public String getPreliminaryScore() {
        return preliminaryScore;
    }

    public String getSemifinalScore() {
        return semifinalScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public String getPreliminaryRank() {
        return preliminaryRank;
    }

    public String getSemifinalRank() {
        return semifinalRank;
    }

    public String getFinalRank() {
        return finalRank;
    }

    // Setter 方法
    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setScoreString(String scoreString) {
        this.scoreString = scoreString;
    }

    public void setRankString(String rankString) {
        this.rankString = rankString;
    }

    public void setPreliminaryScore(String preliminaryScore) {
        this.preliminaryScore = preliminaryScore;
    }

    public void setSemifinalScore(String semifinalScore) {
        this.semifinalScore = semifinalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public void setPreliminaryRank(String preliminaryRank) {
        this.preliminaryRank = preliminaryRank;
    }

    public void setSemifinalRank(String semifinalRank) {
        this.semifinalRank = semifinalRank;
    }

    public void setFinalRank(String finalRank) {
        this.finalRank = finalRank;
    }
}
