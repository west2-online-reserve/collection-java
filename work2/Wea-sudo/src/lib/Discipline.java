package org.example.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Discipline {
    @JsonProperty("DisciplineName")
    private String disciplineName;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Heats")
    private ArrayList<Heat> heats;

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Heat> getHeats() {
        return heats;
    }

    public void setHeats(ArrayList<Heat> heats) {
        this.heats = heats;
    }
}
