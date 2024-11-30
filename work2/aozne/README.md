# **模块接口的设计与实现过程**

## 1前期准备

将所需的json文件从网站上爬取下来，读取json文件中的内容对所需要的模块进行一个大概的规划

## 2设计与实现

### 2.1 CoreModule

#### 2.1.1 ![e09969560eac262a35d0c9768f5a91c9](C:\Users\wuwuwuwu\Documents\Tencent Files\1260516267\nt_qq\nt_data\Pic\2024-11\Ori\e09969560eac262a35d0c9768f5a91c9.png)

该函数的作用为将json文件的内容转为String类型并输出给处理文件内容的函数，如果对应的fileName的内容不存在的话将抛出异常。

### 2.1.2![ce051884276e7179fcb5c4e54802e233](C:\Users\wuwuwuwu\Documents\Tencent Files\1260516267\nt_qq\nt_data\Pic\2024-11\Ori\ce051884276e7179fcb5c4e54802e233.png)



该函数的功能为保证fileName对应的文件不为空或者不报错并输出所需，并且通过hashmap记录如果需要再次输出players则无需经过循坏可直接输出。

### 2.1.3![image-20241201025239100](C:\Users\wuwuwuwu\AppData\Roaming\Typora\typora-user-images\image-20241201025239100.png)

获取输入，并输出。

### 2.1.4![image-20241201025336719](C:\Users\wuwuwuwu\AppData\Roaming\Typora\typora-user-images\image-20241201025336719.png)

这两个函数用来处理result需要输出的每个动作的得分。

### 2.1.5 

![image-20241201025626285](C:\Users\wuwuwuwu\AppData\Roaming\Typora\typora-user-images\image-20241201025626285.png)

getResultInfo()函数用来处理result中会遇到的不同情况，flag==1时为只有result 或者是synchronised这项运动只需输出final的结果，flag==2时 对应为men 3m springboard  women 3m springboard men 10m platform women 10m platform 这四个比赛 flag==3则处理剩下四场有预赛 半决赛 决赛的比赛。

### 2.1.6![image-20241201025758753](C:\Users\wuwuwuwu\AppData\Roaming\Typora\typora-user-images\image-20241201025758753.png)

接受输入 并输出。

## 2.2DWASearch

### 2.2.1

![image-20241201025835350](C:\Users\wuwuwuwu\AppData\Roaming\Typora\typora-user-images\image-20241201025835350.png)

采取map用来比较输入是否与合法比赛标题相同（本来想用其映射内容作为fileName但是后面发现不需要）。

### 2.2.2

### ![image-20241201030000455](C:\Users\wuwuwuwu\AppData\Roaming\Typora\typora-user-images\image-20241201030000455.png)

主函数处理输入是否为合法内容。

### 2.2.3![image-20241201030032569](C:\Users\wuwuwuwu\AppData\Roaming\Typora\typora-user-images\image-20241201030032569.png)

clearFile用来清除上一次输出到output的内容，displayNA 和 displayError则用来输出N/A 和Error

## 3性能改进

使用hashmap减少了重复内容的处理，使用BufferedReader和BufferedWriter 减少磁盘I/O操作，提高效率。

## 4单元测试

![image-20241201030327481](C:\Users\wuwuwuwu\AppData\Roaming\Typora\typora-user-images\image-20241201030327481.png)

## 5异常处理

通过抛出异常并返回给调用者处理