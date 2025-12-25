# 使用方式
可在命令行中输入Java -jar DWASearch.jar input.txt output.txt运行该项目,修改input.txt中的内容可以有不同的结果，具体规则按第二轮考核要求
可以进行测试的比赛有:
## json数据来源
1:所有json数据均来源于https://www.worldaquatics.com/competitions/4725/world-aquatics-championships-singapore-2025/results?disciplines=DV
比赛名称：World Aquatics Championships - Singapore 2025
通过F12在网络处筛选Fetch/XHR来获得对应项目的json文件
- 全部运动员信息:
  - players
- 女子比赛:
  - women 1m springboard
  - women 3m springboard
  - women 10m platform
  - woman 3m synchronised
  - women 10m synchronised
- 男子比赛 :
  - men 1m springboard
  - men 3m springboard
  - men 10m platform
  - men 3m synchronised
  - men 10m synchronised
- 双人比赛:
  - mixed 3m synchronised
  - mixed 10m synchronised
  - mixed 3m & 10m team

# 主要模块
## Athlete类 用于保存运动员信息

| 属性       | 说明                        | 类型 |
|----------|---------------------------|-----|
| fullName | 全名                        | String |
| gender | 性别                        | String |
| country | 国家                        | String |
|preliminaryRank | 初赛排名,默认为"*"               | String |
|semifinalRank | 半决赛排名，默认为"*"              | String |
|finalRank | 决赛排名，默认为"*"               | String |
|preliminaryTotalPoint | 初赛总分                      |float|
|seminifinalTotalPoint| 半决赛总分                     |float|
|finalTotalPoint | 决赛总分                      |float|
|preliminaryScore| 初赛分数,"Score1+Score2+..."  |String|
|seminifinalScore| 半决赛分数,"Score1+Score2+..." |String|
|finalScore | 决赛分数,"Score1+Score2+..."|String|




## CoreModule 类有四个函数
- ## void dispalyAllPlayersInfo(PrintWriter writer)
- 该函数的功能为输出所有运动员信息到输出字符流对象中,需要传入的参数为输出字符流对象。
- 算法的关键在于json文件的解析，我使用了jackson来完成该任务。
- 由于在json文件中，每个运动员的信息都是关联在一起的，因此我没有保存运动员的信息，而是直接将信息输出到输出字符流对象中。

- ## void displayResultForEachEvent(String inputComment, boolean isDetail, PrintWriter writer)
- 该函数的功能是根据传入的参数的不同，来进行对应比赛的结果输出。需要传入的参数为赛事名称，是否显示详细信息，输出字符流对象。
- 赛事的json格式与运动员格式不同，所以无法直接套用dispalyAllPlayersInfo(),但是不同赛事的json格式一致，因此所有赛事可以使用同样的方式解析json文件。
- 对于传入的赛事名称，我要求与该项目下的json文件中的赛事名称一致，若没有找到该赛事名称，则返回"N/A"错误信息。
- 对于json文件解析后得到的信息我保存到了Athlete对象中，并建立了一个HashMap用于保存运动员对象，其中key为运动员的全名，value为运动员对象。（JSON文件中也有每个运动员的ID，改用ID作为key可以避免同名问题）
- ## int parseRankToInt(String rankStr)
- 该函数的功能为将传入的字符串转换为int型数据，同时用于displayResultForEachEvent()函数中按第一场比赛排名排序，要考虑到存在部分运动员三场比赛全都缺席
- ## String formatAndSortTeamNames(String rawFullName)
- 该函数用于多人项目中，运动员名字的排序和输出格式的转换，输入为原始json中的运动们的全名，输出为格式化后的名字
- 由于json文件中本身是无序的且用来隔开的符号是'/'而不是'&'，所以需要该函数进行转换
- ## String[] judgmentFormat(String input)
- 用于判断String是否符合题目要求和查找比赛名称和是否需要输出细节，需要传入的参数为String,输出为Sting数组 = {比赛名称，是否需要显示详细信息}

## DWASearch类
- 程序主入口，只有一个main函数，判断输入的参数是否合规和读取输入文件并分析命令类型

# 单元测试
## ![单元测试结果](../../../DWASearch/png/Junit%20Result.png)
## ![单元测试内容](../../../DWASearch/png/Junit%20Test.png)

# 异常处理说明
- 没有自己写异常，只用了基本的Exception
