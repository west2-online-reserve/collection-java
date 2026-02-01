**作业地址：**https://github.com/Cai-Qiyoung/my-compulishments-java.git

------

# --数据库学习笔记--

# 一、初识MySQL

## **1.1 数据库的分类**

**1. 关系型数据库（SQL）：**

- 特点：通过表和表之间，行和列之间的关系进行数据的存储
- 例如：MySQL，Oracle，Sql Server，DB2，SQLlite

**2. 非关系型数据库（NoSQL（Not Only SQL））：**

- 特点：对象存储，通过对象的自身属性来决定
- 例如：Redis，MongDB

## 1.2 DBMS（数据库管理系统）

数据库管理系统，用于管理和操作数据

## 1.3 可视化工具Sequel Ace的使用

1. 表头的释义：

   | 列名           | 含义（作用）                                                 |
   | -------------- | ------------------------------------------------------------ |
   | **Field**      | 字段名（比如`id`、`username`，是表中每个列的名称）           |
   | **Type**       | 字段数据类型（比如`INT`、`VARCHAR(50)`，决定字段存储的数据格式） |
   | **Length**     | 字段长度（全称`Length/Values`，比如`VARCHAR(50)`的`50`就是长度） |
   | **Unsigned**   | 仅对数值类型有效（勾选后表示 “无符号”，比如`INT UNSIGNED`只能存非负数） |
   | **Zerofill**   | 仅对数值类型有效（勾选后，数值不足长度时会用`0`填充，比如`INT(3)`存`5`会显示`005`） |
   | **Bin...**     | 全称`Binary`（勾选后表示 “二进制存储”，通常用于字符串类型，区分大小写） |
   | **Allow Null** | 是否允许字段为空（不勾选 = `NOT NULL`，必须填值；勾选 = 可以为空） |
   | **Key**        | 字段的索引类型（比如`PRI`= 主键、`UNI`= 唯一索引、`MUL`= 普通索引） |
   | **Default**    | 字段的默认值（当插入数据不指定该字段时，自动填充的默认内容） |
   | **Extra**      | 额外属性（比如`AUTO_INCREMENT`= 自增、`ON UPDATE CURRENT_TIMESTAMP`= 更新时自动取当前时间） |
   | **Encoding**   | 字段的字符编码（一般继承数据库的编码，比如`utf8mb4`）        |
   | **Collation**  | 字段的排序规则（一般继承数据库，比如`utf8mb4_general_ci`，决定字符串的排序 / 比较规则） |
   | **Comment**    | 字段的备注说明（给字段加注释，方便理解字段含义）             |

2. 基础操作：
   - 创建DB，导航栏DataBase —— Add DB ——输入名字 选择编码格式 和如何排序
   - 创建表，选择数据库，左下角加号创建
   - 添加字段 添加数据

## 1.4 命令行操作

``` sql
mysql -uroot -p+密码 -- 连接数据库

------------------------------

-- 所有的语句都要用；结尾
show databases; -- 查看所有数据库
use 数据库名   -- 切换数据库,不需要分号
show tables;  -- 查看数据库中所有的表
describe 表名; -- 显示数据库中所有表的信息
create database 库名; -- 创建新的数据库

----------------------------
exit;  -- 退出连接
/*
多行注释
*
```

# 二、 数据库语言

## 2.1 数据库语言分类

- DDL 数据库定义语言
- DML 数据库管理语言
- DQL 数据库查询语言
- DCL 数据库控制语言

## 2.2 操作数据库

操作数据库>操作数据库中的表>操作数据库中表的数据

### 2.2.1 创建数据库

``` mysql
create database [if not exists] 数据库名;
```

### 2.2.2 删除数据库

``` mysql
drop database [if exists] 数据库名;
```

### 2.2.3 使用数据库

``` mysql
use 数据库名; -- 如果数据库名是 特殊字段，名字上需要带 ``
use `school`;
```

### 2.2.4 查看数据库

``` mysql
show databases -- 查看所有数据库
```

## 2.3 数据库的字段类型

- 数据
  - **int           标准的整数      4个字节 **
  - **decimal  字符串形式的浮点数  适用于金融计算**

- 字符串
  - char            固定大小的字符串      0-255
  - **varchar     可变字符串               0-65535**
  - tinytext      微型文本                  2^8 - 1
  - **text         文本串                    2^16 - 1**

