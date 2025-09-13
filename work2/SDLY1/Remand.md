## 1.爬取数据

找到要爬取的页面,点击F12,再点击网络，然后点击你要爬取的项目，观察右边，发现（红色下划线）这样的标识，然后直接下载就行了。

![image-20241120183605879](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20241120183605879.png)

### 2.分析json数据，找到需要的数据。

- 在比赛的json数据中需要获取的数据有：人物名称，排名，总得分，各个项目的子得分。
- 在运动员的json数据中需要获取的数据有：选手城市名称，选手名字，选手性别。

### 3.根据需要的数据构建需要的类

- player类：

- 主要记录运动员城市名称，性别，姓名数据。

  - ![image-20241123233443878](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20241123233443878.png)

  - 实现基本的get,set,constructor,toString方法。

- Contest类：

- 主要记录运动员单场比赛的总分，排名等结果

- ![image-20241123233906698](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20241123233906698.png)

- 实现基本的get,set,constructor,toString方法。

- Detailed类：

- 主要记录运动员单项目的全部比赛结果。

- 分析contest的内容按照一定格式输出。

- ![image-20241123234259570](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20241123234259570.png)

- 实现基本的get,set,constructor,toString方法。

### 4.接口封装

- #### 要求：

为了提高代码的可维护性和可扩展性，需要将基本功能独立成一个模块，称之为"Core模块"。这个模块包括两个基本功能：

1. **输出所有选手信息**
2. **输出每个比赛项目的结果**

这样的设计使得命令行能够共享相同的代码，通过定义清晰的API，实现与其他模块的交流。

API设计应考虑以下几点：

- **清晰的函数命名：** 使函数名称表达其功能，方便其他开发者理解。

- **参数设计：** 确定需要传递的参数类型和数量，使接口简洁而有用。

- **错误处理：** 考虑可能的错误情况，设计良好的错误处理机制。

- #### 结果：

- ![image-20241123235759484](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20241123235759484.png)

- 方法一   displayAllPlayersInfo() ：通过解析athletes.json获取所有的运动员信息到player类里，再通过调用player类里toString方法输出。

- 方法二 displayResults(String contestName, int type): 通过contestName获取的比赛名称和比赛类型，再去解析对应比赛的json文件获取该比赛的结果数据，最后封装到Contest类中的集合中。

- 方法三 displayResults(String contestName): 调用方法二中封装好的数据，再利用Contest类中toString方法输出。

- 方法四 displayDetailedResults(String contestName): 调用方法二中封装好的数据，同时重载sort方法，使其按照第一次比赛的排名排序，最后利用Detailed类中的toString 方法输出。

### 5.性能改进

- （1）创建IOstream类封装输入输出，主要利用BufferedReader 和 BufferedWriter 作为输入\输出流，提高文件读入的效率。
- （2）利用Sort算法和其重载方法，对于大部分数据除非极端条件下比普通冒泡排序和插入排序要块，时间复杂度更低。
- （3）利用哈希表来存储数据（虽然存储数据量不能过大，不然可能发生哈希碰撞），可以再LOG条件的预处理下实现O1的查询。
- （4）使用数据库相关知识实现更大规模的数据的快速读取（？？？）//目前对数据库了解比较少，不确定其原理。

### 6.异常处理

对解析json数据中使用try...catch来抛出异常

### 7.单元测试

对输入格式进行简单的测试。

![image-20241124004507831](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20241124004507831.png)



### 8.补充说明（发现的问题）

- （1）在运行cmd命令前检查java环境有没有配置，如果平常用IDE没问题也不代表你java环境配置了。可以先使用win+R后打开cmd，输入java,如果出现下图则表示有java环境。
- ![image-20241124143108590](C:\Users\86180\AppData\Roaming\Typora\typora-user-images\image-20241124143108590.png)
- （2）如果在maven中要导入别的库，记得先去镜像源那下载对应的jar包导入，再添加依赖后才额能使用。





















