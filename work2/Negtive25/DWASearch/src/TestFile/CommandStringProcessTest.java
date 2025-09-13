package com.src.Lib;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandStringProcessTest {

    // 测试正常路径（happy path）
    @Test
    public void testCommandDistinguish() {
        CommandStringProcess processor = new CommandStringProcess();

        // 测试有效的命令
        assertEquals(10, processor.commandDistinguish("players"));
        assertEquals(21, processor.commandDistinguish("result women 1m springboard"));
        assertEquals(121, processor.commandDistinguish("result women 1m springboard detail"));
        assertEquals(31, processor.commandDistinguish("result men 1m springboard"));
        assertEquals(134, processor.commandDistinguish("result men 3m synchronised detail"));
    }

    // 不合理命令
    @Test
    public void testCommandDistinguishInvalidCommand() {
        CommandStringProcess processor = new CommandStringProcess();

        // 测试空命令
        assertEquals(0, processor.commandDistinguish(""));

        // 测试未识别的命令
        assertEquals(-1, processor.commandDistinguish("unknown command"));

        // 测试只包含"result"的命令
        assertEquals(1, processor.commandDistinguish("result"));

        // 测试不完整的命令
        assertEquals(1, processor.commandDistinguish("result women"));

        assertEquals(1, processor.commandDistinguish("result men invalid"));
    }
}
