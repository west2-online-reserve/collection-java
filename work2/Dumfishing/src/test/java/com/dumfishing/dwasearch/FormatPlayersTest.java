package com.dumfishing.dwasearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatPlayersTest {

    @Test
    @DisplayName("单个选手输出四行")
    void singlePlayerGivesFourLines() {
        List<Player> list = List.of(
                new Player("COLE", "Ellie", "Female", "Australia")
        );
        @SuppressWarnings("TextBlockMigration") String expected = "Full Name:COLE Ellie\n" + "Gender:Female\n" + "Country:Australia\n" + "-----\n";
        assertEquals(expected, DWASearch.formatPlayers(list));
    }

    @Test
    @DisplayName("空列表输出空字符串")
    void emptyListGivesEmptyString() {
        assertEquals("", DWASearch.formatPlayers(List.of()));
    }

    @Test
    @DisplayName("缺字段时抛出 IllegalStateException")
    void missingFieldThrows() throws Exception {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree("{\"Rank\":1}");
        assertThrows(IllegalStateException.class, () -> DWASearch.requireText(node, "FullName", "test.json"));
    }

    @Test
    @DisplayName("决赛第一名是陈佳")
    void firstPlaceIsChenJia() throws Exception {
        String out = DWASearch.formatEvent(new ObjectMapper(), "Women's 3m Springboard.json");
        assertTrue(out.startsWith("Full Name:CHEN Jia\nRank:1\nScore:73.50 + "));
    }

    @Test
    @DisplayName("字段存在时返回正确的值")
    void requireTextReturnsValue() throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree("{\"Rank\":1,\"FullName\":\"CHEN Jia\"}");
        String actual = DWASearch.requireText(node, "FullName", "test.json");
        assertEquals("CHEN Jia", actual);
    }
}


