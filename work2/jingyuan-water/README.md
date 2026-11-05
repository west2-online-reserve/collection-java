# DWASearch

2026 年 World Aquatics Diving World Cup Super Final（北京）成绩查询工具。
读取一份指令文件，把每条指令的查询结果写到输出文件。

```
java -jar DWASearch-1.0-SNAPSHOT.jar <指令文件> <输出文件>
```

---

## 1. 环境与构建

| 项 | 版本 |
| --- | --- |
| JDK | 25（`maven.compiler.release=17`，字节码目标 Java 17） |
| Maven | 3.9（IDEA 自带即可） |
| 依赖 | Jackson 2.17.2（JSON 解析）、JUnit Jupiter 5.11.4（测试，test 作用域） |

```bash
mvn clean             # 清理
mvn test              # 跑单元测试
mvn clean package     # 打包（生成 target/DWASearch-1.0-SNAPSHOT.jar，已含 Jackson）
```

打包后运行：

```bash
java -jar target/DWASearch-1.0-SNAPSHOT.jar input.txt output.txt
```

---

## 2. 数据来源

数据来自 World Aquatics 官网前端自用的公开 API（Pulselive 平台，无需鉴权），赛事 ID **5019**：

| 用途 | 端点 |
| --- | --- |
| 项目列表（含各项目 disciplineId） | `https://api.worldaquatics.com/fina/competitions/5019/events` |
| 选手名单 | `https://api.worldaquatics.com/fina/competitions/5019/athletes` |
| 单项目全轮次成绩 | `https://api.worldaquatics.com/fina/events/{disciplineId}` |

- **抓取脚本**：`src/main/java/dwasearch/FetchData.java`（Java，`HttpClient` + Jackson）
  - 可重复运行：`java -cp target/DWASearch-1.0-SNAPSHOT.jar dwasearch.FetchData [输出路径]`
  - 带 3 次重试 + 退避，请求间隔 600ms（礼貌抓取）
  - 失败时打印原因并以非 0 退出码结束，不会写半份数据
- **数据快照**：`src/main/resources/data.json`（**89 名选手 / 16 个国家 / 8 个项目**）
  - 快照抓取时间：**2026-11-02 19:02（UTC+8）**
  - 2026-11-05 15:33 重新运行抓取脚本复核：players / events / meta 与快照**完全一致**（仅时间戳不同），可复现性已验证
- **查询程序只读本地 `data.json`，运行时不联网**

> 说明：`Gender` 字段在 API 里是数字（`0`=男，`1`=女），脚本已转换为 `Male` / `Female`；
> 双人项目的 `FullName` 形如 `"CHEN Jia / CHEN Yiwen"`（两名选手的组合名）。

---

## 3. 功能与设计决定

### 支持的指令

| 指令 | 输出 |
| --- | --- |
| `players` | 全部选手：`Full Name` / `Gender` / `Country`，每条后跟 `-----` |
| `result <项目名>` | 该项目决赛成绩：`Full Name` / `Rank` / `Score`，每条后跟 `-----` |

排序**由程序完成**（不依赖抓取数据的原始顺序）：

- `players`：国家升序 → 国家相同时按姓氏升序
- `result`：按名次 `rank` 升序

### 合法项目名（仅这八个，区分大小写，空格必须完全一致）

```
women 3m springboard      women 10m platform
women 3m synchronised     women 10m synchronised
men 3m springboard        men 10m platform
men 3m synchronised       men 10m synchronised
```

### 指令判定规则（顺序即优先级）

1. 去掉行尾 `\r`（兼容 Windows CRLF 输入）
2. 完全等于 `players` → 输出选手名单
3. 完全等于 `result` → `N/A`（认出了 result，但没给项目名）
4. 以 `result ` 开头 → 取剩余部分作为项目名：在八个之内则输出成绩，否则 `N/A`
5. 其它 → `Error`

即：**连 `result` 都认不出** → `Error`；**认出 result 但项目名不对** → `N/A`。两者都输出 `answer` + `-----` 后继续处理下一条，不中断。

### 已知设计决定：空行

**空行按「忽略」处理**（不输出任何内容），由 `CommandRunnerTest#blankLineIsIgnored` 固定。

