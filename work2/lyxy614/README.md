# DWASearch
该项目是一个简单的用于查询跳水比赛结果的控制台程序。

输入的命令和输出的查询结果均放在文本文件中。

比赛名称：World Aquatics Diving World Cup 2026 - Super Final(北京)
## 一、环境版本

- JDK: 25
- Maven: 3.9.11
- 已验证的可运行操作系统: Windows 11

## 二、构建、运行和测试命令

以下命令均需要在项目根目录下执行

- 测试
```powershell
mvn test
```

- 构建（打包得到jar）：
```powershell
mvn clean package
```

- 运行
```powershell
java -jar target/DWASearch.jar input.txt output.txt
```
input.txt和output.txt也可以替换成其他的文本文件。

- 抓取脚本运行（需要更新数据时）
```powershell
java -cp target/DWASearch.jar com.dwasearch.crawler.DataCrawler
```

- `Main-Class`的配置方式：
在pom.xml的shade插件中指定
```xml
<configuration>
    <transformers>
        <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
            <mainClass>com.dwasearch.query.DWASearch</mainClass>
        </transformer>
    </transformers>
</configuration>
```

## 三、数据来源
数据请求自worldaquatics.com网站的api。

官网数据查看: https://www.worldaquatics.com/competitions/5019/world-aquatics-diving-world-cup-2026-super-final/results;

实际请求所用api:

- 获取所有需要查询项目的id字段: https://api.worldaquatics.com/fina/competitions/5019/events;

- 根据id字段获取每个项目的具体结果: https://api.worldaquatics.com/fina/events/<此处填充id字段>;

- 获取所有需要查询的players信息: https://api.worldaquatics.com/fina/competitions/5019/athletes;

抓取时间：2026-10-23 12:52:16(UTC+8)

抓取脚本用法：

将项目打包为jar后直接在终端执行以下指令即可重新抓取数据并更新data.json
```powershell
java -cp target/DWASearch.jar com.dwasearch.crawler.DataCrawler
```

定义的数据快照json结构：
- source：抓取来源与时间（event、url、fetchedAt）
- players：选手列表（firstName、lastName、gender、country）
- competitions：九个比赛，heats、finals、results、dive层层嵌套

抓取脚本会按固定间隔(500ms)请求api，并在终端逐个打印状态码和项目名称，状态码为200表示成功抓取

非200则失败（此时无法获取失败的项目名称）

查询和测试只读 data.json，不访问网络。

抓取到的数据包含第九个项目（Mixed 3m & 10m Team），本项目只查询规定的八个项目，查询时忽略第九项（无法查询）。

## 四、已完成功能
在input.txt中写入下表中的命令，可以在output.txt中输出对应的查询结果，一次可以执行多条查询命令。

| 命令            | 功能                          |
|---------------|-----------------------------|
| players       | 输出全部选手信息，按国籍升序、姓氏升序         |
| result <项目名称> | 按排名输出决赛结果，各跳成绩保留两位小数并用 + 连接 |
合法的项目名称：
```
women 3m springboard
women 10m platform
women 3m synchronised
women 10m synchronised
men 3m springboard
men 10m platform
men 3m synchronised
men 10m synchronised
```
#### 注意事项：
- 命令区分大小写，`Players`、`PLAYERS` 均输出 Error。
- `players` 后面只能跟零个或多个空格；跟任何非空白字符（如 `players extra`）输出 Error。
- `result` 后面必须跟若干个空格再接项目名，项目名称后不得再跟空格，内部也不允许有多余空格；`result` 单独出现、`result sss`、`result women 3m springboard details` 均输出 N/A。
- 重复命令会再次输出，不会去重。
- 一行只能写一条命令；空行被忽略；空文件产生空输出。
- 输入输出统一使用 UTF-8，输出换行使用 `\n`。

## 五、项目结构
```
DWASearch
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─dwasearch
    │  │          ├─crawler # DataCrawler
    │  │          │  └─model # Discipline, Events, Source, Sports
    │  │          ├─model #DataSnapshot
    │  │          │  ├─players # Country, Player
    │  │          │  └─results # Competition, Dive, Heat, Result
    │  │          └─query # DWASearch
    │  │              ├─exception # InvalidDataException
    │  │              ├─service # CommandProcessor, CoreModule
    │  │              └─util # DWAData, OutputFormatter, SnapshotValidator
    │  └─resources
    └─test
        ├─java
        │  └─com
        │      └─dwasearch
        │          └─query # DWASearchTest, TestData
        │              ├─service # CommandProcessorTest, CoreModuleTest
        │              └─util # DWADataTest, SnapshotValidatorTest
        └─resources
```
程序总流程：抓取 -> 读取 -> 校验 -> 命令处理 -> 输出
### 抓取
抓取任务由com.dwasearch.crawler.DataCrawler完成。

过程中用到的主要的JavaBean放在com.dwasearch.model下，与com.dwasearch.query（查询部分）共用。

### 数据读取
由com.dwasearch.query.util.DWAData完成，该类只有一个readData方法，

用于读取data.json并反序列化为DataSnapshot对象，该对象将被用于数据查询。

### 数据校验
由com.dwasearch.query.util.SnapshotValidator完成。

该类用于检查反序列化得到的DataSnapshot中关键的数据是否丢失，

例如player的关键字段是否为空，result中的决赛数据是否缺失。

### 命令处理
由com.dwasearch.query.service.CommandProcessor完成。

### 结果输出
由com.dwasearch.query.service.CoreModule完成。

## 六、测试

`mvn test` 实际结果：Tests run: 31, Failures: 0, Errors: 0, Skipped: 0

覆盖：

- 查询结果、排序、成绩格式与 data.json 快照一致
- 多行命令、重复命令、未知命令、缺失或错误项目名
- 空文件、空行、大小写、多余空格、分隔线与换行
- 非法命令后继续处理合法命令
- JSON 损坏、缺少字段时明确失败
- 参数数量错误、文件不存在、输出不可写时提示并返回非零退出码

坏数据样本放在 src/test/resources，不改动提交的 data.json，

测试默认使用 src/test/resources 下的独立样本；仅在验证“输出与提交的数据快照一致”时读取项目根目录的 data.json，不会修改它。

## 七、已知限制

- 抓取脚本需要联网，查询和测试不联网
- 输出未实现原子写入，写入中途失败会提示输出可能不完整