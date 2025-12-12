import java.util.List;

public class DetailedResult {
    private String fullName;
    private int preliminaryRank;
    private int semifinalRank;
    private int finalRank;
    private String preliminaryScores;
    private String semifinalScores;
    private String finalScores;

    public DetailedResult(String fullName, int preliminaryRank, int semifinalRank, int finalRank,
                          String preliminaryScores, String semifinalScores, String finalScores) {
        this.fullName = fullName;
        this.preliminaryRank = preliminaryRank;
        this.semifinalRank = semifinalRank;
        this.finalRank = finalRank;
        this.preliminaryScores = preliminaryScores;
        this.semifinalScores = semifinalScores;
        this.finalScores = finalScores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Full Name:").append(fullName).append("\n");

        sb.append("Rank:")
                .append((preliminaryRank == 0) ? "*" : preliminaryRank).append(" | ")
                .append((semifinalRank == 0) ? "*" : semifinalRank).append(" | ")
                .append((finalRank == 0) ? "*" : finalRank).append("\n");

        sb.append("Preliminary Score:").append(preliminaryScores).append("\n");
        sb.append("Semifinal Score:").append(semifinalScores).append("\n");
        sb.append("Final Score:").append(finalScores).append("\n");
        sb.append("-----\n");
        return sb.toString();
    }
}
