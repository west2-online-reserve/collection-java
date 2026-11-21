package com.dwasearch.model.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dive {
    @JsonProperty("DivePoints")
    private String divePoints;

    public String getDivePoints() {
        return divePoints;
    }
    public void setDivePoints(String divePoints) {
        this.divePoints = divePoints;
    }
}
