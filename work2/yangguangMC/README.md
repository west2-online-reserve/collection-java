# DWASearch

## 构建、运行与测试

### 环境

- OS: Windows 11（实际环境，非要求环境）
- JDK: 17.0.20.1（要求 JDK 17+）
- Maven: 3.9.16（使用 Maven Wrapper）
- IntelliJ IDEA: 2026.2.3

### 构建与打包

使用 `maven-shade-plugin` 打包依赖，并通过 `ManifestResourceTransformer` 指定入口类：

```xml

<mainClass>top.yangguangmc.dwasearch.DWASearch</mainClass>
```

打包产物在 `target` 文件夹中，有两个：

- `DWASearch.jar` 为可执行的 FatJar/UberJar，已打包全部依赖
- `original-DWASearch.jar` 为普通 Jar，未打包依赖，不可直接运行

打包命令（分别为 Windows 和 macOS/Linux）：

```powershell
.\mvnw.cmd clean package
```

```shell
./mvnw clean package
```

### 测试

测试使用框架 JUnit 编写。

运行测试的命令：

```powershell
.\mvnw.cmd test
```

```shell
./mvnw test
```

实际执行结果（2026-11-06）：

```text
Tests run: 27, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

| 测试类                     | 覆盖行为                                                                          |
|----------------------------|-----------------------------------------------------------------------------------|
| `DWADataSetTest`           | 资源不存在、缺少 `Finals`、JSON 语法错误、字段类型错误                            |
| `DWASearchTest`            | 参数数量错误、输入不存在、空输入文件、输入/输出为目录；使用临时文件/目录          |
| `InstructionProcessorTest` | 空白行、12 种非法命令、重复命令、错误后继续处理                                   |
| `PlayerTest`               | 性别映射与非法值、排序、输出格式                                                  |
| `ResultTest`               | 排名排序、成绩输出格式                                                            |
| `SnapshotQueryTest`        | `players` 首条与 89 条总数；`women 3m springboard` 首条、4 条总数、排除半决赛选手 |

坏数据测试使用 `src/test/resources/test/data` 下的小样本， 不修改 `src/main/resources/data` 中的提交快照；
快照查询测试只读本地资源，不访问网络。

### 运行

#### 可执行 Jar 运行

命令（Windows PowerShell、macOS/Linux 均可）：

```shell
mkdir run
cd run
java -jar ../target/DWASearch.jar <输入文件路径> <输出文件路径>
```

#### 使用 IDEA 运行

导入 Maven 项目后，创建一个 Application 运行配置：

- Main class：`top.yangguangmc.dwasearch.DWASearch`
- Program arguments：`<输入文件> <输出文件>`
- Working directory：建议在项目文件夹下创建 `run` 目录作为工作目录（已在 `.gitignore` 中排除），把输入输出文件放进去

该配置与上面的 java -jar ... 等价，仅用于本地调试，不是运行项目的必要条件。

## 数据抓取

目标赛事：World Aquatics Diving World Cup 2026 - Super Final（北京）。

### 数据源

#### 接口信息

数据源项目名称与地址：

| 项目名                   | 地址                                                                             |
|:-------------------------|:---------------------------------------------------------------------------------|
| `women 3m springboard`   | https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82   |
| `women 10m platform`     | https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1   |
| `women 3m synchronised`  | https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d   |
| `women 10m synchronised` | https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b   |
| `men 3m springboard`     | https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7   |
| `men 10m platform`       | https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48   |
| `men 3m synchronised`    | https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c   |
| `men 10m synchronised`   | https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5   |
| `athletes`               | https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId= |

这些内容也在 `top.yangguangmc.dwasearch.FetchData` 类的顶部 `static` 块中列出。

均使用 HTTP `GET` 请求，头 `Accept` = `application/json`。

#### 数据格式

9 个 JSON 文件中，`athletes.json` 的有效格式如下：

```json5
[
  // A country object
  {
    "CountryName": "CountryName",
    "Participations": [
      // An athlete object
      {
        "PreferredFirstName": "First Name",
        "PreferredLastName": "LAST NAME",
        // 0: male, 1: female
        "Gender": 0
      },
      // ...
    ]
  },
  // ...
]
```

其余赛事 `json` 的有效格式如下：

```json5
{
  "Heats": [
    // A heat object
    {
      // Name/type of this heat. Can be "Finals", "Semifinals" and "Head-To-Head".
      "Name": "Finals",
      "Results": [
        // A result object
        {
          "FullName": "LAST NAME First Name",
          "Rank": 1,
          "Dives": [
            // A dive object
            {
              "DivePoints": "100.00"
            },
            // ...
          ]
        },
        // ...
      ]
    },
    // ...
  ]
}
```

### 运行与抓取

脚本类：`top.yangguangmc.dwasearch.FetchData`，无程序参数。
请求为三线程并发进行，同一线程先后间隔 500ms，超时 30s，失败时向 STDERR 输出错误信息，并且自动重试最多三次。
当前脚本不会因个别文件失败而自动清理上一次生成的文件，也不会返回非零退出码，所以运行后请检查 STDERR。

可以使用命令或类似上面的 IDEA 配置运行。若不存在则创建运行目录。命令如下：

```shell
mkdir run
cd run
java -cp ../target/DWASearch.jar top.yangguangmc.dwasearch.FetchData
```

运行后在工作目录下生成获取到的 JSON 文件，列表如下：

- `women 3m springboard.json`
- `women 10m platform.json`
- `women 3m synchronised.json`
- `women 10m synchronised.json`
- `men 3m springboard.json`
- `men 10m platform.json`
- `men 3m synchronised.json`
- `men 10m synchronised.json`
- `athletes.json`

用这个方法获取到的文件放置在 `src/main/resources/data` 目录下，作为查询模块的数据来源。
该目录下这些文件抓取于北京时间 2026 年 10 月 18 日 11 点 10 分。

## 查询功能

查询以命令为单位。
输入文件当中的每一行将被视作一条命令，空行除外。
空行是指仅存在空白字符（如空格、制表符、换行符）的行，空行不被视为命令，会被忽略，不会视为一个小节而输出分割线。
对于所有非空行，如果命令无效，输出 `Error`；如果命令识别成功但参数不正确（在内容或数量上），输出“N/A”。
命令区分大小写，按文档中的小写形式匹配，否则会被认为是无效命令。

每一条命令的输出之后总会有分割线 `-----`，包括无效命令和参数不正确的命令。
命令之间不输出额外空行；重复命令会再次输出，不会去重。
输出文件已存在时会被覆盖；不存在时会自动创建文件及其父目录（如需）。
成功时 STDOUT 输出 `Query completed.`，输出文件无此行。

### 查询选手

- 命令语法：`players`
- 输出：全部选手的全名、性别和国籍（每条选手信息输出之后带有分割线 `-----`）
- 排序：按 `Country` 升序、`Last Name` 升序
- 输出格式：

```text
Full Name:LAST NAME First Name
Gender:Male/Female
Country:Country Name
```

### 查询决赛结果

- 命令语法：`result <项目名称>`，项目名称必须为以下之一，否则输出 `N/A`：
    - `women 3m springboard`
    - `women 10m platform`
    - `women 3m synchronised`
    - `women 10m synchronised`
    - `men 3m springboard`
    - `men 10m platform`
    - `men 3m synchronised`
    - `men 10m synchronised`
- 输出：该项目决赛中选手姓名、排名、各跳成绩和总分（每条选手信息输出之后带有分割线 `-----`）
- 排序：按 `Rank` 升序
- 输出格式：

```text
Full Name:LAST NAME First Name
Rank:1
Score:1.00 + 2.00 + 3.00 + 4.00 + 5.00 = 15.00
```

## 代码职责分工

|          类名          | 分工                                                       |
|:----------------------:|:-----------------------------------------------------------|
|      `DWASearch`       | 主类，入口点和退出码，负责整个流程的调度                   |
|      `DWADataSet`      | 加载、解析和持有所有赛事数据                               |
|      `IODataSet`       | 持有程序的业务输入输出信息，处理分割线                     |
| `InstructionProcessor` | 与 `IODataSet` 交互，处理指令                              |
|  `Player` 和 `Result`  | 数据模型，分别表示运动员和比赛结果，还负责自身的输出格式化 |
|      `FetchData`       | 数据抓取（另一入口点，与其余模块分开）                     |

## 已知限制

- 目前仅支持 UTF-8 输入输出（UTF-8 with BOM 不受支持）
- 输出文件写入失败时，可能留下部分输出
- 运动员和比赛结果排序遇到并列时将保留数据源的原始顺序

## 故障处理

| 场景                                 | 程序行为                                         | 退出码 |
|:-------------------------------------|:-------------------------------------------------|:------:|
| 命令行参数不是 2 个                  | STDERR 提示正确用法                              |   1    |
| 输入文件不存在、不可读或不是普通文件 | STDERR 指明输入文件                              |   1    |
| 输出路径已存在但不可写或是目录       | STDERR 指明输出文件不可写                        |   1    |
| 输出父目录不存在                     | 自动创建父目录（路径包含目录部分时）             |   0    |
| 数据资源文件缺失                     | STDERR 输出 `Resource not found: ...`            |   1    |
| 数据 JSON 语法错误                   | STDERR 输出 JSON 位置信息                        |   1    |
| 数据 JSON 字段类型错误               | STDERR 输出出错节点                              |   1    |
| 数据缺少必要字段                     | STDERR 输出缺少的属性名和所在文件/节点           |   1    |
| 输出写入失败                         | STDERR 明确提示 `The output might be incomplete` |   1    |
| 非法业务命令                         | 输出文件写 `Error` 或 `N/A`，继续处理后续命令    |   0    |
| 空文件或仅空行文件                   | 输出文件为空                                     |   0    |

程序对两类错误进行了分离：

- `Error` / `N/A` 是业务结果，写入输出文件；
- 文件或数据故障属于程序错误，写入 STDERR 并返回非零退出码。
