public class CompetitionDetail extends Competition {
    private String preliminaryScore;
    private String semifinalScore;
    private String finalScore;
    private String preliminaryRank;
    private String semifinalRank;
    private String finalsRank;

    public CompetitionDetail() {
    }

    public CompetitionDetail(String fullName ,String preliminaryScore, String semifinalScore, String finalScore, String preliminaryRank, String semifinalRank, String finalsRank) {
        super(fullName);
        this.preliminaryScore = preliminaryScore;
        this.semifinalScore = semifinalScore;
        this.finalScore = finalScore;
        this.preliminaryRank = preliminaryRank;
        this.semifinalRank = semifinalRank;
        this.finalsRank = finalsRank;
    }

    public String getPreliminaryScore() {
        return preliminaryScore;
    }

    public void setPreliminaryScore(String preliminaryScore) {
        this.preliminaryScore = preliminaryScore;
    }

    public String getSemifinalScore() {
        return semifinalScore;
    }

    public void setSemifinalScore(String semifinalScore) {
        this.semifinalScore = semifinalScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getPreliminaryRank() {
        return preliminaryRank;
    }

    public void setPreliminaryRank(String preliminaryRank) {
        this.preliminaryRank = preliminaryRank;
    }

    public String getSemifinalRank() {
        return semifinalRank;
    }

    public void setSemifinalRank(String semifinalRank) {
        this.semifinalRank = semifinalRank;
    }

    public String getFinalsRank() {
        return finalsRank;
    }

    public void setFinalsRank(String finalsRank) {
        this.finalsRank = finalsRank;
    }

    public String toString() {
        return "{"+preliminaryScore.charAt(0) + semifinalScore.charAt(0) +finalScore.charAt(0) + preliminaryRank.charAt(0) + semifinalRank.charAt(0) + finalsRank.charAt(0)+"}" ;
    }
}


