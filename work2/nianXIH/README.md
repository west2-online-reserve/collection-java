# DWASearch — 跳水世界杯赛事数据查询系统

## 项目简介

DWASearch 是一个命令行程序，用于查询 **2026 World Aquatics Diving World Cup Super Final（北京）** 的选手信息和比赛结果。
程序读取 `input.txt` 中的命令，将查询结果写入 `output.txt`。

## 运行环境

| 项目 | 版本 |
|------|------|
| JDK | Oracle OpenJDK 25.0.1 |
| 构建工具 | Maven 3.x |
| IDE | IntelliJ IDEA 2026.2.0.1 |
| 操作系统 | Windows 11 |

## 快速开始

### 编译打包

```bash
mvn clean package
```

打包成功后生成 `target/DWASearch.jar`。

### 运行程序

```bash
java -jar target/DWASearch.jar input.txt output.txt
```

### 运行测试

```bash
mvn test
```

## 数据说明

- 数据来源：`src/data/data.json`
- 数据版本：schemaVersion = 1
- 赛事：World Aquatics Diving World Cup 2026 - Super Final（北京）
- 数据抓取时间：2026-08-29T06:47:21Z
- SHA-256：`b648a02f56c71c458e29fcc30d5cf17dcc9c62f89b8cc9d012fd0fad70d1984c`
- 程序运行时只读取本地固定数据，不依赖网络服务，不修改原始数据文件。

## 模块接口设计与实现

### 架构概览

```
com.dwa
├── DWASearch.java              # 主程序入口（命令行参数解析、文件 I/O）
├── core/
│   ├── CoreModule.java         # 核心业务模块（命令调度、文件处理）
│   ├── CommandParser.java      # 命令解析器（词法分析、命令分类）
│   └── DataRepository.java     # 数据仓库（JSON 加载、数据查询）
├── model/
│   ├── Player.java             # 选手模型
│   ├── Event.java              # 赛事项目模型
│   ├── ResultEntry.java        # 比赛结果条目模型
│   └── DataFile.java           # 数据包根模型
└── util/
    └── Formatter.java          # 输出格式化器（排序、分数计算、文本格式化）
```

### 模块关系

```
DWASearch → CoreModule → CommandParser（解析命令）
                      → DataRepository（查询数据）
                      → Formatter（格式化输出）
```

### 关键设计

1. **核心逻辑与 I/O 分离**：`CoreModule.processCommand(String)` 可独立测试，不依赖文件 I/O。
2. **命令解析**：`CommandParser` 使用枚举 `CommandType` 区分四种结果（PLAYERS / RESULT / ERROR / NA），合法赛事名称用 `HashSet` 精确匹配。
3. **数据加载**：`DataRepository` 使用 Jackson 从 classpath 加载 JSON，只读缓存，不修改原始文件。
4. **分数精度**：`Formatter` 使用 `BigDecimal` 计算分数之和，避免二进制浮点误差。
5. **排序规则**：选手按国籍（country）升序为主关键字，Last Name（fullName 中最后一个空格后的部分）升序为次关键字。

### 空行处理规则

- 每条命令的输出结果直接相连，命令之间**不插入空行**。
- 每条记录（选手或结果）后输出 `-----` 分割线，包括最后一条。

## 单元测试展示

测试文件位于 `src/test/java/com/dwa/`，使用 JUnit 5 编写，覆盖以下场景：

| 测试类 | 覆盖内容 |
|--------|----------|
| `CommandParserTest` | 合法命令、非法命令、大小写敏感、多余字符、空输入 |
| `DataRepositoryTest` | JSON 加载、选手数量、8 个合法赛事查询、非法查询返回 null |
| `CoreModuleTest` | 端到端命令处理、多行文件、重复命令、空文件、Error/NA 输出 |
| `FormatterTest` | 排序正确性、分割线、分数格式、换行符、空列表 |

## 异常处理说明

| 异常场景 | 处理方式 |
|----------|----------|
| data.json 不存在或无法读取 | `DataRepository` 构造时抛出 `IOException`，程序输出错误信息并退出（exit code 1） |
| 命令行参数不足或多余 | `DWASearch.main` 输出 Usage 提示并退出（exit code 1） |
| 输入文件不存在 | `CoreModule.processFile` 抛出 `NoSuchFileException`，程序捕获后输出错误信息 |
| 输出路径不可写 | 抛出 `IOException`，程序捕获后输出错误信息 |
| 非法命令 | 输出 `Error\n-----\n`，不中断处理 |
| 未知赛事项目 | 输出 `N/A\n-----\n`，不中断处理 |

## 支持的命令

### 查询选手

```
players
```

### 查询赛事结果

```
result women 3m springboard
result women 10m platform
result women 3m synchronised
result women 10m synchronised
result men 3m springboard
result men 10m platform
result men 3m synchronised
result men 10m synchronised
```

## 项目结构

```
java-first/
├── pom.xml                                     # Maven 构建配置
├── README.md                                   # 本文件
├── .gitignore
├── data.json                                   # 固定数据包（根目录备份）
├── manifest.json                               # 数据包元信息
└── src/
    ├── data/
    │   ├── data.json
    │   └── manifest.json
    ├── main/
    │   └── java/com/dwa/
    │       ├── DWASearch.java
    │       ├── core/
    │       │   ├── CoreModule.java
    │       │   ├── CommandParser.java
    │       │   └── DataRepository.java
    │       ├── model/
    │       │   ├── Player.java
    │       │   ├── Event.java
    │       │   ├── ResultEntry.java
    │       │   └── DataFile.java
    │       └── util/
    │           └── Formatter.java
    └── test/java/com/dwa/
        ├── core/
        │   ├── CoreModuleTest.java
        │   ├── CommandParserTest.java
        │   └── DataRepositoryTest.java
        └── util/
            └── FormatterTest.java
```
