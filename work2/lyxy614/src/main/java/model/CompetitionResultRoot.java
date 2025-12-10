package main.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionResultRoot {
    @JsonProperty("Heats")
    private List<Heats> heats;

    public CompetitionResultRoot() {}
    public List<Heats> getHeats() {
        return heats;
    }
    public void setHeats(List<Heats> heats) {
        this.heats = heats;
    }
}
