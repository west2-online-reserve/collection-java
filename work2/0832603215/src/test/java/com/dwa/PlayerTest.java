package com.dwa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerCreation() {

        Player player = new Player(
                "Ellie",
                "COLE",
                1,
                "Australia"
        );

        assertEquals("Ellie", player.getFirstName());
        assertEquals("COLE", player.getLastName());
        assertEquals("Female", player.getGender());
        assertEquals("Australia", player.getCountry());
    }

    @Test
    void testPlayerName() {

        Player player = new Player(
                "Jia",
                "CHEN",
                1,
                "China"
        );

        assertEquals("Jia", player.getFirstName());
        assertEquals("CHEN", player.getLastName());
    }

    @Test
    void testPlayerCountry() {

        Player player = new Player(
                "Wang",
                "ZHANG",
                0,
                "China"
        );

        assertEquals("China", player.getCountry());
    }

}