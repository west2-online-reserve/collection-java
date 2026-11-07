package user.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import core.lib.ReadData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import user.exceptions.ParamException;
import user.support.TestFixtures;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationAndMainTest {

    @TempDir
    Path temporaryDirectory;

    private Path configPath;
    private Path backupConfigPath;

    @BeforeEach
    void isolateConfiguration() throws Exception {
        Path programDirectory = Path.of(
                ReadData.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        );
        configPath = programDirectory.resolve("config.json");
        backupConfigPath = temporaryDirectory.resolve("original-config.json");
        if (Files.exists(configPath)) {
            Files.move(configPath, backupConfigPath);
        }
    }

    @AfterEach
    void restoreConfiguration() throws Exception {
        Files.deleteIfExists(configPath);
        if (Files.exists(backupConfigPath)) {
            Files.move(backupConfigPath, configPath);
        }
    }

    @Test
    void applicationStartsWithEmptyDataObject() {
        Application application = new Application();

        assertNotNull(application.getData());
        assertNull(application.getData().getCompetition());
    }

    @Test
    void commandRunReportsUnknownCommandAsError() {
        Application application = new Application();

        String output = TestFixtures.captureOutput(
                () -> Main.commandRun(application, new String[]{"unknown"})
        );

        assertEquals("Error\n", output);
    }

    @Test
    void appInitReportsReadFailureWhenConfiguredDataIsUnavailable() throws Exception {
        ObjectNode config = new ObjectMapper().createObjectNode();
        config.put("status", 1);
        config.put("path", temporaryDirectory.resolve("missing.json").toString());
        new ObjectMapper().writeValue(configPath.toFile(), config);
        AtomicReference<ParamException> exceptionReference = new AtomicReference<>();
        String output = TestFixtures.captureOutput(
                () -> exceptionReference.set(assertThrows(ParamException.class, Main::appInit))
        );
        ParamException exception = exceptionReference.get();

        assertEquals("程序初始化失败", exception.getMessage());
        assertTrue(output.contains("无法读取文件"));
    }

}
