package DataCrawler.Athlete;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AthleteFormatterTest {
    private static String SMALL_ATHLETES_JSON;

    @BeforeAll
    static void loadAthletesJson() throws Exception {
        SMALL_ATHLETES_JSON = "[{\"CountryName\":\"Austria\",\"CountryCode\":\"AUT\",\"Participations\":["
                + "{\"ResultId\":1057040,\"Gender\":0,\"PreferredLastName\":\"HART\",\"PreferredFirstName\":\"Alexander\"},"
                + "{\"ResultId\":1285434,\"Gender\":0,\"PreferredLastName\":\"LOTFI\",\"PreferredFirstName\":\"Dariush\"}"
                + "]},"
                + "{\"CountryName\":\"Canada\",\"CountryCode\":\"CAN\",\"Participations\":["
                + "{\"ResultId\":1018482,\"Gender\":1,\"PreferredLastName\":\"BELANGER\",\"PreferredFirstName\":\"Eloise\"}"
                + "]}]";
    }

    @Test
    public void loadsFixture() {
        assertFalse(SMALL_ATHLETES_JSON == null || SMALL_ATHLETES_JSON.isEmpty());
    }

    @Test
    public void formatAllPlayers() {
        AthleteFormatter formatter = new AthleteFormatter();
        String output = formatter.formatAllPlayers(SMALL_ATHLETES_JSON);
        assertFalse(output.isEmpty());
        String expected =
                "Full Name:HART Alexander\n" +
                "Gender:Male\n" +
                "Country:Austria\n" +
                "-----\n" +
                "Full Name:LOTFI Dariush\n" +
                "Gender:Male\n" +
                "Country:Austria\n" +
                "-----\n" +
                "Full Name:BELANGER Eloise\n" +
                "Gender:Female\n" +
                "Country:Canada\n" +
                "-----\n";
        assertEquals(expected, output);

    }
    @Test
    public void emptyFormatAllPlayers() {
        AthleteFormatter formatter = new AthleteFormatter();
        String output = formatter.formatAllPlayers(null);
        assertTrue(output.isEmpty());
    }
}
