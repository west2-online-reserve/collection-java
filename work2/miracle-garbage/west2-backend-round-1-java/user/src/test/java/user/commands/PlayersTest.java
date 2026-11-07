package user.commands;

import core.data.JsonData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.exceptions.ParamException;
import user.lib.FileCache;
import user.support.TestFixtures;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayersTest {

    private Path testDirectory;

    private final Players players = new Players();
    private final JsonData data = TestFixtures.data();

    @BeforeEach
    void setUpFiles() {
        testDirectory = TestFixtures.createTestDirectory(getClass());
    }

    @AfterEach
    void cleanFiles() {
        TestFixtures.deleteTestDirectory(testDirectory);
    }

    @Test
    void hasRegisteredName() {
        assertEquals("players", players.getName());
    }

    @Test
    void displaysAllPlayersWhenNoFileOptionIsProvided() {
        String output = TestFixtures.captureOutput(
                () -> players.execute(data, TestFixtures.param(), new FileCache())
        );

        assertAllPlayerInformation(output);
    }

    @Test
    void writesAllPlayersToFileWithoutTerminalDataOutput() throws Exception {
        Path outputFile = testDirectory.resolve("players.txt");
        FileCache fileCache = new FileCache();

        String console = TestFixtures.captureOutput(
                () -> players.execute(
                        data,
                        TestFixtures.param("-f", outputFile.toString()),
                        fileCache
                )
        );
        fileCache.clear();

        assertTrue(console.contains(outputFile.getFileName().toString()));
        assertTrue(console.endsWith("写入成功\n"));
        assertAllPlayerInformation(TestFixtures.readFile(outputFile));
    }

    @Test
    void rejectsUnsupportedOrIncompleteOptions() {
        assertThrows(
                ParamException.class,
                () -> players.execute(data, TestFixtures.param("--unknown"), new FileCache())
        );
        assertThrows(
                ParamException.class,
                () -> players.execute(data, TestFixtures.param("-f"), new FileCache())
        );
        assertThrows(
                ParamException.class,
                () -> players.execute(
                        data,
                        TestFixtures.param("-f", "a.txt", "extra"),
                        new FileCache()
                )
        );
    }

    @Test
    void rejectsWrongDataTypes() {
        assertThrows(RuntimeException.class, () -> players.displayOnTerminal(new Object()));
        assertThrows(RuntimeException.class, () -> players.writeToFile(new Object(), new FileCache(), "a.txt"));
    }

    private static void assertAllPlayerInformation(String output) {
        assertTrue(output.contains("Full Name:ALPHA Alice"));
        assertTrue(output.contains("Gender:Female"));
        assertTrue(output.contains("Country:China"));
        assertTrue(output.contains("Full Name:BETA Bob"));
        assertTrue(output.contains("Gender:Male"));
        assertTrue(output.contains("Country:France"));
        assertFalse(output.contains("N/A"));
    }
}
