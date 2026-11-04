package com.dwasearch.model.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Competition {
    @JsonProperty("DisciplineName")
    private String disciplineName;
    @JsonProperty("Heats")
    private List<Heat> heats;

    public String getDisciplineName() {
        return disciplineName;
    }
    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
    public List<Heat> getHeats() {
        return heats;
    }
    public void setHeats(List<Heat> heats) {
        this.heats = heats;
    }
}
