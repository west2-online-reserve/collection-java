#        第三轮sql存储，JDBC搭建操作sql

## 1.用到的技术栈、

- JDBC
- Mysql

## 2.项目目录结构介绍、

-  [jdbcutils.java](C:\Users\31445\IdeaProjects\work3-Mysql\src\orderManagemaentSystem\jdbcutils.java) 连接sql，释放资源，对executeQuery和executeUpdate进行改造方便JDBC中用来输入执行。实现连接数据库，执行增删查改操作，事务增添。
-  定义异常 [MyException.java](C:\Users\31445\IdeaProjects\work3-Mysql\src\orderManagemaentSystem\MyException.java) ：在jdbcutils中实现继承：
    * 商品已存在再次添加异常
    * 订单未找到异常
    * 订单价格不合理异常
    * 商品未找到异常

-  [orderManagement.java](C:\Users\31445\IdeaProjects\work3-Mysql\src\orderManagemaentSystem\orderManagement.java) 接口
-  [ManagementSystem.java](C:\Users\31445\IdeaProjects\work3-Mysql\src\orderManagemaentSystem\ManagementSystem.java) 中实现对product，order表增删改查，排序
-  [Test.java](C:\Users\31445\IdeaProjects\work3-Mysql\src\orderManagemaentSystem\Test.java) 实例化ManagementSystem，输入测试

## 3.项目功能介绍、

对Mysql中的表order,product进行增删改查

## 4.项目亮点、

-  [db.properties](C:\Users\31445\IdeaProjects\work3-Mysql\src\orderManagemaentSystem\db.properties) 文件

## 5.项目待改进点

- 虚拟查询
- 项目结构有点混乱，应该使用Maven会清楚一点
