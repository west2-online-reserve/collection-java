package core.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.data.Event;
import core.data.JsonData;
import core.data.Player;
import core.data.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OutputFormatTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private JsonData data;
    private Event event;
    private Result firstResult;
    private Result secondResult;

    @BeforeEach
    void setUp() throws Exception {
        data = mapper.readValue("""
                {
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
                      "gender": "Men",
                      "discipline": "10m",
                      "eventType": "platform",
                      "results": [
                        {
                          "fullName": "BETA Bob",
                          "rank": 2,
                          "countryCode": "FRA",
                          "scores": [80.0],
                          "totalPoints": 80.0
                        },
                        {
                          "fullName": "ALPHA Alice",
                          "rank": 1,
                          "countryCode": "CHN",
                          "scores": [90.0],
                          "totalPoints": 90.0
                        }
                      ]
                    }
                  ]
                }
                """, JsonData.class);
        event = data.getEvents().getFirst();
        firstResult = event.getResults().getFirst();
        secondResult = event.getResults().get(1);
    }

    @Test
    void formatsOnePlayerBlock() {
        String expected = """
                Full Name:ALPHA Alice
                Gender:Female
                Country:China
                -----
                """;

        assertEquals(expected, OutputFormat.outputPlayerFormat(data.getPlayers().getFirst()));
    }

    @Test
    void writesEveryPlayerBlock() throws Exception {
        StringWriter output = new StringWriter();
        BufferedWriter writer = new BufferedWriter(output);

        OutputFormat.writeAllPlayersInfo(data, writer);
        writer.flush();

        String text = output.toString();
        assertTrue(text.contains("Full Name:ALPHA Alice"));
        assertTrue(text.contains("Full Name:BETA Bob"));
        assertEquals(2, countOccurrences(text, "-----"));
    }

    @Test
    void displaysEveryPlayerOnTerminal() {
        String text = captureOutput(() -> OutputFormat.displayAllPlayersInfo(data));

        assertTrue(text.contains("Full Name:ALPHA Alice"));
        assertTrue(text.contains("Full Name:BETA Bob"));
    }

    @Test
    void displaysNotFoundMessageForNullEvent() {
        String text = captureOutput(() -> OutputFormat.displayEventResult(null));

        assertEquals("N/A\n-----\n", text);
    }

    @Test
    void writesNotFoundMessageForNullEvent() throws Exception {
        StringWriter output = new StringWriter();
        BufferedWriter writer = new BufferedWriter(output);

        OutputFormat.writeResultPlayerFormat(null, writer);
        writer.flush();

        assertEquals("N/A\n-----\n", output.toString());
    }

    @Test
    void writesResultDetailsWithoutChangingInputOrder() throws Exception {
        StringWriter output = new StringWriter();
        BufferedWriter writer = new BufferedWriter(output);

        OutputFormat.writeResultPlayerFormat(event, writer);
        writer.flush();

        String text = output.toString();
        assertTrue(text.indexOf("BETA Bob") < text.indexOf("ALPHA Alice"));
        assertTrue(text.contains("80.00 = 80.00"));
        assertTrue(text.contains("90.00 = 90.00"));
    }

    @Test
    void writesErrorBlock() throws Exception {
        StringWriter output = new StringWriter();
        BufferedWriter writer = new BufferedWriter(output);

        OutputFormat.writeError(writer);
        writer.flush();

        assertEquals("Error\n-----\n", output.toString());
    }

    @Test
    void displaysErrorBlock() {
        assertEquals("Error\n-----\n", captureOutput(OutputFormat::displayError));
    }

    @Test
    void wrapsWriterFailuresWhenWritingPlayers() {
        assertThrows(
                java.io.UncheckedIOException.class,
                () -> OutputFormat.writeAllPlayersInfo(data, new FailingBufferedWriter())
        );
    }

    @Test
    void wrapsWriterFailuresWhenWritingResultsAndErrors() {
        assertThrows(
                java.io.UncheckedIOException.class,
                () -> OutputFormat.writeResultPlayerFormat(event, new FailingBufferedWriter())
        );
        assertThrows(
                java.io.UncheckedIOException.class,
                () -> OutputFormat.writeError(new FailingBufferedWriter())
        );
    }

    @Test
    void writesResultPlayerBlock() {
        String formatted = OutputFormat.outputResultPlayerFormat(firstResult);

        assertTrue(formatted.startsWith("Full Name:BETA Bob\n"));
        assertTrue(formatted.endsWith("80.00 = 80.00\n-----\n"));
    }

    private static String captureOutput(Runnable runnable) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            runnable.run();
        } finally {
            System.setOut(originalOut);
        }
        return output.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }

    private static int countOccurrences(String text, String target) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(target, index)) >= 0) {
            count++;
            index += target.length();
        }
        return count;
    }

    private static final class FailingBufferedWriter extends BufferedWriter {
        private FailingBufferedWriter() {
            super(Writer.nullWriter());
        }

        @Override
        public void write(char[] buffer, int offset, int length) throws java.io.IOException {
            throw new java.io.IOException("forced failure");
        }

        @Override
        public void write(String value, int offset, int length) throws java.io.IOException {
            throw new java.io.IOException("forced failure");
        }
    }
}
