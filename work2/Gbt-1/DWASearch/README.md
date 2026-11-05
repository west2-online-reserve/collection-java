# DWASearch

World Aquatics Diving World Cup 2026 - Super Final（北京）离线查询工具。

程序读取本地 `data.json`，按 `input.txt` 中的命令查询，把结果写入 `output.txt`。运行和测试时不访问网络。

## 环境

- Windows 11
- JDK 17.0.20.1（Eclipse Temurin）
- Maven 3.9.11（IntelliJ IDEA 自带）
- IntelliJ IDEA 2026.1.2
- Jackson 2.17.1
- JUnit Jupiter 5.10.2

## 构建和运行

在项目根目录执行：

```bash
mvn clean package
```

生成可执行 jar：

```text
target/DWASearch.jar
```

运行：

```bash
java -jar target/DWASearch.jar input.txt output.txt
```

`data.json` 必须放在当前工作目录。

测试：

```bash
mvn test
```

也可以在 IDEA 的 Maven 面板执行 `clean` 和 `package`。

## 数据来源

- 赛事：World Aquatics Diving World Cup 2026 - Super Final
- 地点：北京
- 赛事 ID：5019
- 页面：<https://www.worldaquatics.com/competitions/5019/world-aquatics-diving-world-cup-2026-super-final>

使用的公开接口：

- <https://api.worldaquatics.com/fina/competitions/5019>
- <https://api.worldaquatics.com/fina/competitions/5019/events>
- <https://api.worldaquatics.com/fina/competitions/5019/athletes>
- `https://api.worldaquatics.com/fina/events/{disciplineId}`

抓取时间：

```text
2026-11-03 19:38（Asia/Shanghai）
```

`data.json` 是抓取脚本生成的本地快照，包含 89 名选手和 8 个决赛项目。

## 抓取脚本

位置：

```text
src/main/java/com/dwasearch/scraper/DataScraper.java
```

运行 `DataScraper.main()` 会生成 `data.json`。

脚本使用 JDK 的 `HttpClient`，带 `User-Agent` 和 `Accept: application/json`，连接超时 15 秒，请求超时 30 秒，每次请求间隔 700 毫秒；429 或 5xx 最多重试 3 次，其他非 200 状态直接报错。只抓 8 个目标项目，排除 Mixed Team，不登录、不绕过验证码或访问限制。

## JSON 结构

```json
{
  "players": [
    {
      "firstName": "Ellie",
      "lastName": "COLE",
      "gender": "Female",
      "country": "Australia"
    }
  ],
  "events": [
    {
      "eventName": "women 3m springboard",
      "results": [
        {
          "fullName": "CHEN Jia",
          "rank": 1,
          "diveScores": ["73.50", "74.40", "76.50", "72.00", "78.00"],
          "totalScore": "374.40"
        }
      ]
    }
  ]
}
```

分数用字符串保存，读取后转成 `BigDecimal`。

## 查询规则

### players

输出全部选手的全名、性别、国籍。

排序规则：

1. 国籍升序
2. Last Name 升序
3. First Name 升序

### result <项目名称>

支持 8 个项目：

```text
women 3m springboard
women 10m platform
women 3m synchronised
women 10m synchronised
men 3m springboard
men 10m platform
men 3m synchronised
men 10m synchronised
```

按排名升序；排名相同按姓名升序。

成绩格式：

```text
Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40
```

每条选手、成绩或错误信息后都输出一行 `-----`，最后一条也输出，命令之间不插空行。

## 错误命令

- 无法识别：`Error`
- `result` 缺少项目、项目不存在、名称后多字符：`N/A`
- 每条错误后仍输出 `-----`
- 继续处理后续命令

示例：

| 输入 | 输出 |
|---|---|
| `player` | `Error` |
| `players extra` | `Error` |
| `resultwomen 3m springboard` | `Error` |
| `result` | `N/A` |
| `result sss` | `N/A` |
| `result women 10m springboard` | `N/A` |
| `result women 3m springboard details` | `N/A` |

空行和只包含空格的行忽略；空文件产生空输出。

## 文件和异常

以下情况输出到标准错误并返回非零退出码：

- 参数不是 2 个：提示用法，退出码 2
- 输入或数据文件不存在、无法读取：提示具体文件，退出码 1
- JSON 损坏或缺少必要字段：抛出 `DataException`，退出码 1
- 输出文件无法写入：提示输出路径，退出码 1
- 写入中途失败：提示输出可能不完整

没有空 `catch`，不会吞掉异常后继续输出正常结果。

## 代码结构

```text
src/main/java/com/dwasearch/
├─ DWASearch.java                  入口、参数、文件读写、退出码
├─ command/CommandProcessor.java   命令分流
├─ data/DataRepository.java        读取和校验 data.json
├─ data/DataException.java         数据异常
├─ model/                          数据模型
├─ query/PlayerQueryService.java   选手排序和格式化
├─ query/ResultQueryService.java   成绩查找、排序和格式化
└─ scraper/DataScraper.java        抓取并生成 data.json
```

## 测试

实际执行结果：

```text
Tests run: 25, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

测试覆盖：模型、JSON 读取和损坏数据、真实快照抽样、排序、输出格式、`Error` / `N/A`、空行、空文件、重复命令、错误后继续、文件异常。

另外实际运行过：

```text
java -jar target/DWASearch.jar input.txt output_jar.txt
```

并与 IDEA 生成的 `output.txt` 比较，结果为 `FC: 找不到差异`。

## 已知限制

- 数据是固定快照，不反映抓取后的赛事变化
- `data.json` 必须位于运行目录
- 输出写入不是原子操作，失败时可能留下部分文件
- 不查询 Mixed 3m & 10m Team
- 不提供图形界面或 CSV 数据源

## 提交

按 `west2-online-reserve/collection-java` 的 `work2/` 说明提交。

提交源码、测试、`data.json`、`pom.xml`、`README.md`、`.gitignore`；不提交 `target/`、`output.txt`、`output_jar.txt`、`.idea/`。