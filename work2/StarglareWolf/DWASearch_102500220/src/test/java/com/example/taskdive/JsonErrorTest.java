package com.example.taskdive;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


public class JsonErrorTest {

    // 测试文件问题
    @Test
    public void testCorruptedJsonThrowsException(){
        String badJson = "{\"CountryName\" : \"Full Name\",……broken";

        ObjectMapper mapper = new ObjectMapper();
        assertThrows(IOException.class, () ->{mapper.readTree(badJson);},"损坏的 json 应该抛出异常");
    }

    @Test
    public void testMissing(){
        String missing = "{  }" ;

        ObjectMapper mapper = new ObjectMapper();

        assertDoesNotThrow(() -> {
            var root = mapper.readTree(missing);
            assertNull(root.get("CountryName"));
        });
    }

}
