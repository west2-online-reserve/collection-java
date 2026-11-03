package com.dwa.service;

import com.dwa.model.Player;
import java.util.List;
import java.util.Comparator;

public class PlayerService {
    public static void printPlayers(List<Player> players) {
        //按国籍升序，国籍相同按姓氏升序
        players.sort(Comparator.comparing((Player p) -> p.country)
                               .thenComparing(p -> p.lastName));

        //按格式输出
        for (Player p : players) {
            System.out.println("Full Name:" + p.fullName);
            System.out.println("Gender:" + p.gender);
            System.out.println("Country:" + p.country);
            System.out.println("----");
        }
    }
}