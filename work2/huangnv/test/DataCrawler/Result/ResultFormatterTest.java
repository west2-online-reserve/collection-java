package DataCrawler.Result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultFormatterTest {
    private static final String SINGLE_FINAL_JSON =
            "{"
                    + "\"Heats\":["
                    + "{"
                    + "\"PhaseName\":\"Preliminaries\","
                    + "\"Results\":["
                    + "{"
                    + "\"PersonId\":\"def10055-0632-4178-8f19-b37cf4acd933\","
                    + "\"FullName\":\"FUNG Katelyn\","
                    + "\"Rank\":1,"
                    + "\"TotalPoints\":\"298.20\","
                    + "\"Dives\":["
                    + "{\"DiveOrder\":1,\"DivePoints\":\"60.20\",\"TotalPoints\":\"60.20\"},"
                    + "{\"DiveOrder\":2,\"DivePoints\":\"56.00\",\"TotalPoints\":\"116.20\"},"
                    + "{\"DiveOrder\":3,\"DivePoints\":\"57.60\",\"TotalPoints\":\"173.80\"},"
                    + "{\"DiveOrder\":4,\"DivePoints\":\"54.00\",\"TotalPoints\":\"227.80\"},"
                    + "{\"DiveOrder\":5,\"DivePoints\":\"70.40\",\"TotalPoints\":\"298.20\"}"
                    + "]"
                    + "}"
                    + "]"
                    + "},"
                    + "{"
                    + "\"PhaseName\":\"Semifinals\","
                    + "\"Results\":["
                    + "{"
                    + "\"PersonId\":\"d230b899-c7e6-44c7-83b4-e39f8a15eb7d\","
                    + "\"FullName\":\"PRAASTERINK Else\","
                    + "\"Rank\":3,"
                    + "\"TotalPoints\":\"295.25\","
                    + "\"Dives\":["
                    + "{\"DiveOrder\":1,\"DivePoints\":\"65.25\",\"TotalPoints\":\"65.25\"},"
                    + "{\"DiveOrder\":2,\"DivePoints\":\"63.00\",\"TotalPoints\":\"128.25\"},"
                    + "{\"DiveOrder\":3,\"DivePoints\":\"49.50\",\"TotalPoints\":\"177.75\"},"
                    + "{\"DiveOrder\":4,\"DivePoints\":\"62.40\",\"TotalPoints\":\"240.15\"},"
                    + "{\"DiveOrder\":5,\"DivePoints\":\"55.10\",\"TotalPoints\":\"295.25\"}"
                    + "]"
                    + "}"
                    + "]"
                    + "},"
                    + "{"
                    + "\"PhaseName\":\"Finals\","
                    + "\"Results\":["
                    + "{"
                    + "\"PersonId\":\"d230b899-c7e6-44c7-83b4-e39f8a15eb7d\","
                    + "\"FullName\":\"PRAASTERINK Else\","
                    + "\"Rank\":1,"
                    + "\"TotalPoints\":\"308.80\","
                    + "\"Dives\":["
                    + "{\"DiveOrder\":1,\"DivePoints\":\"62.35\",\"TotalPoints\":\"62.35\"},"
                    + "{\"DiveOrder\":2,\"DivePoints\":\"65.80\",\"TotalPoints\":\"128.15\"},"
                    + "{\"DiveOrder\":3,\"DivePoints\":\"54.00\",\"TotalPoints\":\"182.15\"},"
                    + "{\"DiveOrder\":4,\"DivePoints\":\"67.20\",\"TotalPoints\":\"249.35\"},"
                    + "{\"DiveOrder\":5,\"DivePoints\":\"59.45\",\"TotalPoints\":\"308.80\"}"
                    + "]"
                    + "},"
                    + "{"
                    + "\"PersonId\":\"def10055-0632-4178-8f19-b37cf4acd933\","
                    + "\"FullName\":\"FUNG Katelyn\","
                    + "\"Rank\":2,"
                    + "\"TotalPoints\":\"297.00\","
                    + "\"Dives\":["
                    + "{\"DiveOrder\":1,\"DivePoints\":\"58.80\",\"TotalPoints\":\"58.80\"},"
                    + "{\"DiveOrder\":2,\"DivePoints\":\"54.60\",\"TotalPoints\":\"113.40\"},"
                    + "{\"DiveOrder\":3,\"DivePoints\":\"57.60\",\"TotalPoints\":\"171.00\"},"
                    + "{\"DiveOrder\":4,\"DivePoints\":\"54.00\",\"TotalPoints\":\"225.00\"},"
                    + "{\"DiveOrder\":5,\"DivePoints\":\"72.00\",\"TotalPoints\":\"297.00\"}"
                    + "]"
                    + "},"
                    + "{"
                    + "\"PersonId\":\"45101f00-7ed3-4aa9-acfa-c54b4e1f0ac5\","
                    + "\"FullName\":\"ANTOLINO Valeria\","
                    + "\"Rank\":3,"
                    + "\"TotalPoints\":\"292.10\","
                    + "\"Dives\":["
                    + "{\"DiveOrder\":1,\"DivePoints\":\"49.50\",\"TotalPoints\":\"49.50\"},"
                    + "{\"DiveOrder\":2,\"DivePoints\":\"52.80\",\"TotalPoints\":\"102.30\"},"
                    + "{\"DiveOrder\":3,\"DivePoints\":\"52.20\",\"TotalPoints\":\"154.50\"},"
                    + "{\"DiveOrder\":4,\"DivePoints\":\"75.20\",\"TotalPoints\":\"229.70\"},"
                    + "{\"DiveOrder\":5,\"DivePoints\":\"62.40\",\"TotalPoints\":\"292.10\"}"
                    + "]"
                    + "}"
                    + "]"
                    + "}"
                    + "]"
                    + "}";

    private static final String SYNCHRONISED_FINAL_JSON =
            "{"
                    + "\"Heats\":["
                    + "{"
                    + "\"PhaseName\":\"Finals\","
                    + "\"Results\":["
                    + "{"
                    + "\"Rank\":1,"
                    + "\"TotalPoints\":\"401.55\","
                    + "\"Dives\":["
                    + "{\"DiveOrder\":1,\"DivePoints\":\"47.40\",\"TotalPoints\":\"47.40\"},"
                    + "{\"DiveOrder\":2,\"DivePoints\":\"46.80\",\"TotalPoints\":\"94.20\"},"
                    + "{\"DiveOrder\":3,\"DivePoints\":\"79.80\",\"TotalPoints\":\"174.00\"},"
                    + "{\"DiveOrder\":4,\"DivePoints\":\"70.35\",\"TotalPoints\":\"244.35\"},"
                    + "{\"DiveOrder\":5,\"DivePoints\":\"78.54\",\"TotalPoints\":\"322.89\"},"
                    + "{\"DiveOrder\":6,\"DivePoints\":\"78.66\",\"TotalPoints\":\"401.55\"}"
                    + "],"
                    + "\"Competitors\":["
                    + "{\"FirstName\":\"Moritz\",\"LastName\":\"WESEMANN\"},"
                    + "{\"FirstName\":\"Alexander\",\"LastName\":\"LUBE\"}"
                    + "]"
                    + "},"
                    + "{"
                    + "\"Rank\":2,"
                    + "\"TotalPoints\":\"370.32\","
                    + "\"Dives\":["
                    + "{\"DiveOrder\":1,\"DivePoints\":\"47.40\",\"TotalPoints\":\"47.40\"},"
                    + "{\"DiveOrder\":2,\"DivePoints\":\"46.20\",\"TotalPoints\":\"93.60\"},"
                    + "{\"DiveOrder\":3,\"DivePoints\":\"72.00\",\"TotalPoints\":\"165.60\"},"
                    + "{\"DiveOrder\":4,\"DivePoints\":\"64.17\",\"TotalPoints\":\"229.77\"},"
                    + "{\"DiveOrder\":5,\"DivePoints\":\"64.05\",\"TotalPoints\":\"293.82\"},"
                    + "{\"DiveOrder\":6,\"DivePoints\":\"76.50\",\"TotalPoints\":\"370.32\"}"
                    + "],"
                    + "\"Competitors\":["
                    + "{\"FirstName\":\"Adrian\",\"LastName\":\"ABADIA\"},"
                    + "{\"FirstName\":\"Nicolas\",\"LastName\":\"GARCIA BOISSIER\"}"
                    + "]"
                    + "}"
                    + "]"
                    + "}"
                    + "]"
                    + "}";

    @Test
    public void formatFinalResultsOrdersAndPairs() {
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatFinalResults(SINGLE_FINAL_JSON);
        String expected =
                "Full Name:PRAASTERINK Else\n" +
                "Rank:1\n" +
                "Score:62.35 + 65.80 + 54.00 + 67.20 + 59.45 = 308.80\n" +
                "-----\n" +
                "Full Name:FUNG Katelyn\n" +
                "Rank:2\n" +
                "Score:58.80 + 54.60 + 57.60 + 54.00 + 72.00 = 297.00\n" +
                "-----\n" +
                "Full Name:ANTOLINO Valeria\n" +
                "Rank:3\n" +
                "Score:49.50 + 52.80 + 52.20 + 75.20 + 62.40 = 292.10\n" +
                "-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void formatFinalResultsSynchronisedSortsNames() {
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatFinalResults(SYNCHRONISED_FINAL_JSON);
        String expected =
                "Full Name:LUBE Alexander & WESEMANN Moritz\n" +
                "Rank:1\n" +
                "Score:47.40 + 46.80 + 79.80 + 70.35 + 78.54 + 78.66 = 401.55\n" +
                "-----\n" +
                "Full Name:ABADIA Adrian & GARCIA BOISSIER Nicolas\n" +
                "Rank:2\n" +
                "Score:47.40 + 46.20 + 72.00 + 64.17 + 64.05 + 76.50 = 370.32\n" +
                "-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void formatDetailedResultsAllPhases() {
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatDetailedResults(SINGLE_FINAL_JSON);
        String expected =
                "Full Name:FUNG Katelyn\n" +
                "Rank:1 | * | 2\n" +
                "Preliminary Score:60.20 + 56.00 + 57.60 + 54.00 + 70.40 = 298.20\n" +
                "Semifinal Score:*\n" +
                "Final Score:58.80 + 54.60 + 57.60 + 54.00 + 72.00 = 297.00\n" +
                "-----\n" +
                "Full Name:PRAASTERINK Else\n" +
                "Rank:* | 3 | 1\n" +
                "Preliminary Score:*\n" +
                "Semifinal Score:65.25 + 63.00 + 49.50 + 62.40 + 55.10 = 295.25\n" +
                "Final Score:62.35 + 65.80 + 54.00 + 67.20 + 59.45 = 308.80\n" +
                "-----\n" +
                "Full Name:ANTOLINO Valeria\n" +
                "Rank:* | * | 3\n" +
                "Preliminary Score:*\n" +
                "Semifinal Score:*\n" +
                "Final Score:49.50 + 52.80 + 52.20 + 75.20 + 62.40 = 292.10\n" +
                "-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void formatDetailedResultsSynchronisedFinalOnly() {
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatDetailedResults(SYNCHRONISED_FINAL_JSON);
        String expected =
                "Full Name:LUBE Alexander & WESEMANN Moritz\n" +
                "Rank:* | * | 1\n" +
                "Preliminary Score:*\n" +
                "Semifinal Score:*\n" +
                "Final Score:47.40 + 46.80 + 79.80 + 70.35 + 78.54 + 78.66 = 401.55\n" +
                "-----\n" +
                "Full Name:ABADIA Adrian & GARCIA BOISSIER Nicolas\n" +
                "Rank:* | * | 2\n" +
                "Preliminary Score:*\n" +
                "Semifinal Score:*\n" +
                "Final Score:47.40 + 46.20 + 72.00 + 64.17 + 64.05 + 76.50 = 370.32\n" +
                "-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void formatFinalResultsReturnsEmptyWhenNoFinals() {
        String json =
                "{"
                        + "\"Heats\":["
                        + "{"
                        + "\"PhaseName\":\"Preliminaries\","
                        + "\"Results\":[]"
                        + "}"
                        + "]"
                        + "}";
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatFinalResults(json);
        assertEquals("", output);
    }

    @Test
    public void formatFinalResultsDivesMissingYieldsStar() {
        String json =
                "{"
                        + "\"Heats\":["
                        + "{"
                        + "\"PhaseName\":\"Finals\","
                        + "\"Results\":["
                        + "{"
                        + "\"FullName\":\"TEST A\","
                        + "\"Rank\":1,"
                        + "\"Dives\":[]"
                        + "}"
                        + "]"
                        + "}"
                        + "]"
                        + "}";
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatFinalResults(json);
        String expected =
                "Full Name:TEST A\n" +
                "Rank:1\n" +
                "Score:*\n" +
                "-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void formatFinalResultsUsesLastDiveTotalWhenTotalMissing() {
        String json =
                "{"
                        + "\"Heats\":["
                        + "{"
                        + "\"PhaseName\":\"Finals\","
                        + "\"Results\":["
                        + "{"
                        + "\"FullName\":\"TEST B\","
                        + "\"Rank\":1,"
                        + "\"Dives\":["
                        + "{\"DiveOrder\":1,\"DivePoints\":\"10\",\"TotalPoints\":\"10\"},"
                        + "{\"DiveOrder\":2,\"DivePoints\":\"20\",\"TotalPoints\":\"30\"}"
                        + "]"
                        + "}"
                        + "]"
                        + "}"
                        + "]"
                        + "}";
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatFinalResults(json);
        String expected =
                "Full Name:TEST B\n" +
                "Rank:1\n" +
                "Score:10 + 20 = 30\n" +
                "-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void formatFinalResultsFallsBackToLastFirstName() {
        String json =
                "{"
                        + "\"Heats\":["
                        + "{"
                        + "\"PhaseName\":\"Finals\","
                        + "\"Results\":["
                        + "{"
                        + "\"LastName\":\"DOE\","
                        + "\"FirstName\":\"Jane\","
                        + "\"Rank\":1,"
                        + "\"TotalPoints\":\"5.00\","
                        + "\"Dives\":["
                        + "{\"DiveOrder\":1,\"DivePoints\":\"5.00\",\"TotalPoints\":\"5.00\"}"
                        + "]"
                        + "}"
                        + "]"
                        + "}"
                        + "]"
                        + "}";
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatFinalResults(json);
        String expected =
                "Full Name:DOE Jane\n" +
                "Rank:1\n" +
                "Score:5.00 = 5.00\n" +
                "-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void formatDetailedResultsRankMissingUsesStar() {
        String json =
                "{"
                        + "\"Heats\":["
                        + "{"
                        + "\"PhaseName\":\"Preliminaries\","
                        + "\"Results\":["
                        + "{"
                        + "\"PersonId\":\"p1\","
                        + "\"FullName\":\"TEST C\","
                        + "\"Dives\":["
                        + "{\"DiveOrder\":1,\"DivePoints\":\"11.00\",\"TotalPoints\":\"11.00\"}"
                        + "]"
                        + "}"
                        + "]"
                        + "}"
                        + "]"
                        + "}";
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatDetailedResults(json);
        String expected =
                "Full Name:TEST C\n" +
                "Rank:* | * | *\n" +
                "Preliminary Score:11.00 = 11.00\n" +
                "Semifinal Score:*\n" +
                "Final Score:*\n" +
                "-----\n";
        assertEquals(expected, output);
    }

    @Test
    public void formatDetailedResultsSemifinalOnly() {
        String json =
                "{"
                        + "\"Heats\":["
                        + "{"
                        + "\"PhaseName\":\"Semifinals\","
                        + "\"Results\":["
                        + "{"
                        + "\"PersonId\":\"p2\","
                        + "\"FullName\":\"TEST D\","
                        + "\"Rank\":2,"
                        + "\"Dives\":["
                        + "{\"DiveOrder\":1,\"DivePoints\":\"12.00\",\"TotalPoints\":\"12.00\"}"
                        + "]"
                        + "}"
                        + "]"
                        + "}"
                        + "]"
                        + "}";
        ResultFormatter formatter = new ResultFormatter();
        String output = formatter.formatDetailedResults(json);
        String expected =
                "Full Name:TEST D\n" +
                "Rank:* | 2 | *\n" +
                "Preliminary Score:*\n" +
                "Semifinal Score:12.00 = 12.00\n" +
                "Final Score:*\n" +
                "-----\n";
        assertEquals(expected, output);
    }
}
