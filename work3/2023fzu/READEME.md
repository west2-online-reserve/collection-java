- 果然最难的还是需求分析,因为不知道有没有需求,就做了一大堆的方法
- 都用MyBatis了,还要JDBC干嘛?
- Druid写在mybatis-config.xml里了
- Maven项目依赖?显然!
- 没有判断价格是否合法地异常,因为我想用负数的钱表示收入之类的?不知道有没有这个需求,暂且保留
[项目仓库](https://github.com/2023fzu/work3)

# 10.18 

- 完成对两张表的设计
- 创建Maven项目,导入包等
- 对GoodMapper的大致规划

## 表:

```mysql
create database shop;
use shop;

```

```mysql
create table good(
    id int(5) comment '主键,商品号' primary key auto_increment,
    name varchar(15) comment '商品名,唯一' unique ,
    price float comment '商品价格,即单价' not null,
    stock int comment '库存' check (stock>=0)
)comment '商品表' ;
```


```mysql
create table bill(
    id int(10) comment '主键,账单号' primary key auto_increment,
    good_id int comment '商品id' not null,
    constraint fk_bill_good_id foreign key(good_id) references good(id) ,
    customer_id int comment '用户id' not null ,
    price   float comment '订单价格,实际交易金额,包括单价*数量,折扣优惠等一系列因素',
    bill_date time comment '订单成交时间,年月日时分秒'
) comment '账单表';
```

# 10.19 

- 完成对GoodMapper方法及其实现
    - 做了GoodMappers这个类似于"工具类"的东西
    - 但不是工具类,因为里面的方法不static
    - 主要用来
        - 对传入GoodMapper的参数做检查
        - 对数据库的异常进行捕获
- 有关异常
- 完成测试
- 完成格式化输出
- 完成日志记录
- 对BillMapper进行了大致规划
- 完成了BillMapper.xml
- **对明天排满了的课充满了恐惧**

现在已经早上一点半了,明天还要早八,昨天(10.19)的英语还没背完

qwq



10.20 及之后 完成BillMapper,完成外键关联,学习合理的注释,合理的文件夹分类

```Dos
TREE C:\Users\27970\Desktop\IT\JDK\work3 /F /A
```





```DOS
\WORK3
|   .gitignore
|   pom.xml
|   READEME.md
|
+---.idea
|   ....
|
+---log
|       output.log
|
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---harvey
|   |   |           |   App.java
|   |   |           |
|   |   |           +---exc
|   |   |           |       ForeignKeyException.java
|   |   |           |       IdNotFoundException.java
|   |   |           |       IdNotSetException.java
|   |   |           |       LowLargerThanHighException.java
|   |   |           |       NameExistException.java
|   |   |           |       StockMinusException.java
|   |   |           |       StringTooLongException.java
|   |   |           |
|   |   |           +---mapper
|   |   |           |       BillMapper.java
|   |   |           |       GoodMapper.java
|   |   |           |
|   |   |           +---pojo
|   |   |           |       Bill.java
|   |   |           |       BillColumn.java
|   |   |           |       Good.java
|   |   |           |       GoodColumn.java
|   |   |           |
|   |   |           \---utils
|   |   |                   BillMappers.java
|   |   |                   DruidDataSourceFactory.java
|   |   |                   GoodMappers.java
|   |   |
|   |   \---resources
|   |       |   logback.xml
|   |       |   mybatis-config.xml
|   |       |
|   |       \---com
|   |           \---harvey
|   |               \---mapper
|   |                       BillMapper.xml
|   |                       GoodMapper.xml
|   |
|   \---test
|       +---java
|       |   \---com
|       |       \---harvey
|       |               BillTest.java
|       |               GoodTest.java
|       |
|       \---resources
\---target
    ......

```


# 10.20
- 尽可能地增加了尽可能规范的注释
- 完成了Bill有关的基础的增删改查,外键关联
- 以整齐的二维表的形式输出Bill表
- 阿里巴巴ok
- 第三方API调用这题.....不是调用完之后和上面这题一模一样吗?emmmmmm
- 我决定先去学Maven高级和sql高级和运维,把API放一放先