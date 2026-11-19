package com.dwasearch;

import com.dwasearch.model.Player;
import com.dwasearch.query.PlayerQueryService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerQueryServiceTest {

    @Test
    void shouldSortByCountryThenLastName() {
        Player keeney = new Player("Maddison", "KEENEY", "Female", "Australia");
        Player chen = new Player("Jia", "CHEN", "Female", "People's Republic of China");
        Player cole = new Player("Ellie", "COLE", "Female", "Australia");

        List<Player> sorted = new PlayerQueryService()
                .sortedPlayers(List.of(keeney, chen, cole));

        assertEquals(3, sorted.size());
        assertEquals("COLE Ellie", sorted.get(0).getFullName());
        assertEquals("KEENEY Maddison", sorted.get(1).getFullName());
        assertEquals("CHEN Jia", sorted.get(2).getFullName());
    }

    @Test
    void shouldFormatPlayersWithSeparatorAfterEach() {
        Player cole = new Player("Ellie", "COLE", "Female", "Australia");
        Player keeney = new Player("Maddison", "KEENEY", "Female", "Australia");

        String actual = new PlayerQueryService()
                .formatPlayers(List.of(cole, keeney));

        String expected =
                "Full Name:COLE Ellie\n" +
                        "Gender:Female\n" +
                        "Country:Australia\n" +
                        "-----\n" +
                        "Full Name:KEENEY Maddison\n" +
                        "Gender:Female\n" +
                        "Country:Australia\n" +
                        "-----\n";

        assertEquals(expected, actual);
    }
}