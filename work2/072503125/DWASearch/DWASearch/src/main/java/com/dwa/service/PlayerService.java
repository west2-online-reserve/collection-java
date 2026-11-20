package com.dwa.service;

import com.dwa.model.Player;
import java.util.List;
import java.util.Comparator;
import java.io.PrintWriter;

public class PlayerService {
    public static void printPlayers(List<Player> players, PrintWriter writer) {
        //按国籍升序，国籍相同按姓氏升序
        players.sort(Comparator.comparing((Player p) -> p.country)
                               .thenComparing(p -> p.lastName));

        //按格式输出
        for (Player p : players) {
            writer.println("Full Name:" + p.fullName);
            writer.println("Gender:" + p.gender);
            writer.println("Country:" + p.country);
            writer.println("-----");
        }
    }
}