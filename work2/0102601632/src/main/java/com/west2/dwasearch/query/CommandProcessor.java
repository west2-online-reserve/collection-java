package com.west2.dwasearch.query;

import com.west2.dwasearch.data.DataStore;
import com.west2.dwasearch.data.DiveScore;
import com.west2.dwasearch.data.EventCatalog;
import com.west2.dwasearch.data.EventResult;
import com.west2.dwasearch.data.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommandProcessor {
    public static final String SEPARATOR = "-----";
    public static final String ERROR = "Error";
    public static final String NOT_AVAILABLE = "N/A";

    private static final String PLAYERS_COMMAND = "players";
    private static final String RESULT_COMMAND = "result";

    private final DataStore dataStore;

    public CommandProcessor(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<String> process(List<String> commands) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < commands.size(); i++) {
            output.addAll(processLine(commands.get(i)));
        }
        return output;
    }

    public List<String> processLine(String command) {
        List<String> output = new ArrayList<>();

        if (command.isEmpty()) {
            return output;
        }

        if (command.equals(PLAYERS_COMMAND)) {
            output.addAll(formatPlayers());
        } else if (isResultCommand(command)) {
            String keyword;
            if (command.equals(RESULT_COMMAND)) {
                keyword = "";
            } else {
                keyword = command.substring(RESULT_COMMAND.length() + 1);
            }
            if (EventCatalog.contains(keyword)) {
                output.addAll(formatResult(keyword));
            } else {
                output.add(NOT_AVAILABLE);
                output.add(SEPARATOR);
            }
        } else {
            output.add(ERROR);
            output.add(SEPARATOR);
        }
        return output;
    }

    private boolean isResultCommand(String command) {
        return command.equals(RESULT_COMMAND) || command.startsWith(RESULT_COMMAND + " ");
    }

    public List<String> formatPlayers() {
        List<Player> players = new ArrayList<>(dataStore.getPlayers());
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player a, Player b) {
                int result = a.getCountry().compareTo(b.getCountry());
                if (result != 0) {
                    return result;
                }
                result = a.getLastName().compareTo(b.getLastName());
                if (result != 0) {
                    return result;
                }
                return a.getFirstName().compareTo(b.getFirstName());
            }
        });

        List<String> output = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            output.add("Full Name:" + player.getFullName()
                    + " / Gender:" + player.getGender()
                    + " / Country:" + player.getCountry());
            output.add(SEPARATOR);
        }
        return output;
    }

    public List<String> formatResult(String keyword) {
        List<EventResult> results = new ArrayList<>(dataStore.getResults(keyword));
        results.sort(new Comparator<EventResult>() {
            @Override
            public int compare(EventResult a, EventResult b) {
                return Integer.compare(a.getRank(), b.getRank());
            }
        });

        List<String> output = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            EventResult result = results.get(i);
            StringBuilder line = new StringBuilder();
            line.append("Full Name:").append(result.getFullName());
            line.append(" / Rank:").append(result.getRank());
            line.append(" / Score:");

            List<DiveScore> dives = result.getDives();
            for (int j = 0; j < dives.size(); j++) {
                if (j > 0) {
                    line.append(" + ");
                }
                line.append(ScoreFormatter.format(dives.get(j).getPoints()));
            }
            line.append(" = ").append(ScoreFormatter.format(result.getTotalPoints()));

            output.add(line.toString());
            output.add(SEPARATOR);
        }
        return output;
    }
}
