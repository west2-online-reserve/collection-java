import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class DWASearchEntryTest {
    // ---------- 参数数量 ----------

    @Test
    void noArgs_returnsNonZero() {
        assertEquals(1, DWASearch.run(new String[]{}));
    }

    @Test
    void oneArg_returnsNonZero() {
        assertEquals(1, DWASearch.run(new String[]{"only-input.txt"}));
    }

    @Test
    void threeArgs_returnsNonZero() {
        assertEquals(1, DWASearch.run(new String[]{"a", "b", "c"}));
    }

    @Test
    void errorMessageGoesToStderr() {
        PrintStream originalErr = System.err;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        System.setErr(new PrintStream(buf, true, StandardCharsets.UTF_8));
        try {
            DWASearch.run(new String[]{});
        } finally {
            System.setErr(originalErr);
        }
        assertFalse(buf.toString(StandardCharsets.UTF_8).isBlank(), "参数错误时应向 stderr 输出提示信息");
    }

    // ---------- 文件 ----------

    @Test
    void missingInputFile_returnsNonZero(@TempDir Path dir) {
        Path missing = dir.resolve("does-not-exist.txt");
        Path out = dir.resolve("out.txt");
        assertEquals(1, DWASearch.run(new String[]{missing.toString(), out.toString()}));
        assertFalse(Files.exists(out), "输入文件不存在时不应创建输出文件");
    }

    @Test
    void unwritableOutput_returnsNonZero(@TempDir Path dir) throws Exception {
        Path in = dir.resolve("in.txt");
        Files.writeString(in, "players\n");
        Path out = dir.resolve("no-such-dir/out.txt"); // 父目录不存在 → 写入失败
        assertEquals(1, DWASearch.run(new String[]{in.toString(), out.toString()}));
    }

    @Test
    void validArgs_returnsZero(@TempDir Path dir) throws Exception {
        Path in = dir.resolve("in.txt");
        Files.writeString(in, "players\n");
        Path out = dir.resolve("out.txt");
        assertEquals(0, DWASearch.run(new String[]{in.toString(), out.toString()}));
        assertTrue(Files.readString(out).contains("Full Name:COLE Ellie"));
    }

    // ---------- 真实命令行运行：打包后的 jar 也能读输入、写输出 ----------

    @Test
    void packagedJar_readsInputAndWritesOutput(@TempDir Path dir) throws Exception {
        Path jar = Paths.get("target", "DWASearch-1.0-SNAPSHOT.jar");
        Assumptions.assumeTrue(Files.exists(jar),
                "跳过：请先执行 mvn package 生成可执行 jar，再跑本测试");

        Path in = dir.resolve("input.txt");
        Path out = dir.resolve("output.txt");
        Files.writeString(in, "result men 10m synchronised\n");

        Process p = new ProcessBuilder(
                "java", "-jar", jar.toAbsolutePath().toString(),
                in.toAbsolutePath().toString(), out.toAbsolutePath().toString())
                .redirectErrorStream(true)
                .start();
        String log = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        assertTrue(p.waitFor(60, TimeUnit.SECONDS), "打包程序应在 60 秒内结束");
        assertEquals(0, p.exitValue(), "退出码应为 0，日志: " + log);

        String result = Files.readString(out).replace("\r\n", "\n");
        assertTrue(result.startsWith("Full Name:ZHAO Renjie & YANG Zhihao\n"),
                "打包程序应读取输入并生成正确成绩，实际: " + result);
        assertTrue(result.endsWith("-----\n"));
    }
}
