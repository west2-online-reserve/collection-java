package com.diving;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Crawler {
    public static void main(String[] args) {
        // 1. 8个项目的URL存进数组
        String[] eventUrls = {
            "https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82", // women 3m springboard
            "https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1", // women 10m platform
            "https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d", // women 3m synchronised
            "https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b", // women 10m synchronised
            "https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7", // men 3m springboard
            "https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48", // men 10m platform
            "https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c", // men 3m synchronised
            "https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5"  // men 10m synchronised
        };
        
        ObjectMapper mapper = new ObjectMapper(); // 实例化Jackson
        ArrayNode allPlayers = mapper.createArrayNode(); // 存放所有选手
        ArrayNode allResults = mapper.createArrayNode(); // 存放所有成绩记录
        HttpClient client = HttpClient.newHttpClient();
        
        // 2. 循环抓取每一个URL
        for (String url : eventUrls) {
            try {
                System.out.println("正在抓取: " + url);
                
                // 构建器模式发起网络请求
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url)) // 设置请求的地址
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0") // 添加请求头伪装
                        .GET() // 设置请求方法为GET
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // 同步请求，转字符串
                
                if (response.statusCode() != 200) { // 200 状态码代表成功
                    System.err.println("抓取失败，状态码: " + response.statusCode());
                    continue;
                }

                JsonNode root = mapper.readTree(response.body()); // 将JSON文本转成树模型
                
                // 3. 转换项目名称的格式（例如 Women's 3m Springboard -> women 3m springboard）
                String rawName = root.path("DisciplineName").asText(); 
                String eventName = rawName.toLowerCase().replace("'s", "").replace("'", "").trim();
                
                // 4. 找到 Finals（决赛）轮次的数据
                JsonNode finalsResults = null;
                for (JsonNode heat : root.path("Heats")) {
                    if ("Finals".equals(heat.path("Name").asText())) {
                        finalsResults = heat.path("Results"); // 抓到真实的成绩数组
                        break;
                    }
                }
                
                if (finalsResults == null) {
                    System.err.println("未找到 Finals 数据: " + url);
                    continue;
                }

                // 5. 遍历该决赛项目的所有参赛者
                for (JsonNode r : finalsResults) {
                    String fullName = r.path("FullName").asText();
                    String lastName = r.path("LastName").asText();
                    int rank = r.path("Rank").asInt();
                    String nat = r.path("NAT").asText(); // 国家代码，如 CHN
                    int gender = r.path("Gender").asInt(); // 1 为女子，2 为男子

                    // 6. 判断是单人还是双人项目（看 Competitors 数组是否有内容）
                    JsonNode competitors = r.path("Competitors");
                    boolean isSynchronised = competitors.isArray() && competitors.size() > 0;
                    
                    if (isSynchronised) {
                        // 遍历 Competitors 提取两人的信息
                        for (JsonNode comp : competitors) {
                            ObjectNode playerNode = mapper.createObjectNode(); // 创建空 JSON 对象
                            playerNode.put("fullName", comp.path("FullName").asText());
                            playerNode.put("gender", gender == 1 ? "Female" : "Male");
                            playerNode.put("country", comp.path("NAT").asText());
                            playerNode.put("lastName", comp.path("LastName").asText());
                            allPlayers.add(playerNode); 
                        }
                    } else {
                        //直接提取选手信息
                        ObjectNode playerNode = mapper.createObjectNode();
                        playerNode.put("fullName", fullName);
                        playerNode.put("gender", gender == 1 ? "Female" : "Male");
                        playerNode.put("country", nat);
                        playerNode.put("lastName", lastName);
                        allPlayers.add(playerNode);
                    }
                    
                    // 7. 无论单人还是双人，都往 results 列表加一条成绩记录
                    ObjectNode resultNode = mapper.createObjectNode();
                    resultNode.put("event", eventName); // 项目名
                    resultNode.put("rank", rank); // 排名
                    resultNode.put("diverName", fullName); // 成绩单上的名字
                    
                    // 提取每一跳的成绩
                    ArrayNode scoresArray = mapper.createArrayNode();
                    for (JsonNode dive : r.path("Dives")) {
                        //如果不存在或格式错误，返回默认值 0.0
                        scoresArray.add(dive.path("DivePoints").asDouble(0.0));
                    }
                    resultNode.set("scores", scoresArray); // 封装进 scores 字段
                    
                    //如果不存在或格式错误，返回默认值 0.0
                    resultNode.put("totalScore", r.path("TotalPoints").asDouble(0.0)); 
                    
                    allResults.add(resultNode); 
                }
                System.out.println("成功抓取: " + eventName + "，记录数: " + finalsResults.size());
                
                Thread.sleep(500); //延迟500ms爬取
                
            } catch (Exception e) {
                System.err.println("抓取 " + url + " 发生异常: " + e.getMessage());
            }
        }
        
        // 8. 组装最终结构并写入 data.json
        try {
            ObjectNode finalData = mapper.createObjectNode();
            finalData.set("players", allPlayers); // 放入所有选手
            finalData.set("results", allResults); // 放入所有成绩
            
            // writerWithDefaultPrettyPrinter 使 JSON 文件美化、可读
            String finalJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(finalData);
            
            // 直接将字符串写入根目录的 data.json
            Files.writeString(Paths.get("data.json"), finalJson);
            System.out.println("\n======== 全部抓取完成， data.json 已更新 ========");
            System.out.println("共包含 " + allPlayers.size() + " 条选手数据，" + allResults.size() + " 条成绩数据。");
            
        } catch (Exception e) {
            System.err.println("写入 data.json 失败: " + e.getMessage());
        }
    }
}