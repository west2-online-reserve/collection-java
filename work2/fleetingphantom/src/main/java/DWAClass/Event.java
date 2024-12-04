package DWAClass;

import java.util.List;

public class Event {
    private String fullName;
    private int rank;
    private List<String> dives;
    private double totalScore;

    public Event() {
    }

    public Event(String fullName, int rank, List<String> dives, double totalScore) {
        this.fullName = fullName;
        this.rank = rank;
        this.dives = dives;
        this.totalScore = totalScore;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<String> getDives() {
        return dives;
    }

    public void setDives(List<String> dives) {
        this.dives = dives;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public String ScoreString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dives.size(); i++) {
            if (i == dives.size() - 1) {
                stringBuilder.append(dives.get(i)).append(" = ");
                break;
            }
            stringBuilder.append(dives.get(i)).append(" + ");
        }
        stringBuilder.append(totalScore);

        return stringBuilder.toString();
    }

    @Override

    public String toString() {
        return "Full Name:" + fullName + "\n" +
                "Rank:" + rank + "\n" +
                "Score:" + ScoreString()+"\n"+
                "-----";
    }
}
