package com.wdasearch.domain;

import java.util.ArrayList;

public class Player {
    private String PreferredLastName;
    private String PreferredFirstName;
    private String Gender;
    private String NAT;
    private final ArrayList<Game> games = new ArrayList<>();

    public void setPreferredFirstName(String preferredFirstName) {
        PreferredFirstName = preferredFirstName;
    }

    public void setGender(int gender) {
        if (gender == 1) {
            Gender = "Female";
        } else {
            Gender = "Male";
        }
    }

    @Override
    public String toString() {
        return "Full Name:" + PreferredLastName + " " + PreferredFirstName + "\n"
                + "Gender:" + Gender + "\n"
                + "Country:" + NAT;
    }

    public String getFullName() {
        return PreferredLastName + " " + PreferredFirstName;
    }

    public int getGamesSize() {
        return games.size();
    }

    public Game getGame(int i) {
        return games.get(i);
    }

    public Game getGame(String gameType) {
        for (Game game : games) {
            if (game.getType().equals(gameType)) {
                return game;
            }
        }
        return null;
    }

    public Game addScore(String type) {
        Game game = contains(type);
        if (game == null) {
            game = new Game(type);
            this.games.add(game);
        }
        return game;
    }

    private Game contains(String type) {
        int size = games.size();
        for (Game game : games) {
            if (game.getType().equals(type)) {
                return game;
            }
        }
        return null;
    }

    public void setNAT(String NAT) {
        this.NAT = NAT;
    }

    public void setPreferredLastName(String preferredLastName) {
        PreferredLastName = preferredLastName;
    }
}
