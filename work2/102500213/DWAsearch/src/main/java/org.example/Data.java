package org.example;

import java.util.List;
import java.util.ArrayList;

public class Data {
    private List<Player> players = new ArrayList<>();
    private List<Final> finals = new ArrayList<>();

    public Data(){}
    public List<Player> getPlayers(){ return players; }
    public void setPlayers(List<Player> players) { this.players = players; }
    public List<Final> getFinals(){ return finals; }
    public void setFinals(List<Final> finals) {
        this.finals = finals;
    }

    public String getPlayersContent(){
        players.sort(null);
        StringBuilder str = new StringBuilder();
        for(Player p:players){
            str.append(p.getContent());
        }
        str.append("\n");
        return str.toString();
    }
    public String getFinalsContent(int i){
        Final f = finals.get(i);
        return f.getContent();
    }

    public void showPlayers(){
        players.sort(null);
        System.out.println("-----Players-----");
        for(Player p:players){
            p.showPlayer();
        }
    }
    public void showFinals(int i){
        Final f = finals.get(i);
        f.showFinal();

    }
}