- 时间

  - year  年份表示

  - date   YYYY-MM-DD   日期格式

  - time   HH:mm:ss          时间格式

  - **datetime     YYYY-MM-DD  HH:mm:ss  最常用时间格式**

  - **timestamp   时间戳     1970.1.1到现在的毫秒数**

- null
  - 没有值，未知（不要用NULL进行运算）

## 2.4 数据库的字段属性

- **Unsigned**
  - 无符号的整数
  - 声明了该列不能为负数
- **zerofill**
  - 0填充
  - 不足长度用0填充
- **auto_increment**(自增)
  - 通常理解为自增，自动在上一条记录的基础上+1（默认）
  - 通常用于设计唯一主键（index）必须是整数
  - 可以自定义主键自增的起始值和步长
- **Allow Null？**
  - 打勾代表允许是空的，不填写值默认为null
  - 不打勾代表不允许为空，如果不赋值就会报错
- **Default**（默认）
  - 不赋值就用默认值

- **PRI**（主键）
  - 主索引

## 2.5 创建表（SQL语句）

``` sql
CREATE TABLE `student` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '学员id',
  `age` int NOT NULL COMMENT '学员年龄',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '学员姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3
```

格式：

``` sql
create table [if not exists] `表名`(
  `字段名` 列类型 [属性] [索引] [注释],
  ......
  `字段名` 列类型 [属性] [索引] [注释]
)[表类型][字符集设置][注释]
```

```sql
show create database 数据库名; -- 查看创建数据库的语句
show create table 表名 -- 查看表的创建语句
desc 表名 -- 显示表的结构
```

## 2.6 修改和删除表

- **修改表**

  ``` sql
  -- 修改旧的表名为新的表名
  alter table 旧表名 rename as 新表名;
  -- 增加表的字段
  alter table 表名 add `字段名` 列属性 （例如：int(10)）
  -- 修改表字段的约束
  alter table 表名 modify 字段名 列属性（例如：varchar(10)）
  -- 修改表的字段名（字段重命名）
  alter table 表名 change 旧字段名 新字段名 列属性
  -- 删除表的字段
  alter table 表名 drop 字段名
  ```

- **删除表**

  ``` sql
  drop table [if exists] 表名
  ```

  

  ***所有的创建和删除操作尽量加上条件判断，以免报错***

  

# 三、MySQL数据管理

## 3.1 外键

在一张表中引用另外一张表

- 物理外键：在创建表时说明，或者在表创建完成后说明（不建议）
- 最佳实践：用程序实现（查出主表的数据，根据主表的数据去查从表中对应的数据）

## 3.2 DML语言

数据库操作语言

- insert
- updata
- delete

## 3.3 添加

``` sql
insert into 表名(字段名1,字段名2,...) values('值1')('值2')

EX：
insert into `grade`(`gradename`)
values('大一')('大二')

insert into `student`(`name`) values('张三')

insert into `student`(`name`,`pwd`,`sex`)
values('张三'，'aaaaa','男'),('李四','bbbbb','男')

/*
*1.字段和字段之间用英文逗号隔开
*2.字段可以省略，但是后面的值要一一对应，不能少
*3.同时可以插入多条数据，值和值之间要用英文逗号隔开
*/
```



## 3.4 修改

``` mysql
update 修改谁 set 字段 where 条件

-- 指定修改
update `表名` set `字段名` = 'xxxxx' where 条件；

-- 不指定（全修改）
update `表名` set `字段名` = 'xxxxx'

-- 修改多个数据
update `表名` set 值1，值2 where 条件；

-- 特殊运算符
/*
1. between ... and ... 闭区间
2. and
3. or
*/
```



## 3.5 删除

``` mysql
-- delete命令
delete from 表名 [where 条件]

-- truncate 清空数据库
truncate 表名

```

**delete 和 truncate 的区别**

- 相同点 ：都能删除数据，但是不会删除表结构
- 不同点 ：
  - truncate 重新设置 自增列 计数器会归零；
  - truncate 不会影响事务

# 四、 DQL查询数据

## 4.1 DQL(Date Quary Language)

- 所有查询操作都用它 selsct
- 数据库最核心语言
- 总语法：

