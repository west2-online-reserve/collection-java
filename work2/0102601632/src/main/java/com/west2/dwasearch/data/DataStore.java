package com.west2.dwasearch.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
    private final List<Player> players;
    private final Map<String, List<EventResult>> results;

    public DataStore(List<Player> players, Map<String, List<EventResult>> results) {
        this.players = players;
        this.results = new HashMap<>(results);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<EventResult> getResults(String keyword) {
        return results.get(keyword);
    }
}
