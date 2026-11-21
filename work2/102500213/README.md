# README
## 环境、构建与运行
- JDK：21
- 构建工具：Maven 3.9.16
- 打包方式：
  - 命令行：`mvn clean package`
  - IDEA：Maven面板 → Lifecycle → 先 `clean`，再 `package`
  打包后在 target 目录生成可执行 jar：`DWAsearch.jar`
- 运行命令：
java -jar DWAsearch.jar inputFile.txt outputFile.txt
  - `inputFile.txt`：查询指令输入文件
  - `outputFile.txt`：结果输出文件，程序自动生成或覆盖

## 数据源与爬虫脚本 spider.py
- 来源页面：[https://www.worldaquatics.com/competitions/5019/world-aquatics-diving-world-cup-2026-super-final](https://www.worldaquatics.com/competitions/5019/world-aquatics-diving-world-cup-2026-super-final)
- 原始抓取时间：2026-11-01 16:58:28

- Python 版本：3.11
- 安装依赖：
pip install requests
- 运行爬虫：
python spider.py
- 说明：在脚本同目录生成 `data.json`，会覆盖原有文件,选择将比赛选手信息及八场决赛信息放入一个json文件
- JSON 结构
{
  "players": [
    {
      "fullName": "选手全名",
      "gender": 性别编号,
      "country": "国家名称"
    }
  ],
  "finals": [
    {
      "disciplineName": "项目名称",
      "results": [
        {
          "fullName": "选手姓名（双人项目用 / 分隔两名选手）",
          "totalPoints": "总分（字符串）",
          "rank": 名次,
          "scores": [
            "每一跳得分，字符串数组"
          ]
        }
      ]
    }
  ]
}

- 字段说明

1. **顶层**

- `players`：数组，存放所有参赛选手基础信息
- `finals`：数组，存放各个决赛项目成绩

2. 选手对象（players 内元素）

- `fullName`：String，选手姓名
- `gender`：int，`0` = 男，`1` = 女
- `country`：String，国家全称

3. 决赛项目对象（finals 内元素）

- `disciplineName`：String，跳水项目名称
- `results`：数组，该项目下所有运动员成绩

4. 成绩对象（results 内元素）

- `fullName`：String，单人直接名字；双人项目 `名字 / 名字`
- `totalPoints`：String，总成绩
- `rank`：int，排名
- `scores`：List<String>，每一跳的分数

## 已实现功能
1. 校验命令行传入参数数量
2. 读取 `data.json`，反序列化为Java实体对象
3. 逐行读取输入指令，自动忽略空行，解析查询命令
4. 根据指令执行选手、决赛成绩查询
5. 将格式化结果写入输出文件
6. 异常捕获：文件缺失、读写权限不足、JSON解析失败、非法指令等

## 执行流程
1. 运行 `spider.py` 拉取数据，生成 `data.json`（已提交一份data.json快照）
2. Java 程序加载 `data.json`，将选手与成绩数据载入内存
3. 读取输入文件中的查询指令，解析并执行查询
4. 把查询结果写入输出文件

## 单元测试覆盖
- 命令行参数数量校验
- 文件读写权限：输入文件、data.json 读权限，输出文件写权限
- 容错处理：错误指令、残缺指令、空行指令