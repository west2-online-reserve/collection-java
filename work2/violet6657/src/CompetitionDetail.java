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

    /**
     * 获取
     * @return preliminaryScore
     */
    public String getPreliminaryScore() {
        return preliminaryScore;
    }

    /**
     * 设置
     * @param preliminaryScore
     */
    public void setPreliminaryScore(String preliminaryScore) {
        this.preliminaryScore = preliminaryScore;
    }

    /**
     * 获取
     * @return semifinalScore
     */
    public String getSemifinalScore() {
        return semifinalScore;
    }

    /**
     * 设置
     * @param semifinalScore
     */
    public void setSemifinalScore(String semifinalScore) {
        this.semifinalScore = semifinalScore;
    }

    /**
     * 获取
     * @return finalScore
     */
    public String getFinalScore() {
        return finalScore;
    }

    /**
     * 设置
     * @param finalScore
     */
    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    /**
     * 获取
     * @return preliminaryRank
     */
    public String getPreliminaryRank() {
        return preliminaryRank;
    }

    /**
     * 设置
     * @param preliminaryRank
     */
    public void setPreliminaryRank(String preliminaryRank) {
        this.preliminaryRank = preliminaryRank;
    }

    /**
     * 获取
     * @return semifinalRank
     */
    public String getSemifinalRank() {
        return semifinalRank;
    }

    /**
     * 设置
     * @param semifinalRank
     */
    public void setSemifinalRank(String semifinalRank) {
        this.semifinalRank = semifinalRank;
    }

    /**
     * 获取
     * @return finalsRank
     */
    public String getFinalsRank() {
        return finalsRank;
    }

    /**
     * 设置
     * @param finalsRank
     */
    public void setFinalsRank(String finalsRank) {
        this.finalsRank = finalsRank;
    }

    public String toString() {
        return "{"+preliminaryScore.charAt(0) + semifinalScore.charAt(0) +finalScore.charAt(0) + preliminaryRank.charAt(0) + semifinalRank.charAt(0) + finalsRank.charAt(0)+"}" ;
    }
}


