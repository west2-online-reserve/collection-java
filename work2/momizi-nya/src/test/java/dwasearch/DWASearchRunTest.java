package dwasearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DWASearchRunTest {

    @TempDir
    Path tmp;

    private Path realData() {
        Path p = Paths.get(System.getProperty("user.dir"), "data.json");
        assertTrue(Files.exists(p), "测试需要项目根目录的 data.json");
        return p;
    }

    private PrintStream err(ByteArrayOutputStream buf) {
        return new PrintStream(buf, true, StandardCharsets.UTF_8);
    }

    @Test
    public void testWrongArgs() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int code = DWASearch.run(new String[]{"only-one"}, err(buf));
        assertEquals(DWASearch.ERR_ARGS, code);
        assertTrue(buf.toString(StandardCharsets.UTF_8).contains("用法"));
    }

    @Test
    public void testInputNotExist() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        String[] args = {tmp.resolve("nope.txt").toString(), tmp.resolve("o.txt").toString()};
        int code = DWASearch.run(args, err(buf));
        assertEquals(DWASearch.ERR_INPUT, code);
        assertTrue(buf.toString(StandardCharsets.UTF_8).contains("输入文件不存在"));
    }

    @Test
    public void testBadData() throws IOException {
        Path bad = tmp.resolve("bad.json");
        Files.writeString(bad, "{oops", StandardCharsets.UTF_8);
        Path in = tmp.resolve("in.txt");
        Files.writeString(in, "players\n", StandardCharsets.UTF_8);

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int code = DWASearch.run(in, tmp.resolve("o.txt"), bad, err(buf));
        assertEquals(DWASearch.ERR_DATA, code);
        assertTrue(buf.toString(StandardCharsets.UTF_8).contains("数据文件出错"));
        assertFalse(Files.exists(tmp.resolve("o.txt")));
    }

    @Test
    public void testOutputCantWrite() throws IOException {
        Path in = tmp.resolve("in.txt");
        Files.writeString(in, "players\n", StandardCharsets.UTF_8);
        Path dir = tmp.resolve("a-dir");
        Files.createDirectory(dir);

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int code = DWASearch.run(in, dir, realData(), err(buf));
        assertEquals(DWASearch.ERR_OUTPUT, code);
        assertTrue(buf.toString(StandardCharsets.UTF_8).contains("写出输出文件失败"));
    }

    @Test
    public void testRunWithRealData() throws IOException {
        Path in = tmp.resolve("input.txt");
        Files.writeString(in,
                "players\nresult women 3m springboard\n\nhello\nresult\nresult men 10m platform\n",
                StandardCharsets.UTF_8);
        Path out = tmp.resolve("output.txt");

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int code = DWASearch.run(in, out, realData(), err(buf));
        assertEquals(DWASearch.OK, code, buf.toString(StandardCharsets.UTF_8));

        String text = Files.readString(out, StandardCharsets.UTF_8);
        assertTrue(text.startsWith("Full Name:"));

        assertTrue(text.contains("Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40"));

        assertTrue(text.contains("Error\n-----"));
        assertTrue(text.contains("N/A\n-----"));

        assertFalse(text.contains("\r"));
        assertTrue(text.endsWith("-----\n"));
    }

    @Test
    public void testEmptyInput() throws IOException {
        Path in = tmp.resolve("empty.txt");
        Files.writeString(in, "", StandardCharsets.UTF_8);
        Path out = tmp.resolve("out.txt");

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int code = DWASearch.run(in, out, realData(), err(buf));
        assertEquals(DWASearch.OK, code);
        assertEquals("", Files.readString(out, StandardCharsets.UTF_8));
    }

    @Test
    public void testDuplicateCommand() throws IOException {
        Path in = tmp.resolve("dup.txt");
        Files.writeString(in, "result men 3m synchronised\nresult men 3m synchronised\n",
                StandardCharsets.UTF_8);
        Path out = tmp.resolve("dup-out.txt");

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int code = DWASearch.run(in, out, realData(), err(buf));
        assertEquals(DWASearch.OK, code);

        String text = Files.readString(out, StandardCharsets.UTF_8);

        int n = 0;
        int index = 0;
        while (true) {
            int i = text.indexOf("Full Name:", index);
            if (i == -1) {
                break;
            }
            n++;
            index = i + 10;
        }
        assertEquals(16, n);
    }
}
