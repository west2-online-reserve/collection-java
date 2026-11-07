package user.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.exceptions.ParamException;
import user.support.TestFixtures;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandManagerTest {

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
    void rejectsMissingOrIllegalFirstArgument() {
        assertThrows(
                ParamException.class,
                () -> new CommandManager(TestFixtures.args()).commandManager(TestFixtures.data())
        );
        assertThrows(
                ParamException.class,
                () -> new CommandManager(TestFixtures.args("unknown"))
                        .commandManager(TestFixtures.data())
        );
    }

    @Test
    void executesDirectPlayersCommand() {
        String output = TestFixtures.captureOutput(
                () -> new CommandManager(TestFixtures.args("players"))
                        .commandManager(TestFixtures.data())
        );

        assertPlayerOutput(output);
    }

    @Test
    void executesCommandsReadFromInputFile() throws Exception {
        Path input = Files.writeString(testDirectory.resolve("commands.txt"), """
                players

                result men 10m platform
                """);

        String output = TestFixtures.captureOutput(
                () -> new CommandManager(TestFixtures.args(input.toString()))
                        .commandManager(TestFixtures.data())
        );

        assertPlayerOutput(output);
        assertTrue(output.contains("Full Name:BETA Bob"));
        assertTrue(output.contains("91.00 + 92.00 = 183.00"));
    }

    @Test
    void writesAllFileCommandsToSharedOutputFile() throws Exception {
        Path input = Files.writeString(testDirectory.resolve("commands.txt"), """
                players
                result men 10m platform
                """);
        Path outputFile = testDirectory.resolve("output.txt");

        String console = TestFixtures.captureOutput(
                () -> new CommandManager(TestFixtures.args(input.toString(), outputFile.toString()))
                        .commandManager(TestFixtures.data())
        );

        String fileContent = TestFixtures.readFile(outputFile);
        assertPlayerOutput(fileContent);
        assertTrue(fileContent.contains("91.00 + 92.00 = 183.00"));
        assertEquals(2, countOccurrences(console, "写入成功"));
    }

    @Test
    void printsErrorForIllegalCommandInsideInputFile() throws Exception {
        Path input = Files.writeString(testDirectory.resolve("invalid.txt"), "unknown\n");

        String output = TestFixtures.captureOutput(
                () -> new CommandManager(TestFixtures.args(input.toString()))
                        .commandManager(TestFixtures.data())
        );

        assertEquals("Error\n-----\n", output);
    }

    @Test
    void writesErrorToRequestedFileForIllegalFileCommand() throws Exception {
        Path input = Files.writeString(
                testDirectory.resolve("invalid.txt"),
                "unknown -f " + testDirectory.resolve("error.txt") + "\n"
        );
        Path errorFile = testDirectory.resolve("error.txt");

        TestFixtures.captureOutput(
                () -> new CommandManager(TestFixtures.args(input.toString()))
                        .commandManager(TestFixtures.data())
        );

        assertEquals("Error\n-----\n", TestFixtures.readFile(errorFile));
    }

    @Test
    void reportsParameterErrorsAndContinuesQueueProcessing() {
        String output = TestFixtures.captureOutput(
                () -> new CommandManager(TestFixtures.args("config", "--setpath"))
                        .commandManager(TestFixtures.data())
        );

        assertEquals("ParameterError\n", output);
    }

    private static void assertPlayerOutput(String output) {
        assertTrue(output.contains("Full Name:ALPHA Alice"));
        assertTrue(output.contains("Full Name:BETA Bob"));
        assertFalse(output.contains("N/A"));
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
}
