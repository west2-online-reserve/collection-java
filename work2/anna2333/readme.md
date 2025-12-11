# DWASearch 项目文档 

## 项目简介

DWASearch 是一个命令行应用程序，主要功能是从网站抓取数据，并根据 `input.txt` 中的指令进行数据处理和输出。

## 核心组件

### DWASearch
应用程序的主入口，负责：
- 处理命令行参数
- 顺序读取 `input.txt` 文件
- 调度相应处理方法

### 数据模型

#### Athlete
运动员信息的数据传输对象：
- 实现 JSON 到字符串的转换
- 提供自定义的 `toString()` 方法以支持单元测试

#### Competition
比赛信息处理类：
- 处理 JSON 格式的比赛数据
- 提供格式化输出功能

#### CompetitionDetails
比赛详情处理类：
- 转换详细的比赛数据
- 实现自定义字符串表示

### 辅助类

#### Constns
常量定义类：
- 为 `choose` 方法提供命令验证常量

### Tools
核心工具类，提供主要功能：

##### 主要方法

1. **fetchAndWriteAthletes**
    - 从官方网站实时获取数据
    - 将数据存储在 Athlete 对象中
    - 使用 ArrayList 进行数据管理和排序
    - 将处理结果输出到 `output.txt`

2. **writeCompetition**
    - 处理本地 JSON 文件
    - 采用与 fetchAndWriteAthletes 类似的处理流程
    - 将结果写入 `output.txt`

3. **writeCompetitionDetails**
    - 高级比赛数据处理
    - 使用 HashMap 优化数据存储效率
    - 实现复杂的字符串处理逻辑

4. **choose**
    - 解释 input.txt 中的命令
    - 处理需求文档中指定的错误情况
    - 路由到相应的处理方法

5. **工具方法**
    - 用于代码优化的辅助函数

## 测试

项目包含完整的测试覆盖：
- 专门的测试类实现
- 针对 4 个核心方法的 10+ 测试用例
