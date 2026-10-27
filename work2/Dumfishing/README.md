# DWASearch

跳水比赛成绩离线查询工具。读取本地赛事数据快照，按指令文件查询选手信息与决赛成绩，结果写入输出文件。

对应考核：west2-online learn-backend **第一轮（Java）**。

---

## 1. 环境与运行

| 项 | 版本 |
|---|---|
| 操作系统 | Windows 11 |
| JDK | 21.0.12.1（Oracle） |
| 构建工具 | Maven 3.9.16 |
| IDE | IntelliJ IDEA 2026.2.3（Ultimate） |
| 第三方库 | Jackson 2.18.2（JSON 解析）、JUnit 5.11.4（测试） |

### 构建

```bash
mvn clean package
```

产物：`target/DWASearch.jar`（约 **2.2 MB**，**已包含 Jackson 依赖**，可直接运行）。

> `target/DWASearch-1.0-SNAPSHOT.jar`（约 10 KB）是未打包依赖的原始 jar，**不能**直接用 `java -jar` 运行。

### 运行

**运行目录必须包含 `data/` 数据快照。**

```
你的目录/
├─ DWASearch.jar
├─ data/            ← 9 个 JSON（见第 2 节）
├─ input.txt
└─ output.txt       （运行后生成）
```

```bash
java -jar DWASearch.jar input.txt output.txt
```

### 测试

```bash
mvn clean test
```

预期输出：`Tests run: 10, Failures: 0, Errors: 0`

> 必须带 `clean`。不带时 Maven 可能执行 `target/` 里上一次编译残留的测试类，造成“假绿”。

### 打包方式说明

使用 `maven-shade-plugin` 把 Jackson 的 class 解压合并进同一个 jar，`Main-Class` 配置为 `com.dumfishing.dwasearch.DWASearch`。

不用 shade 时，普通 jar 里只有本项目自己的 class，运行会报 `NoClassDefFoundError: com/fasterxml/jackson/databind/ObjectMapper`。

---

## 2. 数据

### 来源

| 用途 | 请求 |
|---|---|
| 赛事 | `GET https://api.worldaquatics.com/fina/competitions/5019` |
| 赛程（取各项目 DisciplineId） | `GET /fina/competitions/5019/schedule` |
| 单个项目完整成绩 | `GET /fina/events/{DisciplineId}` |
| 选手名单 | `GET /fina/competitions/5019/athletes` |

- 赛事：**World Aquatics Diving World Cup 2026 – Super Final**（北京，2026-05-01 ~ 05-03）
- competitionId = `5019`
- **抓取时间：2026-10-20 20:11–20:13**
- 复核时间：2026-10-24（重新拉取 8 个项目，SHA256 与本地快照一致）

完整接口清单与「文件 ← 请求」映射见 [`docs/data-source.md`](docs/data-source.md)。

### 抓取脚本用法

`src/main/java/com/dumfishing/dwasearch/FetchData.java`

```bash
mvn -q compile
java -cp target/classes com.dumfishing.dwasearch.FetchData
```

行为：请求 1 个选手文件 + 8 个项目文件，**每个请求间隔 500 ms**，写入 `data/`。若状态码非 200 则抛异常并指明 URL。

验证可重复性：把 `data/` 移走 → 重跑脚本 → 9 个文件全部再生，字节数一致。

### 数据结构（自定义快照格式 = 接口原始响应）

**`data/athletes.json`** —— 顶层是数组，每项是**一个国家**：

```json
[
  {
    "CountryName": "Australia",
    "Participations": [
      { "PreferredLastName": "COLE", "PreferredFirstName": "Ellie", "Gender": 1, ... }
    ]
  }
]
```

- `Gender`：**0 = 男，1 = 女**
- 共 16 个国家 / **89 条参赛记录**

**`data/{项目名}.json`** —— 顶层是对象：

```json
{
  "DisciplineName": "Women's 3m Springboard",
  "Heats": [
    { "Name": "Finals",       "Results": [ ... ] },
    { "Name": "Semifinals",   "Results": [ ... ] },
    { "Name": "Head-To-Head", "Results": [ ... ] }
  ]
}
```

- **只使用 `Name == "Finals"` 的那一段**
- 每条 `Results[]`：`Rank`、`FullName`、`NAT`、`TotalPoints`、`Dives[]`
- `Dives[]` 每项：`DiveNo`、`DD`、`DivePoints`（本跳得分）、`TotalPoints`（累计到本跳）、`JudgeScores[]`（7 个裁判分）
- 决赛人数：个人项目 4 人、双人项目 8 对；跳数 5（3m 板）或 6（10m 台）

---

## 3. 功能、空行规则与已知限制

### 已实现

| 指令 | 输出 |
|---|---|
| `DWASearch` | 全部 89 名选手：全名、性别、国籍；按**国籍升序 → 姓氏升序** |
| `result <项目>` | 该项目**决赛**成绩：选手姓名、排名、各跳得分 ` + ` 连接、`=` 后接总分；按排名升序 |
| 无法识别的命令 | `Error` |
| 识别为 `result` 但项目缺失/不在列表/尾部多字符 | `N/A` |

