package com.west2online.dwasearch.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.west2online.dwasearch.model.CompetitionData;

import java.io.IOException;
import java.io.InputStream;

/**
 * 负责读取 data.json（数据快照）并解析成 CompetitionData 对象。
 *
 * 数据出问题时抛出 DataException，绝不返回一个「看似正常」的空结果。
 */
public class DataLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    /** 从 classpath 读取 data.json（打包进 jar 的那份快照）。 */
    public CompetitionData loadFromClasspath() {
        InputStream in = DataLoader.class.getResourceAsStream("/data.json");
        if (in == null) {
            throw new DataException("数据文件 data.json 不存在");
        }
        try (in) {
            return validate(mapper.readValue(in, CompetitionData.class));
        } catch (IOException e) {
            throw new DataException("数据文件 data.json 无法解析: " + e.getMessage(), e);
        }
    }

    /** 从 JSON 字符串解析（主要给测试用，方便传入坏数据验证失败路径）。 */
    public CompetitionData parse(String json) {
        try {
            return validate(mapper.readValue(json, CompetitionData.class));
        } catch (IOException e) {
            throw new DataException("数据 JSON 无法解析: " + e.getMessage(), e);
        }
    }

    /** 校验数据是否具备查询所需的字段，缺了就明确失败。 */
    private CompetitionData validate(CompetitionData data) {
        if (data == null || data.getPlayers() == null) {
            throw new DataException("数据缺少 players 字段");
        }
        if (data.getResults() == null) {
            throw new DataException("数据缺少 results 字段");
        }
        for (String event : Events.NAMES) {
            if (data.getResults().get(event) == null) {
                throw new DataException("数据缺少项目结果: " + event);
            }
        }
        return data;
    }
}
