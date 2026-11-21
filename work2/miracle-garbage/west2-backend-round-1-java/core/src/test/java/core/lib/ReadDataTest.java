package core.lib;

import core.exceptions.DataPathException;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadDataTest {

    @Test
    void rejectsBlankDataPath() {
        assertThrows(DataPathException.class, () -> ReadData.changePath(" "));
    }

    @Test
    void rejectsMissingRelativeDataPath() {
        assertThrows(
                DataPathException.class,
                () -> ReadData.changePath(Path.of("target", "missing-test-data.json").toString())
        );
    }

    @Test
    void exposesParsedConfigurationValues() {
        ReadData.DataPath dataPath = new ReadData.DataPath();
        dataPath.setPath("sample.json");
        dataPath.setReadDesign(1);

        assertEquals(1, dataPath.getStatus());
        assertEquals("sample.json", dataPath.getPath());

        dataPath.setReadDefault();
        assertEquals(0, dataPath.getStatus());
        assertEquals("sample.json", dataPath.getPath());
    }
}
