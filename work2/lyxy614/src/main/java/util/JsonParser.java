package main.java.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class JsonParser {
    public static <T> List<T> parserArray(String resourceFile, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            List<T> t = objectMapper.readValue(
                    resourceFile,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, targetClass)
            );
            return t;
        }catch (IOException e) {
            // 处理可能的异常（文件不存在、JSON格式错误、字段不匹配等）
            System.err.println("解析失败：" + e.getMessage());
            return null;
        }
    }

    public static <T> T parser(String jsonFile, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(jsonFile, targetClass);
        }catch (IOException e){
            System.out.println("json文件解析失败：" + e.getMessage());
            return null;
        }
    }
}
