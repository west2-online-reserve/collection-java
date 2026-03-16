# DWASearch - 世界游泳锦标赛跳水项目数据查询工具

## 项目简介

DWASearch是一个用于查询世界游泳锦标赛跳水项目数据的控制台程序。该程序可以从世界游泳锦标赛官网爬取选手信息和比赛结果，并提供多种查询功能。

## 功能特性

1. **输出所有选手信息**：按国籍和姓名排序输出所有参赛选手的详细信息
2. **输出决赛结果**：查询特定比赛项目的决赛结果
3. **输出详细结果**：查询特定比赛项目的初赛、半决赛和决赛完整结果

## 项目结构

```
|- src
 |- main
  |- java
   |- DWASearch.java          # 主程序类，处理命令行参数
   |- CoreModule.java         # 核心模块，包含数据爬取和处理功能
   |- Lib.java                # 工具类，提供通用辅助方法
 |- test
  |- java
   |- CoreModuleTest.java     # 单元测试类
|- pom.xml                    # Maven配置文件
|- README.md                  # 项目说明文档
```

## 模块设计

### 1. CoreModule类

CoreModule是程序的核心模块，负责数据的爬取、解析和处理。

**主要功能：**
- `fetchAllData()`: 爬取所有需要的数据（选手信息和比赛结果）
- `displayAllPlayersInfo(String outputPath)`: 输出所有选手信息到指定文件
- `displayFinalResults(String eventName, String outputPath)`: 输出特定比赛项目的决赛结果
- `displayDetailedResults(String eventName, String outputPath)`: 输出特定比赛项目的详细结果（所有阶段）

**内部类：**
- `Player`: 存储选手信息（全名、性别、国籍）
- `Event`: 存储比赛项目信息（名称、各阶段结果）
- `Result`: 存储单场比赛结果（选手名、排名、分数列表、总分）
- `CombinedResult`: 存储综合比赛结果（包含所有阶段）

### 2. DWASearch类

DWASearch是主程序类，负责处理命令行参数和解析用户指令。

**主要功能：**
- `main(String[] args)`: 程序入口，处理命令行参数
- `processCommand(String command, CoreModule coreModule, String outputPath)`: 处理单个命令
- `processResultCommand(String[] parts, CoreModule coreModule, String outputPath)`: 处理result命令

### 3. Lib类

Lib是工具类，提供通用的辅助方法。

**主要功能：**
- `sendHttpRequest(String url)`: 发送HTTP请求
- `extractContentFromHtml(String html, String tag, String attribute, String value)`: 从HTML中提取内容
- `cleanHtmlTags(String html)`: 清理HTML标签
- `saveToFile(String content, String filePath)`: 保存内容到文件
- `readFromFile(String filePath)`: 从文件读取内容
- `formatScoreString(List<Double> scores, double totalScore)`: 格式化分数字符串

## 使用方法

### 编译项目

```bash
mvn clean package
```

### 运行程序

```bash
java -jar target/DWASearch-1.0-SNAPSHOT-jar-with-dependencies.jar input.txt output.txt
```

### 命令格式

1. **输出所有选手信息**
   ```
   players
   ```

2. **输出决赛结果**
   ```
   result <event_name>
   ```
   例如：
   ```
   result women 1m springboard
   ```

3. **输出详细结果**
   ```
   result <event_name> detail
   ```
   例如：
   ```
   result women 10m platform detail
   ```

### 合法的比赛项目名称

```
women 1m springboard
women 3m springboard
women 10m platform
women 3m synchronised
women 10m synchronised
men 1m springboard
men 3m springboard
men 10m platform
men 3m synchronised
men 10m synchronised
```

## 性能改进

1. **数据缓存**：程序在初始化时一次性爬取所有数据并缓存到内存中，后续查询直接从内存读取，避免重复网络请求

2. **批量处理**：使用集合框架批量处理数据，减少I/O操作次数

3. **高效排序**：使用Collections.sort()方法结合自定义Comparator进行排序，确保排序效率

4. **HTTP连接池**：使用Apache HttpClient的连接池功能，提高网络请求效率

## 单元测试

项目使用JUnit框架编写了至少10个单元测试用例，覆盖了以下功能：

1. CoreModule初始化测试
2. 输出所有选手信息测试
3. 输出决赛结果（有效比赛项目）测试
4. 输出决赛结果（无效比赛项目）测试
5. 输出详细结果（有效比赛项目）测试
6. 输出详细结果（无效比赛项目）测试
7. 选手信息排序测试
8. 文件追加功能测试
9. 输出格式测试
9. 测试比赛结果排序和分数计算

运行单元测试：

```bash
mvn test
```

## 异常处理

1. **网络异常**：处理HTTP请求失败的情况，如网络连接超时、服务器错误等

2. **文件I/O异常**：处理文件读写过程中的异常，如文件不存在、权限不足等

3. **命令格式错误**：处理用户输入的命令格式错误，如无法识别的命令、参数不足等

4. **数据解析异常**：处理HTML解析过程中的异常，如页面结构变化等

5. **空值处理**：对可能为null的变量进行安全检查，避免空指针异常

## 注意事项

1. 程序需要网络连接才能爬取数据
2. 请确保有足够的权限读写文件
3. 由于网页结构可能变化，程序可能需要定期更新解析逻辑
4. 爬取数据时请遵守网站的robots.txt规则

## 参考链接

- [2024年福州大学软件工程实践第二次作业](https://bbs.csdn.net/topics/618087255)
- [世界游泳锦标赛官网](https://www.worldaquatics.com/)
- [Apache HttpClient文档](https://hc.apache.org/httpcomponents-client-ga/tutorial/html/)
- [JUnit文档](https://junit.org/junit4/)