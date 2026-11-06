package dwasearch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class QueryTest {

    private GameData makeData() {
        GameData data = new GameData();

        data.players.add(new Player("ZHANG Wei", "Male", "China", "CHN"));
        data.players.add(new Player("COLE Ellie", "Female", "Australia", "AUS"));
        data.players.add(new Player("KEENEY Maddison", "Female", "Australia", "AUS"));
        data.players.add(new Player("BAI Yuming", "Male", "China", "CHN"));

        ArrayList<ResultItem> list = new ArrayList<ResultItem>();

        ResultItem a = new ResultItem();
        a.fullName = "CHEN Jia";
        a.rank = 1;
        a.scores.add("73.50");
        a.scores.add("74.40");
        a.totalPoints = "147.90";
        list.add(a);

        ResultItem b = new ResultItem();
        b.fullName = "CHEN Yiwen";
        b.rank = 2;
        b.scores.add("69.00");
        b.scores.add("72.00");
        b.totalPoints = "141.00";
        list.add(b);

        data.events.put("women 3m springboard", list);
        return data;
    }

    @Test
    public void testPlayersOrder() {
        Query q = new Query(makeData());
        String out = q.showPlayers();

        int au1 = out.indexOf("Full Name:COLE Ellie");
        int au2 = out.indexOf("Full Name:KEENEY Maddison");
        int cn1 = out.indexOf("Full Name:BAI Yuming");
        int cn2 = out.indexOf("Full Name:ZHANG Wei");

        assertTrue(au1 >= 0);
        assertTrue(au2 > au1);
        assertTrue(cn1 > au2);
        assertTrue(cn2 > cn1);
    }

    @Test
    public void testPlayersHasSeparatorEveryRecord() {
        Query q = new Query(makeData());
        String out = q.showPlayers();

        assertEquals(4, count(out, "-----"));
        assertTrue(out.endsWith("-----"));
    }

    @Test
    public void testResultFormat() {
        Query q = new Query(makeData());
        String out = q.showResult("women 3m springboard");
        assertTrue(out.contains("Full Name:CHEN Jia"));
        assertTrue(out.contains("Rank:1"));
        assertTrue(out.contains("Score:73.50 + 74.40 = 147.90"));
        assertTrue(out.contains("Rank:2"));
        assertTrue(out.contains("Score:69.00 + 72.00 = 141.00"));
    }

    @Test
    public void testResultSortedByRank() {
        Query q = new Query(makeData());
        String out = q.showResult("women 3m springboard");
        assertTrue(out.indexOf("Rank:1") < out.indexOf("Rank:2"));
    }

    @Test
    public void testUnknownEventReturnNA() {
        Query q = new Query(makeData());
        assertEquals("N/A\n-----", q.showResult("men 10m platform"));
    }

    private int count(String text, String sub) {
        int n = 0;
        int index = 0;
        while (true) {
            int i = text.indexOf(sub, index);
            if (i == -1) {
                break;
            }
            n++;
            index = i + sub.length();
        }
        return n;
    }
}
