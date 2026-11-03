# DWASearch

世界泳联跳水世界杯 2026 超级总决赛（北京）的查询小工具。

做法很简单：先用 `Fetcher` 从官网抓下选手和 8 个项目的决赛成绩，存到 `data.json`；然后 `DWASearch` 读这份数据，按照 `input.txt` 里的命令查东西，结果写进 `output.txt`。查询的时候不联网。

## 环境

我自己用的是 JDK 21、Maven 3.9.16。依赖两个库：Jackson 2.17.2 解析 JSON，JUnit 5.10.2 做测试。JDK 17 以上应该都能跑。

## 构建和运行

```bash
mvn package
java -jar target/DWASearch.jar input.txt output.txt
```

`input.txt` 放命令，`output.txt` 是输出，都用 UTF-8。

测试：

```bash
mvn test
```

重新抓数据：

```bash
mvn compile exec:java
```

（`pom.xml` 里已经配好 `exec-maven-plugin` 的 `mainClass`，直接这么执行就行。）

## 数据

数据是官网接口抓的，竞赛 ID 5019。接口有三个：

- 选手：`GET https://api.worldaquatics.com/fina/competitions/5019/athletes`
- 项目列表：`GET https://api.worldaquatics.com/fina/competitions/5019/events`
- 单个项目成绩：`GET https://api.worldaquatics.com/fina/events/{DisciplineId}`

8 个项目的 `DisciplineId`（UUID）写死在 `Fetcher` 里。抓的时候两次请求之间 sleep 800ms，碰上 429/5xx 会等一会儿重试。

抓取时间：2026-11-03。

存下来的 `data.json` 大概是这样：

```json
{
  "players": [
    { "country": "Australia", "lastName": "COLE", "firstName": "Ellie", "gender": "Female" }
  ],
  "results": {
    "women 3m springboard": [
      { "rank": 1, "fullName": "CHEN Jia",
        "dives": ["73.50", "74.40", "76.50", "72.00", "78.00"],
        "totalPoints": "374.40" }
    ]
  }
}
```

分数我全存成字符串，输出的时候用 `BigDecimal` 转一下，保证两位小数。

## 功能

- `players`：所有选手，先按国籍升序，同国籍再按姓氏升序。
- `result <项目名>`：某个项目决赛的全部成绩，按排名升序。每跳分数用 ` + ` 连起来，最后 `= 总分`，两位小数。

8 个项目名是固定的，就是文档里那 8 个。

输出大概是这个样：

```text
Full Name:COLE Ellie
Gender:Female
Country:Australia
-----
Full Name:CHEN Jia
Rank:1
Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40
-----
```

每条记录后面都跟一行 `-----`，最后一条也有；命令之间不插空行。

命令写错的话：不认识的命令输出 `Error`；`result` 后面的项目名不对（缺了、写错、多了空格）输出 `N/A`。

空行我直接把这一行跳过，不输出也不报错。

## 代码结构

- `Fetcher`：联网抓数据，写 `data.json`
- `core.DataLoader`：读 `data.json`，顺便检查字段全不全
- `core.CommandProcessor`：一行行解析命令
- `core.QueryService`：查询、排序、拼输出
- `DWASearch`：入口，管文件读写
- `model/Athlete`、`model/Contest`、`model/CompetitionData`：数据类

## 测试

`mvn test` 跑了 14 个测试，主要覆盖：

- 查询：排序、输出格式、两位小数
- 命令：多行、重复命令、未知命令、缺/错项目名、空行、报错后继续
- 数据：JSON 坏了、少字段

测试都是自己造数据，不依赖本机路径，也不会改 `data.json`。

## 故障处理

参数个数不对、输入文件打不开、输出写不了、`data.json` 坏了，这些都会往 stderr 打一句话说明，然后以非零退出码结束。

## 已知限制

- 双人项目的名字是按接口原样输出的，就是 `A / B` 这样。
- 数据是静态快照，不会自动更新。
- 没做原子写入，写一半失败可能留下不完整的输出。
