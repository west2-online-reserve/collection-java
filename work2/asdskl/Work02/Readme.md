# 1.爬取数据
打开对应比赛网站，点击对应的比赛项目和运动员栏目，左键进入检查模式，通过监测网络活动获取对应的json文件。



# 2. 模块接口设计与实现过程
解析对应的json文件结构，设计对应的output函数和文件流io函数，分出核心的函数模块core和FileIo。

## 解析json文件数据

json数据主要分为两类，运动员数据和对应的项目数据，通过要收集的数据创建对应javabean类。

1.``Althete``类：记录一位运动员的信息，包括运动员的国家名，性别，姓，名。
2.``Contest``类：记录一场比赛的信息（单项目单种比赛类型)，包括运动员的总得分，排名，运动员的姓名，分开的得分点，特别使用``getscore``函数来统计得分点得出总得分。
3.``ContestDetailed``类：考虑到需要输出单项目多种比赛的类型，记录了单项目三种类型比赛，使用Contest类型数组来存储对应的三场比赛信息,并且使用了``setContests``函数，用来接受一场比赛信息，根据其类型放在对应的Contest数组位置上。



## FileIo函数

简单的文件流io函数类，主要用途是在input.txt和程序之间建立读取通路和在output.txt和程序之间建立输出通路，节省代码量，同时避免了文件io流的频繁开启关闭（自程序开始运行时开启到程序结尾时才会关闭）。
1.setInputPath和setOutputPath函数

2.inputRead和outputWrite函数
![Image.png](pic/Image.png)

3.close函数

## core函数

核心的core函数，用于解析json数据，反序列化为对象并且将对象数据输入进output文件中（），借用了FileIo类的输出函数。
1.outputPlayer函数，输出对应的运动员信息
![Image [2].png](pic/Image.png)

2.outputContest函数，可以根据输入的项目类型和比赛类型读取对应的多场比赛数据，解析json数据得到多场比赛数据，存储在contest的Array里面，最后函数返回该Array。
![Image [3].png](pic/Image.png)

![Image [4].png](pic/Image.png)

3.outputFinal函数，调用outputContest函数，选择Final类型（决赛）,提取该项目的每一场决赛信息输入output.txt文件中。
4.outputContestDetailed函数，运用了一个以String（运动员姓名）为索引的ContestDatiled（长度为3的Contest数组）的哈希表，调用outputContest分别提取三种类型比赛的所有数据，根据参赛的运动员名字，利用ContestDetailed的setContests函数为哈希表里对应运动员的对应类型的比赛赋值，然后将哈希表转为可以排序的Array，然后根据特定的逻辑将Array排序后输入到output.txt中。

![Image [5].png](pic/Image.png)
![Image [6].png](pic/Image.png)
![Image [7].png](pic/Image.png)


//其余部分暂无未写出