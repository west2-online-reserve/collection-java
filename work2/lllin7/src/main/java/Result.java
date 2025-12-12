import java.util.List;

public class Result {
    private String fullName;
    private int rank;
    private String Scores;

    public Result(String fullName, int rank, String Scores) {
        this.fullName = fullName;
        this.rank = rank;
        this.Scores = Scores;
    }
    public String getFullName() {
        return fullName;
    }
    public int getRank() {
        return rank;
    }
    public String getScores() {
        return Scores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Full Name:").append(fullName).append("\n");
        sb.append("Rank:").append(rank).append("\n");
        sb.append("Score:").append(Scores).append("\n");
        sb.append("-----\n");

        return sb.toString();
    }
}
