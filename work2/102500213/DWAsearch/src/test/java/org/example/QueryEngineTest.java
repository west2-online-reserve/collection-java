package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class QueryEngineTest {

    private final QueryEngine engine = new QueryEngine();

    @Test
    void testCommandPlayers() {
        Data data = new Data();
        Player p1 = new Player();
        p1.setFullName("Alice");
        p1.setCountry("USA");
        p1.setGender(false);
        data.getPlayers().add(p1);

        List<String> cmds = List.of("players");
        String out = engine.runQuery(cmds, data);
        assertTrue(out.contains("Alice"));
    }

    @Test
    void testCommandResultExist() {
        Data data = new Data();
        Final f = new Final();
        f.setDisciplineName("women 3m springboard");

        Result r = new Result();
        r.setFullName("Alice");
        r.setRank(1);
        r.setTotalPoints("350.25");
        List<String> scoreList = List.of("70.00", "72.50");
        r.setScores(scoreList);

        f.getResults().add(r);
        data.getFinals().add(f);

        List<String> cmds = List.of("result women 3m springboard");
        String out = engine.runQuery(cmds, data);
        assertTrue(out.contains("Full Name:Alice"));
    }





    @Test
    void testCommandResultNoMatch() {
        Data data = new Data();
        List<String> cmds = List.of("result abcdef");
        String out = engine.runQuery(cmds, data);
        assertTrue(out.contains("N/A"));
    }

    @Test
    void testUnknownCommand() {
        Data data = new Data();
        List<String> cmds = List.of("hello123");
        String out = engine.runQuery(cmds, data);
        assertTrue(out.contains("Error"));
    }

    @Test
    void testResultArgNotEnough() {
        Data data = new Data();
        List<String> cmds = List.of("result");
        String out = engine.runQuery(cmds, data);
        assertTrue(out.contains("N/A"));
    }
}
