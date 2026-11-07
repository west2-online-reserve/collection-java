package scraper.analysis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts the raw World Aquatics responses into the data format used by test_data.
 *
 * @author Codex
 * @date 2026-11-07
 */
public final class DataOrganizer {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Pattern PAIR_SEPARATOR = Pattern.compile("\\s*(?:/|&)\\s*");
    private static final Pattern DISCIPLINE_NAME_PATTERN = Pattern.compile(
            "\\b(3m|10m)\\b", Pattern.CASE_INSENSITIVE);

    private static final int SCHEMA_VERSION = 1;
    private static final int FEMALE_GENDER_CODE = 1;
    private static final int MALE_GENDER_CODE = 0;
    private static final String COMPETITION_ID = "5019";
    private static final String FINAL_PHASE_NAME = "Finals";
    private static final String MIXED_GENDER = "Mixed";
    private static final String PAIR_JOINER = " & ";

    private static final String PLATFORM = "platform";
    private static final String SPRINGBOARD = "springboard";
    private static final String SYNCHRONISED = "synchronised";

    private static final String COMMAND_PREFIX = "result";
    private static final String COUNTRY_NAME = "CountryName";
    private static final String COUNTRY_CODE = "CountryCode";
    private static final String PARTICIPATIONS = "Participations";
    private static final String PREFERRED_LAST_NAME = "PreferredLastName";
    private static final String PREFERRED_FIRST_NAME = "PreferredFirstName";
    private static final String GENDER = "Gender";
    private static final String HEATS = "Heats";
    private static final String PHASE_NAME = "PhaseName";
    private static final String RESULTS = "Results";
    private static final String FULL_NAME = "FullName";
    private static final String RANK = "Rank";
    private static final String NATION = "NAT";
    private static final String TOTAL_POINTS = "TotalPoints";
    private static final String DIVES = "Dives";
    private static final String DIVE_ORDER = "DiveOrder";
    private static final String DIVE_POINTS = "DivePoints";

    private DataOrganizer() {
    }

