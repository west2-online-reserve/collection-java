package core.data;

import java.util.List;

public class Result {
    private String fullName;
    private int rank;
    private String countryCode;
    private List<Double> scores;
    private double totalPoints;

    public String getFullName() {
        return fullName;
    }

    public int getRank() {
        return rank;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public List<Double> getScores() {
        return scores;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void printAll() {
        System.out.println("Result{fullName=" + fullName
                + ", rank=" + rank
                + ", countryCode=" + countryCode
                + ", scores=" + scores
                + ", totalPoints=" + totalPoints + "}");
    }
}
