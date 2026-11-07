# DWASearch

查询 2026 年世界泳联跳水世界杯北京总决赛的选手与八项决赛成绩。

## 构建和运行

使用 JDK 17 或以上。实测环境为 Windows、JDK 25.0.4.1 和 IntelliJ IDEA 2026.1.5 自带的 Maven。项目以 Java 17 为编译目标。

在项目根目录执行：

```text
mvn test
mvn package
java -jar target/DWASearch.jar input.txt output.txt
```

Maven Shade 插件打包依赖，并在清单中将 Main-Class 设为 org.west2.learning.DWASearch。在 IDEA 中也可以选择 DWASearch 配置直接运行。

## 数据来源

FetchData 使用 HTTP GET 从 Omega Timing 获取[决赛 XML](https://www.omegatiming.com/File/00011A0102FFFFFFFFFFFFFFFFFFFFC0.xml)和[报名名单 PDF](https://www.omegatiming.com/File/00011A0102FFFFFFFFFFFFFFFFFFFF02.pdf)。随附快照的抓取时间是 2026-09-21 07:12:40 UTC。

打包后，在需要更新快照的目录运行：

```text
java -cp <JAR路径> org.west2.learning.FetchData
```

FetchData 生成 data.json。source 和 capturedAt 记录数据来源及抓取时间，players 保存全名、姓氏、性别和国籍，events 按项目名称保存姓名、排名、各跳和总分。分数以十进制字符串保存。查询与测试使用本地数据。重新抓取依赖上游文件可访问且格式兼容。

## 命令和错误处理

DWASearch 读取 UTF-8 命令文件并写出结果。DataLoader 读取和验证快照。QueryEngine 区分大小写，按国籍再按姓氏排列选手，按排名排列成绩，分数保留两位小数。空行忽略，只有空格的行按非法命令处理。重复命令重复输出。未知命令返回 Error，已识别但项目无效的 result 返回 N/A，并继续处理后续行。

参数数目错误返回退出码 2。读取、解析或写入失败向标准错误报告，并返回退出码 1。写入失败时输出文件可能不完整。

题面把 men 10m synchronised 同时列为固定有效项目和 N/A 示例。这里遵循八项固定清单，将其作为有效查询。

## 验证与 AI 使用

6 项 JUnit 测试覆盖排序、分数格式、多行和重复命令、非法输入、空行、坏数据、参数错误及文件故障，实际执行全部通过。也已在 IDEA 中运行入口并核对输出文件。

AI 辅助整理和核验本机已有实现、配置 IDEA 运行、补写文档与执行验证。

2026-11-07 交付复验：重新打包成功，6 项测试通过，可执行 JAR 离线运行成功。抓取客户端改用 HTTP/1.1 以规避此前 HTTP/2 连接重置。网络抓取复验尚未成功，交付保留原有快照。
