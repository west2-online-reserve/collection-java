package user.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.data.JsonData;
import user.lib.CommandParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public final class TestFixtures {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private TestFixtures() {
    }

    public static JsonData data() {
        try {
            return MAPPER.readValue("""
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
                        },
                        {
                          "fullName": "BETA Bob",
                          "gender": "Male",
                          "country": "France",
                          "countryCode": "FRA"
                        }
                      ],
                      "events": [
                        {
                          "command": "result men 10m platform",
                          "eventId": "event-1",
                          "gender": "Men",
                          "discipline": "10m",
                          "eventType": "platform",
                          "results": [
                            {
                              "fullName": "BETA Bob",
                              "rank": 2,
                              "countryCode": "FRA",
                              "scores": [80.0, 90.0],
                              "totalPoints": 170.0
                            },
                            {
                              "fullName": "ALPHA Alice",
                              "rank": 1,
                              "countryCode": "CHN",
                              "scores": [91.0, 92.0],
                              "totalPoints": 183.0
                            }
                          ]
                        }
                      ]
                    }
                    """, JsonData.class);
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to create test data", exception);
        }
    }

    public static ArrayList<String> args(String... values) {
        return new ArrayList<>(List.of(values));
    }

    public static CommandParam param(String... values) {
        return new CommandParam(args(values));
    }

    public static String captureOutput(Runnable action) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            action.run();
        } finally {
            System.setOut(originalOut);
        }
        return output.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }

    public static String readFile(Path path) throws Exception {
        return Files.readString(path, StandardCharsets.UTF_8).replace("\r\n", "\n");
    }

    public static Path createTestDirectory(Class<?> testClass) {
        Path directory = Path.of("target", "test-files", testClass.getSimpleName());
        deleteTestDirectory(directory);
        try {
            return Files.createDirectories(directory);
        } catch (IOException exception) {
            throw new UncheckedIOException("Unable to create test directory: " + directory, exception);
        }
    }

    public static void deleteTestDirectory(Path directory) {
        if (!Files.exists(directory)) {
            return;
        }

        try (Stream<Path> paths = Files.walk(directory)) {
            for (Path path : paths.sorted(Comparator.reverseOrder()).toList()) {
                Files.deleteIfExists(path);
            }
        } catch (IOException exception) {
            throw new UncheckedIOException("Unable to delete test directory: " + directory, exception);
        }
    }
}