``` mysql
select [all | distinct]
{* | table.* | table.field1[as alias1],[table.field2[as alias2],...]}
from table_Name [as table_alias]
	[left | right | inner join table_name2]  -- 联合查询
	[where ...]                              -- 条件查询
	[group by ...]                           -- 指定结果按照字段分组
	[having]                                 -- 过滤分组的记录
	[order by ...]                           -- 查询数据排序
	[limit {[offset,]row_count | row_countoffset offset}] -- 指定查询记录从哪到哪
```



## 4.2 查询指定字段

### 4.2.1 select 查询

```  mysql
select 字段1,字段2 from `表名` -- * -> 所有字段

-- 查询时给字段取别名
select `字段名1` as 别名1 , `字段名2` as 别名2 from `表名`

-- 拼接字符串函数 concat(a,b)
select concat(str1,str2) [as 别名] form `表名`

-- 其他用法
select version() -- 查询系统版本(函数)
select 91*78-61 as 计算结果 -- 用来计算(表达式)
select @@auto_increment_increment -- 查询自增步长(变量)
select `studentresult`+1 from `result` -- 将查询后的成绩加1
```

### 4.2.2 distinct 去重

```mysql
-- select语句查询后去重
select distinct `字段名` from `表名`
```

### 4.2.3 where条件子句查询

作用：检索数据中符合条件的数据

``` sql
select StudnetNo,StudentResult from result
where StudentResult >= 95 && StudentResult <= 100;

select StudnetNo,StudentResult from result
where StudentNo != 1000;
```

### 4.2.4 模糊查询

- **is null** ：为空
- **is not null** ：不为空
- **between and** : 闭区间
- **like + %** : %代表后面跟任意个字符
  - 例：姓刘的同学，where StudentName like '刘%' ; 名字里面含有嘉的同学，where StydentName like '%嘉%'

- **like + _ ** :  _代表一个字符
  - 例：两个字的刘姓同学，where StudentName like '刘_'

- **in** :   一次性查询多个学生
  - select ... from ...  where ... in (具体值)

### 4.2.5 联表查询

| 操作       | 描述                                       |
| ---------- | ------------------------------------------ |
| Inner join | 两个表中至少有一个匹配就返回               |
| Left join  | 会从左表中返回所有的值，即使右表中没有匹配 |
| Right join | 会从右表中返回所有的值，即使左表中没有匹配 |

``` mysql
-- attention:如果是两个表共同拥有的字段，那么要在select后表明是哪个表中的字段
-- inner join 
select s.studentNo , studentName , StudentResult
from student as s
inner join result as r
on s.studentNo == r.studentNo

-- left join
select s.studentNo , studentName , StudentResult
from student as s
left join result as r
on s.studentNo == r.studentNo

-- right join
select s.studentNo , studentName , StudentResult
from student as s
right join result as r
on s.studentNo == r.studentNo

-- 连接三个表/多个表
select s.studentNo , studentName , StudentResult , subjectName
from student as s
right join result as r
on s.studentNo == r.studentNo
inner join subject sub
on sub.subjectName = r.subjectName

-- 自连接 核心：一张表看成两张一样的表，进行连接查询
select a.studentName as '父级' ， b.studentName as '子级'
from student as a, student as b
where a.studentid = b.parentid

```

### 4.2.6 分页(limit)和排序(order by)

#### 4.2.6.1 排序

- 升序：**ASC**    降序：**DESC**

- example：

  ``` mysql
  select ...
  from ...
  order by studentresult asc
  ```

#### 4.2.6.2 分页

``` sql
limit 起始值，pagesize
```

### 4.2.7 子查询

在where语句里潜逃查询，查询出条件值

``` sql
-- 查询某一个科目学生成绩
select studentNo , subjectNo , studentResult
from result
where subjectNo = (
select subjectNo from subject where subjectName = 'xxxx'
)
```

# 五、 MySQL函数

官网：https://dev.mysql.com/doc/refman/5.7/en/func-op-summary-ref.html

## 5.1常用函数

```` sql
-- 数学函数
abs()     -- 绝对值
ceiling() -- 向上取整
floor()   -- 向下取整
rand()    -- 返回0-1之间的随机数
sign()    -- 判断一个数字的符号 负数返回-1 
......

-- 获取时间函数
curdate()     -- 获取当前日期
now()         -- 获取当前时间
localtime()   -- 本地时间
sysdate()     -- 系统时间
......

