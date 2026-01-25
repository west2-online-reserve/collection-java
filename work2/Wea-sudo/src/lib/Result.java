package org.example.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @JsonProperty("TotalPoints")
    private double TotalPoints;

    @JsonProperty("Rank")
    private String rank;

    @JsonProperty("Dives")
    private ArrayList<Dive> dives;

    @JsonProperty("FullName")
    private String fullName;

    @JsonProperty("PersonId")
    private String personId;

    public double getTotalPoints() {
        return TotalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        TotalPoints = totalPoints;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public ArrayList<Dive> getDives() {
        return dives;
    }

    public void setDives(ArrayList<Dive> dives) {
        this.dives = dives;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
