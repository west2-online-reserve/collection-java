package com.dwasearch;

import com.dwasearch.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    void fullNameShouldBeLastNameThenFirstName() {
        Player player = new Player("Ellie", "COLE", "Female", "Australia");

        assertEquals("COLE Ellie", player.getFullName());
        assertEquals("Australia", player.getCountry());
    }
}