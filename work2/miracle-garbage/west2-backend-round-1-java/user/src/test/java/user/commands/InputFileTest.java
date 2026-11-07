package user.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.exceptions.ParamException;
import user.lib.CommandList;
import user.lib.CommandParam;
import user.support.TestFixtures;

import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayDeque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputFileTest {

    private Path testDirectory;

    @BeforeEach
    void setUpFiles() {
        testDirectory = TestFixtures.createTestDirectory(getClass());
    }

    @AfterEach
    void cleanFiles() {
        TestFixtures.deleteTestDirectory(testDirectory);
    }

    @Test
    void readsNonBlankLinesIntoCommandQueue() throws Exception {
        Path input = Files.writeString(testDirectory.resolve("commands.txt"), """

                players

                result men 10m platform
                """);
        ArrayDeque<CommandParam> queue = new ArrayDeque<>();

        InputFile.execute(TestFixtures.param(input.toString()), queue, new CommandList());

        assertEquals(2, queue.size());
        assertEquals("players", queue.removeFirst().getFirstArg());
        CommandParam result = queue.removeFirst();
        assertEquals("result", result.getFirstArg());
        assertEquals(4, result.size());
    }

    @Test
    void appendsOutputFileToEveryCommand() throws Exception {
        Path input = Files.writeString(testDirectory.resolve("commands.txt"), """
                players
                result men 10m platform
                """);
        Path output = testDirectory.resolve("output.txt");
        ArrayDeque<CommandParam> queue = new ArrayDeque<>();

        InputFile.execute(
                TestFixtures.param(input.toString(), output.toString()),
                queue,
                new CommandList()
        );

        CommandParam players = queue.removeFirst();
        CommandParam result = queue.removeFirst();
        assertEquals("-f", players.getArg(1));
        assertEquals(output.toString(), players.getArg(2));
        assertEquals("-f", result.getArg(4));
        assertEquals(output.toString(), result.getArg(5));
    }

    @Test
    void rejectsTooManyArgumentsOrInvalidOutputArgument() {
        ArrayDeque<CommandParam> queue = new ArrayDeque<>();
        CommandList commandList = new CommandList();

        assertThrows(
                ParamException.class,
                () -> InputFile.execute(TestFixtures.param("a.txt", "b.txt", "c.txt"), queue, commandList)
        );
        assertThrows(
                ParamException.class,
                () -> InputFile.execute(TestFixtures.param("a.txt", "players"), queue, commandList)
        );
        assertThrows(
                ParamException.class,
                () -> InputFile.execute(TestFixtures.param("a.txt", "output.md"), queue, commandList)
        );
    }

    @Test
    void reportsMissingFileAndInvalidPath() {
        ArrayDeque<CommandParam> queue = new ArrayDeque<>();
        CommandList commandList = new CommandList();

        assertThrows(
                UncheckedIOException.class,
                () -> InputFile.execute(
                        TestFixtures.param(testDirectory.resolve("missing.txt").toString()),
                        queue,
                        commandList
                )
        );
        assertThrows(
                InvalidPathException.class,
                () -> InputFile.execute(TestFixtures.param("\0"), queue, commandList)
        );
    }
}
