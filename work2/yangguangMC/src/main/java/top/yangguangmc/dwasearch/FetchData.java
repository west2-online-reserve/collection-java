package top.yangguangmc.dwasearch;

import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class FetchData {
    private static final Map<String, URI> URI_MAP = new HashMap<>();

    static {
        URI_MAP.put("women 3m springboard", URI.create("https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82"));
        URI_MAP.put("women 10m platform", URI.create("https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1"));
        URI_MAP.put("women 3m synchronised", URI.create("https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d"));
        URI_MAP.put("women 10m synchronised", URI.create("https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b"));
        URI_MAP.put("men 3m springboard", URI.create("https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7"));
        URI_MAP.put("men 10m platform", URI.create("https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48"));
        URI_MAP.put("men 3m synchronised", URI.create("https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c"));
        URI_MAP.put("men 10m synchronised", URI.create("https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5"));
        URI_MAP.put("athletes", URI.create("https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId="));
    }

    private FetchData() {
        throw new AssertionError();
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        HttpClient client = HttpClient.newBuilder().build();
        URI_MAP.forEach((key, uri) -> pool.submit(() -> {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .timeout(Duration.ofSeconds(30))
                    .build();
            ObjectMapper mapper = new ObjectMapper();
            for (int i = 1; i <= 3; i++) {
                System.out.printf("Fetching %s, attempt %d%n", key, i);
                try {
                    Thread.sleep(500);
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() != 200) {
                        System.err.printf("Failed to get: %s (Status code %d), uri: %s!%n", key, response.statusCode(), uri);
                        continue;
                    }
                    String body = response.body();
                    Object o = mapper.readValue(body, Object.class);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(Path.of(key + ".json"), o);
                    System.out.printf("Done fetching %s.json%n", key);
                    break;
                } catch (Exception e) {
                    System.err.printf("Exception while getting: %s, uri: %s!%n", key, uri);
                    e.printStackTrace(System.err);
                }
            }
        }));
        pool.shutdown();
    }
}
