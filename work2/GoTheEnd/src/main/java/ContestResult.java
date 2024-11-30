public class ContestResult {
    private String name;
    private double totalPoints;
    private double[] divePoints = new double[6];
    private int rank;
    private String gander;

    public ContestResult(String name, double totalPoints, double[] divePoints, int rank, String gender) {
        this.name = name;
        this.totalPoints = totalPoints;
        this.divePoints = divePoints;
        this.rank = rank;
        this.gander = gender;
    }

    @Override
    public String toString() {
        if (gander.equals("Men")){
            return ("""
                Full Name:%s
                Rank:%d
                Score:%.2f + %.2f + %.2f + %.2f + %.2f + %.2f = %.2f
                -----""").formatted(name, rank, divePoints[0], divePoints[1], divePoints[2],
                    divePoints[3], divePoints[4], divePoints[5], totalPoints);
        }else {
            return ("""
                Full Name:%s
                Rank:%d
                Score:%.2f + %.2f + %.2f + %.2f + %.2f = %.2f
                -----""").formatted(name, rank, divePoints[0], divePoints[1], divePoints[2],
                    divePoints[3], divePoints[4], totalPoints);
        }
    }
}
