package core.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.data.Player;
import core.data.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataOutputTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private Player player;
    private Result result;

    @BeforeEach
    void setUp() throws Exception {
        player = mapper.readValue("""
                {
                  "fullName": "ALPHA Alice",
                  "gender": "Female",
                  "country": "China",
                  "countryCode": "CHN"
                }
                """, Player.class);
        result = mapper.readValue("""
                {
                  "fullName": "ALPHA Alice",
                  "rank": 1,
                  "countryCode": "CHN",
                  "scores": [81.6, 86.4, 77.0],
                  "totalPoints": 245.0
                }
                """, Result.class);
    }

    @Test
    void outputsDefaultAndCustomSeparatorLines() {
        assertEquals("-----\n", DataOutput.outputLine());
        assertEquals("----------\n", DataOutput.outputLine(10));
        assertEquals("\n", DataOutput.outputLine(0));
    }

    @Test
    void outputsPlayerFields() {
        assertEquals("Full Name:ALPHA Alice\n", DataOutput.outputPlayerName(player));
        assertEquals("Gender:Female\n", DataOutput.outputPlayerGender(player));
        assertEquals("Country:China\n", DataOutput.outputPlayerCountry(player));
    }

    @Test
    void outputsResultPlayerName() {
        assertEquals("Full Name:ALPHA Alice\n", DataOutput.outputResultPlayerName(result));
    }

    @Test
    void formatsEveryDiveAndTotalToTwoDecimalPlaces() {
        result.getScores().add(9.876);

        assertEquals(
                "81.60 + 86.40 + 77.00 + 9.88 = 245.00\n",
                DataOutput.outputResultPlayerScore(result)
        );
    }

    @Test
    void outputsScoreLineWithoutDives() throws Exception {
        Result emptyResult = mapper.readValue("""
                {
                  "fullName": "ALPHA Alice",
                  "rank": 1,
                  "countryCode": "CHN",
                  "scores": [],
                  "totalPoints": 0.0
                }
                """, Result.class);

        assertEquals(" = 0.00\n", DataOutput.outputResultPlayerScore(emptyResult));
    }
}
