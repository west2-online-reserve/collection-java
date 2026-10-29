package com.example.taskdive;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RankPoint {
    @JsonProperty("TotalPoints")
    private String TotalPoints;

    @JsonProperty("CarryOverPoints")
    private String CarryOverPoints;

    @JsonProperty("Rank")
    @JsonAlias("DiveRank")
    private String DiveRank;

    private List<String>DivePoints;

    private List<Athlete> Athletes;

    public RankPoint(){}
    public RankPoint(String DiveRank, String TotalPoints, List<String> DivePoints, List<Athlete> Athletes){
        this.DiveRank = DiveRank;
        this.TotalPoints = TotalPoints;
        this.DivePoints = DivePoints;
        this.Athletes = Athletes;
    }

    public String getTotalPoints() {
        return TotalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        TotalPoints = totalPoints;
    }

    public String getDiveRank() {
        return DiveRank;
    }
    public void setDiveRank(String diveRank) {
        DiveRank = diveRank;
    }

    public List<String> getDivePoints() {
        return DivePoints;
    }

    public void setDivePoints(List<String> divePoints) {
        DivePoints = divePoints;
    }

    public List<Athlete> getAthletes() {
        return Athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        Athletes = athletes;
    }

    public String getCarryOverPoints() {
        return CarryOverPoints;
    }

    public void setCarryOverPoints(String carryOverPoints) {
        CarryOverPoints = carryOverPoints;
    }

    public void addDivePoint(String divepoint)
    {
        DivePoints.add(divepoint);
    }

    public void addAthlete(Athlete athlete)
    {
        Athletes.add(athlete);
    }

    @Override
    public String toString() {
        return "Points{" +
                "TotalPoints='" + TotalPoints + '\'' +
                ", DivePoints=" + DivePoints +
                ", Athletes=" + Athletes +
                '}';
    }
}
