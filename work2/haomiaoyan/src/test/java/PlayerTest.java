import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private List<Player> sixPlayers(){
        // 在这里统一编写，避免代码重复
        List<Player> list = new ArrayList<>();
        list.add(new Player("VERZYL Sophia", "Female", "United States of America"));
        list.add(new Player("AERZYL Sophia", "Female", "China"));
        list.add(new Player("RERZYL Sophia", "Female", "Japan"));
        list.add(new Player("CERZYL Sophia", "Female", "England"));
        list.add(new Player("HERZYL Sophia", "Female", "Russian"));
        list.add(new Player("IERZYL Sophia", "Female", "United States of America"));

        return list;
    }

    @Test
    void sort_Player(){
        // 创建对象以及排序
        List<Player> sp_players = sixPlayers();  // 这里直接拿来用
        sp_players.sort(Comparator.comparing(Player::getCountry)
                .thenComparing(Player::getLastName));

        // 断言
        assertEquals("AERZYL Sophia", sp_players.get(0).getFullname());
        assertEquals("CERZYL Sophia", sp_players.get(1).getFullname());
        assertEquals("RERZYL Sophia", sp_players.get(2).getFullname());
        assertEquals("HERZYL Sophia", sp_players.get(3).getFullname());
        assertEquals("IERZYL Sophia", sp_players.get(4).getFullname());
        assertEquals("VERZYL Sophia", sp_players.get(5).getFullname());
    }

    @Test
    void format_players(){
        // 创建对象以及排序
        List<Player> fp_players = sixPlayers();
        fp_players.sort(Comparator.comparing(Player::getCountry)
                .thenComparing(Player::getLastName));

        String result = PlayerFormatter.formatterPlayer(fp_players);

        String expectedFirstFour = "Full Name:AERZYL Sophia\n" +
                "Gender:Female\n" +
                "Country:China\n" +
                "-----\n";

        assertTrue(result.startsWith(expectedFirstFour));

        // 断言分隔线计数：应恰好出现 6 次 "-----"
        long separatorCount = result.lines().filter(line -> line.equals("-----")).count();
        assertEquals(6, separatorCount, "应该有 6 条分隔线");

    }

}
