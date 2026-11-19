package com.dwasearch.model;

import java.util.List;

public class CompetitionData {
    private final List<Player> players;
    private final List<EventResult> events;

    public CompetitionData(List<Player> players, List<EventResult> events) {
        this.players = List.copyOf(players);
        this.events = List.copyOf(events);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<EventResult> getEvents() {
        return events;
    }
}