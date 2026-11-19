import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import utils.Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DWASearchOutputFailureTest {
    @Test
    void bufferedWriteFailsOnClose_reportsIncompleteOutput(@TempDir Path dir) throws Exception {
        FailingOutputStream stream = new FailingOutputStream(true, false);
        RunResult result = runWithOutput(dir, "players\n", bufferedWriter(stream, 65536));

        assertTrue(Boolean.TRUE.equals(stream.firstWriteDuringClose),
                "单条查询应直到关闭并刷出缓冲区时才触发写入失败");
        assertTrue(stream.bufferedCloseStarted, "刷出缓冲区失败后仍应尝试关闭输出流");
        assertFailure(result, "模拟写入失败");
    }

    @Test
    void writeFailsDuringProcessing_reportsIncompleteOutput(@TempDir Path dir) throws Exception {
        FailingOutputStream stream = new FailingOutputStream(true, false);
        RunResult result = runWithOutput(dir, "players\n".repeat(20), bufferedWriter(stream, 128));

        assertTrue(Boolean.FALSE.equals(stream.firstWriteDuringClose), "应在处理命令期间触发写入失败");
        assertTrue(stream.bufferedCloseStarted, "写入失败后仍应尝试关闭输出流");
        assertFailure(result, "模拟写入失败");
    }

    @Test
    void closeFails_reportsIncompleteOutput(@TempDir Path dir) throws Exception {
        FailingOutputStream stream = new FailingOutputStream(false, true);
        RunResult result = runWithOutput(dir, "players\n", bufferedWriter(stream, 65536));

        assertTrue(stream.bytesWritten > 0, "写入应成功，只有关闭操作失败");
        assertTrue(stream.closed, "应尝试关闭输出流");
        assertFailure(result, "模拟关闭失败");
    }

    @Test
    void utilityClose_bothStreamsFail_attemptsBothAndPreservesExceptions() {
        boolean[] readerClosed = {false};
        FailingOutputStream stream = new FailingOutputStream(false, true);
        Utility.br = new BufferedReader(new StringReader("")) {
            @Override
            public void close() throws IOException {
                readerClosed[0] = true;
                super.close();
                throw new IOException("模拟输入关闭失败");
            }
        };
        Utility.bw = bufferedWriter(stream, 128);
        try {
            IOException failure = assertThrows(IOException.class, Utility::close);
            assertTrue(readerClosed[0], "应尝试关闭输入流");
            assertTrue(stream.closed, "另一个流关闭失败时仍应尝试关闭输出流");
            assertEquals("模拟关闭失败", failure.getMessage());
            assertEquals(1, failure.getSuppressed().length);
            assertEquals("模拟输入关闭失败", failure.getSuppressed()[0].getMessage());
        } finally {
            Utility.br = null;
            Utility.bw = null;
        }
    }

    private static BufferedWriter bufferedWriter(FailingOutputStream stream, int bufferSize) {
        return new BufferedWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8), bufferSize) {
            @Override
            public void close() throws IOException {
                stream.bufferedCloseStarted = true;
                super.close();
            }
        };
    }

    private static RunResult runWithOutput(Path dir, String input, BufferedWriter output) throws IOException {
        Path in = dir.resolve("input.txt");
        Files.writeString(in, input, StandardCharsets.UTF_8);
        ByteArrayOutputStream errors = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        try (PrintStream stderr = new PrintStream(errors, true, StandardCharsets.UTF_8)) {
            System.setErr(stderr);
            int code = DWASearch.run(new String[]{in.toString(), dir.resolve("output.txt").toString()},
                    path -> output);
            return new RunResult(code, errors.toString(StandardCharsets.UTF_8));
        } finally {
            System.setErr(originalErr);
        }
    }

    private static void assertFailure(RunResult result, String reason) {
        assertNotEquals(0, result.code(), "输出失败时不能返回成功");
        assertTrue(result.stderr().contains("处理失败"), result.stderr());
        assertTrue(result.stderr().contains("输出可能不完整"), result.stderr());
        assertTrue(result.stderr().contains(reason), "应保留具体失败原因: " + result.stderr());
    }

    private record RunResult(int code, String stderr) {}

    private static class FailingOutputStream extends OutputStream {
        private final boolean failOnWrite;
        private final boolean failOnClose;
        private boolean bufferedCloseStarted;
        private Boolean firstWriteDuringClose;
        private boolean closed;
        private int bytesWritten;

        private FailingOutputStream(boolean failOnWrite, boolean failOnClose) {
            this.failOnWrite = failOnWrite;
            this.failOnClose = failOnClose;
        }

        @Override
        public void write(int value) throws IOException {
            write(new byte[]{(byte) value}, 0, 1);
        }

        @Override
        public void write(byte[] bytes, int offset, int length) throws IOException {
            if (firstWriteDuringClose == null) {
                firstWriteDuringClose = bufferedCloseStarted;
            }
            if (failOnWrite) {
                throw new IOException("模拟写入失败");
            }
            bytesWritten += length;
        }

        @Override
        public void close() throws IOException {
            closed = true;
            if (failOnClose) {
                throw new IOException("模拟关闭失败");
            }
        }
    }
}
