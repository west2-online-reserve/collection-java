#        第三轮sql存储，JDBC搭建操作sql

## 学习记录

### 1.第一次连接数据库用到反射：

```java
Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动
```



### 2.编写jdbcutils:

- 包含了连接sql，释放资源，
- 对executeQuery和executeUpdate进行改造方便JDBC中用来输入，执行。
- 事务启动，事务提交，回滚方法。
- 自定义异常：检查商品是否存在，价格是否合法，订单是否存在。



### 3.ManagementSystm：

1.ManagementOrderSystem方法用来引用对数据库操作的所有方法（增删改查，排序)

2.商品编号，订单编号自增无需设置

### 4.不足

一个订单只对应一个商品，商品订单准确查询，还有就是测试类里可以用输入测试吗，固定的测试，比如说删除订单，只有第一次成功。











