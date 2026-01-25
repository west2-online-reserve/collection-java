## 模块接口设计与实现
### 项目结构
项目核心功能封装在 `CoreModule` 类中，配合主程序 `DWASearch.java` 处理命令行参数
- **CoreModule**：核心模块，读取、解析数据，输出结果
- **DWASearch**：主程序入口，处理命令行参数
- **辅助类**：`Player`、`Result`、`DetailedResult` ，封装数据实体

### 核心模块
#### 读取数据：`getJsonContent(String eventName)`
读取指定赛事/选手的 JSON 数据文件内容  

#### 输出所有选手信息：`displayAllPlayersInfo(String outputPath)`
解析 `players.json`，按国籍升序、姓氏升序输出所有选手信息
1. 读取 `players.json` 内容并解析为 JSON 数组
2. 遍历国家列表，提取每个国家的参赛选手信息（姓名、性别、国籍）
3. 封装为 `Player` 对象，按格式写入输出文件
```java
        String jsonContent = getJsonContent("players");
        //解析数据
        JSONArray countries = JSONArray.parseArray(jsonContent);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))){
            for (int i = 0; i < countries.size(); i++) {
                JSONObject country = countries.getJSONObject(i);
                
                String countryName = country.getString("CountryName");
                JSONArray participations = country.getJSONArray("Participations");
                for (int j = 0; j < participations.size(); j++) {
                    JSONObject paticipation = participations.getJSONObject(j);
                    
                    String gender = paticipation.getBoolean("Gender") ? "Female" : "Male";
                    String preferredLastName = paticipation.getString("PreferredLastName");
                    String preferredFirstName = paticipation.getString("PreferredFirstName");
                    
                    Player player = new Player(preferredLastName, preferredFirstName, gender, countryName);
                    bw.write(player.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
```


