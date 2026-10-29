package com.example.taskdive;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    @Test
    public void testJson(){
        Map<String, String> files = DataUtil.readFiles("Athlete_Contest/Contest/Group");

        assertFalse(files.isEmpty(), "文件夹不应该为空!");
        for(Map.Entry<String, String> entry: files.entrySet()){
            String fileName = entry.getKey();
            String fileContent = entry.getValue();

            if (fileName.endsWith(".json")){
                assertNotNull(fileContent,"文件" + fileName + "是空的");
                assertFalse(fileContent.trim().isEmpty(), "文件" + fileName + "只有空格");

                try{
                    new com.fasterxml.jackson.databind.ObjectMapper().readTree(fileContent);
                }
                catch (Exception e){
                    fail("文件 [" + fileName + "] 的 JSON 格式已经损坏!\n" + e.getMessage());
                }
            }
        }
    }

}
