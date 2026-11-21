package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DWASearchTest {

    @Test
    void testArgCountLess() {
        String[] args = {};
        RuntimeException ex = assertThrows(RuntimeException.class, () -> DWASearch.run(args));
        assertTrue(ex.getMessage().contains("命令行参数数量不对"));
    }

    @Test
    void testArgCountMore() {
        String[] args = {"a.txt", "b.txt", "c.txt"};
        RuntimeException ex = assertThrows(RuntimeException.class, () -> DWASearch.run(args));
        assertTrue(ex.getMessage().contains("命令行参数数量不对"));
    }

    /*@Test
    void testDataJsonNotExist() {
        String[] args = {"in.txt", "out.txt"};
        RuntimeException ex = assertThrows(RuntimeException.class, () -> DWASearch.run(args));
        assertTrue(ex.getMessage().contains("数据文件不存在"));
    }*/

    @Test
    void testInputFileNotExist() {
        String[] args = {"no_input.txt", "out.txt"};
        RuntimeException ex = assertThrows(RuntimeException.class, () -> DWASearch.run(args));
        assertTrue(ex.getMessage().contains("输入文件不存在"));
    }
}
