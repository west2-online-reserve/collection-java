package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CmdFileReaderTest {

    private final CmdFileReader cmdFileReader = new CmdFileReader();

    /**
     * 测试：正常多行、包含空行、全空白行，空行会被跳过
     */
    @Test
    void readSkipBlankLines(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("cmd.txt");
        String content = """
                players

                result women 3m springboard
                   
                result women 10m platform
                """;
        Files.writeString(testFile, content);

        List<String> lines = cmdFileReader.read(testFile);

        assertEquals(3, lines.size());
        assertEquals("players", lines.get(0));
        assertEquals("result women 3m springboard", lines.get(1));
        assertEquals("result women 10m platform", lines.get(2));
    }

    /**
     * 测试：文件为空，返回空集合
     */
    @Test
    void readEmptyFile(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("empty.txt");
        Files.writeString(testFile, "");

        List<String> lines = cmdFileReader.read(testFile);
        assertTrue(lines.isEmpty());
    }

    /**
     * 测试：文件不存在时，预期抛出 IOException
     */
    @Test
    void fileNotExistThrowException() {
        Path notExist = Path.of("this_file_never_exist_1234.txt");
        assertThrows(IOException.class, () -> cmdFileReader.read(notExist));
    }
}
