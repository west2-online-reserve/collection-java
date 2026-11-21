package scraper.analysis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataOrganizerTest {

    private static final String ATHLETES_JSON = """
            [
              {
                "CountryName": "Zed",
                "CountryCode": "ZZZ",
                "Participations": [
                  {
                    "PreferredLastName": "Zulu",
                    "PreferredFirstName": "Amy",
                    "Gender": 1
                  },
                  {
                    "PreferredLastName": "Alpha",
                    "PreferredFirstName": "Bob",
                    "Gender": 0
                  }
                ]
              },
              {
                "CountryName": "Alpha",
                "CountryCode": "AAA",
                "Participations": [
                  {
                    "PreferredLastName": "Alpha",
                    "PreferredFirstName": "Carol",
                    "Gender": 1
                  }
                ]
              }
            ]
            """;

    private static final String MEN_COMPETITION_JSON = """
            {
              "EventName": "Diving World Cup",
              "DisciplineStartDate": "2026-05-02",
              "DisciplineEndDate": "2026-05-03",
              "Id": "men-10m-synchronised",
              "Gender": "Men",
              "DisciplineName": "Men 10m Synchronised",
              "Heats": [
                {
                  "PhaseName": "Preliminary",
                  "Results": [
                    {
                      "FullName": "Ignored Preliminary",
                      "Rank": 1,
                      "NAT": "NON",
                      "TotalPoints": "999.00",
                      "Dives": [
                        {
                          "DiveOrder": 1,
                          "DivePoints": "999.00"
                        }
                      ]
                    }
                  ]
                },
                {
                  "PhaseName": "Finals",
                  "Results": [
                    {
                      "FullName": "Zulu Amy / Alpha Bob",
                      "Rank": 2,
                      "NAT": "ZZZ",
                      "TotalPoints": "190.50",
                      "Dives": [
                        {
                          "DiveOrder": 2,
                          "DivePoints": "100.25"
                        },
                        {
                          "DiveOrder": 1,
                          "DivePoints": "90.25"
                        }
                      ]
                    },
                    {
                      "FullName": "Charlie Delta & Echo Foxtrot",
                      "Rank": 1,
                      "NAT": "AAA",
                      "TotalPoints": "200.00",
                      "Dives": [
                        {
                          "DiveOrder": 1,
                          "DivePoints": "200.00"
                        }
                      ]
                    }
                  ]
                }
              ]
            }
            """;

    private static final String WOMEN_COMPETITION_JSON = """
            {
              "EventName": "Unused Competition Name",
              "DisciplineStartDate": "2026-04-30",
              "DisciplineEndDate": "2026-05-01",
              "Id": "women-3m-springboard",
              "Gender": "Women",
              "DisciplineName": "Women 3m Springboard",
              "Heats": [
                {
                  "PhaseName": "Finals",
                  "Results": [
                    {
                      "FullName": "Alpha Carol",
                      "Rank": 1,
                      "NAT": "AAA",
                      "TotalPoints": "300.00",
                      "Dives": [
                        {
                          "DiveOrder": 1,
                          "DivePoints": "150.00"
                        },
                        {
                          "DiveOrder": 2,
                          "DivePoints": "150.00"
                        }
                      ]
                    }
                  ]
                }
              ]
            }
            """;

    private static final String MIXED_COMPETITION_JSON = """
            {
              "EventName": "Diving World Cup",
              "DisciplineStartDate": "2026-05-01",
              "DisciplineEndDate": "2026-05-01",
              "Gender": "Mixed"
            }
            """;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void organizesAthletesCompetitionAndFinalEvents() throws Exception {
        String organizedJson = DataOrganizer.organize(
                ATHLETES_JSON,
                List.of(MEN_COMPETITION_JSON, WOMEN_COMPETITION_JSON, MIXED_COMPETITION_JSON)
        );
        JsonNode root = mapper.readTree(organizedJson);

        JsonNode competition = root.path("competition");
        JsonNode players = root.path("players");
        JsonNode events = root.path("events");

        assertAll(
                () -> assertEquals(1, root.path("schemaVersion").asInt()),
                () -> assertEquals("5019", competition.path("id").asText()),
                () -> assertEquals("Diving World Cup", competition.path("name").asText()),
                () -> assertEquals("2026-04-30", competition.path("from").asText()),
                () -> assertEquals("2026-05-03", competition.path("to").asText()),
                () -> assertEquals(3, players.size()),
                () -> assertEquals("Alpha Carol", players.get(0).path("fullName").asText()),
                () -> assertEquals("Female", players.get(0).path("gender").asText()),
                () -> assertEquals("Alpha", players.get(0).path("country").asText()),
                () -> assertEquals("AAA", players.get(0).path("countryCode").asText()),
                () -> assertEquals("Alpha Bob", players.get(1).path("fullName").asText()),
                () -> assertEquals("Male", players.get(1).path("gender").asText()),
                () -> assertEquals("Zulu Amy", players.get(2).path("fullName").asText()),
                () -> assertEquals(2, events.size())
        );

        JsonNode menEvent = events.get(0);
        assertAll(
                () -> assertEquals("result men 10m synchronised", menEvent.path("command").asText()),
                () -> assertEquals("men-10m-synchronised", menEvent.path("eventId").asText()),
                () -> assertEquals("Men", menEvent.path("gender").asText()),
                () -> assertEquals("10m", menEvent.path("discipline").asText()),
                () -> assertEquals("synchronised", menEvent.path("eventType").asText()),
                () -> assertEquals(2, menEvent.path("results").size()),
                () -> assertEquals(
                        "Charlie Delta & Echo Foxtrot",
                        menEvent.path("results").get(0).path("fullName").asText()
                ),
                () -> assertEquals(1, menEvent.path("results").get(0).path("rank").asInt()),
                () -> assertEquals(
                        "Alpha Bob & Zulu Amy",
                        menEvent.path("results").get(1).path("fullName").asText()
                ),
                () -> assertEquals(2, menEvent.path("results").get(1).path("rank").asInt()),
                () -> assertEquals(
                        "90.25",
                        menEvent.path("results").get(1).path("scores").get(0).asText()
                ),
                () -> assertEquals(
                        "100.25",
                        menEvent.path("results").get(1).path("scores").get(1).asText()
                ),
                () -> assertEquals(
                        "190.50",
                        menEvent.path("results").get(1).path("totalPoints").asText()
                )
        );

        JsonNode womenEvent = events.get(1);
        assertAll(
                () -> assertEquals("result women 3m springboard", womenEvent.path("command").asText()),
                () -> assertEquals("women-3m-springboard", womenEvent.path("eventId").asText()),
                () -> assertEquals("Women", womenEvent.path("gender").asText()),
                () -> assertEquals("3m", womenEvent.path("discipline").asText()),
                () -> assertEquals("springboard", womenEvent.path("eventType").asText()),
                () -> assertFalse(organizedJson.contains("Ignored Preliminary")),
                () -> assertFalse(organizedJson.contains("\"gender\" : \"Mixed\""))
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "not-json", "{}"})
    void rejectsMissingMalformedOrNonArrayAthletes(String athletesJson) {
        assertThrows(
                IllegalArgumentException.class,
                () -> DataOrganizer.organize(athletesJson, List.of(MEN_COMPETITION_JSON))
        );
    }

    @Test
    void rejectsMissingCompetitionList() {
        assertThrows(IllegalArgumentException.class, () -> DataOrganizer.organize(ATHLETES_JSON, null));
        assertThrows(IllegalArgumentException.class, () -> DataOrganizer.organize(ATHLETES_JSON, List.of()));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "not-json", "[]"})
    void rejectsMissingMalformedOrNonObjectCompetition(String competitionJson) {
        assertThrows(
                IllegalArgumentException.class,
                () -> DataOrganizer.organize(ATHLETES_JSON, Collections.singletonList(competitionJson))
        );
    }

    @Test
    void rejectsInvalidAthleteStructuresAndValues() {
        assertAll(
                () -> assertInvalidAthletes("""
                        [{"CountryName":"Alpha","CountryCode":"AAA"}]
                        """),
                () -> assertInvalidAthletes("""
                        [{
                          "CountryName":"Alpha",
                          "CountryCode":"AAA",
                          "Participations":[{
                            "PreferredLastName":"Alpha",
                            "PreferredFirstName":"Alice",
                            "Gender":"Female"
                          }]
                        }]
                        """),
                () -> assertInvalidAthletes("""
                        [{
                          "CountryName":"Alpha",
                          "CountryCode":"AAA",
                          "Participations":[{
                            "PreferredLastName":"Alpha",
                            "PreferredFirstName":"Alice",
                            "Gender":2
                          }]
                        }]
                        """),
                () -> assertInvalidAthletes("""
                        [{
                          "CountryName":"Alpha",
                          "CountryCode":"AAA",
                          "Participations":[{
                            "PreferredFirstName":"Alice",
                            "Gender":1
                          }]
                        }]
                        """)
        );
    }

    @Test
    void rejectsUnknownDisciplineTypesAndLevels() {
        String unknownType = competition("Men 1m Springboard", "Finals");
        String unknownLevel = competition("Men Springboard", "Finals");

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> DataOrganizer.organize(ATHLETES_JSON, List.of(unknownType))
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> DataOrganizer.organize(ATHLETES_JSON, List.of(unknownLevel))
                )
        );
    }

    @Test
    void rejectsInvalidHeatsResultsAndDives() {
        String missingHeats = competition("Men 10m Platform", null);
        String invalidFinalResults = competition("Men 10m Platform", "invalid");
        String invalidDives = finalCompetitionWithDives("{}");
        String missingRank = finalCompetitionWithResult("""
                {
                  "FullName": "Alpha Alice",
                  "NAT": "AAA",
                  "TotalPoints": "100.00",
                  "Dives": [{"DiveOrder":1,"DivePoints":"100.00"}]
                }
                """);

        assertAll(
                () -> assertInvalidCompetition(missingHeats),
                () -> assertInvalidCompetition(invalidFinalResults),
                () -> assertInvalidCompetition(invalidDives),
                () -> assertInvalidCompetition(missingRank)
        );
    }

    @Test
    void rejectsSynchronisedResultWithoutExactlyTwoAthletes() {
        String invalidPair = """
                {
                  "FullName": "Only One Athlete",
                  "Rank": 1,
                  "NAT": "AAA",
                  "TotalPoints": "100.00",
                  "Dives": [{"DiveOrder":1,"DivePoints":"100.00"}]
                }
                """;

        assertThrows(
                IllegalArgumentException.class,
                () -> DataOrganizer.organize(
                        ATHLETES_JSON,
                        List.of(finalSynchronisedCompetition(invalidPair))
                )
        );
    }

    @Test
    void outputIsStableAcrossRepeatedRuns() {
        List<String> competitions = List.of(MEN_COMPETITION_JSON, WOMEN_COMPETITION_JSON);

        String first = DataOrganizer.organize(ATHLETES_JSON, competitions);
        String second = DataOrganizer.organize(ATHLETES_JSON, competitions);

        assertEquals(first, second);
        assertTrue(first.contains("\"events\""));
    }

    private void assertInvalidAthletes(String athletesJson) {
        assertThrows(
                IllegalArgumentException.class,
                () -> DataOrganizer.organize(athletesJson, List.of(MEN_COMPETITION_JSON))
        );
    }

    private void assertInvalidCompetition(String competitionJson) {
        assertThrows(
                IllegalArgumentException.class,
                () -> DataOrganizer.organize(ATHLETES_JSON, List.of(competitionJson))
        );
    }

    private static String competition(String disciplineName, String resultsValue) {
        String heats = resultsValue == null
                ? ""
                : "\"Heats\":[{\"PhaseName\":\"Finals\",\"Results\":" + resultsValue + "}]";
        return """
                {
                  "EventName":"Diving World Cup",
                  "DisciplineStartDate":"2026-05-01",
                  "DisciplineEndDate":"2026-05-03",
                  "Id":"event-1",
                  "Gender":"Men",
                  "DisciplineName":"%s"
                  %s
                }
                """.formatted(disciplineName, heats.isBlank() ? "" : "," + heats);
    }

    private static String finalCompetitionWithDives(String divesJson) {
        return finalCompetitionWithResult("""
                {
                  "FullName": "Alpha Alice",
                  "Rank": 1,
                  "NAT": "AAA",
                  "TotalPoints": "100.00",
                  "Dives": %s
                }
                """.formatted(divesJson));
    }

    private static String finalCompetitionWithResult(String resultJson) {
        return """
                {
                  "EventName":"Diving World Cup",
                  "DisciplineStartDate":"2026-05-01",
                  "DisciplineEndDate":"2026-05-03",
                  "Id":"event-1",
                  "Gender":"Men",
                  "DisciplineName":"Men 10m Platform",
                  "Heats":[{"PhaseName":"Finals","Results":[%s]}]
                }
                """.formatted(resultJson);
    }

    private static String finalSynchronisedCompetition(String resultJson) {
        return """
                {
                  "EventName":"Diving World Cup",
                  "DisciplineStartDate":"2026-05-01",
                  "DisciplineEndDate":"2026-05-03",
                  "Id":"event-1",
                  "Gender":"Men",
                  "DisciplineName":"Men 10m Synchronised",
                  "Heats":[{"PhaseName":"Finals","Results":[%s]}]
                }
                """.formatted(resultJson);
    }
}