理由：空行表示"这一行没有指令"，不等于"这一行写错了"，输出 `Error` 会把"没写"误报成"写错"。
仅含空格或 `\r` 的行同样视为空行。

### 数值处理

分数与总分全程使用 `BigDecimal`（`new BigDecimal(String)` + `setScale(2, HALF_UP)` + `toPlainString()`），
**不使用 `double`**，避免二进制浮点误差导致两位小数失真。

### 输出约定

- UTF-8 编码，换行一律 `\n`（不使用 `System.lineSeparator()` / `writer.newLine()`）
- 每条记录（包括最后一条）后面都有 `-----`
- 记录之间不插空行；冒号后无空格（`Full Name:COLE Ellie`）
- 逐条对应输入：同一条指令出现两次就输出两遍，**不去重**

---

## 4. 代码分工

| 文件 | 职责 |
| --- | --- |
| `Player` / `EventResult` / `CompetitionData` | 数据模型，字段与 `data.json` 一一对应（Jackson 反序列化） |
| `DataLoader` | 用 `getResourceAsStream("/data.json")` 读数据，打包进 jar 后换目录也能找到 |
| `CommandRunner` | 指令解析 + 查询 + 格式化输出；可独立实例化调用，测试无需启动整个程序 |
| `DWASearch` | 程序入口：参数校验、故障处理、调用 `process()` 完成文件读写 |
| `FetchData` | 数据抓取脚本（独立 main，可重复运行） |

`DWASearch.process(Path, Path, CommandRunner)` 单独抽出：它只抛 `IOException`、不调 `System.exit`，
因此文件读写链路可以被 JUnit 直接测试。

---

## 5. 测试

```bash
mvn test
```

结果（12 个测试全部通过）：

| 测试类 | 数量 | 覆盖 |
| --- | --- | --- |
| `CommandRunnerTest` | 9 | 选手排序（国家→姓氏）、成绩格式与两位小数、重复指令输出两次、`Error` / `N/A` 各种情况、`\r` 处理、空行忽略、每条记录（含最后一条）带 `-----` |
| `FileIOTest` | 3 | 空输入→空输出、多指令链路（含 Error/N/A 计数、结尾分隔线）、输出必须是纯 LF 无 `\r` |

实测输出（Maven surefire）：

```
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0   -- CommandRunnerTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0   -- FileIOTest
```

文件相关测试使用 JUnit `@TempDir`，不依赖本机具体路径。

### 故障处理（已实测）

| 故障 | 行为 |
| --- | --- |
| 参数个数不为 2 | 打印用法到 **stderr**，退出码 **1** |
| 输入文件不存在 / 输出路径不可写 | 打印含输入、输出路径及原因到 **stderr**，退出码 **1** |
| `data.json` 损坏或缺失 | 打印"数据文件 data.json 读取失败"到 **stderr**，退出码 **1** |

正常结束退出码 `0`。**故障一律走 stderr + 非零退出码，绝不返回空结果或伪造成功。**

真实运行验证：在 `C:\Windows\Temp` 目录下用绝对路径调用 jar，正常生成输出，
内容与 IDEA 中运行结果**逐字节一致**；故障场景退出码实测为 `1`，正常场景为 `0`。

---

## 6. 目录结构

```
DWASearch/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   ├── java/dwasearch/
    │   │   ├── DWASearch.java          程序入口（参数校验、故障处理、文件读写）
    │   │   ├── CommandRunner.java      指令解析 + 查询 + 格式化
    │   │   ├── DataLoader.java         读取 classpath 下的 data.json
    │   │   ├── CompetitionData.java    数据模型根对象（含内部类 Event）
    │   │   ├── Player.java             选手
    │   │   ├── EventResult.java        单条决赛成绩
    │   │   └── FetchData.java          数据抓取脚本（独立 main）
    │   └── resources/
    │       └── data.json               数据快照（随 jar 打包）
    └── test/java/dwasearch/
        ├── CommandRunnerTest.java      指令解析与格式化（9 个）
        └── FileIOTest.java             文件读写链路（3 个）
```

构建产物在 `target/`，`.gitignore` 已排除 `target/`、`.idea/` 与本地测试用的 `input.txt` / `output.txt`。
