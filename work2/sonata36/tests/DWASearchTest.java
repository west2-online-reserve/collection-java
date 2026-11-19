import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Dependency-free regression suite; each named case is an independent test. */
public final class DWASearchTest {
    private static int passed;
    private static CoreModule real;

    public static void main(String[] args) throws Exception {
        real = CoreModule.fromBundledData();
        test("official player count", () -> equal(295, count(real.displayAllPlayersInfo(), "Full Name:")));
        test("country and surname order", () -> {
            String p = real.displayAllPlayersInfo();
            check(p.startsWith("Full Name:ENOKYAN Arman\nGender:Male\nCountry:Armenia\n-----\n"));
            check(p.indexOf("Full Name:HART Alexander") < p.indexOf("Full Name:LOTFI Dariush"));
        });
        test("official women 1m champion", () -> check(real.displayResultsForEachEvent(
                "women 1m springboard", false).startsWith("Full Name:KOLOI Alysha\nRank:1\n"
                + "Score:56.40 + 50.60 + 45.60 + 54.60 + 53.30 = 260.50\n-----\n")));
        test("official men 10m champion", () -> check(real.displayResultsForEachEvent(
                "men 10m platform", false).contains("Full Name:YANG Hao\nRank:1\n"
                + "Score:86.40 + 99.75 + 81.60 + 102.60 + 86.40 + 107.30 = 564.05\n-----\n")));
        test("synchronised name order", () -> check(real.displayResultsForEachEvent(
                "women 3m synchronised", false).startsWith("Full Name:CHANG Yani & CHEN Yiwen\nRank:1\n")));
        test("three round rank alignment", () -> check(real.displayResultsForEachEvent(
                "women 10m platform", true).contains("Full Name:QUAN Hongchan\nRank:2 | 2 | 1\n")));
        test("rounds without semifinal", () -> check(real.displayResultsForEachEvent(
                "women 1m springboard", true).contains("Full Name:KOLOI Alysha\nRank:6 | * | 1\n")));
        test("synchronised final only", () -> check(real.displayResultsForEachEvent(
                "women 3m synchronised", true).startsWith("Full Name:CHANG Yani & CHEN Yiwen\n"
                + "Rank:* | * | 1\nPreliminary Score:*\nSemifinal Score:*\n")));
        test("all ten final events", () -> {
            equal(10, CoreModule.supportedEvents().size());
            for (String event : CoreModule.supportedEvents()) {
                check(real.displayResultsForEachEvent(event, false).startsWith("Full Name:"));
            }
        });
        test("case sensitive command", () -> equal("Error\n-----\n", DWASearch.executeOne("Players", real)));
        test("unrecognized command", () -> equal("Error\n-----\n", DWASearch.executeOne("resultwomen 1m springboard", real)));
        test("unknown event", () -> equal("N/A\n-----\n", DWASearch.executeOne("result women 10m springboard", real)));
        test("bad detail suffix", () -> equal("N/A\n-----\n", DWASearch.executeOne("result women 1m springboard details", real)));
        test("extra spaces", () -> equal("N/A\n-----\n", DWASearch.executeOne("result men 10m     synchronised", real)));
        test("blank line", () -> equal("Error\n-----\n", DWASearch.executeOne("", real)));
        test("multiline commands concatenate exactly", () -> equal("Error\n-----\nN/A\n-----\nError\n-----\n",
                DWASearch.execute(List.of("player", "result detail", "Players"), real)));
        test("LF only", () -> check(!DWASearch.executeOne("players", real).contains("\r")));
        test("malformed player data fails", () -> {
            try {
                new CoreModule(bytes("wrong"), bytes(""));
                throw new AssertionError("expected IOException");
            } catch (IOException expected) { check(expected.getMessage().contains("Malformed")); }
        });
        test("unknown API event fails clearly", () -> {
            try {
                real.displayResultsForEachEvent("unknown", false);
                throw new AssertionError("expected IllegalArgumentException");
            } catch (IllegalArgumentException expected) { check(expected.getMessage().contains("Unknown event")); }
        });
        test("fixture preserves first-stage order and missing rounds", () -> {
            CoreModule fixture = fixtureCore();
            String detail = fixture.displayResultsForEachEvent("women 10m platform", true);
            check(detail.indexOf("Full Name:ALPHA A") < detail.indexOf("Full Name:BETA B"));
            check(detail.contains("Full Name:ALPHA A\nRank:1 | * | 1\n"));
            check(detail.contains("Full Name:BETA B\nRank:2 | 1 | *\n"));
        });
        test("file input handles UTF-8 BOM and multiple commands", () -> {
            Path input = Files.createTempFile("dwa-input-", ".txt");
            Path output = Files.createTempFile("dwa-output-", ".txt");
            try {
                Files.writeString(input, "\uFEFFplayers\r\nresult women 1m springboard\r\n",
                        StandardCharsets.UTF_8);
                DWASearch.run(input, output, real);
                equal(DWASearch.execute(List.of("players", "result women 1m springboard"), real),
                        Files.readString(output, StandardCharsets.UTF_8));
            } finally {
                Files.deleteIfExists(input);
                Files.deleteIfExists(output);
            }
        });
        test("input and output may be the same file", () -> {
            Path file = Files.createTempFile("dwa-same-", ".txt");
            try {
                Files.writeString(file, "result women 1m springboard\n", StandardCharsets.UTF_8);
                DWASearch.run(file, file, real);
                equal(real.displayResultsForEachEvent("women 1m springboard", false),
                        Files.readString(file, StandardCharsets.UTF_8));
            } finally {
                Files.deleteIfExists(file);
            }
        });
        test("large command file streams all output", () -> {
            Path input = Files.createTempFile("dwa-large-input-", ".txt");
            Path output = Files.createTempFile("dwa-large-output-", ".txt");
            try {
                Files.writeString(input, "unknown\n".repeat(20_000), StandardCharsets.UTF_8);
                DWASearch.run(input, output, real);
                equal("Error\n-----\n".repeat(20_000),
                        Files.readString(output, StandardCharsets.UTF_8));
            } finally {
                Files.deleteIfExists(input);
                Files.deleteIfExists(output);
            }
        });
        test("failed input leaves existing output intact", () -> {
            Path output = Files.createTempFile("dwa-preserve-", ".txt");
            Path missing = output.resolveSibling(output.getFileName() + ".missing");
            try {
                Files.writeString(output, "previous", StandardCharsets.UTF_8);
                try {
                    DWASearch.run(missing, output, real);
                    throw new AssertionError("expected IOException");
                } catch (IOException expected) {
                    equal("previous", Files.readString(output, StandardCharsets.UTF_8));
                }
            } finally {
                Files.deleteIfExists(output);
            }
        });
        System.out.println("PASS " + passed + " tests");
    }

