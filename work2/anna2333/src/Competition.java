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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String toString() {
        return "{"+fullName.charAt(0) + rank.charAt(0) + score.charAt(0) + "}";
    }
}

