package com.dwa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Heat {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Results")
    private List<AthleteResult>results;

    public String getName() {
        return name;
    }
    public List<AthleteResult> getResults() {
        return results;
    }
}
