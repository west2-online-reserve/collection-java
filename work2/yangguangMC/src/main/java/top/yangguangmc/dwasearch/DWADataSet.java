package top.yangguangmc.dwasearch;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

public record DWADataSet(
        List<Player> players,
        Map<String, List<Result>> results
) {
    private static final List<String> RESULT_KEYS = Arrays.asList(
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    );

    public static DWADataSet load(DWADataSourceProvider provider) throws IOException {
        List<Player> players = new ArrayList<>();
        Map<String, List<Result>> results = new HashMap<>(RESULT_KEYS.size());
        ObjectMapper mapper = new ObjectMapper();
        String athletesResource = "/data/athletes.json";
        try (InputStream stream = provider.getDataSource(athletesResource)) {
            if (stream == null) {
                throw new FileNotFoundException(athletesResource);
            }
            for (JsonNode countryNode : mapper.readTree(stream).asArray()) {
                for (JsonNode participationNode : countryNode.optional("Participations")
                        .orElseThrow(() -> new DataFormatException("Participations", athletesResource, countryNode))
                        .asArray()) {
                    players.add(new Player(
                            participationNode.optional("PreferredFirstName")
                                    .orElseThrow(() -> new DataFormatException("PreferredFirstName", athletesResource, participationNode))
                                    .stringValue(),
                            participationNode.optional("PreferredLastName")
                                    .orElseThrow(() -> new DataFormatException("PreferredLastName", athletesResource, participationNode))
                                    .stringValue(),
                            participationNode.optional("Gender")
                                    .orElseThrow(() -> new DataFormatException("Gender", athletesResource, participationNode))
                                    .intValue(),
                            countryNode.optional("CountryName")
                                    .orElseThrow(() -> new DataFormatException("CountryName", athletesResource, countryNode))
                                    .stringValue()
                    ));
                }
            }
            players.sort(null);
        }
        for (String key : RESULT_KEYS) {
            String keyResource = "/data/" + key + ".json";
            try (InputStream stream = provider.getDataSource(keyResource)) {
                if (stream == null) {
                    throw new FileNotFoundException(keyResource);
                }
                JsonNode tree = mapper.readTree(stream);
                ArrayNode heatsArray = tree.optional("Heats")
                        .orElseThrow(() -> new DataFormatException("Heats", keyResource, tree))
                        .asArray();
                JsonNode finalHeatsArray = heatsArray
                        .valueStream()
                        .filter(jsonNode -> "Finals".equals(jsonNode.get("Name").stringValue()))
                        .findAny()
                        .orElseThrow(() -> new DataFormatException("Finals", "all the heats in " + keyResource, heatsArray));
                ArrayNode heatResultsArray = finalHeatsArray
                        .optional("Results")
                        .orElseThrow(() -> new DataFormatException("Results", keyResource, finalHeatsArray))
                        .asArray();
                ArrayList<Result> list = new ArrayList<>(heatResultsArray.size());
                results.put(key, list);
                for (JsonNode result : heatResultsArray) {
                    list.add(new Result(
                            result.optional("FullName")
                                    .orElseThrow(() -> new DataFormatException("FullName", keyResource, result))
                                    .stringValue(),
                            result.optional("Rank")
                                    .orElseThrow(() -> new DataFormatException("Rank", keyResource, result))
                                    .intValue(),
                            result.optional("Dives")
                                    .orElseThrow(() -> new DataFormatException("Dives", keyResource, result))
                                    .asArray()
                                    .valueStream()
                                    .map(diveObject -> new BigDecimal(diveObject.get("DivePoints").asString()))
                                    .toList()
                    ));
                }
                list.sort(null);
            }
        }
        return new DWADataSet(players, results);
    }

    public static class DataFormatException extends IOException {
        public DataFormatException(String missedProperty, String context, JsonNode node) {
            super("Missing property '%s' in %s!".formatted(missedProperty, context) + System.lineSeparator() + node);
        }
    }

    @FunctionalInterface
    public interface DWADataSourceProvider {
        InputStream getDataSource(String resource);
    }
}
