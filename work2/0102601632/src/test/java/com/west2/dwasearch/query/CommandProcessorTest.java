package com.west2.dwasearch.query;

import com.west2.dwasearch.data.DataStore;
import com.west2.dwasearch.data.Player;
import com.west2.dwasearch.io.DataException;
import com.west2.dwasearch.io.DataReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandProcessorTest {
    private static CommandProcessor processor;
    private static DataStore store;

    private static final String PLAYERS_HEAD =
            "Full Name:COLE Ellie";

    private static final List<String> FIRST_PLAYER = Arrays.asList(
            "Full Name:COLE Ellie",
            "Gender:Female",
            "Country:Australia",
            "-----");

    private static final List<String> CHEN_JIA = Arrays.asList(
            "Full Name:CHEN Jia",
            "Rank:1",
            "Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40",
            "-----");

    private static final List<String> CHEN_YIWEN = Arrays.asList(
            "Full Name:CHEN Yiwen",
            "Rank:2",
            "Score:69.00 + 72.00 + 76.50 + 79.05 + 76.50 = 373.05",
            "-----");

    @BeforeAll
    static void loadData() throws DataException {
        store = new DataReader().loadFromClasspath();
        processor = new CommandProcessor(store);
    }

    @Test
    void playersFirstRecordMatchesSample() {
        List<String> lines = processor.processLine("players");

        assertEquals(FIRST_PLAYER, lines.subList(0, 4));
    }

    @Test
    void playersRecordHasNameGenderCountryOnSeparateLines() {
        List<String> lines = processor.processLine("players");
        List<String> first = lines.subList(0, 4);

        assertEquals(4, first.size());
        assertEquals("Full Name:COLE Ellie", first.get(0));
        assertEquals("Gender:Female", first.get(1));
        assertEquals("Country:Australia", first.get(2));
        assertEquals("-----", first.get(3));
    }

    @Test
    void playersOutputIsFourLinesPerPlayer() {
        List<String> lines = processor.processLine("players");

        assertEquals(store.getPlayers().size() * 4, lines.size());
        for (int i = 0; i < lines.size(); i++) {
            if (i % 4 == 0) {
                assertTrue(lines.get(i).startsWith("Full Name:"), "第 " + i + " 行：" + lines.get(i));
            } else if (i % 4 == 1) {
                assertTrue(lines.get(i).startsWith("Gender:"), "第 " + i + " 行：" + lines.get(i));
            } else if (i % 4 == 2) {
                assertTrue(lines.get(i).startsWith("Country:"), "第 " + i + " 行：" + lines.get(i));
            } else {
                assertEquals("-----", lines.get(i));
            }
        }
    }

    @Test
    void playersFieldsAreNotJoinedIntoOneLine() {
        List<String> lines = processor.processLine("players");

        for (int i = 0; i < lines.size(); i++) {
            assertFalse(lines.get(i).contains(" / "), "字段又被拼到一行了：" + lines.get(i));
        }
        assertFalse(PLAYERS_HEAD.contains("/"));
    }

    @Test
    void playersSortedByCountryThenLastName() {
        Map<String, Player> byName = new HashMap<>();
        for (int i = 0; i < store.getPlayers().size(); i++) {
            Player player = store.getPlayers().get(i);
            byName.put(player.getFullName(), player);
        }

        List<String> lines = processor.processLine("players");
        String lastCountry = null;
        String lastLastName = null;
        int seen = 0;
        for (int i = 0; i < lines.size(); i += 4) {
            String fullName = lines.get(i).substring("Full Name:".length());
            String country = lines.get(i + 2).substring("Country:".length());
            Player player = byName.get(fullName);
            assertTrue(player != null, "输出里出现了数据里没有的选手：" + fullName);
            assertEquals(player.getCountry(), country, "国籍那一行不对：" + fullName);
            seen++;

            if (lastCountry != null) {
                int byCountry = lastCountry.compareTo(player.getCountry());
                assertTrue(byCountry < 0
                                || (byCountry == 0 && lastLastName.compareTo(player.getLastName()) <= 0),
                        "排序不对：" + lastCountry + " " + lastLastName + " 在 "
                                + player.getCountry() + " " + player.getLastName() + " 前面");
            }
            lastCountry = player.getCountry();
            lastLastName = player.getLastName();
        }
        assertEquals(store.getPlayers().size(), seen, "有选手没输出");
    }

    @Test
    void playersEveryRecordEndsWithSeparator() {
        List<String> lines = processor.processLine("players");

        assertEquals("-----", lines.get(3));
        assertEquals("-----", lines.get(lines.size() - 1));
        assertEquals(store.getPlayers().size(), countSeparators(lines));
    }

    @Test
    void resultMatchesSnapshotForWomen3mSpringboard() {
        List<String> lines = processor.processLine("result women 3m springboard");

        assertEquals(CHEN_JIA, lines.subList(0, 4));
        assertEquals(CHEN_YIWEN, lines.subList(4, 8));
    }

    @Test
    void resultRecordHasNameRankScoreOnSeparateLines() {
        List<String> lines = processor.processLine("result women 3m springboard");

        assertEquals("Full Name:CHEN Jia", lines.get(0));
        assertEquals("Rank:1", lines.get(1));
        assertEquals("Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40", lines.get(2));
        assertEquals("-----", lines.get(3));
    }

    @Test
    void resultHasFourRecordsForIndividualFinal() {
        List<String> lines = processor.processLine("result women 10m platform");

        assertEquals(4 * 4, lines.size());
        assertEquals(4, countSeparators(lines));
    }

    @Test
    void resultHasEightRecordsForSynchronisedFinal() {
        List<String> lines = processor.processLine("result men 10m synchronised");

        assertEquals(8 * 4, lines.size());
        assertEquals(8, countSeparators(lines));
    }

    @Test
    void resultRankIsAscending() {
        List<String> lines = processor.processLine("result men 3m synchronised");
        int expectedRank = 1;
        for (int i = 0; i < lines.size(); i += 4) {
            assertEquals("Rank:" + expectedRank, lines.get(i + 1),
                    "第 " + expectedRank + " 名不对：" + lines.get(i + 1));
            expectedRank++;
        }
    }

    @Test
    void allEightEventsCanBeQueried() {
        String[] keywords = {
                "women 3m springboard", "women 10m platform", "women 3m synchronised",
                "women 10m synchronised", "men 3m springboard", "men 10m platform",
                "men 3m synchronised", "men 10m synchronised"
        };
        for (String keyword : keywords) {
            List<String> lines = processor.processLine("result " + keyword);
            assertFalse(lines.isEmpty(), "查不到：" + keyword);
            assertEquals(0, lines.size() % 4, "不是四条一段：" + keyword);
            assertTrue(lines.get(0).startsWith("Full Name:"), "第一行不对：" + keyword);
            assertEquals("-----", lines.get(lines.size() - 1));
            assertNotEquals("N/A", lines.get(0), "不应该输出 N/A：" + keyword);
        }
    }

    @Test
    void unknownCommandOutputsError() {
        assertEquals(Arrays.asList("Error", "-----"), processor.processLine("player"));
        assertEquals(Arrays.asList("Error", "-----"), processor.processLine("Players"));
        assertEquals(Arrays.asList("Error", "-----"), processor.processLine("players extra"));
        assertEquals(Arrays.asList("Error", "-----"), processor.processLine("resultwomen 3m springboard"));
        assertEquals(Arrays.asList("Error", "-----"), processor.processLine("  "));
    }

    @Test
    void resultWithoutValidEventOutputsNotAvailable() {
        assertEquals(Arrays.asList("N/A", "-----"), processor.processLine("result"));
        assertEquals(Arrays.asList("N/A", "-----"), processor.processLine("result "));
        assertEquals(Arrays.asList("N/A", "-----"), processor.processLine("result sss"));
        assertEquals(Arrays.asList("N/A", "-----"), processor.processLine("result women 10m springboard"));
        assertEquals(Arrays.asList("N/A", "-----"),
                processor.processLine("result women 3m springboard details"));
        assertEquals(Arrays.asList("N/A", "-----"),
                processor.processLine("result men 10m     synchronised"));
    }

    @Test
    void emptyLineIsIgnored() {
        assertEquals(Collections.emptyList(), processor.processLine(""));
        List<String> withoutEmpty = processor.process(new ArrayList<>(
                Arrays.asList("players", "result sss")));
        List<String> withEmpty = processor.process(new ArrayList<>(
                Arrays.asList("players", "", "result sss")));
        assertEquals(withoutEmpty, withEmpty);
    }

    @Test
    void repeatedCommandsRepeatOutput() {
        List<String> once = processor.processLine("result sss");
        List<String> twice = processor.process(Arrays.asList("result sss", "result sss"));
        assertEquals(2, twice.size() / once.size());
        assertEquals(once, twice.subList(0, once.size()));
        assertEquals(once, twice.subList(once.size(), twice.size()));
    }

    @Test
    void processingContinuesAfterError() {
        List<String> lines = processor.process(Arrays.asList("abc", "players"));

        assertEquals("Error", lines.get(0));
        assertEquals("-----", lines.get(1));
        assertEquals(FIRST_PLAYER, lines.subList(2, 6));
    }

    @Test
    void everyRecordIsFollowedBySeparatorLine() {
        List<String> lines = processor.process(Arrays.asList(
                "result", "players", "xxx", "result men 3m springboard"));

        int records = 2 + store.getPlayers().size()
                + store.getResults("men 3m springboard").size();
        assertEquals(records, countSeparators(lines));
        assertEquals("-----", lines.get(lines.size() - 1));
    }

    @Test
    void emptyCommandFileProducesNoOutput() {
        assertEquals(Collections.emptyList(), processor.process(Collections.emptyList()));
    }

    private int countSeparators(List<String> lines) {
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("-----")) {
                count++;
            }
        }
        return count;
    }
}
