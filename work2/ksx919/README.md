项目一共包含四个java类

DWASearch为主类，运行后会读取input.txt中的信息，并进行处理后调用CoreModule中的函数将结果输出到output.txt中

CoreModule为核心类，里面定义了显示选手信息、显示比赛结果、显示比赛详细结果、导入选手数据以及导入比赛结果信息数据的函数

Athlete类为定义的运动员类，可返回选手的名字 性别 国家等信息

EventResult类为定义的比赛结果类，通过调用此类来得到选手的比赛信息

lib中还加入了一个fastjson2的库，用于读取json中的信息

data文件夹为从网站中爬虫得到的数据



如何运行：在含有DWASearch.jar的文件夹中打开终端输入Java -jar DWASearch.jar input.txt output.txt（在该文件夹中须包含一个input.txt文件并输入所需获取的数据） 



该程序实现了1、输出所有选手信息 2、输出决赛每个运动项目结果 3、输出详细结果 4、指令区分大小写



不知什么原因 将DWASearch.jar放在src外的文件运行时就会出现java.io.FileNotFoundException: data\athletes.json (系统找不到指定的路径。)，所以我将该文件放在src文件夹中进行提交。



//第二次提交

添加了单元测试代码，于src/test中。test使用的是junit5.8.1，idea自动生成的外部库依赖，未加入到lib中。

将input.txt和output.txt加入了src中，为result women 10m platform detail示例。


