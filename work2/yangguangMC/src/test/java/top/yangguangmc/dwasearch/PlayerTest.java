package top.yangguangmc.dwasearch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new Player("First", "Last", 1, "Country"));
        assertThrows(IllegalArgumentException.class, () -> new Player("First", "Last", 2, "Country"));
    }

    @Test
    void testCompareTo() {
        Player p1 = new Player("First", "LastA", 1, "CountryA");
        Player p2 = new Player("First", "LastA", 1, "CountryB");
        Player p3 = new Player("First", "LastB", 1, "CountryB");
        List<Player> players = new ArrayList<>(3);
        players.add(p3);
        players.add(p2);
        players.add(p1);
        players.sort(null);
        assertEquals(p1, players.get(0));
        assertEquals(p2, players.get(1));
        assertEquals(p3, players.get(2));
    }

    @Test
    void testPrint() {
        IODataSet ioDataSet = new IODataSet();
        new Player("First", "Last", 0, "Country").print(ioDataSet);
        String str = ioDataSet.getOutputs();
        assertEquals("""
                Full Name:Last First
                Gender:Male
                Country:Country
                -----
                """, str);
    }
}