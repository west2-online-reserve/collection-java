import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 命令行文件接口测试：使用临时目录验证真实文件读写与错误路径。
class DWASearchCliTest {

    @TempDir
    Path tempDir;

    @Test
    void fileModeWritesExpectedOutput() throws IOException {
        Path input = tempDir.resolve("input.txt");
        Path output = tempDir.resolve("output.txt");
        Files.write(input,
                java.util.List.of("players", "", "  ", "result men 10m platform", "badcmd"),
                StandardCharsets.UTF_8);

        assertEquals(0, DWASearch.runWithFiles(input, output));

        String text = Files.readString(output, StandardCharsets.UTF_8);
        assertTrue(text.startsWith("Full Name:COLE Ellie\nGender:Female\nCountry:Australia\n-----\n"));
        assertTrue(text.contains("Full Name:BAI Yuming\nRank:1\n"));
        assertTrue(text.contains("Error\n-----\n"));
        assertTrue(text.endsWith("-----\n"));
        assertFalse(text.contains("\r"));
        assertEquals(1, count(text, "Full Name:COLE Ellie"));
    }

    @Test
    void missingInputFileReturnsErrorCode() throws IOException {
        Path missing = tempDir.resolve("missing.txt");
        Path output = tempDir.resolve("output.txt");

        assertEquals(1, runExpectingIoError(missing, output));
        assertFalse(Files.exists(output));
    }

    @Test
    void unwritableOutputPathReturnsErrorCode() throws IOException {
        Path input = tempDir.resolve("input.txt");
        Path outputDirectory = tempDir.resolve("output-dir");
        Files.writeString(input, "players\n", StandardCharsets.UTF_8);
        Files.createDirectory(outputDirectory);

        assertEquals(1, runExpectingIoError(input, outputDirectory));
    }

// 执行预期失败的 IO 调用，并抑制其写入 stderr 的错误信息。
    private int runExpectingIoError(Path inputPath, Path outputPath) {
        PrintStream originalError = System.err;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setErr(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            return DWASearch.runWithFiles(inputPath, outputPath);
        } finally {
            System.setErr(originalError);
        }
    }

    private static int count(String text, String needle) {
        int occurrences = 0;
        int from = 0;
        while ((from = text.indexOf(needle, from)) != -1) {
            occurrences++;
            from += needle.length();
        }
        return occurrences;
    }
}
