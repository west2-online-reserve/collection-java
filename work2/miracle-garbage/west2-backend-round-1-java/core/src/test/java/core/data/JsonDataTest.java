package core.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonDataTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private JsonData data;

    @BeforeEach
    void setUp() throws Exception {
        String json = """
                {
                  "schemaVersion": 1,
                  "competition": {
                    "id": "5019",
                    "name": "Diving World Cup",
                    "from": "2026-05-01",
                    "to": "2026-05-03"
                  },
                  "players": [
                    {
                      "fullName": "ALPHA Alice",
                      "gender": "Female",
                      "country": "China",
                      "countryCode": "CHN"
                    }
                  ],
                  "events": [
                    {
                      "command": "result women 10m platform",
                      "eventId": "event-1",
                      "gender": "Women",
                      "discipline": "10m",
                      "eventType": "platform",
                      "results": [
                        {
                          "fullName": "ALPHA Alice",
                          "rank": 1,
                          "countryCode": "CHN",
                          "scores": [81.6, 86.4],
                          "totalPoints": 168.0
                        }
                      ]
                    }
                  ]
                }
                """;
        data = mapper.readValue(json, JsonData.class);
    }

    @Test
    void deserializesAllNestedData() {
        Competition competition = data.getCompetition();
        Player player = data.getPlayers().getFirst();
        Event event = data.getEvents().getFirst();
        Result result = event.getResults().getFirst();

        assertAll(
                () -> assertEquals(1, data.getSchemaVersion()),
                () -> assertEquals("5019", competition.getId()),
                () -> assertEquals("Diving World Cup", competition.getName()),
                () -> assertEquals("2026-05-01", competition.getFrom()),
                () -> assertEquals("2026-05-03", competition.getTo()),
                () -> assertEquals("ALPHA Alice", player.getFullName()),
                () -> assertEquals("Female", player.getGender()),
                () -> assertEquals("China", player.getCountry()),
                () -> assertEquals("CHN", player.getCountryCode()),
                () -> assertEquals("result women 10m platform", event.getCommand()),
                () -> assertEquals("event-1", event.getEventId()),
                () -> assertEquals("Women", event.getGender()),
                () -> assertEquals("10m", event.getDiscipline()),
                () -> assertEquals("platform", event.getEventType()),
                () -> assertEquals(List.of(81.6, 86.4), result.getScores()),
                () -> assertEquals(168.0, result.getTotalPoints())
        );
    }

    @Test
    void printAllHandlesNullNestedCollections() {
        JsonData emptyData = new JsonData();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            emptyData.printAll();
        } finally {
            System.setOut(originalOut);
        }

        String text = output.toString(StandardCharsets.UTF_8);
        assertAll(
                () -> assertTrue(text.contains("schemaVersion=0")),
                () -> assertTrue(text.contains("players: 0")),
                () -> assertTrue(text.contains("events: 0"))
        );
    }

    @Test
    void nestedPrintMethodsIncludeAllFields() {
        Player player = data.getPlayers().getFirst();
        Result result = data.getEvents().getFirst().getResults().getFirst();
        Event event = data.getEvents().getFirst();
        Competition competition = data.getCompetition();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            player.printAll();
            result.printAll();
            event.printAll();
            competition.printAll();
        } finally {
            System.setOut(originalOut);
        }

        String text = output.toString(StandardCharsets.UTF_8);
        assertAll(
                () -> assertTrue(text.contains("Player{fullName=ALPHA Alice")),
                () -> assertTrue(text.contains("Result{fullName=ALPHA Alice, rank=1")),
                () -> assertTrue(text.contains("Event{command=result women 10m platform")),
                () -> assertTrue(text.contains("Competition{id=5019")),
                () -> assertNotNull(player)
        );
    }
}
