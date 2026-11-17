package com.dwa;

import com.dwa.model.Player;
import com.dwa.model.DivingResult;
import com.dwa.service.PlayerService;
import com.dwa.service.ResultService;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {
//测试1.排序错误和分割线错误
    @Test
    public void testPlayerSortingAndFormat() {
        List<Player> players = Arrays.asList(
            createPlayer("China", "SMITH", "M"),
            createPlayer("Australia", "CHEN", "F")
        );

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        PlayerService.printPlayers(players, writer);

        String result = sw.toString();
        assertTrue(result.indexOf("Australia") < result.indexOf("China"), "排序错误");
        assertTrue(result.contains("-----"), "缺失分隔线");
    }
//测试2.成绩计算和格式错误及分割线错误
    @Test
    public void testResultServiceQuery() {
        DivingResult r1 = new DivingResult();
        r1.event = "women 3m springboard"; 
        r1.diverName = "Player A"; 
        r1.rank = 1;
        r1.scores = Arrays.asList(50.0, 50.0);

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        
        ResultService.printResults(Arrays.asList(r1), "women 3m springboard", writer);

        String result = sw.toString();
        
        assertTrue(result.contains("Score:") && result.contains("100.00"), "成绩计算或格式错误");
        assertTrue(result.contains("-----"));
    }

    private Player createPlayer(String country, String lastName, String gender) {
        Player p = new Player();
        p.country = country; 
        p.lastName = lastName; 
        p.fullName = lastName; 
        p.gender = gender;
        return p;
    }
}