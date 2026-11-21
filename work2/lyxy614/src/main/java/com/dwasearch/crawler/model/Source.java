package com.dwasearch.crawler.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Source {
    @JsonProperty("Event")
    private String event;
    @JsonProperty("EventsURL")
    private String eventsURL;
    @JsonProperty("PlayersURL")
    private String playersURL;
    @JsonProperty("PlayersFetchedAt")
    private Instant playersFetchedAt;
    @JsonProperty("ResultsFetchedAt")
    private Instant resultsFetchedAt;

    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public String getEventsURL() {
        return eventsURL;
    }
    public void setEventsURL(String eventsURL) {
        this.eventsURL = eventsURL;
    }
    public String getPlayersURL() {
        return playersURL;
    }
    public void setPlayersURL(String playersURL) {
        this.playersURL = playersURL;
    }
    public Instant getPlayersFetchedAt() {
        return playersFetchedAt;
    }
    public void setPlayersFetchedAt(Instant fetchedAt) {
        this.playersFetchedAt = fetchedAt;
    }
    public Instant getResultsFetchedAt() {
        return resultsFetchedAt;
    }
    public void setResultsFetchedAt(Instant resultsFetchedAt) {
        this.resultsFetchedAt = resultsFetchedAt;
    }
}
