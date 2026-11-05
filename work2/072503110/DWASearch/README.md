# DWASearch 项目说明

## 1. 环境版本与运行方式
- 环境要求：JDK 21，Maven 3.8+，VS Code
- 构建命令：`mvn clean package`
- 运行命令：`java "-Dfile.encoding=UTF-8" -jar target/DWASearch-1.0-SNAPSHOT.jar input.txt`
- 测试命令：`mvn test`
- 打包方式：使用 `maven-shade-plugin` 将项目依赖打入 `DWASearch-1.0-SNAPSHOT.jar`，支持通过 `java -jar` 独立运行。

## 2. 数据来源与抓取说明
- 赛事名称：World Aquatics Diving World Cup 2026 - Super Final
- 数据接口（8个比赛项目）：
  - `https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82`
  - `https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1`
  - `https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d`
  - `https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b`
  - `https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7`
  - `https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48`
  - `https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c`
  - `https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5`
- 抓取时间：2026年10月29日
- 抓包工具：Edge 浏览器开发者工具 (F12) -> Network -> Fetch/XHR

抓取流程实现：
1. 运行 `Crawler.java` 工具类，通过 HTTP `HttpClient` 自动向官方接口发起请求（附带浏览器 `User-Agent`）。
2. 脚本遍历 8 个项目的 UUID，精准定位到 `Heats` 数组中 `Name="Finals"` 的节点，获取决赛数据。
3. 判断 `Competitors` 数组是否含有元素，自动识别单双人项目，提取姓名及组合成绩，写入本地的 `data.json`。
4. 主程序运行时仅读取本地 `data.json` 快照，不再发起网络请求，保证查询效率。

## 3. 项目结构
- `MainApplication.java`：程序主入口。负责校验启动参数，读取 `input.txt` 脚本文件，初始化服务类并将解析后的指令逐行分发给控制层处理。
- `controller/CommandController.java`：控制层。负责解析用户指令（兼容多余空格），精准截取查询参数。严格处理未知指令等异常边界，并通过 `StringBuilder` 等工具，按照规范精准拼接字符串，完成终端的格式化输出。
- `service/DataFetchService.java`：数据加载接口。定义了数据读取的规范，方便后续扩展不同的数据源。
- `service/impl/DataFetchServiceImpl.java`：业务实现类。通过 Jackson 库读取本地 `data.json` 文件，将 JSON 节点转化为 `Player` 实体集合。若文件缺失或解析失败，抛出自定义异常。
- `service/QueryService.java`：核心查询逻辑。负责接收控制层的参数，对所有数据进行过滤筛选。内部采用基础的冒泡排序算法，确保成绩结果严格按照 `Rank` 升序排列。
- `model/Player.java`：数据实体类。采用统一实体设计，同时封装选手基础信息与比赛成绩数据，通过内部的 `type` 字段区分类型，方便数据在同一个集合中流转。
- `util` / `exception`：基础设施与异常层。`util` 包含通用文件读写工具 `FileUtil`；`exception` 包含自定义的 `DataAccessException`，用于封装底层 IO 异常，避免底层报错直接暴露给上层。
- `Crawler.java`：独立的爬虫脚本，通过 HTTP `HttpClient` 请求官方 API。解析嵌套 JSON 节点，可以识别并处理单双人项目结构，处理数据后生成标准规范的 `data.json` 文件。
## 4. 异常处理规则
本项目采用了防御性编程思想，全面覆盖考核要求的错误处理与边界情况：
1. 未知指令：输入非 `players` 或 `result` 开头的命令，系统捕获后输出 `Error`，并继续执行下一行。
2. 非法查询（数据为空）：使用 `result <项目名>` 查询不存在的项目时，系统捕获空结果，严格输出 `N/A`，不抛出异常。
3. 空行与多余空格：`input.txt` 中的空行被安全忽略；使用 `\\s+` 正则切分指令，完美兼容单词间存在多个连续空格的情况。
4. 文件缺失/损坏：若本地无 `data.json`，`DataFetchServiceImpl` 会抛出自定义 `DataAccessException`，主程序捕获并提示，避免直接崩溃闪退。
5. 字段缺失兜底：爬虫解析时，若遇到官方数据字段缺失，使用 `asDouble(0.0)` 自动兜底为 0，确保程序跑完全程。

## 5. 单元测试与断言机制
本项目使用 JUnit 5 编写了完善的单元测试，测试类位于 `src/test/java/com/diving/QueryServiceTest.java`。

测试覆盖行为：
- 验证了选手基础信息过滤逻辑。
- 验证了按项目查询成绩及排名升序逻辑。
- 验证了查询不存在项目时的边界行为。

断言机制（Assertions）使用：
- 使用 `assertEquals(expected, actual)`：精准比对返回的选手数量、姓名、性别、排名（Rank）以及总分（如严格断言真实成绩 374.40）。
- 使用 `assertTrue(condition, message)`：验证查询无结果时，返回的 `List` 确实为空（`isEmpty()`）。
- 测试结果：全量测试用例绿色通过，无断言失败。

