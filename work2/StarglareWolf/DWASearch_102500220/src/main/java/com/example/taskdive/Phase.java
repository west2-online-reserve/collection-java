package com.example.taskdive;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Phase {

    @JsonProperty("PhaseName")
    private String PhaseName;

    @JsonProperty("HeatName")
    @JsonAlias("Name")
    private String HeatName;

    private List<RankPoint> RankPoint;

    public Phase(){}
    public Phase(String PhaseName, String HeatName){
        this.PhaseName = PhaseName;
        this.HeatName = HeatName;
    }

    public String getPhaseName() {
        return PhaseName;
    }

    public void setPhaseName(String phaseName) {
        PhaseName = phaseName;
    }

    public String getHeatName() {
        return HeatName;
    }

    public void setHeatName(String heatName) {
        HeatName = heatName;
    }

    public List<RankPoint> getRankPoint() {
        return RankPoint;
    }

    public void setRankPoint(List<RankPoint> rankPoint) {
        RankPoint = rankPoint;
    }

    public void addRankPoint(RankPoint rankPoint){
        RankPoint.add(rankPoint);
    }

    @Override
    public String toString() {
        return "PhaseName:'" + PhaseName + '\'' +
                ", HeatName:'" + HeatName + '\'' +
                ", RankPoint:\n" + RankPoint + "\n";
    }
}
