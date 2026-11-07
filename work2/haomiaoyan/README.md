# DWASearch

2026 World Aquatics Diving World Cup Super Final（北京，赛事 ID `5019`）赛事数据命令行查询程序。
west2-online「learn-backend」第一轮考核项目：[考核说明](https://github.com/west2-online/learn-backend/blob/main/docs/1-基础语法-java.md)。

## 功能

- `players`：输出全部 89 名参赛选手（按国籍升序、同国籍按姓氏升序）；
- `result <项目>`：输出指定项目决赛结果（名次、各轮分数、总分）；
- 未知命令输出 `Error`，未知/非法项目输出 `N/A`；
- 输入输出均为文件：`java -jar DWASearch.jar input.txt output.txt`。

## 环境与运行

| 项 | 值 |
|---|---|
| JDK | OpenJDK 25.0.2（`pom.xml` 中 `maven.compiler.source/target` 为 25，需 JDK 25 或更高） |
| 构建 | Maven 3.9.11 + JUnit 5（jupiter 5.14.4，surefire 3.2.5） |
| 已验证运行环境 | Windows 11 |

```bash
mvn package                      # 会先自动执行全部测试；产物：target/DWASearch.jar
java -jar target/DWASearch.jar input.txt output.txt
mvn test                         # 27 个测试，预期全绿
```

**打成可执行 jar 的方式**：`pom.xml` 用 `maven-jar-plugin` 写入 `MANIFEST.MF` 的 `Main-Class`，因此 `java -jar` 可直接运行，无需 `-cp`：

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>3.4.1</version>
    <configuration>
        <archive>
            <manifest>
                <mainClass>DWASearch</mainClass>   <!-- 即 MANIFEST.MF 的 Main-Class -->
            </manifest>
        </archive>
    </configuration>
</plugin>
```

`<finalName>DWASearch</finalName>` 使产物固定为 `target/DWASearch.jar`。

> 程序从**项目根目录**读取数据快照 `data_test.json`（相对路径），因此请在项目根目录下执行 `java -jar`，或保证工作目录含该文件。

Maven 生命周期顺序：

validate → compile → test → package → verify → install → deploy

| 阶段 | 作用 |
|---|---|
| `validate` | 验证项目配置与必要信息是否齐全 |
| `compile` | 编译主源码到 `target/classes` |
| `test` | 编译并运行单元测试（Surefire 插件） |
| `package` | 打包成 jar，输出到 `target` |
| `verify` | 对打包结果做验证（集成测试、质量检查） |
| `install` | 安装到本地仓库 `~/.m2/repository` |
| `deploy` | 发布到远程仓库 |

---

## 数据来源与抓取

### 来源

数据来自 World Aquatics 官方公开 API，共三个接口：

| 用途 | 接口 |
|---|---|
| 赛事列表 | `https://api.worldaquatics.com/fina/competitions/5019/events` |
| 参赛选手 | `https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId=` |
| 单项目详情 | `https://api.worldaquatics.com/fina/events/{eventId}` |

抓取时间：**2026-11-05T07:34Z**（本机 UTC+8 于 15:34 抓取）。数据快照 `data_test.json` 于 **2026-11-07T10:44Z** 由 `DataMerger` 重新合并生成。

程序运行与测试期间**只读本地文件，不访问网络**。项目自带的数据快照是 `data_test.json`（项目根目录），程序只读它。

### 抓取与合并脚本

抓取与合并由两个 Java 类完成，不引入任何第三方 HTTP 或 JSON 库：

| 类 | 职责 |
|---|---|
| `src/main/java/Crawler.java` | 用 `java.net.http.HttpClient` 顺序请求赛事列表与选手两个接口，落地 `athletes.json` / `events.json`；再按 `(Gender, DisciplineCode)` 在 `events.json` 中定位 8 个项目的 `eventId`，逐个请求详情并落地 `event_<Gender>_<Code>.json`。请求间 `Thread.sleep(500)` 控制频率 |
| `src/main/java/DataMerger.java` | 把上述原始 JSON 合并为最终数据快照 `data_test.json`（89 名选手 + 8 个项目决赛成绩） |

用法：

```bash
javac -d target/classes src/main/java/*.java
java -cp target/classes Crawler        # 生成 athletes.json / events.json / event_*.json
java -cp target/classes DataMerger     # 合并生成 data_test.json
```

`Crawler` 固定抓取以下 8 个项目（考核规定的 8 条 `result` 命令）：

| Gender | Code | 命令 |
|---|---|---|
| Women | 002 | `result women 3m springboard` |
| Women | 003 | `result women 10m platform` |
| Women | 202 | `result women 3m synchronised` |
| Women | 203 | `result women 10m synchronised` |
| Men | 002 | `result men 3m springboard` |
| Men | 003 | `result men 10m platform` |
| Men | 202 | `result men 3m synchronised` |
| Men | 203 | `result men 10m synchronised` |

### 自定义 JSON 结构

合并后的数据文件结构如下（`schemaVersion` 固定为 `1`）：

```text
{
  "schemaVersion": 1,
  "players": [
    {
      "fullName":    string   // "姓 名"，姓在前且全大写，如 "COLE Ellie"
      "gender":      string   // "Male" | "Female" | "Unknown"
      "country":     string   // 国籍全称，如 "People's Republic of China"
      "countryCode": string   // 三字母代码，如 "CHN"
    }
  ],
  "events": [
    {
      "command": string       // 完整 result 命令，如 "result women 3m springboard"
      "results": [
        {
          "fullName":    string   // 同步项目为 "A & B"
          "rank":        number   // 决赛名次，整数
          "countryCode": string   // 三字母代码
          "scores":      string[] // 每跳得分，两位小数字符串，如 "73.50"
          "totalPoints": string   // 官方总分，两位小数字符串
        }
      ]
    }
  ]
}
```

设计要点：

1. **`scores` 与 `totalPoints` 用字符串而非浮点数。** 分数全程以字符串保存、以 `BigDecimal` 解析，避免二进制浮点误差污染展示与累加。
2. **`scores` 按数组顺序即 `DiveOrder` 顺序。** 源数据中 `Dives` 数组本身按跳水顺序排列，直接按序取值。
3. **同步项目（202/203）的 `fullName` 为两人姓名。** 源数据 `result.FullName` 用 ` / ` 分隔且顺序与官方不一致；`Competitors[].FullName` 数组顺序同样不一致。经逐条比对，官方顺序为 **`Competitors` 按姓名升序**，故合并器按该规则排序后用 ` & ` 拼接。
4. **数据文件只含程序实际读取的字段。** 上游 API 另有 `competition` 块与 `eventId`/`gender`/`discipline`/`eventType` 等元数据，本程序不读取，故合并时未生成。

---

## 设计与实现

### 模块结构

```
DWASearch.java     命令行入口：参数检查 → 读数据 → 逐行调 CoreModule → 写出结果。不含业务逻辑。
CoreModule.java    核心门面：构造时注入数据全文；加载+排序+预格式化选手块；
                   handleLine(String)→String：一行命令进、该命令的完整输出文本出。纯计算，无 IO、无打印。
DataLoader.java    数据访问：按 {} 圈定记录边界后取字段，loadPlayers / loadResults。
PlayerFormatter    选手信息块格式化。
ResultFormatter    成绩块格式化（分数累加用 BigDecimal）。
Crawler.java       数据抓取（开发期使用，不参与考核运行）。
DataMerger.java    数据合并（开发期使用，不参与考核运行）。
```

数据流：`args → input.txt 逐行 → CoreModule.handleLine → StringBuilder 拼装 → Files.writeString(output.txt, UTF-8)`。


---

## 异常处理说明

| 场景 | 行为 |
|---|---|
| 命令行参数不足 2 个 | `System.err` 输出一行用法提示，退出码 1，不打印堆栈 |
| input/数据文件不存在或不可读 | `System.err` 输出一行错误说明，退出码 1；**错误信息绝不写入 output.txt**（错误走 stderr，结果走结果文件，双通道分离） |
| **数据文件 JSON 损坏 / 缺必需字段** | `System.err` 输出 `Error: invalid data - <原因>`，退出码 1，**不产出结果文件**，不打印堆栈 |
| 未知命令（含大小写不符、命令粘连） | 输出块 `Error\n-----\n` |
| `result` 后项目名不合法/带多余字符/多余空格 | 输出块 `N/A\n-----\n` |
| output 路径不可写/目录不存在 | 与读失败同一 catch：一行 stderr + 退出码 1 |

### 三条固定行为约定（考核要求）

| # | 场景 | 固定行为 |
|---|---|---|
| 1 | **空输入文件** | 输出文件为 0 字节（`readAllLines` 返回空列表，循环不执行） |
| 2 | **空行** | **按「非法命令」处理**，输出 `Error\n-----\n`；N 个空行产出 N 个 Error 块。纯空白行（空格/制表符）同样处理，因为命令匹配不做空格归一化 |
| 3 | **JSON 损坏 / 缺字段** | **明确失败**：抛 `IllegalArgumentException`，入口转为 `Error: invalid data - <原因>` + 退出码 1。已覆盖 11 种缺字段场景（`events`/`gender`/`country`/`rank`/`scores`/`totalPoints`/`countryCode`/`fullName`/数组未闭合等） |

设计原则：先定义每种输入的可观察行为（契约），再实现；异常不被吞掉，也不原样抛给用户。

---

## 单元测试展示

`mvn test`：**27 个测试全绿**（JUnit 5）。

| 测试类 | 数量 | 覆盖 |
|---|---|---|
| `CoreModuleTest` | 5 | 选手块开头逐字符、89 条分隔线计数、`result sss`→N/A 精确匹配、`player`→Error（大小写敏感）、官方样例成绩行 `Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40` |
| `DataLoaderTest` | 11 | **三条必测要求**：空文件→0 字节输出、空行→Error 块（含多空行、纯空白行）、JSON 缺字段必须明确失败（9 种损坏场景） |
| `PlayerTest` | 2 | 双关键字排序逐位断言、格式化输出精确匹配+分隔线计数 |
| `BigDecimalTest` | 3 | 分数累加=374.40、单元素、空列表契约（返回 `0.00`） |
| `WarmupTest` / `EnvCheckTest` | 6 | 学习阶段的语法与环境验证练习，作为学习轨迹保留 |

测试断言的期望值一律来自官方样例、数据实测或行为契约（README 明文约定），**不来自被测代码的输出**（防"抄代码自证"）。`DataLoaderTest` 在构造损坏数据时额外断言"数据确实被改坏了"，避免正则未命中导致测试假通过。

### 回归样本

`input.txt` 为 5 命令混合样本（`players` / 合法 `result` / 非法命令 `player` / 未知项目 `result sss` / 重复 `result`），输出 **392 行**，作为人工回归基线（`output.txt` 已列入 `.gitignore`，运行后可自行生成对比）。

---

## 已知限制

1. **排序只用两个关键字**：`(country, lastName)`，其中 `lastName` 取 `fullName` 空格前的子串。当同国籍且同姓氏时（如 `CHEN Jia` / `CHEN Yiwen`），两键相等，顺序依赖 `List.sort` 的稳定性（保留数据快照中的原始顺序）。若换成未预排序的数据源，应补 `fullName` 作为第三排序键。
2. **同步项目姓名顺序**：上游 `Competitors` 数组顺序与官方展示顺序不一致，合并器按姓名升序拼接后 ` & ` 连接。若上游改动该规则，需重新核对。
3. **输入文件需为 UTF-8**：程序按 UTF-8 读取 `input.txt`。Windows PowerShell 的 `echo >` 默认写 UTF-16LE，会导致读取失败（`Error: Input length = 1`）。请用 `[IO.File]::WriteAllText(path, text, [Text.Encoding]::UTF8)` 或编辑器另存为 UTF-8 生成输入文件。
4. **手写 JSON 解析**：未引入 Gson/Jackson，按"先圈 `{}` 边界再取字段"的规则解析。上游若改变字段名或嵌套结构，`DataLoader` 需同步调整；字段缺失会明确抛 `IllegalArgumentException` 而非静默降级。
5. **`Crawler` 依赖上游接口**：赛事 ID 固定为 `5019`，若 World Aquatics 变更接口路径或赛事 ID，需同步修改 `Crawler.java` 中的 URL 常量。
6. **不做原子写入**：输出写入中途失败可能留下部分结果文件，程序以非零退出码结束，本轮不要求原子写入。

---

## 目录结构

```
├─ src/main/java/        # DWASearch / CoreModule / DataLoader / Player / 两个 Formatter
│                        # Crawler / DataMerger 为抓取与合并脚本（可重复运行）
├─ src/test/java/        # JUnit 5 测试（27 个）
├─ data_test.json        # ★数据快照：程序唯一读取的数据文件（Crawler + DataMerger 产物）
├─ athletes.json         # 抓取原始结果：选手列表
├─ events.json           # 抓取原始结果：赛事列表
├─ event_*.json (8 个)   # 抓取原始结果：8 个项目详情
├─ input.txt             # 输入样例（多命令回归样本）
├─ pom.xml               # Maven 构建配置（含 Main-Class）
├─ README.md / .gitignore / .gitattributes
```

`target/`、`output.txt` 为构建产物与运行输出，已列入 `.gitignore`，不提交。
