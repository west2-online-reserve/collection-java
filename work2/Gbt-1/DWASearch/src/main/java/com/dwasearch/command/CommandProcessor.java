package com.dwasearch.command;

import com.dwasearch.model.CompetitionData;
import com.dwasearch.model.EventResult;
import com.dwasearch.query.PlayerQueryService;
import com.dwasearch.query.ResultQueryService;

public class CommandProcessor {
    private final PlayerQueryService playerQueryService = new PlayerQueryService();
    private final ResultQueryService resultQueryService = new ResultQueryService();

    public String process(String line, CompetitionData data) {
        if (line.isBlank()) {
            return "";
        }

        if (line.equals("players")) {
            return playerQueryService.formatPlayers(
                    playerQueryService.sortedPlayers(data.getPlayers())
            );
        }

        if (line.equals("result") || line.startsWith("result ")) {
            String eventName = line.length() > 7 ? line.substring(7) : "";
            EventResult event = resultQueryService.findEvent(data, eventName);

            if (event == null) {
                return "N/A\n-----\n";
            }

            return resultQueryService.formatResults(
                    resultQueryService.sortedResults(event.getResults())
            );
        }

        return "Error\n-----\n";
    }
}