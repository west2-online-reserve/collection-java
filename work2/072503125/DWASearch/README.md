# DWASearch 项目说明

## 环境版本、构建、运行和测试命令，以及打成可执行 jar 的方式
- 环境要求：JDK 21，Maven 3.8+，VS Code
- 构建命令：`mvn clean package`
- 运行命令：`java "-Dfile.encoding=UTF-8" -jar target/DWASearch-1.0-SNAPSHOT.jar input.txt`
- 测试命令：`mvn test`
- 打包JAR方式：使用 `maven-shade-plugin` 将项目依赖一起打入 `DWASearch-1.0-SNAPSHOT.jar`，该JAR可通过 `java -jar` 独立运行。

## 数据来源
- 赛事：World Aquatics Diving World Cup 2026 - Super Final
- 数据接口：`"https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82"`, `"https://api.worldaquatics.com/fina/events581a26af-8e97-427c-9adb-5fdd8f4fc9c1"`
`"https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d"`, 
`"https://api.worldaquatics.com/fina/eventsc3cca7c9-5ef7-4a69-a173-82fe75ffcc4b"`, 
`"https://api.worldaquatics.com/fina/events4c18d241-ad14-436a-bd38-25dfb47f1cb7"`,
`"https://api.worldaquatics.com/fina/events450a9e84-5db0-40c4-a45c-4c3bb2392f48"`, 
`"https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c"`, 
`"https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5"`
- 抓取时间：2026年9月27日
- 抓包工具：Edge浏览器开发者工具 (F12) -> Network -> Fetch/XHR

## 项目结构
- `model`: 数据实体类(包含 Player,DivingResult,DivingData 把JSON里的数据用Java对象表示)
- `service`: 业务逻辑层 (DataLoader读取本地JSON, PlayerService排序选手, ResultService排序成绩)
- `Main.java`: 程序主入口，读取`input.txt`并分发查询指令

## 运行方式
使用Maven打包成JAR后，在target目录执行：
`java "-Dfile.encoding=UTF-8" -jar DWASearch-1.0-SNAPSHOT.jar input.txt`

## 数据抓取说明
1. 运行src/main/java/com/dwa/crawler/Crawler.java工具类，自动向官方接口发起 HTTP 请求。
2. 脚本会遍历8个比赛项目的UUID，获取各项目决赛（Finals）的真实比赛数据。
3. 将原始JSON结构转换为本项目所需的players和results格式，并保存为本地data.json。
4. 对于双人项目，数据中Competitors数组包含两名选手，脚本会自动提取两人的姓名并分别存入players列表，同时将组合成绩写入results列表。
5. 主程序运行时仅读取本地文件，不再发起网络请求。
6. 自定义 JSON 结构：
  ```json
  {
    "players": [ { "fullName": "...", "gender": "...", "country": "...", "lastName": "..." } ],
    "results": [ { "event": "...", "rank": 1, "diverName": "...", "scores": [73.5, 74.4], "totalScore": 147.9 } ]
  }

## 排序规则
1. `players` 指令：先按 Country 升序，再按 LastName 升序。
2. `result` 指令：按 Rank 升序。

##  已完成功能、空行处理规则和已知限制
- 已完成功能：
  1. 数据抓取：通过 `Crawler` 脚本抓取 World Aquatics 官方接口的真实比赛数据，覆盖考核要求的 8 个比赛项目。
  2. 选手查询：`players` 指令支持按国籍（Country）升序、国籍相同按姓氏（LastName）升序输出全部选手信息。
  3. 成绩查询：`result <event>` 指令支持按排名（Rank）升序输出指定项目的决赛成绩，总分强制保留两位小数。
  4. 异常处理：能够识别并处理未知指令、非法项目名、多余空格等边界情况。
- 空行处理规则：`input.txt` 中的空行会被直接忽略，不产生任何输出，且不影响后续指令的执行。
- 已知限制：数据仅来源于抓取时的数据（本地 `data.json`），不涉及实时数据更新；仅支持考核规定的8个项目查询，限定单词，不支持模糊匹配。

## 抓取、数据读取、命令处理、结果输出分别由哪些脚本、方法或类负责
- 数据抓取：由 `com.dwa.crawler.Crawler` 类负责。它遍历 8 个项目的 UUID，通过网络请求获取原始 JSON，并将其转换为标准结构写入本地 `data.json`。
- 数据读取：由 `com.dwa.service.DataLoader` 类负责。它读取根目录下的 `data.json`，通过 Jackson 库转化为`DivingData` 对象，供后续业务使用。
- 命令处理：由 `com.dwa.Main` 类负责。它读取 `input.txt`，逐行解析命令，根据命令类型（`players` 或 `result`）将任务分发给相应的业务逻辑层。
- 结果输出：
  - `com.dwa.service.PlayerService`：负责处理 `players` 指令，对选手进行多级排序并格式化输出。
  - `com.dwa.service.ResultService`：负责处理 `result` 指令，对决赛成绩进行排序、计算总分（使用 `BigDecimal` 保证精度）并格式化输出。

## 单元测试
使用 JUnit 5 进行测试，测试类位于 `src/test/java/com/dwa/DWASearchTest.java`。
- 测试覆盖：`DWASearchTest.java` 覆盖了“选手多级排序”和“BigDecimal总分计算精度”两个核心行为。
- 实际执行结果：所有测试用例全部通过（绿色）。
- 主要故障处理：
  - 命令行参数缺失：输出错误提示并以非零状态码退出。
  - `data.json` 不存在：输出错误提示并以非零状态码退出。
  - JSON 解析异常：`DataLoader` 捕获异常，主程序输出错误信息并退出。
  - 未知指令/非法参数：输出 `Error` 或 `N/A`，并带分隔符 `----`。