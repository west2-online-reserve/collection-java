# DWASearch

这是我第一轮 Java 作业的代码。要求是先把 2026 年世界泳联跳水世界杯超级总决赛（北京站）的
选手名单和 8 个项目的决赛成绩抓下来，存成本地的 json，然后写一个命令行程序，不联网，
只读这些 json 来回答两种问题：`players` 和 `result <项目名>`。

写之前我在网上找了一些类似的作业看思路，代码是我自己一行一行敲的。可能还有写得不好的地方，
如果哪里明显不对，欢迎指出来。

## 我用的环境

- 系统：Windows 11
- JDK：25（这题我把编译目标设置成了 17，所以理论上 17 以上的 JDK 都能跑，我没一个个试过）
- Maven：3.9.16
- IDE：IntelliJ IDEA，不过是直接在终端里用 maven 命令打包和运行的

## 怎么编译、运行、测试

下面这些命令都要在项目根目录（有 pom.xml 的那一层）里执行：

```bat
:: 编译 + 跑测试 + 打包，最后得到 target\DWASearch.jar
mvn clean package

:: 运行，第一个参数是放命令的文件，第二个参数是结果写到哪个文件
java -jar target\DWASearch.jar testdata\input.txt output.txt

:: 只跑测试
mvn test
```

有一个我一开始也搞不懂的地方：**运行的时候屏幕上什么都不显示是正常的**。
程序不会往屏幕打印结果，所有内容都写进第二个参数那个文件里。所以跑完要去看 output.txt，
如果想知道有多少行，可以用 `find /c /v "" output.txt`。

打包是用 maven-shade-plugin 把 Jackson 也打进 jar 里，Main-Class 写在 pom.xml 的
shade 插件里（`com.west2.dwasearch.DWASearch`），所以 `java -jar` 不用另外配 classpath。
`target/` 是编译出来的东西，我写进 .gitignore 了，不提交。

## 数据是我怎么抓的

先去官网看它的接口返回什么，确认能直接拿 json，才决定自己写脚本抓，没有手抄数据。

- 比赛：World Aquatics Diving World Cup 2026 - Super Final，在北京办的
  （2026-05-01 到 05-03），接口里的编号是 5019
- 选手：`https://api.worldaquatics.com/fina/competitions/5019/athletes`
- 每个项目的成绩：`https://api.worldaquatics.com/fina/events/{项目编号}`
- 抓的时间：2026-11-06 20:56:54，这个时间我也存到 `fetch-info.json` 里了

抓取脚本是 `src/main/java/com/west2/dwasearch/crawler/Crawler.java`，运行方法：

```bat
mvn -q compile exec:java
```

脚本做了什么：

1. 先下 athletes.json，再一个一个下 8 个项目；
2. 每次请求之间等 800 毫秒，怕把人家服务器打崩，也怕被封；
3. 一次失败会重试，最多试 3 次，还不行就打印原因，用非 0 的退出码结束；
4. 接口返回的内容原样存文件，我一个字都没改，方便以后对数据。

脚本是可以重复跑的，跑一次会把 json 全部覆盖成最新的。

## json 里大概长这样

我截了一段出来，只写程序真正用到的字段：

```json
[
  {
    "CountryName": "Australia",
    "Participations": [
      {
        "PreferredLastName": "COLE",
        "PreferredFirstName": "Ellie",
        "Gender": 1,
        "NAT": "Australia"
      }
    ]
  }
]
```

上面是 athletes.json，最外面是数组，一个元素就是一个国家/地区，这个国家的人都在
Participations 里。`Gender` 是 1 表示女，0 表示男。

项目 json 是这样的：

```json
{
  "DisciplineName": "Women's 3m Springboard",
  "Heats": [
    { "Name": "Semifinals", "Results": [ ... ] },
    {
      "Name": "Finals",
      "Results": [
        {
          "FullName": "CHEN Jia",
          "Rank": 1,
          "TotalPoints": "374.40",
          "Dives": [ { "DiveOrder": 1, "DivePoints": "73.50" } ]
        }
      ]
    }
  ]
}
```

一个项目里其实有三组成绩（Head-To-Head、Semifinals、Finals），程序只用 `Name` 是
`Finals` 的那一组，别的组我没管。

顺便说一下，这届比赛决赛真的只有 4 个人（单人项目）或者 8 对（双人项目），因为前面还有
半决赛和 Head-To-Head，不是我把数据抓漏了。

## 做完了什么功能

### players

输出全部 89 个选手的姓名、性别、国籍，一个人两行（内容一行，`-----` 一行）。

全名的格式是「姓 名」，比如 `COLE Ellie`，和题目给的样例一样。

排序是程序里自己排的，不靠 json 里原来的顺序：

1. 先按国家/地区名字从小到大；
2. 国家一样的，再按姓氏从小到大；
3. 姓氏也一样的（确实有同名同姓的），再按名字排一下，不然每次运行顺序可能不一样。

第三条是我自己加的，题目只要求前两条。

### result 项目名

支持题目里定的 8 个项目，名字必须一字不差，而且是小写：

```
women 3m springboard      women 10m platform
women 3m synchronised     women 10m synchronised
men 3m springboard        men 10m platform
men 3m synchronised       men 10m synchronised
```

输出按名次从小到大，一条是：姓名、名次、每一跳的成绩（照数据里的顺序，中间用 ` + ` 连起来）、
` = `、总分，后面跟一行 `-----`。

成绩统一保留两位小数。json 里本来就是字符串（比如 `"73.50"`），我用 `BigDecimal` 处理，
没有用 double。因为刚开始我用 double 试过，`0.1 + 0.2` 会变成 0.30000000000000004，
所以后面全改成 BigDecimal 了。

### 空行

