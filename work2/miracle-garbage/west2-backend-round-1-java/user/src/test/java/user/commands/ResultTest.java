package user.commands;

import core.data.Event;
import core.data.JsonData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.exceptions.ParamException;
import user.lib.FileCache;
import user.support.TestFixtures;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultTest {

    private Path testDirectory;

    private final Result result = new Result();
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
        assertEquals("result", result.getName());
    }

    @Test
    void displaysMatchingEventResults() {
        String output = TestFixtures.captureOutput(
                () -> result.execute(
                        data,
                        TestFixtures.param("men", "10m", "platform"),
                        new FileCache()
                )
        );

        assertTrue(output.contains("Full Name:BETA Bob"));
        assertTrue(output.contains("Full Name:ALPHA Alice"));
        assertTrue(output.contains("80.00 + 90.00 = 170.00"));
        assertTrue(output.contains("91.00 + 92.00 = 183.00"));
    }

    @Test
    void displaysNotFoundForMissingOrMalformedEventName() {
        String wrongDiscipline = TestFixtures.captureOutput(
                () -> result.execute(
                        data,
                        TestFixtures.param("men", "3m", "platform"),
                        new FileCache()
                )
        );
        String tooShortName = TestFixtures.captureOutput(
                () -> result.execute(
                        data,
                        TestFixtures.param("men", "10m"),
                        new FileCache()
                )
        );
        String optionWithoutEvent = TestFixtures.captureOutput(
                () -> result.execute(
                        data,
                        TestFixtures.param("--default-output"),
                        new FileCache()
                )
        );

        assertEquals("N/A\n-----\n", wrongDiscipline);
        assertEquals("N/A\n-----\n", tooShortName);
        assertEquals("N/A\n-----\n", optionWithoutEvent);
    }

    @Test
    void writesMatchingResultsToFile() throws Exception {
        Path outputFile = testDirectory.resolve("result.txt");
        FileCache fileCache = new FileCache();

        String console = TestFixtures.captureOutput(
                () -> result.execute(
                        data,
                        TestFixtures.param(
                                "men", "10m", "platform",
                                "-f", outputFile.toString()
                        ),
                        fileCache
                )
        );
        fileCache.clear();

        assertTrue(console.contains(outputFile.getFileName().toString()));
        assertTrue(console.endsWith("写入成功\n"));
        String fileContent = TestFixtures.readFile(outputFile);
        assertTrue(fileContent.contains("Full Name:BETA Bob"));
        assertTrue(fileContent.contains("91.00 + 92.00 = 183.00"));
    }

    @Test
    void writesNotFoundBlockForNullEvent() throws Exception {
        Path outputFile = testDirectory.resolve("missing.txt");
        FileCache fileCache = new FileCache();

        result.writeToFile(null, fileCache, outputFile.toString());
        fileCache.clear();

        assertEquals("N/A\n-----\n", TestFixtures.readFile(outputFile));
    }

    @Test
    void rejectsUnknownOptions() {
        assertThrows(
                ParamException.class,
                () -> result.execute(
                        data,
                        TestFixtures.param("men", "10m", "platform", "--unknown"),
                        new FileCache()
                )
        );
    }

    @Test
    void displayAndWriteRejectWrongDataType() {
        assertThrows(RuntimeException.class, () -> result.displayOnTerminal(new Object()));
        assertThrows(
                RuntimeException.class,
                () -> result.writeToFile(new Object(), new FileCache(), "unused.txt")
        );
    }

    @Test
    void acceptsEventDirectlyForFileAndTerminalOutput() throws Exception {
        Event event = data.getEvents().getFirst();
        Path outputFile = testDirectory.resolve("event.txt");
        FileCache fileCache = new FileCache();

        TestFixtures.captureOutput(() -> result.displayOnTerminal(event));
        result.writeToFile(event, fileCache, outputFile.toString());
        fileCache.clear();

        assertTrue(TestFixtures.readFile(outputFile).contains("Full Name:ALPHA Alice"));
    }
}
