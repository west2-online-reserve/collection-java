package com.dwa.service;

import com.dwa.model.DivingData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class DataLoader {
    public static DivingData loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // 读取最外层的 data.json
            return mapper.readValue(new File("data.json"), DivingData.class);
        } catch (Exception e) {
            System.out.println("解析 data.json 失败：" + e.getMessage());
            return null;
        }
    }
}