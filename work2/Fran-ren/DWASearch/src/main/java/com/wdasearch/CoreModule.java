package com.wdasearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wdasearch.domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CoreModule {
    private static final Map<String, Player> playersMap = new LinkedHashMap<>();
    private static final Map<String, ArrayList<Player>> resultsMap = new HashMap<>();
    private static final Map<String, ArrayList<Player>> resultsWithDetailMap = new HashMap<>();
    private static final Map<String, String> all = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    private static final String[] gameTypes = {"men 1m springboard","men 3m springboard",
            "men 3m synchronised","men 10m platform",
            "men 10m synchronised", "women 1m springboard",
            "women 3m springboard","women 3m synchronised",
            "women 10m platform","women 10m synchronised"};

    // 输出所有选手信息
    public static void displayAllPlayersInfo(BufferedWriter writer) {
        if (all.containsKey("players")) {
            try {
                writer.write(all.get("players"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (playersMap.isEmpty()) {
            initPlayers();
        }
        for (Map.Entry<String, Player> entry :  playersMap.entrySet()) {
            try {
                writer.write(entry.getValue().toString() + "\n-----\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void displayResults(BufferedWriter writer, String gameType) {
        if (all.containsKey(gameType)) {
            try {
                writer.write(all.get(gameType));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        boolean isDetail;
        if (gameType.matches(".* detail")) {
            isDetail = true;
            gameType = gameType.substring(0, gameType.length() - 7);
        } else {
            isDetail = false;
        }
        if (!CoreModule.contains(gameType)) {
            try {
                writer.write("N/A\n-----\n");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ArrayList<Player> list;
        WriteFullName writeFullName;
        WriteRankAndScore writeRankAndScore;
        if (!resultsMap.containsKey(gameType)){
            try {
                initResult(gameType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (isDual(gameType)) {
            writeFullName = (Player player, Game game) -> {
                return player.getFullName() + " & " + game.getCompetitor() + "\n";
            };
        } else {
            writeFullName = (Player player, Game game) -> {
                return player.getFullName() + "\n";
            };
        }
        if (isDetail) {
            list = resultsWithDetailMap.get(gameType);
            String finalGameType = gameType;
            writeRankAndScore = (Player player, Game game) -> {
                return getRanks(player, finalGameType)
                        + "\nPreliminary Score:" + getScore(game, 0)
                        + "\nSemifinal Score:" + getScore(game,1)
                        + "\nFinal Score:" + getScore(game,2)
                        + "\n-----\n";
            };
        } else {
            list = resultsMap.get(gameType);
            writeRankAndScore = (Player player, Game game) -> {
                return game.getRank(2)
                        + "\nScore:" + getScore(game, 2)
                        + "\n-----\n";
            };
        }
        for (Player player : list) {
            Game game = player.getGame(gameType);
            sb.append("FullName:").append(writeFullName.writeFullName(player, game));
            sb.append("Rank:").append(writeRankAndScore.writeRankAndScore(player, game));
        }
        if (isDetail) {
            gameType = gameType + " detail";
        }
        all.put(gameType, sb.toString());
        sb.setLength(0);
        displayResults(writer, gameType);
    }

    private static void initPlayers(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("data/players.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append(line);
        }
        JSONArray nations = JSONArray.parseArray(sb.toString());
        sb.setLength(0);
        for (int i = 0; i < nations.size(); i++) {
            JSONArray nation = nations.getJSONObject(i).getJSONArray("Participations");
            for (int j = 0; j < nation.size(); j++) {
                Player player = JSON.parseObject(nation.getJSONObject(j).toString(), Player.class);
                playersMap.put(player.getFullName(), player);
                sb.append(player);
            }
        }
        all.put("players", sb.toString());
        sb.setLength(0);
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initResult(String gameType) throws IOException {
        resultsMap.put(gameType, new ArrayList<>());
        resultsWithDetailMap.put(gameType, new ArrayList<>());
        if (playersMap.isEmpty()) {
            initPlayers();
        }
        boolean isDual = isDual(gameType);
        BufferedReader reader = new BufferedReader(new FileReader("data/" + gameType + ".json"));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONArray heats = JSONObject.parseObject(sb.toString()).getJSONArray("Heats");
        sb.setLength(0);
        for (int i = 0; i < heats.size(); i++) {
            JSONObject heat = heats.getJSONObject(i);
            String name = heat.getString("Name");
            JSONArray results = heat.getJSONArray("Results");
            int size = results.size();
            for (int j = 0; j < size; j++) {
                JSONObject result = results.getJSONObject(j);
                JSONArray dives = result.getJSONArray("Dives");
                for (int k = 0; k < 5; k++) {
                    sb.append(dives.getJSONObject(k).getString("DivePoints")).append(" + ");
                }
                int len = sb.length();
                sb.delete(len - 3, len).append(" = ").append(result.getString("TotalPoints"));
                byte rank = result.getByte("Rank");
                String fullName;
                if (isDual) {
                    JSONArray competitors = result.getJSONArray("Competitors");
                    fullName = competitors.getJSONObject(0).getString("FullName");
                    String competitor = competitors.getJSONObject(1).getString("FullName");
                    addScore(fullName, gameType, name, new Score(sb.toString(), rank), competitor);
                } else {
                    fullName = result.getString("FullName");
                    addScore(fullName, gameType, name, new Score(sb.toString(), rank));
                }
                sb.setLength(0);
                if (i == 0) {
                    addPlayerToResultList(gameType, fullName);
                }
                if (i == heats.size() - 1) {
                    addPlayerToResultWithDetailList(gameType, fullName);
                }
            }
        }
    }

    private static void addScore(String fullName, String gameType, String stage, Score score) {
        Player player = playersMap.get(fullName);
        Game game = contains(gameType, player);
        if (game == null) {
            game = player.addScore(gameType);
        }
        game.setScore(stage, score);
    }

    private static void addScore(String fullName, String gameType, String stage, Score score, String competitor) {
        Player player = playersMap.get(fullName);
        Game game = contains(gameType, player);
        if (game == null) {
            game = player.addScore(gameType);
        }
        game.setCompetitor(competitor);
        game.setScore(stage, score);
    }

    private static Game contains(String gameType, Player player) {
        for (int i = 0; i < player.getGamesSize(); i++) {
            if (player.getGame(i).getType().equals(gameType)) {
                return player.getGame(i);
            }
        }
        return null;
    }

    private static void addPlayerToResultList(String gameType, String fullName) {
        Player player = playersMap.get(fullName);
        ArrayList<Player> list = resultsMap.get(gameType);
        if (!list.contains(player)) {
            list.add(player);
        }
    }

    private static void addPlayerToResultWithDetailList(String gameType, String fullName) {
        Player player = playersMap.get(fullName);
        ArrayList<Player> list = resultsWithDetailMap.get(gameType);
        if (!list.contains(player)) {
            list.add(player);
        }
    }

    private static String getRanks(Player player, String type) {
        Game game = player.getGame(type);
        int len = sb.length();
        for (int i = 0; i < 3; i++) {
            if (game.getScore(i) == null) {
                sb.append(" | *");
            } else {
                sb.append(" | ").append(game.getRank(i));
            }
        }
        sb.delete(len,len + 3);
        String ranks = sb.toString();
        sb.setLength(0);
        return ranks;
    }

    private static String getScore(Game game, int i) {
        Score score = game.getScore(i);
        return score == null ? "*" : score.getScore();
    }

    public static boolean isDual(String gameType) {
        return gameType.matches(".*synchronised");
    }

    public static boolean contains(String gameType) {
        int size = gameTypes.length;
        for (String type : gameTypes) {
            if (type.equals(gameType)) {
                return true;
            }
        }
        return false;
    }
}