- 输出 UTF-8、换行 `\n`、每条记录后单独一行 `-----`
- 分数全程 `BigDecimal`，每跳与总分统一保留两位小数（`HALF_UP`）
- 一条命令出错不影响后续命令

### 空行规则

**空行忽略，不产生任何输出。** 该行为由 `ClassifyTest` 与端到端运行固定。

### 已知限制

- 只支持考核固定的 **8 个项目关键字**；赛程中的 `Mixed 3m & 10m Team`（混合团体）不支持，输入会得到 `N/A`
- `data/` 必须位于运行目录下，程序不读 `jar` 内部资源
- 数据快照依赖赛事接口的实际结构；若上游改字段名，程序会以退出码 4 明确失败（`Reason: missing '<字段>' in <文件>`），不会输出错误结果
- 输入文件必须是 UTF-8 编码（GBK 会乱码）

---

## 4. 代码结构

```
src/main/java/com/dumfishing/dwasearch/
├─ DWASearch.java     入口类（含 main）+ 全部查询/格式化逻辑
├─ Player.java        数据模型（record：country / lastName / firstName / gender）
└─ FetchData.java     抓取脚本（独立 main，不参与查询）

src/test/java/com/dumfishing/dwasearch/
├─ ClassifyTest.java          命令分类（5 个用例）
└─ FormatPlayersTest.java     格式化/字段校验/决赛内容（5 个用例）
```

| 职责 | 位置 |
|---|---|
| 抓取数据 | `FetchData.java` 的 `fetch()` / `main()` |
| 数据读取（选手） | `DWASearch.loadPlayers()` |
| 数据读取（比赛成绩） | `DWASearch.formatEvent()` |
| 字段校验 | `DWASearch.requireText()`（缺字段抛 `IllegalStateException`） |
| 命令分类 | `DWASearch.classify()` → `Command` 枚举 |
| 项目名 → 文件映射 | `DWASearch.KEYWORD_TO_FILE`（常量 `Map`） |
| 结果格式化 | `DWASearch.formatPlayers()` / `DWASearch.formatEvent()` |
| 入口与文件读写 | `DWASearch.main()` |

设计要点：

- 入口类为 **`DWASearch.java`**，含 `public static void main(String[] args)`
- 命令判断、查询、格式化都是**可独立调用的 `static` 方法**，测试不需要启动整个命令行程序
- 格式化方法**不读文件、不写 `System.out`**，只接收数据、返回字符串 → 可直接断言
- 数据异常一律 `throw`，由 `main` 统一捕获并转成退出码，不在业务方法里静默降级

### 性能说明

数据量很小（89 名选手 + 8 个项目），当前实现没有性能瓶颈，也没有引入缓存或并行：

- 选手数据加载一次，`players` 指令重复出现时不再重复解析 JSON
- 每个项目文件按需读取，单个 `result` 指令读取 1 个文件（约 30–55 KB）
- 格式化使用 `StringBuilder` 单次拼接，避免大量临时字符串
- 单次运行（9 条命令）实测 < 1 秒

若数据量放大，可优化方向：项目文件解析结果按文件名缓存（`Map<String, List<...>>`），避免重复读取与解析；但这会增加内存占用与失效逻辑，当前规模下不划算。

---

## 5. 测试与故障处理

### 测试覆盖

`mvn clean test` → **Tests run: 10, Failures: 0, Errors: 0**

| 测试类 | 用例数 | 覆盖 |
|---|---|---|
| `ClassifyTest` | 5 | 输入 `players` / `result <项目>` / `result` / `players extra` / `Players`（大小写敏感） |
| `FormatPlayersTest` | 5 | 单人输出格式、空列表输出空串、缺字段抛异常、字段正常取值、决赛第一名内容断言 |

关键断言示例：

```java
assertEquals("Full Name:COLE Ellie\nGender:Female\nCountry:Australia\n-----\n",
             DWASearch.formatPlayers(list));
```

端到端验证（人工，每次改动后执行）：用包含 9 条命令的 `input.txt` 运行，输出固定为 **386 行**（89 位选手 + 4 条决赛 + 4 个 `Error` + 3 个 `N/A`，另含 100 条分隔线）。

### 致命故障与退出码

业务错误（`Error` / `N/A`）**不改变退出码**，永远是 0，且不影响后续命令。

| 情况 | 提示（stderr） | 退出码 |
|---|---|---|
| 参数个数不为 2 | `Error: expected 2 arguments, got N` + 用法 | **2** |
| 输入文件不存在/不可读 | `Error: cannot read input file: <path>` | **3** |
| 数据 JSON 损坏或缺字段 | `Error: cannot load ...` + `Reason: ...` | **4** |
| 输出路径不可写 | `Error: cannot write output file: <path>` | **5** |
| 正常 | — | 0 |

实测（2026-10-27）：`2 / 3 / 4 / 5` 全部符合；缺字段时提示形如

```
Error: cannot load event data: Women's 3m Springboard.json
Reason: missing 'TotalPoints' in Women's 3m Springboard.json
```

不写默认成绩、不输出空结果。


