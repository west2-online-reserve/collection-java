package Command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {

    @TempDir
    Path tempDir;

    @Test
    void parsePlayersCommand() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("players"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertEquals(1, commands.get(0).getLine());
        assertTrue(commands.get(0) instanceof PlayerCommand);
        assertNull(commands.get(0).immediateOutput());
    }

    @Test
    void invalidCommandBecomesError() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("PLAYERS"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertEquals(1, commands.get(0).getLine());
        assertTrue(commands.get(0) instanceof ErrorCommand);
        assertEquals("Error\n-----\n", commands.get(0).immediateOutput());
    }

    @Test
    void emptyCommand() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.emptyList(), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(0, commands.size());
        assertTrue(commands.isEmpty(), "命令列表应该为空");
    }

    @Test
    void parseResultCommand() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("result Women 1m Springboard"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ResultCommand);
        assertNull(commands.get(0).immediateOutput());
    }

    @Test
    void parseResultCommandDetail() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("result Women 1m Springboard detail"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        ResultCommand result = (ResultCommand) commands.get(0);
        assertTrue(result.isDetail());
        assertTrue(commands.get(0) instanceof ResultCommand);
        assertNull(commands.get(0).immediateOutput());

    }

    @Test
    void errorResultBecomesNA() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("result women 1m Springboard"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ResultCommand);
        assertEquals("N/A\n-----\n", commands.get(0).immediateOutput());
    }

    @Test
    void playersWithExtraArgsBecomesError() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("players extra"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ErrorCommand);
        assertEquals("Error\n-----\n", commands.get(0).immediateOutput());
    }

    @Test
    void resultOnlyBecomesError() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("result"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ErrorCommand);
        assertEquals("Error\n-----\n", commands.get(0).immediateOutput());
    }

    @Test
    void resultDetailWithoutEventBecomesNA() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("result detail"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ResultCommand);
        assertEquals("N/A\n-----\n", commands.get(0).immediateOutput());
        assertTrue(((ResultCommand) commands.get(0)).isDetail());
    }

    @Test
    void resultDetailsSuffixBecomesNA() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("result women 1m springboard details"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ResultCommand);
        assertEquals("N/A\n-----\n", commands.get(0).immediateOutput());
    }

    @Test
    void resultDetailWithExtraArgBecomesNA() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("result women 1m springboard detail extra"), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ResultCommand);
        assertEquals("N/A\n-----\n", commands.get(0).immediateOutput());
    }

    @Test
    void whitespaceOnlyLineBecomesError() throws Exception {
        Path input = tempDir.resolve("input.txt");
        Files.write(input, Collections.singletonList("   "), StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ErrorCommand);
        assertEquals("Error\n-----\n", commands.get(0).immediateOutput());
    }

    @Test
    void mixedCommandsKeepOrderAndLineNumbers() throws Exception {
        Path input = tempDir.resolve("input.txt");
        List<String> lines = Arrays.asList(
                "players",
                "result",
                "result Women 1m Springboard",
                "   ",
                "result detail",
                "players extra"
        );
        Files.write(input, lines, StandardCharsets.UTF_8);

        CommandParser parser = new CommandParser();
        List<Command> commands = parser.parse(input.toString());

        assertEquals(6, commands.size());
        assertEquals(1, commands.get(0).getLine());
        assertTrue(commands.get(0) instanceof PlayerCommand);

        assertEquals(2, commands.get(1).getLine());
        assertTrue(commands.get(1) instanceof ErrorCommand);

        assertEquals(3, commands.get(2).getLine());
        assertTrue(commands.get(2) instanceof ResultCommand);
        assertNull(commands.get(2).immediateOutput());

        assertEquals(4, commands.get(3).getLine());
        assertTrue(commands.get(3) instanceof ErrorCommand);
        assertEquals("Error\n-----\n", commands.get(3).immediateOutput());

        assertEquals(5, commands.get(4).getLine());
        assertTrue(commands.get(4) instanceof ResultCommand);
        assertEquals("N/A\n-----\n", commands.get(4).immediateOutput());

        assertEquals(6, commands.get(5).getLine());
        assertTrue(commands.get(5) instanceof ErrorCommand);
        assertEquals("Error\n-----\n", commands.get(5).immediateOutput());
    }


}












