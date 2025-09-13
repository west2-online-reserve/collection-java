import java.util.ArrayList;

public class ScheduleDetail {
    private double totalPoints;
    private ArrayList<Double> divePoints;
    private int rank;

    public ScheduleDetail(double totalPoints, ArrayList divePoints, int rank) {
        this.totalPoints = totalPoints;
        this.divePoints = divePoints;
        this.rank = rank;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public ArrayList<Double> getDivePoints() {
        return divePoints;
    }

    public int getRank() {
        return rank;
    }
}
