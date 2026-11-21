package com.dwasearch.query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DWASearchTest {
    private final DWASearch app = new DWASearch();
    @TempDir
    Path tempDir;

    @Test
    @DisplayName("参数数量不对时返回非零退出码")
    void execute_wrongArgsCount() {
        assertEquals(1, app.execute(new String[]{"input.txt"}, Paths.get("data.json")));
    }

    @Test
    @DisplayName("数据文件无法读取返回非零退出码")
    void execute_dataSnapshotNotReadable(){
        assertEquals(2, app.execute(new String[]{"input.txt", "output.txt"}, Paths.get("bad_data.json")));
    }

    @Test
    @DisplayName("data.json数据不完整返回非零退出码")
    void execute_dataSnapshotBroken() throws Exception {
        //读取用于测试检验类的坏数据文件
        Path badDataPath = Paths.get(
                getClass().getClassLoader().getResource("competition_missing_Finals.json").toURI()
        );

        assertEquals(2, app.execute(new String[]{"input.txt", "output.txt"}, badDataPath));
    }

    @Test
    @DisplayName("输入文件不存在时返回非零退出码")
    void execute_inputFileMissing() {
        Path out = tempDir.resolve("out.txt");
        int code = app.execute(new String[]{"no_such_file.txt", out.toString()}, Paths.get("data.json"));
        assertEquals(1, code);
    }

    @Test
    @DisplayName("输出路径不可写时返回非零退出码")
    void execute_outputNotWritable() {
        Path in = tempDir.resolve("in.txt");
        Path badOut = tempDir.resolve("no_such_dir").resolve("out.txt");
        int code = app.execute(new String[]{in.toString(), badOut.toString()}, Paths.get("data.json"));
        assertEquals(1, code);
    }
}