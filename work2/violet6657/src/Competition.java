public class Competition {
    private String fullName;
    private String rank;
    private String score;

    public Competition() {
    }
    public Competition(String fullName) {
        this.fullName = fullName;
    }

    public Competition(String fullName, String rank, String score) {
        this.fullName = fullName;
        this.rank = rank;
        this.score = score;
    }

    /**
     * 获取
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取
     * @return rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * 设置
     * @param rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 获取
     * @return score
     */
    public String getScore() {
        return score;
    }

    /**
     * 设置
     * @param score
     */
    public void setScore(String score) {
        this.score = score;
    }

    public String toString() {
        return "{"+fullName.charAt(0) + rank.charAt(0) + score.charAt(0) + "}";
    }
}

