package com.dwasearch.model.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Heat {
    @JsonProperty("PhaseName")
    private String phaseName;
    @JsonProperty("Results")
    private List<Result> results;

    public String getPhaseName() {
        return phaseName;
    }
    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }
    public List<Result> getResults() {
        return results;
    }
    public void setResults(List<Result> results) {
        this.results = results;
    }
}
