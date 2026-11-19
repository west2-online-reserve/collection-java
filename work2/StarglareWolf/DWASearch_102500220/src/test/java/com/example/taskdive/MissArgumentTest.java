package com.example.taskdive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 测试参数数目不正确、返回非 0 退出码
public class MissArgumentTest {
    @Test
    public void testMissArgument(){
       InvalidInputException exception = assertThrows(
               InvalidInputException.class,
               () -> TaskRunner.run(new String[]{})
       );
        assertTrue(exception.getMessage().contains("Usage: java -jar DWASearch.jar <input_file> <output_file>"));
    }
}
