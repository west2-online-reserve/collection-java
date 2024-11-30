# **1.代码接口的设计和实现过程****

## 1.1前期准备

###  1.1.2Javabean模块

根据爬取数据，构造相应的Javabean类

#### 1.Player类，实现Getter,Setter等方法

![image-20241128223553406](C:\Users\Leo\AppData\Roaming\Typora\typora-user-images\image-20241128223553406.png)



#### 2.Detail类（合并了单场比赛结果和多场比赛结果）

实现了基本Getter,Setter等方法

![image-20241128223624604](C:\Users\Leo\AppData\Roaming\Typora\typora-user-images\image-20241128223624604.png)

​                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

 

## 1.2接口设计

在所有代码都实现后，为了提高代码的可维护性和可扩展性，抽取重复的方法稍加修改，统一为一个方法作为接口，然后以多个类来实现接口中的方法，用接口返回输出内容，大大提高了代码的简洁性，其包括了两个功能：

1.输出所有选手的信息

2.输出每个比赛的结果

实现类有

#### 1.PlayerService,该类实现了查询所有 player 的基本信息。

![image-20241129113246354](C:\Users\Leo\AppData\Roaming\Typora\typora-user-images\image-20241129113246354.png)



#### 2.displayResults,该类实现了查询比赛的全部信息

![image-20241129113719107](C:\Users\Leo\AppData\Roaming\Typora\typora-user-images\image-20241129113719107.png)



#### 3.displayFinalResults,该类实现了查询决赛的信息

###### （若想查询其他赛程，只需改变方法中数组内的数字）

![image-20241129113739607](C:\Users\Leo\AppData\Roaming\Typora\typora-user-images\image-20241129113739607.png)

#### 4.优点

利用HashMap和循环重复读取比赛数据，这样每次读取指令先对hashMap中的指令进行查找，
如果查找到了，则将hashMap的内容输出到数组中，减少了代码运行的计算时间

![image-20241129114425304](C:\Users\Leo\AppData\Roaming\Typora\typora-user-images\image-20241129114425304.png)



# **2.接口部分的性能改进**

1.使用批量化操作数据库的方法，在执行完命令后一次性插入数据库
避免了重复IO的过程







# 3.单元测试部分展示

对ReadFileUtils类进行测试

![image-20241128094628663](C:\Users\Leo\AppData\Roaming\Typora\typora-user-images\image-20241128094628663.png)









# 4.异常处理

1.使用抛出异常的方式返回给调用者处理