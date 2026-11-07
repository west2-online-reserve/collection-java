package core.data;/*
    用于写入json数据的数据结构
    根据数据包结构构建
 */

import java.util.List;

public class JsonData {
    private int schemaVersion;
    private Competition competition;
    private List<Player> players;
    private List<Event> events;

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public Competition getCompetition() {
        return competition;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Event> getEvents() {
        return events;
    }

    // 用于验证数据是否读取正确 (其子项的printAll均ai生成)
    public void printAll() {
        System.out.println("schemaVersion=" + schemaVersion);

        System.out.println("competition:");
        if (competition != null) {
            competition.printAll();
        }

        System.out.println("players: " + (players == null ? 0 : players.size()));
        if (players != null) {
            for (Player player : players) {
                player.printAll();
            }
        }

        System.out.println("events: " + (events == null ? 0 : events.size()));
        if (events != null) {
            for (Event event : events) {
                event.printAll();
            }
        }
    }
}

