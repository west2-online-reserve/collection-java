#####1.模块接口的设计与实现过程
######主要思路
![](D:\JAVA\idea\work2\主要代码.png)
######Athlete
![](D:\JAVA\idea\work2\Athlete.png)
######contest
![](D:\JAVA\idea\work2\contest.png)

####2.计算模块接口部分的性能改进
#####core模块
######方法共有4个；
>displayAllPlayersInfo()；</br>
> displayResults(String contestName)；</br>
> displayResults(String contestName,int contestType)；</br>
> displayDetailedResults(String contestName)；</br>
>使用io流提升输入输出速度</br>
>通过 Matcher 类可以从目标字符串中依次取出特定子串</br>
> 使用hashmap提高运行效率

####3.计算模块部分单元测试展示
>单元测试
![](D:\JAVA\idea\work2\单元测试1.png)
![](D:\JAVA\idea\work2\单元测试2.png)> 



####4.计算模块部分异常处理说明
#####*1.使用try catch处理错误
#####*2.抛出异常，返回给调用者处理*