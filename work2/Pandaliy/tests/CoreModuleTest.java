import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 查询核心测试：验证数据数量、输出格式与不可变性。
class CoreModuleTest {

    private final StringWriter buffer = new StringWriter();
    private final PrintWriter writer = new PrintWriter(buffer, true);
    private final CoreModule core = new CoreModule(writer);

    @Test
    void returnsAllPlayersAndEvents() {
        assertEquals(89, core.getAllPlayers().size());
        assertEquals(8, core.getEvents().size());
    }

    @Test
    void eventCommandsMatchFixedData() {
        List<String> commands = core.getEvents().stream()
                .map(Event::command)
                .toList();
        assertIterableEquals(List.of(
                "result men 10m platform",
                "result men 10m synchronised",
                "result men 3m springboard",
                "result men 3m synchronised",
                "result women 10m platform",
                "result women 10m synchronised",
                "result women 3m springboard",
                "result women 3m synchronised"), commands);
    }

    @Test
    void resultCountsMatchEventType() {
        assertEquals(4, eventByName("result men 10m platform").results().size());
        assertEquals(8, eventByName("result men 10m synchronised").results().size());
        assertEquals(4, eventByName("result women 3m springboard").results().size());
    }

    @Test
    void playersOutputFollowsRequiredFormat() {
        core.displayAllPlayersInfo();
        writer.flush();

        String output = buffer.toString();
        assertTrue(output.startsWith(
                "Full Name:COLE Ellie\nGender:Female\nCountry:Australia\n-----\n"));
        assertTrue(output.endsWith("-----\n"));
        assertFalse(output.contains("\r"));
        assertEquals(89, count(output, "-----\n"));
    }

    @Test
    void eventOutputContainsRankAndScores() {
        core.displayResultsForEvent(eventByName("result men 10m platform"));
        writer.flush();

        String output = buffer.toString();
        assertTrue(output.contains(
                "Full Name:BAI Yuming\n"
                        + "Rank:1\n"
                        + "Score:81.60 + 86.40 + 77.00 + 91.80 + 104.40 + 101.75 = 542.95\n"
                        + "-----\n"));
        assertFalse(output.contains("\r"));
    }

    @Test
    void returnedPlayerListIsImmutable() {
        List<Player> players = core.getAllPlayers();
        assertThrows(UnsupportedOperationException.class, players::clear);
    }

    @Test
    void sortPlayersUsesCountryThenName() {
        // 故意打乱输入顺序：排序不能依赖传入顺序
        List<Player> unsorted = List.of(
                new Player("ZHAO Renjie", "Male", "China", "CHN"),
                new Player("KEENEY Maddison", "Female", "Australia", "AUS"),
                new Player("COLE Ellie", "Female", "Australia", "AUS"));

        List<Player> sorted = CoreModule.sortPlayers(unsorted);

        assertEquals(
                List.of("COLE Ellie", "KEENEY Maddison", "ZHAO Renjie"),
                sorted.stream().map(Player::fullName).toList());
    }

    @Test
    void sortResultsUsesRankAscending() {
        List<Result> unsorted = List.of(
                new Result("C", 3, "CHN", List.of("1.00"), "1.00"),
                new Result("A", 1, "CHN", List.of("3.00"), "3.00"),
                new Result("B", 2, "CHN", List.of("2.00"), "2.00"));

        List<Result> sorted = CoreModule.sortResults(unsorted);

        assertEquals(List.of(1, 2, 3), sorted.stream().map(Result::rank).toList());
        assertEquals(List.of("A", "B", "C"), sorted.stream().map(Result::fullName).toList());
    }

    @Test
    void playersListIsInRequiredOrder() {
        List<Player> players = core.getAllPlayers();
        for (int i = 1; i < players.size(); i++) {
            Player previous = players.get(i - 1);
            Player current = players.get(i);
            int byCountry = previous.country().compareTo(current.country());
            assertTrue(byCountry < 0
                            || (byCountry == 0
                                && previous.fullName().compareTo(current.fullName()) <= 0),
                    "第 " + i + " 位顺序不对：" + previous.country() + "/" + previous.fullName()
                            + " 排在 " + current.country() + "/" + current.fullName() + " 之前");
        }
    }

    @Test
    void resultsListIsInRankOrder() {
        for (Event event : core.getEvents()) {
            List<Result> sorted = CoreModule.sortResults(event.results());
            for (int i = 0; i < sorted.size(); i++) {
                assertEquals(i + 1, sorted.get(i).rank(),
                        event.command() + " 的第 " + (i + 1) + " 条排名不是 " + (i + 1));
            }
        }
    }

    private Event eventByName(String command) {
        return core.getEvents().stream()
                .filter(event -> event.command().equals(command))
                .findFirst()
                .orElseThrow();
    }

    private static int count(String text, String needle) {
        int occurrences = 0;
        int from = 0;
        while ((from = text.indexOf(needle, from)) != -1) {
            occurrences++;
            from += needle.length();
        }
        return occurrences;
    }
}
