package com.dwasearch.model;

import java.util.List;

public class EventResult {
    private final String eventName;
    private final List<FinalResult> results;

    public EventResult(String eventName, List<FinalResult> results) {
        this.eventName = eventName;
        this.results = List.copyOf(results);
    }

    public String getEventName() {
        return eventName;
    }

    public List<FinalResult> getResults() {
        return results;
    }
}