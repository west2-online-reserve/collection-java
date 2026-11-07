# west2 backend round-1
## 描述
使用题目提供的 2026 World Aquatics Diving World Cup Super Final（北京）固定数据包，实现一个能够查询赛事数据的控制台程序。项目包括基本功能、单元测试和 README 撰写。

开发环境：
- Oracle OpenJDK 21.0.10
- jetbrain IDEA 
- windows 11

## 使用方法
- 数据包文件默认读取同目录下的 data.json 文件。你可以运行命令config更改读取路径，同目录下会生成config.json文件

### RUN
```
java -jar DWASearch.jar input.txt output.txt
```
`input.txt`每行一条指令 `output.txt`输出结果会存放在该文件中

输入指令如果是空行会直接跳过，文件中的指令不允许再带有文件指令input.txt

```
java -jar DWASearch.jar <command> [file]
```
也是可以的，如果指令中的file没有填写，那么结果输出在终端中

### support commands
#### config

```
config --setpath <dir>                        设置数据读取路径为 dir
config --setpath-status default               使用默认设置
config --setpath-status design                使用个人设置
```

#### players
```
players [-f filename]    输出所有选手信息[输出到文件]
```

#### result

```
result <event name> [-f filename]   输出对应比赛的结果[到文件]
```

## 构建、运行和测试命令
本项目构建运行与测试在IDEA中使用maven进行，直接使用工程软件中的指令调试

构建打包：`mvn clean package`

测试：`mvn clean test`

打包成jar的方式：爬虫模块单独打包，user模块和core模块合并打包成目标产物

## 数据来源
来源后端java考核初版仓库提供的json数据`data.json`

后仓库更新取消了提供数据，遂改用爬虫抓取数据后整理为旧版数据格式

抓取来源：`https://www.worldaquatics.com/competitions/5019/world-aquatics-diving-world-cup-2026-super-final/results`
通过抓取json获得数据

抓取时间：2026.11.7

脚本用法：直接运行

定义的json结构：同`data.json`，在仓库`test_data/`

## 已完成的功能
- 输入 players，输出全部选手的全名、性别和国籍
- 输入 result <项目名称>，按排名升序输出该项目的决赛结果：选手姓名、排名、各跳成绩和总分。各跳成绩按数据中的顺序用 + 连接，等号后输出总分，成绩与总分统一保留两位小数
- 指令文件可以包含多行，也可以重复查询
- 无法识别的命令输出 Error。能识别为 result（后面是空格或行尾），但项目缺失、不在上述列表内，或名称后多了字符时，输出 N/A
- 支持 `java -jar DWASearch.jar input.txt output.txt` 文件或直接输入指令 `java -jar DWASearch.jar players`

## 项目架构

本项目分为：
- `user` 命令行解析代码
- `core` 数据处理代码
- `scraper` 爬虫

依赖：
- `user` -> `core` 
- `scraper` 不依赖其他模块

### core

负责核心数据的处理和输出
- `core.data`
  - json的数据结构设计
- `core.exceptions`
  - 自定义的异常类
- `core.lib`
  - `Collector` 采集器，用于根据参数寻找数据
  - `DataOutput` 定义单项数据输出格式
  - `OutputFormat` 定义数据组输出格式
  - `ReadData` 从文件读取数据进入内存

### user

命令行指令处理模块

- `user.app`
  - `Application` 程序初始化，执行读取数据文件等
  - `CommandManager` 解析指令，异常捕获
  - Main 主接口，异常捕获
- `user.commands`
  - `Command` 所有指令的父类
  - `Config` 指令config的定义，调整设置（依赖同目录的config.json文件，不存在则默认设置）
  - `InputFile` 如果指令参数为文件，该类负责读取文件并将所有指令存入执行队列
  - `Players` 指令players的定义
  - `Result` 指令result的定义
- `user.exceptions`
  - 自定义异常类
- `user.ilb`
  - `CommandList` 所有指令的注册
  - `CommandPara` 指令参数的包装类
  - `FileCache` 文件缓存，在指令参数为文件时，且文件里面的指令也包含文件（输出目标）时，使用该类构造文件池

### scraper

爬虫模块

- `scraper.analysis`
  - 数据清洗整理
- `scraper.app`
  - 爬虫主程序接口
- `scraper.get`
  - 抓取原始数据模块

## 执行效果
例如，`result women 3m springboard` 的输出如下（节选）：
```
Full Name:CHEN Jia
Rank:1
Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40
-----
Full Name:CHEN Yiwen
Rank:2
Score:69.00 + 72.00 + 76.50 + 79.05 + 76.50 = 373.05
-----
```

执行效果和教程仓库示例一致


