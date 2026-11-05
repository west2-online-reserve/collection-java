package dwasearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试"读文件 -> 处理 -> 写文件"的完整链路。
 *
 * <p>被测方法 {@link DWASearch#process} 只抛 IOException、不调用 System.exit，
 * 因此可以在测试 JVM 中直接执行。
 */
public class FileIOTest {

    /** JUnit 提供的临时目录，测试结束后自动清理，不依赖本机路径。 */
    @TempDir
    Path tempDir;

    @Test
    void emptyInputProducesEmptyOutput() throws Exception {
        Path in = tempDir.resolve("in.txt");
        Path out = tempDir.resolve("out.txt");
        Files.writeString(in, "", StandardCharsets.UTF_8);   // 空文件

        DWASearch.process(in, out, new CommandRunner(DataLoader.load()));

        assertEquals("", Files.readString(out, StandardCharsets.UTF_8));
    }

    @Test
    void threeCommandsProduceThreeRecords() throws Exception {
        Path in = tempDir.resolve("in.txt");
        Path out = tempDir.resolve("out.txt");
        Files.writeString(in, "players\nplayer\nresult\n", StandardCharsets.UTF_8);

        DWASearch.process(in, out, new CommandRunner(DataLoader.load()));

        String outText = Files.readString(out, StandardCharsets.UTF_8);
        assertTrue(outText.startsWith("Full Name:COLE Ellie"));
        assertTrue(outText.endsWith("-----\n"), "最后一条后面也要有分隔线");
        assertEquals(1, countOccurrences(outText, "Error"), "应该有 1 处 Error");
        assertEquals(1, countOccurrences(outText, "N/A"), "应该有 1 处 N/A");
    }

    @Test
    void outputUsesLfNotCrlf() throws Exception {
        Path in = tempDir.resolve("in.txt");
        Path out = tempDir.resolve("out.txt");
        Files.writeString(in, "players\n", StandardCharsets.UTF_8);

        DWASearch.process(in, out, new CommandRunner(DataLoader.load()));

        String raw = Files.readString(out, StandardCharsets.UTF_8);
        assertFalse(raw.contains("\r"), "输出里不能有 \\r，必须是纯 LF 换行");
    }

    /** 数一个子串在文本里出现了几次 */
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
