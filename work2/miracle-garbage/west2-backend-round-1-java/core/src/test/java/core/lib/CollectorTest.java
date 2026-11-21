package core.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.data.Event;
import core.data.JsonData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class CollectorTest {

    private JsonData data;

    @BeforeEach
    void setUp() throws Exception {
        String json = """
                {
                  "events": [
                    {
                      "command": "result men 10m platform",
                      "eventId": "platform",
                      "gender": "Men",
                      "discipline": "10m",
                      "eventType": "platform",
                      "results": []
                    },
                    {
                      "command": "result women 3m springboard",
                      "eventId": "springboard",
                      "gender": "Women",
                      "discipline": "3m",
                      "eventType": "springboard",
                      "results": []
                    }
                  ]
                }
                """;
        data = new ObjectMapper().readValue(json, JsonData.class);
    }

    @Test
    void findsEventUsingCaseInsensitiveStoredValues() {
        Event event = Collector.findEvent(data, "men", "10m", "platform");

        assertSame(data.getEvents().getFirst(), event);
        assertEquals("platform", event.getEventId());
    }

    @Test
    void findsSecondEventWhenFirstDoesNotMatch() {
        Event event = Collector.findEvent(data, "women", "3m", "springboard");

        assertSame(data.getEvents().get(1), event);
    }

    @ParameterizedTest
    @CsvSource({
            "women, 10m, platform",
            "men, 3m, platform",
            "men, 10m, springboard",
            "unknown, 10m, platform"
    })
    void returnsNullForAnyMismatchedCriterion(String gender, String discipline, String eventType) {
        assertNull(Collector.findEvent(data, gender, discipline, eventType));
    }
}
