DWASearch


主要功能

- 查询参赛运动员的姓名、性别和所属国家。
- 按照指定规则对运动员进行排序。
- 查询八个跳水项目的决赛成绩。
- 支持连续输入多条查询命令。
- 对非法命令、文件异常和 JSON 数据异常进行处理。

数据来源

本项目使用 World Aquatics 官方 API 获取赛事相关数据。

官方 API 地址：

- 运动员名单："https://api.worldaquatics.com/fina/competitions/{competitionId}/athletes"
- 赛事详细数据："https://api.worldaquatics.com/fina/events/{eventId}"

本项目获取的数据包括：

- 参赛运动员的姓名、性别和国家信息。
- 八个跳水项目的决赛成绩，包括运动员排名、各跳分数和总分。

赛事名称：World Aquatics Diving World Cup 2026 - Super Final

赛事地点：北京，中国。

数据获取时间：2026年11月3日，17:41:54


JSON 数据结构

本项目使用 JSON 格式保存运动员名单和赛事成绩数据。

1. players.json

顶层为国家列表，每个国家对象包含以下字段：
"CountryName"：国家名称
"CountryCode"：国家代码
"Participations"：该国家的参赛运动员列表

"Participations" 中包含运动员的相关信息，例如：

- "FirstName"：名
- "LastName"：姓
- "Gender"：性别

2. 赛事成绩 JSON 文件

赛事成绩数据按照不同项目分别保存，每个文件对应一个赛事项目。

程序通过赛事数据中的 "heats" 信息读取决赛成绩，并提取运动员、排名、每跳分数和总分。


项目目录结构

DWASearch/
├── src/
│   ├── main/
│   │   └── java/com/dwa/    # Java 程序源代码
│   └── test/
│       └── java/com/dwa/    # JUnit 测试代码
├── data/
│   ├── players.json         # 运动员名单
│   ├── event_list.json      # 赛事列表
│   └── results/             # 八个项目的决赛成绩
├── pom.xml                  # Maven 项目配置文件
├── README.md                # 项目说明文档
└── .gitignore               # Git 忽略配置


项目目录结构

DWASearch/
├── src/
│   ├── main/
│   │   └── java/com/dwa/    # Java 程序源代码
│   └── test/
│       └── java/com/dwa/    # JUnit 测试代码
├── data/
│   ├── players.json         # 运动员名单
│   ├── event_list.json      # 赛事列表
│   └── results/             # 八个项目的决赛成绩
├── pom.xml                  # Maven 项目配置文件
├── README.md                # 项目说明文档
└── .gitignore               # Git 忽略配置


构建与运行

环境要求

- JDK 25
- Apache Maven
- IntelliJ IDEA（推荐）

1. 编译项目

在项目根目录打开终端，执行：

mvn compile

2. 运行单元测试

执行：

mvn test

项目使用 JUnit 进行自动化测试。

3. 打包项目

执行：

mvn clean package

打包成功后，可在 "target" 目录中找到生成的 JAR 文件。

4. 运行程序

在项目根目录执行：

java -jar target/DWASearch.jar input.txt output.txt

其中：

- "input.txt"：存放用户输入的查询命令。
- "output.txt"：用于保存程序执行结果。

5. 支持的查询命令

查询运动员名单：

players

查询赛事决赛成绩：

result women 3m springboard
result women 10m platform
result women 3m synchronised
result women 10m synchronised
result men 3m springboard
result men 10m platform
result men 3m synchronised
result men 10m synchronised