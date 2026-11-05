# DWASearch

2026 年世界泳联跳水世界杯总决赛的数据查询程序。读本地数据快照，按输入文件里的命令逐行执行，输出选手名单或某个项目的决赛成绩。

## 环境

- JDK 17 及以上（pom 里用 `maven.compiler.release=17` 编译，实测用 Oracle JDK 24.0.2 构建和运行）
- Maven 3.9（构建和跑测试用的是 3.9.16）
- 运行时依赖 Gson 2.10.1，测试依赖 JUnit Jupiter 5.10.2

源码、数据和 pom 都使用 UTF-8 编码。

## 目录结构

```text
DWAsearch/
├─ src/
│  ├─ DWASearch.java        # 入口：解析参数、打开输入输出
│  ├─ CommandProcessor.java # 逐行读命令并分发
│  ├─ CoreModule.java       # 查询、排序、格式化输出
│  ├─ DataStore.java        # 读 data.json，建缓存和索引
│  ├─ Competition.java      # 赛事信息
│  ├─ CompetitionData.java  # data.json 最外层
│  ├─ Player.java           # 选手
│  ├─ Event.java            # 项目
│  ├─ Result.java           # 一条成绩
│  └─ data/
│     ├─ data.json          # 抓取脚本生成的快照
│     ├─ manifest.json      # 来源、时间、数量、SHA-256
│     └─ raw/               # 原始响应缓存，不提交
├─ tools/
│  └─ DataFetcher.java      # 抓取脚本，不参与 Maven 构建
├─ tests/                   # JUnit 5 测试
├─ pom.xml
└─ README.md
```

## 编译和运行

```bash
mvn -q package
java -jar target/DWASearch.jar input.txt output.txt
```

`mvn package` 会先跑测试，再用 `maven-assembly-plugin` 打成 `target/DWASearch.jar`。打包用的是 `jar-with-dependencies`，Gson 一起打进去，`Main-Class` 在 pom 里配成 `DWASearch`，所以可以直接 `java -jar`。`target/` 已经在 `.gitignore` 里，构建产物不提交。

第一个参数是命令文件，第二个是输出文件。程序按行读命令，结果按顺序写进输出文件，每次运行整个覆盖。

不传参数时是交互模式，从标准输入读、结果打到控制台，Windows 下按 Ctrl+Z 再回车结束：

```bash
java -jar target/DWASearch.jar
```

两种模式都必须在项目根目录启动，数据路径是相对的 `src/data/data.json`。

打包后的程序实际跑过一次：输入 12 行（8 个项目 + `players` + 1 个非法命令 + 1 个内部多空格的命令），输出 552 行、139 条记录，退出码 0，输出里没有 `\r`。

## 输入命令

| 命令 | 说明 |
|---|---|
| `players` | 全部选手 |
| `result women 3m springboard` | 女子 3 米跳板 |
| `result women 10m platform` | 女子 10 米跳台 |
| `result women 3m synchronised` | 女子双人 3 米跳板 |
| `result women 10m synchronised` | 女子双人 10 米跳台 |
| `result men 3m springboard` | 男子 3 米跳板 |
| `result men 10m platform` | 男子 10 米跳台 |
| `result men 3m synchronised` | 男子双人 3 米跳板 |
| `result men 10m synchronised` | 男子双人 10 米跳台 |

命令区分大小写，全是小写。首尾空白会去掉，命令内部的空格不动，所以 `result men 10m     synchronised` 这种中间多空格的输入输出 `N/A`。空行和纯空白行直接跳过，空文件产生空输出。

认不出来的输入输出 `Error`；`result` 后面跟的项目名不存在输出 `N/A`；只有 `result` 一个词、没有项目名也输出 `N/A`；`resultat`、`resultfoo men 10m platform` 这类前缀对不上的输出 `Error`。

## 输出格式

UTF-8，换行统一 `\n`，每条记录后面跟一行 `-----`，最后一条也有。多条命令的输出直接接在一起，中间不插空行。