-- 系统
user()    -- 获取用户
version() -- 获取版本号
````



## 5.2 聚合函数

``` sql
count()    -- 计数
sum()      -- 求和
avg()      -- 平均值
max()			 -- 最大值
min()		   -- 最小值
```

``` sql
select count(字段) from 表名  -- 会忽略null值
select count(*)  from 表名   -- 不会忽略null值
select count(1)  from 表名	 -- 不会忽略null值
。。。
```

# 六、 事务

## 6.1 特性(ACID)

- **原子性(Atomicity)**

  要么都成功，要么都失败

- **一致性(Consistency)**

  提交前后数据保持一致

- **持久性(Durability)**

  事务一旦提交就会被持久化，不可逆

- **隔离性(Islation)**

  多个用户并发访问数据库时，数据库为每一个用户开启的事务，事务之间要相互隔离

  

**事务执行流程**

``` sql
set autocommit = 0; -- 关闭自动提交
start transaction   -- 开启一个事务

/* 一组事务 */

commit;            -- 成功：提交事务，被持久化
rollback;					 -- 失败：回滚

set autocommit = 1;-- 恢复默认值
```

# 七、 索引

## 7.1 索引的分类

- **主键索引(Primary Key)**
  - 唯一标识，主键不可重复，只有一个列作为主键

- **唯一索引 (Unique Key)**
  - 索引的字段（或联合索引的字段组合）在整张表中不存在重复值。既可以为单个字段创建唯一索引，也可以为多个字段创建**联合唯一索引**（此时要求多个字段的组合值唯一，单个字段允许重复）。

- **常规索引(Key/Index)**
  - 默认索引
- **全文索引(FullText)**
  - 快速定位数据

## 7.2 索引原则

- 索引不是越多越好
- 不要对经常变动的数据加索引
- 小数据量不要加索引
- 索引一般加在经常查询的字段上

# 八、 数据库设计规范

## 8.1 流程

- 分析需求：分析业务和需要处理的数据库需求
- 概要设计：设计关系图，E-R图

## 8.2 三大范式

- **第一范式**：

  数据库的每一个字段都是不可分割的原子数据项

- **第二范式**：

  前提：第一范式

  内容：每一张表只描述一件事情

- **第三范式**：

  前提：第一第二范式

  内容：每一列数据都和主键直接相关

# 九、JDBC

## 10.1 数据库驱动

程序通过数据库驱动来操作数据库

## 10.2 JDBC

为了简化开发人员对数据库的同一操作，提供了一个java操控数据库的规范，俗称JDBC

``` java
import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.加载驱动,8.0+版本不需要这一步
        Class.forName("com.mysql.jdbc.Driver");
        // 2.用户信息和url
        String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=utf-8&useSSL=true";
        String username = "root";
        String password = "@Cqy15850479975";
        // 3.连接数据库，connection代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        // 4.创建执行sql对象
        Statement statement = connection.createStatement();
        // 5.创建sql语句，用sql对象去执行sql
        String sql = "select * from student";
        ResultSet resultSet = statement.executeQuery(sql); // 返回结果集，结果集封装了我们的全部查询结果
        while (resultSet.next()) {
            System.out.println(resultSet.getObject("id") + " "
                             + resultSet.getObject("name") + " "
                             + resultSet.getObject("pwd") + " "
                             + resultSet.getObject("sex") );

        }
        // 6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}

```



## 10.3 sql注入

- **本质原因 ： **

  **程序未对用户输入进行有效校验与过滤，导致用户输入的恶意 SQL 代码被当作合法 SQL 指令执行**

## 10.4 preparedStatement对象

``` sql
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class test02 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();

            //区别
            //使用？占位符代替参数 用户输入通过?传入
            String sql = "insert into student(id,`age`,`name`,`pwd`,sex) values(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql); //预编译 先写sql不执行

            stmt.setInt(1, 999999);
            stmt.setString(3, "caiqiyang");
            stmt.setString(4, "123444");
            stmt.setInt(2, 12);
            stmt.setString(5, "男");

            int i = stmt.executeUpdate();
            if (i > 0) {
                System.out.println("Success");
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally{
            JdbcUtils.release(conn,stmt,rs);
        }

    }
}

```