#### 输出比赛结果：`displayResultsForEachEvent(String outputPath, String eventName, boolean isDetailed)`
根据赛事名称和是否详细输出的标志，生成对应比赛结果
1. 读取对应赛事的 JSON 文件（如 `women 1m springboard.json`）
2. 解析 Heats 数据，按预赛、半决赛、决赛分类存储结果
```java
    String jsonContent = getJsonContent(eventName);
        //解析数据
        Map<String, List<Result>> heatMap = new HashMap<>();

        JSONObject event = JSONObject.parseObject(jsonContent);
        JSONArray heats = event.getJSONArray("Heats");
        for (int i = 0; i < heats.size(); i++) {
            JSONObject heat = heats.getJSONObject(i);
            String name = heat.getString("Name");

            List<Result> resultList = new ArrayList<>();
            JSONArray results = heat.getJSONArray("Results");
            for (int j = 0; j < results.size(); j++) {
                JSONObject result = results.getJSONObject(j);
                String fullName = result.getString("FullName").replace('/', '&');
                int rank = result.getInteger("Rank");
                String totalPoint = result.getString("TotalPoints");

                String points = "";
                JSONArray dives = result.getJSONArray("Dives");
                for (int k = 0; k < dives.size(); k++) {
                    JSONObject dive = dives.getJSONObject(k);
                    points += dive.getString("TotalPoints");
                    if (k < dives.size() - 1) {
                        points += " + ";
                    }
                }
                points += (" = " + totalPoint);
                resultList.add(new Result(fullName, rank, points));
            }
            heatMap.put(name, resultList);
        }
```
3. 非详细模式：仅输出决赛结果，封装为 `Result` 对象
```java
            List<Result> finalResults = heatMap.get("Final");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))) {     
                for (Result result : finalResults) {
                    bw.write(result.toString());
                }
                }catch (IOException e) {
                throw new RuntimeException(e);
            }
```
4. 详细模式：输出预赛、半决赛、决赛全结果，封装为 `DetailedResult` 对象
```java
            Map<String, Result> preliminaryMap = new HashMap<>();
            Map<String, Result> semifinalMap = new HashMap<>();
            Map<String, Result> finalMap = new HashMap<>();
            List<String> firstHeatNames = new ArrayList<>();

            List<Result> preliminaryList = heatMap.get("Preliminary");
            if (preliminaryList != null) {
                for (Result result : preliminaryList) {
                    preliminaryMap.put(result.getFullName(), result);
                    firstHeatNames.add(result.getFullName());
                }
            }
            List<Result> semifinalList = heatMap.get("Semifinal");
            if (semifinalList != null) {
                for (Result result : semifinalList) {
                    semifinalMap.put(result.getFullName(), result);
                    if (preliminaryList == null) {
                        firstHeatNames.add(result.getFullName());
                    }
                }
            }
            List<Result> finalList = heatMap.get("Final");
            if (finalList != null) {
                for (Result result : finalList) {
                    finalMap.put(result.getFullName(), result);
                    if (preliminaryList == null && semifinalList == null) {
                        firstHeatNames.add(result.getFullName());
                    }
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))){
                for (String fullName : firstHeatNames) {
                    Result preliminaryResult = preliminaryMap.get(fullName);
                    Result semifinalResult = semifinalMap.get(fullName);
                    Result finalResult = finalMap.get(fullName);

                    int preliminaryRank = (preliminaryResult == null) ? 0 : preliminaryResult.getRank();
                    int semifinalRank = (semifinalResult == null) ? 0 : semifinalResult.getRank();
                    int finalRank = (finalResult == null) ? 0 : finalResult.getRank();
                    String preliminaryScores = (preliminaryResult == null) ? "*" : preliminaryResult.getScores();
                    String semifinalScores = (semifinalResult == null) ? "*" : semifinalResult.getScores();
                    String finalScores = (finalResult == null) ? "*" : finalResult.getScores();

                    DetailedResult detailedResult = new DetailedResult(fullName, preliminaryRank, semifinalRank, finalRank,
                            preliminaryScores, semifinalScores, finalScores);
                    bw.write(detailedResult.toString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
```
### 算法关键
- 使用 FastJSON 快速解析 JSON 数据，通过键值对直接提取所需字段（如 `FullName`、`Rank`、`TotalPoints`）
- 通过 `HashMap` 存储预赛、半决赛、决赛的中间结果，按选手首次参赛顺序关联多轮次数据，确保输出顺序正确
- 所有文件操作使用缓冲流（`BufferedReader`、`BufferedWriter`），减少磁盘 IO 次数
- 写入文件时采用追加模式

## 单元测试
```java
    // 命令行参数数量错误
    @Test
    void testInvalidArgsCount() throws IOException {
        DWASearch.main(new String[]{"input.txt"});
        assertTrue(readOutputFile().equals(""));
    }

    // 空输入文件
    @Test
    void testEmptyInput() throws IOException {
        writeInputFile("");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        assertTrue(readOutputFile().equals(""));
    }

    // 有效players命令
    @Test
    void testValidPlayersCommand() throws IOException {
        writeInputFile("players");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertFalse(output.contains("Error"));
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Gender:"));
        assertTrue(output.contains("Country:"));
    }

    // 有效result命令（不带detail）
    @Test
    void testValidResultWithoutDetail() throws IOException {
        writeInputFile("result women 10m platform");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertFalse(output.contains("Error"));
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Rank:"));
        assertTrue(output.contains("Score:"));
    }

    //有效result命令（带detail）
    @Test
    void testValidResultWithDetail() throws IOException {
        writeInputFile("result women 10m platform detail");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertFalse(output.contains("Error"));
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Rank:"));
        assertTrue(output.contains("Preliminary Score:"));
        assertTrue(output.contains("Semifinal Score:"));
        assertTrue(output.contains("Final Score:"));
    }

    // 无效指令
    @Test
    void testInvalidCommand() throws IOException {
        writeInputFile("invalidCommand");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertTrue(output.contains("Error"));
    }

    // result格式错误
    @Test
    void testInvalidResultFormat() throws IOException {
        writeInputFile("result women invalidEvent");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertTrue(output.contains("N/A"));
    }
```
