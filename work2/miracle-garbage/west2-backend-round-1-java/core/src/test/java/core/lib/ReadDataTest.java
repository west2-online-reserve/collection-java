package core.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.data.JsonData;
import core.exceptions.DataPathException;
import core.exceptions.ReadJsonException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadDataTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @TempDir
    Path temporaryDirectory;

    private Path programDirectory;
    private Path configPath;
    private Path backupConfigPath;

    @BeforeEach
    void isolateConfiguration() throws Exception {
        programDirectory = Path.of(
                ReadData.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        );
        configPath = programDirectory.resolve("config.json");
        backupConfigPath = temporaryDirectory.resolve("original-config.json");
        if (Files.exists(configPath)) {
            Files.move(configPath, backupConfigPath);
        }
    }

    @AfterEach
    void restoreConfiguration() throws IOException {
        Files.deleteIfExists(configPath);
        if (Files.exists(backupConfigPath)) {
            Files.move(backupConfigPath, configPath);
        }
    }

    @Test
    void usesDefaultDataPathWhenConfigurationIsMissing() {
        assertEquals(programDirectory.resolve("data.json"), ReadData.getDataPath());
    }

    @Test
    void readsConfiguredDataPath() throws Exception {
        Path dataPath = temporaryDirectory.resolve("custom-data.json");
        writeConfig(1, "  " + dataPath + "  ");

        assertEquals(dataPath, ReadData.getDataPath());
    }

    @Test
    void fallsBackToDefaultForBlankPathOrDefaultStatus() throws Exception {
        writeConfig(1, " ");
        assertEquals(programDirectory.resolve("data.json"), ReadData.getDataPath());

        writeConfig(0, temporaryDirectory.resolve("ignored.json").toString());
        assertEquals(programDirectory.resolve("data.json"), ReadData.getDataPath());
    }

    @Test
    void fallsBackToDefaultForMalformedConfiguration() throws IOException {
        Files.writeString(configPath, "not-json", StandardCharsets.UTF_8);

        assertEquals(programDirectory.resolve("data.json"), ReadData.getDataPath());
    }

    @Test
    void changesPathAndStatusInConfiguration() throws Exception {
        Path dataFile = temporaryDirectory.resolve("data.json");
        Files.writeString(dataFile, "{}", StandardCharsets.UTF_8);

        ReadData.changePath(dataFile.toString());
        ReadData.DataPath designed = mapper.readValue(configPath.toFile(), ReadData.DataPath.class);
        assertEquals(1, designed.getStatus());
        assertEquals(dataFile.toString(), designed.getPath());

        ReadData.changeStatusDefault();
        ReadData.DataPath defaultStatus = mapper.readValue(configPath.toFile(), ReadData.DataPath.class);
        assertEquals(0, defaultStatus.getStatus());
        assertEquals(dataFile.toString(), defaultStatus.getPath());

        ReadData.changeStatusDesign();
        ReadData.DataPath restoredStatus = mapper.readValue(configPath.toFile(), ReadData.DataPath.class);
        assertEquals(1, restoredStatus.getStatus());
    }

    @Test
    void rejectsBlankInvalidAndMissingDataPaths() {
        assertThrows(DataPathException.class, () -> ReadData.changePath(" "));
        assertThrows(
                DataPathException.class,
                () -> ReadData.changePath(temporaryDirectory.resolve("missing.json").toString())
        );
    }

    @Test
    void readsValidJsonData() throws Exception {
        Path dataFile = temporaryDirectory.resolve("data.json");
        Files.writeString(dataFile, """
                {
                  "schemaVersion": 7,
                  "players": [],
                  "events": []
                }
                """, StandardCharsets.UTF_8);
        writeConfig(1, dataFile.toString());

        JsonData data = ReadData.readData();

        assertEquals(7, data.getSchemaVersion());
    }

    @Test
    void wrapsDataReadingFailures() throws Exception {
        Path dataFile = temporaryDirectory.resolve("invalid.json");
        Files.writeString(dataFile, "{", StandardCharsets.UTF_8);
        writeConfig(1, dataFile.toString());

        assertThrows(ReadJsonException.class, ReadData::readData);
    }

    @Test
    void exposesParsedConfigurationValues() throws URISyntaxException {
        ReadData.DataPath dataPath = new ReadData.DataPath();
        dataPath.setPath("sample.json");
        dataPath.setReadDesign(1);

        assertEquals(1, dataPath.getStatus());
        assertEquals("sample.json", dataPath.getPath());
        dataPath.setReadDefault();
        assertEquals(0, dataPath.getStatus());
        assertEquals(Path.of("sample.json"), Path.of(dataPath.getPath()));
    }

    private void writeConfig(int status, String path) throws IOException {
        ReadData.DataPath dataPath = new ReadData.DataPath();
        dataPath.setPath(path);
        if (status == 1) {
            dataPath.setReadDesign(status);
        } else {
            dataPath.setReadDefault();
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(configPath.toFile(), dataPath);
    }
}
