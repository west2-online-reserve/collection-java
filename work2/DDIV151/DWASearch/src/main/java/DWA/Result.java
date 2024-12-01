package DWA;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

import static DWA.Athlete.turnGenderString;


public class Result {
    @JSONField(name = "Dives", serialize = false)
    private String divesJson;
    @JSONField(name = "ArrayDives")
    private ArrayList<Double> dives = new ArrayList<>();
    @JSONField(name = "FullName")
    private String athleteFullName;
    @JSONField(name = "Gender")
    private String gender;
    @JSONField(name = "TotalPoints")
    private double totalPoints;
    @JSONField(name = "Name")
    private String name;


    public ArrayList<Double> getDives() {
        return dives;
    }

    public void setDives(ArrayList<Double> dives) {
        this.dives = dives;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int rank;

    public String getDivesJson() {
        return divesJson;
    }

    public void setDivesJson(String divesJson) {
        this.divesJson = divesJson;
    }

    public String getAthleteFullName() {
        return athleteFullName;
    }

    public void setAthleteFullName(String athleteFullName) {
        this.athleteFullName = athleteFullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(int Gender) {
        this.gender = turnGenderString(Gender);
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Result() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Result( String athleteFullName, String name, String gender,ArrayList<Double> dives, double totalPoints, int rank) {
        this.dives = dives;
        this.athleteFullName = athleteFullName;
        this.gender = gender;
        this.totalPoints = totalPoints;
        this.name = name;
        this.rank = rank;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Full Name:").append(athleteFullName).append("\nRank:").append(rank).append("\nScore:");
        sb.append(parsePointsString(dives));
        return sb.toString();
    }

    public static String parsePointsString(ArrayList<Double> points) {
        StringBuilder sb = new StringBuilder();
        double sum = 0;
        for (double d : points) {
            sum += d;
            int len = points.size();
            if (d == points.get(len - 1)) {
                String sumStr = String.format("%.2f", sum);
                sb.append(d).append(" = ").append(sumStr);
                break;
            }
            sb.append(d).append(" + ");
        }
        return sb.toString();
    }

}