    /**
     * Organizes raw athlete and discipline JSON into one JSON string.
     *
     * @param athletesJson JSON returned by the competition athletes endpoint
     * @param competitionJsonList JSON returned by every discipline endpoint
     * @return formatted JSON matching the test_data data structure
     * @throws IllegalArgumentException if an input is missing or malformed
     * @throws IllegalStateException if the organized data cannot be serialized
     */
    public static String organize(String athletesJson, List<String> competitionJsonList) {
        JsonNode athletesRoot = parseJsonObjectArray(athletesJson, "athletes");
        List<JsonNode> disciplineRoots = parseCompetitionJsonList(competitionJsonList);

        ObjectNode root = MAPPER.createObjectNode();
        root.put("schemaVersion", SCHEMA_VERSION);
        root.set("competition", buildCompetition(disciplineRoots));
        root.set("players", buildPlayers(athletesRoot));
        root.set("events", buildEvents(disciplineRoots));

        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Unable to serialize organized data", exception);
        }
    }

    private static List<JsonNode> parseCompetitionJsonList(List<String> competitionJsonList) {
        if (competitionJsonList == null || competitionJsonList.isEmpty()) {
            throw new IllegalArgumentException("competitionJsonList must not be empty");
        }

        List<JsonNode> disciplineRoots = new ArrayList<>(competitionJsonList.size());
        for (String competitionJson : competitionJsonList) {
            disciplineRoots.add(parseJsonObject(competitionJson, "competition"));
        }
        return disciplineRoots;
    }

    private static JsonNode parseJsonObjectArray(String json, String sourceName) {
        JsonNode root = parseJson(json, sourceName);
        if (!root.isArray()) {
            throw new IllegalArgumentException(sourceName + " JSON must be an array");
        }
        return root;
    }

    private static JsonNode parseJsonObject(String json, String sourceName) {
        JsonNode root = parseJson(json, sourceName);
        if (!root.isObject()) {
            throw new IllegalArgumentException(sourceName + " JSON must be an object");
        }
        return root;
    }

    private static JsonNode parseJson(String json, String sourceName) {
        if (json == null || json.isBlank()) {
            throw new IllegalArgumentException(sourceName + " JSON must not be blank");
        }

        try {
            JsonNode root = MAPPER.readTree(json);
            if (root == null || root.isMissingNode()) {
                throw new IllegalArgumentException(sourceName + " JSON has no content");
            }
            return root;
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException(sourceName + " JSON is malformed", exception);
        }
    }

    private static ObjectNode buildCompetition(List<JsonNode> disciplineRoots) {
        String competitionName = null;
        String startDate = null;
        String endDate = null;

        for (JsonNode disciplineRoot : disciplineRoots) {
            if (competitionName == null) {
                competitionName = requiredText(disciplineRoot, "EventName", "competition");
            }

            String disciplineStartDate = requiredText(
                    disciplineRoot, "DisciplineStartDate", "competition");
            String disciplineEndDate = requiredText(
                    disciplineRoot, "DisciplineEndDate", "competition");
            if (startDate == null || disciplineStartDate.compareTo(startDate) < 0) {
                startDate = disciplineStartDate;
            }
            if (endDate == null || disciplineEndDate.compareTo(endDate) > 0) {
                endDate = disciplineEndDate;
            }
        }

        ObjectNode competition = MAPPER.createObjectNode();
        competition.put("id", COMPETITION_ID);
        competition.put("name", competitionName);
        competition.put("from", startDate);
        competition.put("to", endDate);
        return competition;
    }

    private static ArrayNode buildPlayers(JsonNode athletesRoot) {
        List<ObjectNode> playerNodes = new ArrayList<>(athletesRoot.size());
        for (JsonNode countryNode : athletesRoot) {
            String country = requiredText(countryNode, COUNTRY_NAME, "athletes");
            String countryCode = requiredText(countryNode, COUNTRY_CODE, "athletes");
            JsonNode participations = countryNode.path(PARTICIPATIONS);
            if (!participations.isArray()) {
                throw new IllegalArgumentException("athletes JSON has invalid Participations");
            }

            for (JsonNode participation : participations) {
                String lastName = requiredText(participation, PREFERRED_LAST_NAME, "athletes");
                String firstName = requiredText(participation, PREFERRED_FIRST_NAME, "athletes");
                ObjectNode player = MAPPER.createObjectNode();
                player.put("fullName", lastName + " " + firstName);
                player.put("gender", parseGender(participation.path(GENDER)));
                player.put("country", country);
                player.put("countryCode", countryCode);
                playerNodes.add(player);
            }
        }

        playerNodes.sort(Comparator
                .comparing((ObjectNode player) -> player.path("country").asText())
                .thenComparing(player -> player.path("fullName").asText()));
        ArrayNode players = MAPPER.createArrayNode();
        players.addAll(playerNodes);
        return players;
    }

    private static String parseGender(JsonNode genderNode) {
        if (!genderNode.isInt()) {
            throw new IllegalArgumentException("athletes JSON has invalid Gender");
        }

        int genderCode = genderNode.asInt();
        if (genderCode == FEMALE_GENDER_CODE) {
            return "Female";
        }
        if (genderCode == MALE_GENDER_CODE) {
            return "Male";
        }
        throw new IllegalArgumentException("athletes JSON has unknown Gender: " + genderCode);
    }

    private static ArrayNode buildEvents(List<JsonNode> disciplineRoots) {
        List<ObjectNode> eventNodes = new ArrayList<>(disciplineRoots.size());
        for (JsonNode disciplineRoot : disciplineRoots) {
            String gender = requiredText(disciplineRoot, GENDER, "competition");
            if (MIXED_GENDER.equalsIgnoreCase(gender)) {
                continue;
            }

            String disciplineName = requiredText(
                    disciplineRoot, "DisciplineName", "competition");
            String discipline = resolveDiscipline(disciplineName);
            String eventType = resolveEventType(disciplineName);
            String command = String.join(" ",
                    COMMAND_PREFIX,
                    gender.toLowerCase(Locale.ROOT),
                    discipline.toLowerCase(Locale.ROOT),
                    eventType);

            ObjectNode event = MAPPER.createObjectNode();
            event.put("command", command);
            event.put("eventId", requiredText(disciplineRoot, "Id", "competition"));
            event.put("gender", gender);
            event.put("discipline", discipline);
            event.put("eventType", eventType);
            event.set("results", buildResults(disciplineRoot, eventType));
            eventNodes.add(event);
        }

        eventNodes.sort(Comparator.comparing(event -> event.path("command").asText()));
        ArrayNode events = MAPPER.createArrayNode();
        events.addAll(eventNodes);
        return events;
    }

    private static String resolveEventType(String disciplineName) {
        String normalizedName = disciplineName.toLowerCase(Locale.ROOT);
        if (normalizedName.contains(PLATFORM)) {
            return PLATFORM;
        }
        if (normalizedName.contains(SPRINGBOARD)) {
            return SPRINGBOARD;
        }
        if (normalizedName.contains(SYNCHRONISED)) {
            return SYNCHRONISED;
        }
        throw new IllegalArgumentException("Unknown discipline type: " + disciplineName);
    }

    private static String resolveDiscipline(String disciplineName) {
        Matcher matcher = DISCIPLINE_NAME_PATTERN.matcher(disciplineName);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Unknown discipline: " + disciplineName);
        }
        return matcher.group(1).toLowerCase(Locale.ROOT);
    }

    private static ArrayNode buildResults(JsonNode disciplineRoot, String eventType) {
        List<JsonNode> resultNodes = new ArrayList<>();
        JsonNode heats = disciplineRoot.path(HEATS);
        if (!heats.isArray()) {
            throw new IllegalArgumentException("competition JSON has invalid Heats");
        }

        for (JsonNode heat : heats) {
            if (!FINAL_PHASE_NAME.equals(heat.path(PHASE_NAME).asText())) {
                continue;
            }
            JsonNode heatResults = heat.path(RESULTS);
            if (!heatResults.isArray()) {
                throw new IllegalArgumentException("competition JSON has invalid Results");
            }
            for (JsonNode result : heatResults) {
                resultNodes.add(result);
            }
        }

        resultNodes.sort(Comparator.comparingInt(result -> result.path(RANK).asInt()));
        ArrayNode results = MAPPER.createArrayNode();
        for (JsonNode result : resultNodes) {
            ObjectNode organizedResult = MAPPER.createObjectNode();
            organizedResult.put("fullName", organizeFullName(
                    requiredText(result, FULL_NAME, "result"), eventType));
            organizedResult.put("rank", requiredInt(result, RANK, "result"));
            organizedResult.put("countryCode", requiredText(result, NATION, "result"));
            organizedResult.set("scores", buildScores(result.path(DIVES)));
            organizedResult.put("totalPoints", requiredText(result, TOTAL_POINTS, "result"));
            results.add(organizedResult);
        }
        return results;
    }

    private static ArrayNode buildScores(JsonNode divesNode) {
        if (!divesNode.isArray()) {
            throw new IllegalArgumentException("competition JSON has invalid Dives");
        }

        List<JsonNode> dives = new ArrayList<>(divesNode.size());
        for (JsonNode dive : divesNode) {
            dives.add(dive);
        }
        dives.sort(Comparator.comparingInt(dive -> dive.path(DIVE_ORDER).asInt()));

        ArrayNode scores = MAPPER.createArrayNode();
        for (JsonNode dive : dives) {
            scores.add(requiredText(dive, DIVE_POINTS, "dive"));
        }
        return scores;
    }

    private static String organizeFullName(String fullName, String eventType) {
        if (!SYNCHRONISED.equals(eventType)) {
            return fullName;
        }

        String[] names = PAIR_SEPARATOR.split(fullName);
        if (names.length != 2) {
            throw new IllegalArgumentException("Invalid synchronised athlete name: " + fullName);
        }

        String firstName = names[0];
        String secondName = names[1];
        if (firstName.compareTo(secondName) <= 0) {
            return firstName + PAIR_JOINER + secondName;
        }
        return secondName + PAIR_JOINER + firstName;
    }

    private static String requiredText(JsonNode parent, String fieldName, String sourceName) {
        JsonNode value = parent.path(fieldName);
        if (!value.isTextual() || value.asText().isBlank()) {
            throw new IllegalArgumentException(
                    sourceName + " JSON has missing or invalid " + fieldName);
        }
        return value.asText();
    }

    private static int requiredInt(JsonNode parent, String fieldName, String sourceName) {
        JsonNode value = parent.path(fieldName);
        if (!value.isInt()) {
            throw new IllegalArgumentException(
                    sourceName + " JSON has missing or invalid " + fieldName);
        }
        return value.asInt();
    }
}
