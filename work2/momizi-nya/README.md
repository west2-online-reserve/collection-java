# DWASearch（第一轮作业）

这是我们第一轮的 Java 作业，做一个跳水比赛的查询小工具。

比赛是 2026 年 World Aquatics Diving World Cup 的总决赛（北京，赛事 ID 5019），
要查的东西有两个：所有选手的信息，还有八个项目的决赛成绩。

程序用法：

```text
java -jar DWASearch.jar input.txt output.txt
```

**记得要在项目根目录下运行**，因为程序默认读当前目录的 `data.json`。
（我一开始把 data.json 塞进 jar 里读，结果一直报错，后来改成读工作目录下的文件就好了。）

## 环境

- 系统：Windows 11
- JDK：Zulu OpenJDK 25（老师要求 17+，我写的时候是按 17 写的）
- 构建工具：电脑上没装 Maven，所以自己写了个 `build.ps1` 来编译和打包；
  同时也放了 `pom.xml`，装了 Maven 的话直接 `mvn package` 也行。
- 依赖：Jackson 2.17.2 用来解析 JSON，JUnit 5.10.2 用来写测试，jar 都在 `lib/` 里。

## 怎么构建、怎么运行、怎么测试

没有 Maven 的话用这个（会先编译、再跑测试、最后打成 jar）：

```bash
powershell -ExecutionPolicy Bypass -File build.ps1
```

跑起来：

```bash
java -jar DWASearch.jar sample_input.txt output.txt
```

单独跑测试：

```bash
java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path "out/classes;out/test-classes;lib/jackson-databind-2.17.2.jar;lib/jackson-core-2.17.2.jar;lib/jackson-annotations-2.17.2.jar" --select-package dwasearch
```

（`build.ps1` 里已经带这一步了，正常不用单独跑。）

`Main-Class` 是 `dwasearch.DWASearch`：`pom.xml` 里用 maven-jar-plugin 写的，
`build.ps1` 里是手动写 `MANIFEST.MF`，然后把 Jackson 的 class 一起解进 jar。

## 数据

数据用的是课程给的第一轮固定数据包（`learn-backend/data/round-1-diving-2026`），
抓取时间是 `2026-08-29T06:47:21Z`，一共 89 名选手、8 个项目。

我另外写了 `scripts/fetch_data.py` 自己抓了一遍，跟数据包对了一下：八个项目的名次、
每一跳的得分、总分都是一样的，选手也一样，只有一个人的名字拼写不太一样
（接口里是 VYSTAVKINA，数据包里是 VYSTAKINA），所以最后交的还是数据包里的那份。

数据接口是这三个：

- `https://api.worldaquatics.com/fina/competitions/5019/events`（项目和选手）
- `https://api.worldaquatics.com/fina/competitions/5019/athletes`
- `https://api.worldaquatics.com/fina/events/{eventId}`（单个项目的成绩）

`data.json` 长这样：

```text
{
  "schemaVersion": 1,
  "competition": { "id", "name", "from", "to" },
  "players": [ { "fullName", "gender", "country", "countryCode" } ],
  "events": [
    { "command", "eventId", "gender", "discipline", "eventType",
      "results": [ { "fullName", "rank", "countryCode", "scores": ["73.50", ...], "totalPoints": "374.40" } ] }
  ]
}
```

分数我都是当字符串用的，没有转成 double。因为试过用 double 加，
结果会变成 374.39999 这种，后面干脆直接拼字符串，反正数据里本来就是两位小数。

## 做了哪些功能

1. `players`：输出所有选手的姓名、性别、国家。排序是自己写的：
   先按国家，国家一样按姓，姓也一样再按全名。
2. `result 项目名`：输出这个项目决赛的成绩，按名次从小到大。
   每一跳用 ` + ` 连起来，等号后面是总分。
3. 命令文件可以写多行，也可以重复写同一个命令（重复的会输出两遍）。
4. 每个选手、每个成绩、每条错误后面都跟一行 `-----`，最后一条也有。
5. 空行我选择直接跳过，不当成错误命令。空文件就是空输出。

八个项目名（要一模一样，大小写和空格都不能改）：

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

## 命令写错了会怎样

- 认不出来的命令（比如 `player`、`Players`、`players extra`、`resultwomen 3m springboard`）输出 `Error`。
- 是 `result` 但项目不对、没写项目、或者后面多了字，输出 `N/A`。
  比如 `result`、`result sss`、`result women 10m springboard`、
  `result women 3m springboard details`、`result men 10m     synchronised`。
- 这些都不会让程序退出，会接着往下处理后面的命令。

## 出错了怎么办

程序打不开文件或者数据坏了的时候，就没法保证结果是对的，所以我让它直接报错退出：

- 参数不是两个：打印用法，退出码 2
- 输入文件不存在或者读不了：说清楚是哪个文件，退出码 3
- data.json 不存在、解析不了、缺字段：提示数据文件出错，退出码 4
- 输出文件写不进去：提示输出可能不完整，退出码 5

这些提示都打到标准错误里，不会混进正常的查询结果里。
数据坏的时候不会生成输出文件，也不会输出一份看着正常的空结果。

## 代码怎么分的

- `DWASearch.java`：主程序，检查参数、读命令文件、写结果
- `DataLoader.java`：读 data.json，检查字段有没有缺
- `Query.java`：两个查询都在这，负责排序和拼输出文本
- `Player.java`、`ResultItem.java`、`GameData.java`：用来存数据的类
- `scripts/fetch_data.py`：抓数据用的脚本

查询和读数据都单独放在类里，测试的时候可以直接 new 出来用，不用启动整个程序。

## 测试

一共 12 个测试，全部通过。大致覆盖了：

- 选手排序对不对，是不是每个人后面都有分隔线
- 成绩的连接格式（` + ` 和等号后的总分）
- 结果按名次排
- 项目名不对返回 N/A
- 参数个数不对、输入文件不存在、输出写不了、数据文件坏掉，退出码对不对
- 用真实的 data.json 跑一遍，抽查了 `73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40` 这条
- 空文件是空输出，重复命令输出两遍，换行都是 `\n`

## 已知的问题

- 空行是跳过的。如果要求把空行当非法命令，改 `DWASearch` 里那个 `if (line.length() == 0)` 就行。
- Bonus 一个都没做。
- 成绩直接用数据里的字符串，不做四舍五入。

## 目录

```text
DWASearch/
├─ data.json             数据快照
├─ manifest.json         数据来源和抓取时间
├─ sample_input.txt      示例命令
├─ sample_output.txt     示例输出
├─ src/main/java/dwasearch/   源码
├─ src/test/java/dwasearch/   测试
├─ scripts/fetch_data.py      抓数据的脚本
├─ lib/                       Jackson 和 JUnit 的 jar
├─ pom.xml
├─ build.ps1
└─ README.md
```
