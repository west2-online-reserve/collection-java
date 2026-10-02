# DWAsearch-tool

从世界泳联（World Aquatics）公开 API 抓取跳水比赛数据，按查询文件批量输出结果。

## 使用方式

### 运行

```bash
java -jar task1-1.0-SNAPSHOT.jar <查询文件> <输出文件>
```

两个参数都是文件路径：第一个是查询文件（输入），第二个是结果文件（输出）。

打包：在 `task1` 目录执行 `mvn package`，产物在 `target/task1-1.0-SNAPSHOT.jar`。

### 查询文件怎么写

每行一条查询，共两种：

| 行内容 | 含义 |
|---|---|
| `players` | 输出全部运动员名单 |
| `result <项目名>` | 输出该项目的决赛成绩 |

`项目名` 必须与 API 返回的名称**完全一致**（区分大小写和撇号）。目前支持 8 个项目：

```
Women's 3m Springboard     Men's 3m Springboard
Women's 10m Platform       Men's 10m Platform
Women's 3m Synchronised    Men's 3m Synchronised
Women's 10m Synchronised   Men's 10m Synchronised
```

查询文件示例：

```
players
result Women's 3m Springboard
result Men's 10m Platform
```

### 输出格式

`players` 段落：

```
Full name:CHEN Jia
Gender:Female
Country:CHN
------------------
```

`result` 段落（Score 里每跳得分用 `+` 连接，等号后为总分）：

```
Full Name:CHEN Jia
Rank:1
Score:72.00+72.00+…=310.50
------------------
```

### 注意

- 输出文件会被**直接覆盖**，不会追加。
- 查询行只认两种：`players`，或以 `result `（小写 + 一个空格）开头。其它行（含空行、过短的行）一律输出 `Error`。
- 项目名匹配不上时输出 `N/A`。
- 需要能访问 `api.worldaquatics.com`。程序按直连方式请求，不走系统代理。

## 实现方式

### 流程



​	这里分别爬取了9个url，包括一个运动员url，8个比赛结果url，具体的思路是点进每个页面fetch/xhr进行筛选，然后f5刷新一下，有新的json就点进去看，找到装载信息的json，然后爬取。

​	这里由于先做的运动员名单的爬取，json较为简单，直接构造了对应的类进行映射，而比赛结果的json较为复杂，于是使用了JsonNode通过节点来找数据。最后我使用了List来存运动员，用map来存比赛项目及结果，达到一种伪数据库的效果。

1. **抓运动员名单** —— 请求 `competitions/5019/athletes`，反序列化成 `List<JoinCountry>`
2. **抓 8 场比赛** —— 遍历写死的 8 个 event UUID，逐个解析，结果放进 `Map<项目名, Discipline>`
3. **执行查询** —— 逐行读查询文件，命中 `players` 或 `result` 就写结果到输出文件

### 代码结构

| 文件 | 职责 |
|---|---|
| `DWAapplication.java` | 主流程：抓数据 → 读查询 → 写结果 |
| `utils/JacksonUtils.java` | 单场赛事解析：读 `Heats[0].Results`，拼出每跳分数串 |
| `pojo/Discipline.java` | 赛事模型：项目名 + 选手列表（姓名 / 名次 / 分数字符串） |
| `pojo/JoinCountry.java` | 运动员模型：国家 + 参赛记录（性别 / 姓名 / NAT） |

### 数据来源

- 比赛 ID：`5019`
- 8 个赛事 UUID 与查询用的名称之间是**写死的映射**，新增项目需要改代码
- 只取 `Heats[0]`，实测即决赛（`PhaseCode=100`）

### 技术栈

| 依赖 | 版本 | 用途 |
|---|---|---|
| Java | 17 | |
| httpclient5 | 5.1.1 | 发 HTTPS 请求 |
| jackson-databind | 2.9.6 | JSON 解析 |
| lombok | 1.18.42 | POJO 的 getter / setter |
| jsoup | 1.13.1 | 已引入，暂未使用 |