空行（就是长度为 0 的那一行）我选择**直接跳过**，不输出任何东西，也不影响后面的命令。

但是「只有空格的行」不算空行，我把它当成看不懂的命令，输出 `Error`。
题目说两种处理都行，我选忽略，并且用测试把这个行为固定住了，以后改代码如果变了我能马上知道。

### 命令出错的时候

| 输入 | 输出 | 为什么 |
| --- | --- | --- |
| `player`、`Players`、`players extra` | `Error` | 不是认得的命令，大小写也算 |
| `resultwomen 3m springboard` | `Error` | result 后面没有空格，没当成 result 命令 |
| `result`、`result `、`result sss` | `N/A` | 看得出是 result，但项目名不对 / 没写 |
| `result women 10m springboard` | `N/A` | 项目名不在这 8 个里 |
| `result women 3m springboard details` | `N/A` | 后面多了东西，项目名对不上 |
| `result men 10m     synchronised` | `N/A` | 中间多了空格 |

每条错误后面照样有一行 `-----`，然后程序继续读下一行，不会因为一条错就停下来。

## 代码都放在哪

```
src/main/java/com/west2/dwasearch/
├── DWASearch.java              入口，检查参数、读命令文件、调用别人、写文件、返回退出码
├── data/
│   ├── Player.java             一个选手：姓、名、性别、国家
│   ├── DiveScore.java          一跳的成绩
│   ├── EventResult.java        一条决赛成绩：姓名、名次、各跳、总分
│   ├── EventCatalog.java       8 个项目的名字，以及名字对应哪个 json 文件
│   └── DataStore.java          把选手和 8 个项目的成绩装在一起
├── io/
│   ├── DataReader.java         读 json，转成 DataStore，数据有问题就抛异常
│   ├── DataException.java      我自己写的异常类，专门表示数据文件有问题
│   └── OutputWriter.java       把结果写进文件（UTF-8，换行用 \n）
├── query/
│   ├── CommandProcessor.java   看这一行是什么命令、查数据、拼成要输出的行
│   └── ScoreFormatter.java     把成绩变成两位小数的字符串
└── crawler/
    └── Crawler.java            抓数据的脚本，程序运行时根本不会碰它
```

抓取和查询我是分开写的：Crawler 只负责把 json 下下来，主程序只读本地文件，不联网。

## 文件或数据出问题的时候

命令写错（`Error` / `N/A`）是正常输出，写到输出文件里，程序正常退出（退出码 0）。

但如果是文件层面的问题，就不是输出 `Error` 了，而是往错误输出打印原因，并且退出码是 1：

- 参数不是两个 → 打印怎么用；
- 命令文件不存在、不是普通文件、读不了、不是 UTF-8 → 说明是哪个文件；
- 数据 json 缺了、解析不了、少字段 → 说清楚是哪个文件、少了什么，不会假装查出了空结果；
- 输出文件写不进去（比如路径是个文件夹）→ 报错，再补一句「输出文件可能不完整」，不会骗人说成功。

文件流都用 try-with-resources 关掉了，异常也没有吞掉。

## 测试

`mvn test` 一共 40 个用例，用的是 JUnit 5，现在全部通过：

| 测试类 | 数量 | 大概测了什么 |
| --- | --- | --- |
| `CommandProcessorTest` | 16 | players 的格式和排序、8 个项目都能查、按名次排、成绩和文档样例对得上（CHEN Jia、CHEN Yiwen）、未知命令、项目名不对、大小写、多空格、重复命令、空行、空文件、出错之后还能继续 |
| `DataReaderTest` | 10 | 正常读数据、数据文件不存在、json 坏了、少字段、找不到 Finals、决赛是空的、空文件，都要求抛出异常并说明是哪个文件 |
| `ScoreFormatterTest` | 4 | 补两位小数、四舍五入、用 BigDecimal 算加法 |
| `DWASearchTest` | 10 | 参数个数、命令文件不存在、正常输出、只输出 \n、CRLF 的输入、空文件、输出是目录、输出目录不存在、非 UTF-8 输入，并且检查退出码 |

测试用临时文件夹（`@TempDir`），可以重复跑，不会改动项目里的文件。核心的
`CommandProcessor`、`ScoreFormatter`、`DataReader` 都可以直接传数据进去测，不用真的读写文件。

除了单元测试，我自己也用打包好的 jar 手动跑过：

```bat
java -jar target\DWASearch.jar testdata\input.txt output.txt
```

output.txt 一共 228 行，其中 CHEN Jia 那条是：

```
Full Name:CHEN Jia / Rank:1 / Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40
-----
```

和题目里给的样例一模一样，说明抓的数据和格式化都没问题。

## 我知道的问题

- 只做了题目要求的 8 个项目，混合团体那种没做；
- 只认 `Finals` 这一组，如果哪天接口里没有这个名字的组，程序会报数据错误，而不是输出空结果；
- `players` 输出的是 athletes.json 里的全部 89 人，其中包括 5 个只在混合团体比赛里出现的选手
  （比如 LIN Shan）。我觉得题目说「全部选手」就该都输出，但这一点我不太确定；
- 抓取脚本里的比赛编号、8 个项目编号都是写死的常量，换一届比赛要自己改；
- Bonus 一个都没做（CSV、表格展示、性能那些）。

## 我不太确定的地方

- 空行我选了「忽略」，测试里写死了。如果要求当成非法命令，把 `CommandProcessor` 里
  那个 `if (command.isEmpty())` 删掉就行，但要一起改测试；
- 排序用的是 `String.compareTo`，就是按字符编码比，没有做本地化的排序规则；
- 包名 `com.west2.dwasearch` 是我照作业名字起的，不一定符合规范；
- 这是我第一次写这么多类的 Java 程序，类是不是拆得太碎了，我也不太确定。
