package org.example.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dive {

    @JsonProperty("DivePoints")
    private double divePoints;

    public double getDivePoints() {
        return divePoints;
    }

    public void setDivePoints(double divePoints) {
        this.divePoints = divePoints;
    }
}