    private static CoreModule fixtureCore() throws IOException {
        String players = "ALPHA A\tFemale\tTestland\nBETA B\tFemale\tTestland\n";
        List<String> rows = new ArrayList<>();
        String[] codes = {"women-1m", "women-3m", "women-10m", "women-3m-synchronised",
                "women-10m-synchronised", "men-1m", "men-3m", "men-10m",
                "men-3m-synchronised", "men-10m-synchronised"};
        for (String code : codes) rows.add(code + "\tfinal\t1\tALPHA A\t10.00\t10.00");
        rows.add("women-10m\tpreliminary\t1\tALPHA A\t11.00\t11.00");
        rows.add("women-10m\tpreliminary\t2\tBETA B\t9.00\t9.00");
        rows.add("women-10m\tsemifinal\t1\tBETA B\t8.00\t8.00");
        return new CoreModule(bytes(players), bytes(String.join("\n", rows) + "\n"));
    }

    private static ByteArrayInputStream bytes(String text) {
        return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
    }
    private static int count(String text, String needle) {
        int n = 0, at = 0;
        while ((at = text.indexOf(needle, at)) >= 0) { n++; at += needle.length(); }
        return n;
    }
    private static void check(boolean condition) { if (!condition) throw new AssertionError(); }
    private static void equal(Object expected, Object actual) {
        if (!expected.equals(actual)) throw new AssertionError("expected " + expected + ", got " + actual);
    }
    private static void test(String name, Checked task) throws Exception {
        task.run(); passed++; System.out.println("PASS " + name);
    }
    private interface Checked { void run() throws Exception; }
}
