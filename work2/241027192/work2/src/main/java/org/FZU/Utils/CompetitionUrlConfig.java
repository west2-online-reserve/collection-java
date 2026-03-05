package org.FZU.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CompetitionUrlConfig {
    private static final Properties properties = new Properties();
    private static final Map<String, String> urlMap = new LinkedHashMap<>();
    static {
        loadConfig();
    }

    private static void loadConfig() {
        try (InputStream input = CompetitionUrlConfig.class.getClassLoader()
                .getResourceAsStream("competition.properties")) {
            if (input == null) {
                throw new IOException("找不到配置文件: competition.properties");
            }
            properties.load(input);
            initializeUrlMap();
        } catch (IOException e) {
            System.err.println("加载配置文件失败: " + e.getMessage());
        }
    }

    private static void initializeUrlMap() {
        // 关键词到配置的映射
        Map<String, String> keywordConfig = new LinkedHashMap<>();
        keywordConfig.put("players","player.info.url");
        keywordConfig.put("result women 1m springboard", "women.1m.springboard.url");
        keywordConfig.put("result women 3m springboard", "women.3m.springboard.url");
        keywordConfig.put("result women 10m platform", "women.10m.platform.url");
        keywordConfig.put("result women 3m synchronised", "women.3m.synchronised.url");
        keywordConfig.put("result women 10m synchronised", "women.10m.synchronised.url");
        keywordConfig.put("result women 1m springboard detail", "women.1m.springboard.url");
        keywordConfig.put("result women 3m springboard detail", "women.3m.springboard.url");
        keywordConfig.put("result women 10m platform detail", "women.10m.platform.url");
        keywordConfig.put("result women 3m synchronised detail", "women.3m.synchronised.url");
        keywordConfig.put("result women 10m synchronised detail", "women.10m.synchronised.url");
        keywordConfig.put("result men 1m springboard", "men.1m.springboard.url");
        keywordConfig.put("result men 3m springboard","men.3m.springboard.url");
        keywordConfig.put("result men 10m platform", "men.10m.platform.url" );
        keywordConfig.put("result men 3m synchronised","men.3m.synchronised.url");
        keywordConfig.put("result men 10m synchronised", "men.10m.synchronised.url");
        keywordConfig.put("result men 1m springboard detail", "men.1m.springboard.url");
        keywordConfig.put("result men 3m springboard detail","men.3m.springboard.url");
        keywordConfig.put("result men 10m platform detail", "men.10m.platform.url" );
        keywordConfig.put("result men 3m synchronised detail","men.3m.synchronised.url");
        keywordConfig.put("result men 10m synchronised detail", "men.10m.synchronised.url");
        for (Map.Entry<String, String> entry : keywordConfig.entrySet()) {
            String keyword = entry.getKey();
            String configKeys = entry.getValue();

            String url = properties.getProperty(configKeys);

            if (url != null) {
                urlMap.put(keyword, url);
            }
        }
    }

    public static Set<String> getUrlMapKey() {
        return urlMap.keySet();
    }

    public static String findUrlByTarget(String target) {
        for (Map.Entry<String, String> entry : urlMap.entrySet()) {
            if (target.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}