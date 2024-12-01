import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

class CoreTest {

    @Test
    void testOutputPlayersInfo() throws IOException {
        List<Core.Player> players = Arrays.asList(
                new Core.Player("John Doe", "Male", "USA"),
                new Core.Player("Jane Smith", "Female", "USA")
        );
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

        Core.outputPlayersInfo(players, writer);

        writer.flush();
        String output = byteArrayOutputStream.toString();
        assertTrue(output.contains("John Doe"));
        assertTrue(output.contains("Male"));
        assertTrue(output.contains("USA"));
    }

    @Test
    void testOutputPlayersInfoEmptyList() throws IOException {
        List<Core.Player> players = new ArrayList<>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

        Core.outputPlayersInfo(players, writer);

        writer.flush();
        String output = byteArrayOutputStream.toString().trim();
        assertTrue(output.isEmpty(), "Expected no output, but got: " + (output == null ? "null" : output));
    }

    @Test
    void testOutputResult() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

        Core.outputResult("Event1", writer);

        writer.flush();
        String output = byteArrayOutputStream.toString();
        assertTrue(output.contains("John Doe"));
    }

    @Test
    void testOutputResultEventNotFound() throws IOException {
        String command = "result invalidEvent";
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);

        Core.processCommand(command, writer);
        writer.flush();

        String output = stringWriter.toString().trim();
        assertTrue(output.contains("Error"), "Expected output to contain 'Error', but got: " + output);
    }

    @Test
    void testOutputDetailedResult() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

        Core.outputDetailedResult("Event1", writer);

        writer.flush();
        String output = byteArrayOutputStream.toString();
        assertTrue(output.contains("Jane Smith"));
    }

    @Test
    void testOutputDetailedResultEventNotFound() throws IOException {
        String command = "result invalidEvent";
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);

        Core.processCommand(command, writer);
        writer.flush();

        String output = stringWriter.toString().trim();
        assertTrue(output.contains("Error"), "Expected output to contain 'Error', but got: " + output);
    }

    @Test
    void testProcessCommand() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

        Core.processCommand("players", writer);
        writer.flush();
        String outputPlayers = byteArrayOutputStream.toString();
        assertTrue(outputPlayers.contains("John Doe"));

        byteArrayOutputStream.reset();
        Core.processCommand("result Event1", writer);
        writer.flush();
        String outputResult = byteArrayOutputStream.toString();
        assertTrue(outputResult.contains("John Doe"));
    }

    @Test
    void testProcessCommandInvalid() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

        Core.processCommand("invalidCommand", writer);
        writer.flush();
        String output = byteArrayOutputStream.toString();
        assertEquals("Error\n", output);
    }

    @Test
    void testProcessCommandWithSpaceInEvent() throws IOException {
        String command = "result men 1m springboard";
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);

        Core.processCommand(command, writer);
        writer.flush();

        String output = stringWriter.toString().trim();
        assertTrue(output.contains("men 1m springboard"), "Expected output to contain 'Event1 with space', but got: " + output);
    }

    @Test
    void testProcessCommandIncomplete() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));

        Core.processCommand("result", writer);
        writer.flush();
        String output = byteArrayOutputStream.toString().trim();
        assertEquals("Error", output, "Expected output to be 'Error', but got: " + output);
    }
}
