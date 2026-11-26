package com.dwa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DivingEvent {
    @JsonProperty("EventName")
    private String eventName;

    @JsonProperty("DisciplineName")
    private String disciplineName;

    @JsonProperty("Heats")
    private List<Heat> heats;

    public String getEventName() {
        return eventName;
    }
    public String getDisciplineName() {
        return disciplineName;
    }
    public List<Heat> getHeats() {
        return heats;
    }
}
