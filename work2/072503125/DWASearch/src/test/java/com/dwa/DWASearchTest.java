package com.dwa;

import com.dwa.model.Player;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DWASearchTest {

    // 测试1：测试选手排序是否正确 
    @Test
    public void testPlayerSorting() {
        List<Player> players = new ArrayList<>();
        
        Player p1 = new Player(); p1.country = "China"; p1.lastName = "SMITH";
        Player p2 = new Player(); p2.country = "Australia"; p2.lastName = "COLE";
        Player p3 = new Player(); p3.country = "Australia"; p3.lastName = "CHEN";
        
        players.add(p1); players.add(p2); players.add(p3);

        players.sort(Comparator.comparing((Player p) -> p.country)
                               .thenComparing(p -> p.lastName));//Comparator链式调用 先按国家排序 再按名字排序

        // 断言：排序后第一个应该是Australia的CHEN
        assertEquals("Australia", players.get(0).country);//assertEquals用于断言期望值和实际值是否相等
        assertEquals("CHEN", players.get(0).lastName);
        assertEquals("China", players.get(2).country);
    }

    // 测试2：测试BigDecimal分数计算是否准确
    @Test
    public void testScoreCalculation() {
        BigDecimal total = BigDecimal.ZERO;
        List<Double> scores = List.of(73.50, 74.40, 76.50, 72.00);

        for (Double score : scores) {
            total = total.add(new BigDecimal(String.valueOf(score)));
        }
        
        String finalScore = total.setScale(2, RoundingMode.HALF_UP).toString();
        
        // 断言：73.50 + 74.40 + 76.50 + 72.00 = 296.40
        assertEquals("296.40", finalScore);
    }
}