`players` 每条记录：

```text
Full Name:COLE Ellie
Gender:Female
Country:Australia
-----
```

`result <event>` 每条记录：

```text
Full Name:BAI Yuming
Rank:1
Score:81.60 + 86.40 + 77.00 + 91.80 + 104.40 + 101.75 = 542.95
-----
```

出错时是单独的两行：

```text
Error
-----
```

```text
N/A
-----
```

## 数据来源

`src/data/data.json` 由本项目的抓取脚本生成，对应赛事 World Aquatics Diving World Cup 2026 - Super Final（id `5019`），`schemaVersion` 为 1，共 89 位选手、8 个项目、48 条成绩。

同一次抓取还写出了 `manifest.json`：

- 来源：World Aquatics 的公开 JSON 接口 `https://api.worldaquatics.com/fina`
- 抓取时间：`2026-11-05T11:10:33Z`（UTC）
- 数量：players 89 / events 8 / results 48
- `data.json` 的 SHA-256：`24ba862b446ccbd8b2534598cc279a806025d881835b5a9d36e1139b70bc0f83`

程序运行和测试只读本地快照，不联网，也不改数据文件。

## 抓取脚本

`tools/DataFetcher.java` 和查询程序是分开的：它联网取数、写快照，`DWASearch` 只读生成出来的文件。

在项目根目录执行，Gson 已经打进 jar 了：

```bash
java -cp target/DWASearch.jar tools/DataFetcher.java
```

参数：

| 参数 | 作用 | 默认 |
|---|---|---|
| `--out <目录>` | 输出目录 | `src/data` |
| `--raw <目录>` | 原始响应缓存目录 | `<out>/raw` |
| `--delay <毫秒>` | 两次请求之间的间隔 | `800` |
| `--retries <次数>` | 单个请求最多试几次 | `4` |
| `--offline` | 只读缓存，不联网 | 关 |
| `--refresh` | 忽略缓存，全部重抓 | 关 |
| `--dry-run` | 只抓和校验，不写文件 | 关 |

几个常用写法：

```bash
# 先写到临时目录看看，确认没问题再覆盖正式快照
java -cp target/DWASearch.jar tools/DataFetcher.java --out tmp/data --raw tmp/raw

# 断网重算，验证重跑结果一致
java -cp target/DWASearch.jar tools/DataFetcher.java --offline

# 只验证接口和数据，不落盘
java -cp target/DWASearch.jar tools/DataFetcher.java --dry-run
```

脚本做的事：

1. 请求之间有间隔，单个请求 30 秒超时，429 和 5xx 指数退避重试；
2. 每个响应先落缓存，之后可以离线复算；
3. 归一化后先自校验：项目数 8、8 个命令逐个对上、排名连续、成绩不为空，不合格就不写盘；
4. 写盘是先写临时文件再改名，失败不会留下半个文件，也不会动旧快照；
5. 失败时往标准错误打一句中文原因，退出码 1；参数不对退出码 2。

抓取要联网。`src/data/raw/` 只是本地缓存，不提交。

## data.json 结构

字段和 `src/` 下的 record 一一对应：

```text
{
  schemaVersion: 1,
  competition: { id, name, from, to },
  players: [ { fullName, gender, country, countryCode } ],
  events: [ {
      command,        // 查询关键字，如 "result women 3m springboard"
      eventId,        // 官方项目 ID
      gender,         // Men / Women
      discipline,     // 3m / 10m
      eventType,      // springboard / platform / synchronised
      results: [ { fullName, rank, countryCode, scores[], totalPoints } ]
  } ]
}
```

`scores` 和 `totalPoints` 都存成字符串，保留两位小数，避免浮点误差。`players` 里只有跳水运动员（按 `Sports == "DV"` 过滤），双人项目不会多出“组合选手”，只是 `result` 里的 `fullName` 写成 `A & B`。

## 代码结构

程序分四层，每层职责单一，方便单独测试：

