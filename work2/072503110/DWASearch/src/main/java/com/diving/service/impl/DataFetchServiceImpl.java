package com.diving.service.impl;

import com.diving.exception.DataAccessException;
import com.diving.model.Player;
import com.diving.service.DataFetchService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataFetchServiceImpl implements DataFetchService {
    private static final Logger logger = LoggerFactory.getLogger(DataFetchServiceImpl.class);
    private static final String DATA_FILE = "data.json";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Player> fetchData() {
        List<Player> list = new ArrayList<>();
        File file = new File(DATA_FILE);
        
        if (!file.exists()) {
            logger.error("找不到 {}，请先运行 Crawler.java 抓取数据！", DATA_FILE);
            return list;
        }

        try {
            JsonNode root = mapper.readTree(file);

            // 1. 读取 players 数组
            for (JsonNode p : root.path("players")) {
                // 先取出字符串，如果为空就为默认值 "N/A"
                String nameStr = p.path("fullName").asText();//asText将JavaNode里的数据转为字符串
                String genderStr = p.path("gender").asText();
                String countryStr = p.path("country").asText();

                if (genderStr.isEmpty()) genderStr = "N/A";
                if (countryStr.isEmpty()) countryStr = "N/A";

                list.add(new Player(nameStr, genderStr, countryStr));
            }

            // 2. 读取 results 数组
            for (JsonNode r : root.path("results")) {
                List<Double> scores = new ArrayList<>();
                for (JsonNode s : r.path("scores")) {
                    scores.add(s.asDouble());
                }
                list.add(new Player(
                    r.path("event").asText(),
                    r.path("rank").asInt(),
                    r.path("diverName").asText(),
                    scores,
                    r.path("totalScore").asDouble()
                ));
            }
            logger.info("成功从 data.json 加载了 {} 条真实数据。", list.size());
        } catch (Exception e) {
            throw new DataAccessException("解析 data.json 失败", e);
        }
        return list;
    }
}