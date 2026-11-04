package com.dwasearch.model.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @JsonProperty("TotalPoints")
    private String totalPoints;
    @JsonProperty("Dives")
    private List<Dive> dives;
    @JsonProperty("Rank")
    private Integer rank;
    @JsonProperty("NAT")
    private String country;
    @JsonProperty("FullName")
    private String fullName;

    public String getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public List<Dive> getDives() {
        return dives;
    }
    public void setDives(List<Dive> dives) {
        this.dives = dives;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