- `DWASearch`：入口。根据参数选文件模式还是标准流模式，打开流，交给命令处理器，IO 出错统一处理。
- `CommandProcessor`：逐行读，跳过空行，识别 `players` 和 `result` 前缀后分发。只去首尾空白。
- `CoreModule`：查询和输出。负责排序，把结果写到构造时传进来的 `PrintWriter`，自己不碰文件。
- `DataStore`：读 `data.json`，解析成不可变缓存，建两份索引。第一次查询时加载，之后都走缓存。

`CoreModule` 主要方法：

```java
List<Player> getAllPlayers();
List<Event> getEvents();
void displayAllPlayersInfo();
void displayResultsForEvent(Event event);
void displayEventResult(String command);
```

`DataStore` 主要方法：

```java
static void loadDataToCache();
static CompetitionData getCache();
static Map<String, Event> getEventsByCommand();
static Map<String, Player> getPlayersByFullName();
```

输入输出流都是从外面传进去的，所以文件模式、交互模式和测试用的是同一套代码。缓存里返回的 List 和 Map 都是不可变副本，外部改不了。数据本体和两份索引都建成功了才更新缓存，中途失败不会留下只加载一半的状态。

## 排序

排序由查询程序做，不依赖 `data.json` 里的原始顺序：

- `players`：国籍升序，同国籍按姓名升序；
- `result <event>`：按排名升序。

快照里存的是排好序的数据，主要是让 `git diff` 稳定。换一份内容相同、顺序被打乱的数据，输出不变。

## 异常与退出码

- 认不出来的命令：输出 `Error` 后继续处理后面的行；
- `result` 的项目不存在：输出 `N/A`；
- 不在项目根目录启动、数据文件损坏或缺字段：抛异常停止加载，向标准错误打一句原因（含当前工作目录或字段问题），退出码 1，不会输出“看起来成功”的结果；
- 输入文件不存在或输出路径写不了：捕获 `IOException`，往标准错误打提示，退出码 1；
- 写入过程中出错：`PrintWriter` 会把写失败吞掉，所以写完用 `checkError()` 检查一次，出错时提示“输出可能不完整”并返回 1；
- 参数个数不对：打印用法，退出码 2。

## 自动化测试

JUnit 5，`tests/` 下以 `Test` 结尾的类由 Surefire 自动发现：

```bash
mvn test
```

`mvn verify` 除了跑测试，还会打包出 `target/DWASearch.jar`。

覆盖范围：

- `CommandProcessorTest`（14 个）：两种命令、多行与重复命令、空行和纯空白行、未知命令、未知赛事、大小写、内部多空格与首尾空白、出错后继续处理、`Error` / `N/A` 输出；
- `CoreModuleTest`（10 个）：选手和项目数量、命令集合、成绩条数、输出格式（冒号后不留空格、`-----` 分隔、没有 `\r`）、返回列表不可变、排序（乱序输入排完有序，真实数据也符合要求）；
- `DataStoreTest`（8 个）：数据加载、索引完整性、缓存不可变、JSON 损坏和缺字段时明确失败（`DataStore.parse(Reader)` 直接收字符串，不用造数据文件）；
- `DWASearchCliTest`（3 个）：真实文件读写、输入文件缺失、输出路径不可写。

验证结果（2026-11-05）：`Tests run: 35, Failures: 0, Errors: 0, Skipped: 0`。

## 已知限制

- 只保存决赛成绩（`PhaseName == "Finals"`），所以不支持 `result <event> detail`。要做三级成绩得同时改快照结构和抓取逻辑；
- 数据来自官方站点的公开 JSON 接口，不是对外文档化的 API。接口字段一变抓取就会失败，校验会拦住并说明原因，那时要按新结构调整 `tools/DataFetcher.java`；
- 两种运行模式都要求从项目根目录启动，数据路径是相对路径；
- 抓取脚本要联网，仓库里只放生成好的快照，测试全程不联网。
