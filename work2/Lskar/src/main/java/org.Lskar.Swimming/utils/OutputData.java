package org.Lskar.Swimming.utils;

import org.Lskar.Swimming.lib.Player;

import java.util.List;

public class OutputData {

    public String outputPlayersData(List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Player player : players) {
            stringBuilder.append(player.toString());
            stringBuilder.append("\n");
            stringBuilder.append("-----");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


}
