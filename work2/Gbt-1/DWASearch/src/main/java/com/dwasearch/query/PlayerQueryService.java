package com.dwasearch.query;

import com.dwasearch.model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerQueryService {

    public List<Player> sortedPlayers(List<Player> players) {
        List<Player> copy = new ArrayList<>(players);

        copy.sort(
                Comparator.comparing(Player::getCountry)
                        .thenComparing(Player::getLastName)
                        .thenComparing(Player::getFirstName)
        );

        return copy;
    }

    public String formatPlayers(List<Player> players) {
        StringBuilder text = new StringBuilder();

        for (Player player : players) {
            text.append("Full Name:").append(player.getFullName()).append('\n');
            text.append("Gender:").append(player.getGender()).append('\n');
            text.append("Country:").append(player.getCountry()).append('\n');
            text.append("-----\n");
        }

        return text.toString();
    }
}