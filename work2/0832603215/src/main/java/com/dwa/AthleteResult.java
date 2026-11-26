package com.dwa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AthleteResult {
    @JsonProperty("FullName")
    private String fullName;
    @JsonProperty("NAT")
    private String nat;
    @JsonProperty("Rank")
    private int rank;
    @JsonProperty("TotalPoints")
    private String totalPoints;
    @JsonProperty("Dives")
    private List<Dive> dives;

    public AthleteResult() {
    }

    public String getFullName(){
        return fullName;
    }
    public String getNat(){
        return nat;
    }
    public int getRank(){
        return rank;
    }
    public String getTotalPoints() {
        return totalPoints;
    }
    public List<Dive> getDives() {
        return dives;
    }
    public BigDecimal calculateTotalPoints() {

        BigDecimal total = BigDecimal.ZERO;

        for (Dive dive : dives) {
            total = total.add(dive.getDivePoints());
        }

        return total;
    }

}
