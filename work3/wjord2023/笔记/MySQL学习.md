

# MySQL学习

MySQL：数据库（DataBase）-软件-安装在操作系统之上，SQL可储存大量数据，500万以上。

## 数据库分类

**关系型数据库：**（SQL）

1. MySQL，Oracle，sql Server, DB2, SQLite
2. 通过表与表之间，行与行之间的关系进行数据的储存 

**非关系型数据库：** （NoSQL：not only SQL)

1. Redis, MongDB
2. 对象储存，通过对象自身的性质决定

**DBSM(数据管理系统)：**

MySQL：数据管理系统



*<u>每个sqlyog的执行操作本质对应一个sql可以在历史记录查看</u>*

##### 连接数据库：

命令行连接

```sql
show databases; -- 查看全部数据
use 数据库名; -- 切换
show tables; -- 查看数据库的表
ctrl + c -- 强行退出
describe student -- 显示数据库全部信息
create database westos; -- 创建一个数据库
-- 单行注释
/**/ -- 多行注释
```

DDL 数据库定义语言

DML 操作

DQL 查询

DCL 控制



#### 操作数据库：

操作数据库>数据库中的表>表中的数据

##### 操作数据库（了解）

增删改查（不区分大小写）

```sql
CREATE DATABASE [IF NOT EXISTS] westo
DROP DATABASE [IF EXISTS] westo
USE `school` -- 如果表名为特殊字符带``
SHOW DATABASE -- 查看所有
```





#### 数据库的列类型:

*数值*

```sql
tinyint -- 1字节
smallint -- 2字节
mediumint -- 3字节
int -- 4字节（标准）
bigint -- 8字节
float -- 4字节
double -- 8字节
decimal -- 字符串形式的浮点数（金融计算）
```

*字符串*

```sql
char -- 字符串固定大小0~255
varchar -- 字符串可变长0~65535 常用 String
tinytext -- 微型文本2^8 - 1
text -- 文本串 2^16 - 1
```

*时间日期*

```sql
date -- 日期(YYYY-MM-DD)
time -- 时间（HH:MM:SS）
datetime -- YYYY-MM-DD HH:MM:SS 常用
timestamp -- 时间戳 1970.1.1到现在的毫秒数（可做到全球统一） 常用
year
```

*null*

```sql
-- 不要使用null进行运算,结果为null
```



#### 数据库的字段属性（重点）：

Unsigned : 

- 无符号的整数

- 不能声明为负数

  

zerofill : 

- 0填充
- 不足的位用0填充



自增AUTO_INCREMENT：

- 自动在上一条记录的基础上+1
- 通常用来设计唯一的主键index，必须为整数类型
- 可以自定义设计主键自增的起始值和步长



非空DEFAULT:

- 不过它赋值就会报错
- 不填默认为null



##### 阿里巴巴规范：

每个列表必须有以下5个字段：

id 主键

`version` 乐观锁

is_delete 伪删除

gmt_create 创建时间

gmt_update 修改时间  





##### 创建数据库表：

```sql
PRIMARY KEY(``) -- 主键一个列表只有一个 
CREATE TABLE [IF NOT EXISTS] `表名`(
 '字段名' 列属性 [属性][索引][注释], -- 每段结束都要有，除了最后一段 
)ENGINE=INNODB DEFAULT CHARSET=utf8 --[表类型][字符集设置][注释]

```

常用命令：

```sql
SHOW CREATE DATABASE `school`
SHOW CREATE TABLE `student`
DESC `student` -- 查看表的结构
```



#### 数据库表的类型（ENGINE）：

INNODB：默认使用~

MYISAM：早些年使用的

|            | MYISAM         | INNODB      |
| ---------- | -------------- | ----------- |
| 事务支持   | 不支持         | 支持        |
| 数据行锁定 | 不支持（行锁） | 支持        |
| 外键约束   | 不支持         | 支持        |
| 全文检索   | 支持           | 不支持      |
| 表空间大小 | 较小           | 较大，约2倍 |

常规使用操作：

- MYISAM 节约空间，速度较快
- INNODB 安全性高，事务的处理，多表多用户操作



##### 物理空间下存在的位置

所有数据库文件都存在date目录下，一个文件夹就对应一个数据库

本质还是文件的储存

MYSQL引擎在物理文件上的区别

- INNODB在数据库中只有一个*.frm文件，以及上级目录ibdata1文件
- MYISAM对应文件
  - *.frm表结构的定义文件
  - *.MYD 数据文件（data）
  - *.MYI 索引文件 （index）



##### 设置数据库的字符集编码

```sql
CHARSET=utf8
```

不设置的话，会是mysql默认的字符集编码（Latin 1）



##### 修改和删除数据库表

> 修改
>
> ```sql
> -- 修改表名
> ALTER TABLE `旧表名` RENAME AS  `新表名`,
> -- 修改表的字段
> ALTER TABLE `表名` ADD `字段名` INT(11) -- [列属性]
> -- 修改表的字段(重命名,修改约束!)
> ALTER TABLE teacher1 MODIFY age VARCHAR(11) -- 修改约束
> ALTER TABLE teacher1 CHANGE age age1 INT(1) -- 字段重命名
> -- 删除表的字段
> ALTER TABLE teacher1 DROP age1
> ```
>
> 

> 删除
>
> ```sql
> -- 删除表 
> DROP TABLE IF EXISTS teacher1
> ```

*所有的删除和创建操作尽量加上判断以免报错*



#### MYSQL数据管理

##### 外键(了解即可)(不用)

```sql
-- 方式一
-- 定义外键key
-- 给这个外键添加约束(执行应用)reference引用
KEY `FK_gradeid`(`gradeid`),
CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)
-- 删除有外键的表需要先删除从表,再删除主表

-- 方式二
-- 创建表的时候没有外键关系
ALTER TABLE `student`
ADD CONSTRAINt `FK_gradeid` FOREIGN KEY(`gradeid` /*作为外键的列*/) REFERENCES `grade`(`gradeid`);
```

以上操作都是物理外键,数据库级别外的外键,一般不用("避免数据库过多造成困扰")

> 阿里:[强制]不得使用外键与级联,一切外键概念必须在应用层解决

***最佳方式***

- 数据库就是单纯的表,只用来存数据,只有行数据和列字段
- 如果我们想使用多张表的数据,想使用外键(程序去实现)



#### DML语言(全部记住)

###### 数据库意义:  数据存储,数据管理

DML语言: 数据操作语言

##### 添加

insert(插入):

```sql
-- 插入语句(添加)
insert into [`表名`]([`字段1`,`字段2`,..])values(`值1`,`值2`,..),
-- 由于主键自增我们可以省略(如果不写表的字段他就会一一匹配)
insert into `grade` values('大三'),

-- 一般写插入语句,我们一定要保证数据与字段一一对应
-- 可以插入多个字段(),(),...
insert into `grade`(`gradename`,`pwd`)
values('大二','123'),('大一','456'),

-- 字段可以省略,如果后面的值一一对应
```

##### 修改

update:

```sql
update `student` set `name`='张三' where id = 1;
-- 不指定条件的情况下,会改所有的值
-- update 表名 set colum_name = value where(条件)[]

-- 修改多个属性,逗号隔开
update `student` set `name`='张三',email = '123@qq.com' where id = 1;

-- 可以设置为变量
update `student` set time = current_time,email = '123@qq.com' where id = 1;
```



条件:where子句 运算符id =/>/在某个区间

操作符会返回布尔值

| 操作符          |        |
| --------------- | ------ |
| =               |        |
| <>或!=          | 不等于 |
| between 2 and 5 | [2,5]  |
| and / or        |        |



##### 删除

delete

```sql
-- delete from 表名 where 条件
```



TRUNCATE命令

作用:完全清空一个数据库表,表的结构和索引约束不会变

```sql
-- 清空"student"表
TRUNCATE 'student' 
```



###### 区别:

- TRUNCATE重新设置 自增列 计算器会归零
- TRUNCATE不会影响事务

了解:delete删除的问题,重启数据库,现象

- innodb 自增项会重1开始(存在内存文件当中的,断电即失)
- MyISAM 继续从上一个自增项开始(存在文件中不会丢失)



## DQL查询数据(最重点)

### 1.DQL

（DATA QUERY LANGUANGE:数据查询语言）

-所以的查询操作都用它 Select

-简单的查询，复杂的查询都用它

-数据库最核心的语言，最重要的语句

-使用频率最高的语句

<img src="C:\Users\苏琎宇\OneDrive\桌面\文件夹\截图\Snipaste_2023-11-07_20-59-10.png" style="zoom: 50%;" />

### 2.指定查询字段

```mysql
select 字段 from 表
select 字段 as 别名 from 表 as 别名
-- 函数 concat(a,b)
select concat('姓名：',StudentName) as 新名字 from 表
-- 去重复（distinct）,只显示一条
select distinct StudentNo from result
-- 
select version() -- 查询系统版本
select 100*3 - 1 as -- 用来计算
select @@auto_increment_increment -- 查询自增的步长
select studentNo,studentResult + 1 as '提分后' from result

```

> 数据库中的表达式：文本值，列，Null，函数，计算表达式，系统变量...



### 3.where条件字句

> 作用：检索数据中符合条件的值
>
> 搜索的条件由一个或多个表达式构成，结果为boolen



##### 逻辑运算符

```sql
-- 两种语法都可以，尽量使用英文符号
and &&
or ||
Not !
```

```sql
-- 列不区分大小写（studentNo和StudentNo结果相同）
select studentNo,studentResult from result
where studentresult >= 95 and studentresult <= 100
-- 模糊查询between（区间）
where studentresult between 95 and 100
where student != 1000
where not student == 1000
```



##### 模糊查询

```sql
-- 比较运算符
is null -- 为空，则结果为True
is not null -- 不为空，则结果为True
between and -- 若a在b和c中间则结果为True
like -- sql匹配，如果a匹配b，则结果为True
in -- a in (a1,a1,...)假设a在a1,a2..中的值结果为真
```

```sql
-- --like-- --
select studentno ,studentname from student
where studnetname like '刘%'
where studnetname like '刘_' -- %表示有0到任意个字符， _表示1个字符 __表示2个字符
where studentname like '%嘉%'

-- --in-- --
where studentno in (1001,1002,1003)
where adress in Fujian -- %和_用不了，in加的是一个具体的值

-- --null / not null-- --
where address = '' or adress is null
where borndate is not null

```



##### 联表查询

```sql
-- --jion-- --
/*思路：
1.分析需求，分析查询的字段来自哪些表（连接查询）
2.确定使用哪种查询？7种
确定交叉点（这两个表哪些数据是相同的）
判断条件：
*/

-- --inner join-- --
select s.studentNo,studentName,SubjectNo,StudentResult
from student as s -- as可以不写
inner join result as r -- 将两张表连起来，s. 使用student的表的studentNo
where s.studentNo = r.studentNo

-- --right join-- --
right join result r
on s.studentNo = r.studentNos.studentNo = r.studentNo

-- --left join-- --
left join result r 
on s.studentNo = r.studentNo

-- join（连接的表） on（判断的条件） 连接查询
-- where 等值查询
```



| 操作       | 描述                                       |
| ---------- | ------------------------------------------ |
| inner join | 如果表中至少有一个匹配，则返回行           |
| right join | 即使左表中没有匹配也会从右表中返回所有的值 |
| left join  | 即使右表中没有匹配也会从左表中返回所有的值 |



##### 自连接

> 自己表和自己的表连接，核心：一张表拆为两张一样的表
>
> 操作:查询父类对应的子类



```sql
select a.`categoryName` as 'fu',b.`categoryName` as 'zhi'
from `categoryName` as a,`categoryName` as b
where a.`categoryName` = b.`pid`
```



##### 分页和排序

```sql
-- --order by-- --
-- order by 通过哪一个字段排序 怎么排
order by studentNo ASC -- 升序
order by s DESC -- 降序

-- --limit-- --
-- 分页(缓解数据库压力) / 瀑布流
-- limit 启始页,页面大小
-- 网页显示:当前页,page_size,总页数
limit 0,5 -- 第一页 0,5 第二页 5,5 ... 
```



##### 子查询

> where (固定的值)
>
> 本质在 where语句中嵌套一个子查询语句

```sql
-- --子查询-- -- 
where StudentNo = (
	select SubjectNO from subject where subjectName = ''
)
-- 由里及外
-- 可以嵌套
```



## MySQL函数

#### 常用函数

```mysql
-- --数学运算-- --
ABS -- 绝对值
CEILING -- 向上取整
FLOOR -- 向下取整
RAND -- 生成随机数
SIGN -- 判断一个数的符号 0->0 -10->-1 10->1

-- --字符串函数-- --
CHAR_LENGTH -- 字符串长度
CONCAT -- 拼接字符串
INSERT('',1,2,'') -- 替换字符（从第一个字符开始替换两个字符）
LOWER -- 转小写
UPPER -- 转大写
INSTR('hello','h') -- 返回第一次出现的index
REPLACE -- 替换
SUBSTR('',4,3) -- 返回指定的子字符串('原字符串'，截取的位置，截取的长度)
REVERSE -- 反转字符

-- --时间和日期函数-- --
CURRENT_DATE -- 获取当前日期
CURDATE -- 和上面一样的
NOW -- 获取当前时间
LOCALTIME -- 获取本地时间
SYSDATE -- 系统时间
YEAR(NOW) -- 获取当前年（月、日、时、分、秒）

-- --系统-- --
SYSTEM——USER 
USER
VERSION 
```



#### 聚合函数

```mysql
COUNT -- 计数
-- == == --
COUNT(StudentName) -- 忽略none
COUNT(*) -- 不忽略none，本质计算行数，两个差不多
COUNT(1) -- 不忽略none
-- == == --
SUM -- 求和
AVG -- 平均值
MAX -- 最大值
MIN -- 最小值
```



## 数据库级别的MD5加密（扩展）

主要增强算法复杂度和不可逆性

```mysql
CREATE TABLE `TESTMD5`(
	`ID` INT(4) NOT NULL
	`NAME` VARCHAR(50) NOT NULL
	`PWD` VARCHAR(50) NOT NULL
	PRIMARY KEY(`ID`)
)ENGINE=INNODB DEFAULT CHARSET=UTF-8

INSERT INTO TESTMD5 VALUES(1,`ZHANGSAN`,`123456`),(2,`WANGWU`,`12345`)

UPDATE TESTMD5 SET PWD=MD5(PWD) WHERE ID=1

-- 插入时加密
INSERT INTO TESTMD5 VALUES(4,'XIAOMING',`123678`)

-- 如何校验
SELECT * FROM TESTMD5 WHERE `NAME` = `XIAOMING` AND PWD = MD5('123456')
```



## 事务

> 核心：将一组sql放在一个批次中执行

事务原则：ACID原则 --> 原子性(Automicity)、一致性(Consistency)、隔离性(Isolation)、持久性(Durability)

##### 原子性

要么都成功要么都失败

##### 一致性

事务前后的数据完整性要保证一致

##### 隔离性

不会被其他事务所干扰

##### 持久性

事务一旦提交则不可逆

<img src="C:\Users\苏琎宇\OneDrive\桌面\文件夹\截图\Snipaste_2023-11-07_21-20-03.png" style="zoom:50%;" />

```mysql
-- == 事务 == --
-- mysql是默认开启事务自动提交的
SET AUTOCOMMIT = 0 -- 关闭
SET AUTOCOMMIT = 1 -- 开启
-- 手动处理事务
-- 事务开启
START TRANSACTION -- 标记一个事务的开始，从这以后的sql都在同一个事务内

INSERT xx
INSERT xx

-- 提交：持久化（成功）
COMMIT
-- 回滚：回到原来的样子（失败）
ROLLBACK
-- 事务结束
SET AUTOCOMMIT = 1

-- 了解
SAVEPOINT -- 设置一个事务的保存点
ROLLBACK TO SAVEPOINT  -- 回滚到保存点
RELEASE TO SAVEPOINT -- 删除保存点
```



> 例子

```sql
CREATE DATABASE SHOP CHARACTER SET UTF-8 COLLATE UTF8_GENERAL_CI
USE SHOP
CREATE TABLE `ACCOUNT`(
	`ID` INT() NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(30) NOT NULL,
    `MONEY` DECIMAL(9,2) NOT NULL,
    PRIMARY KEY(`ID`)
)ENGINE=INNODB DEFAULT CHARSET=UTF8

INSERT INTO ACCOUNT(`NAME`,`MONEY`)
VALUES('A',2000.00),('B',10000.00)

-- 模拟转账
SET AUTOCOMMIT = 0;
START TRANSACTION
UPDATE ACCOUNT SET MONEY=MONEY+500 WHERE `NAME` = 'A'
UPDATE ACCOUNT SET MONEY=MONEY-500 WHERE `NAME` = 'B'

COMMIT;
ROLLBACK;

SET AUTOCOMMIT = 1;
```

## 索引

mysql官方对索引的定义：索引是帮助mysql高效数据的数据结构

#### 索引的分类

- 主键索引（PRIMARY KEY）

  唯一的标识，主键不可重复，只能有一个列主键

- 唯一索引（UNIQUE KEY)

  避免重复的列出现，唯一索引可以重复，多个列都可以标识为唯一索引

- 常规索引（KEY/INDEX）

  默认的，index

- 全文索引（FULLTEXT）

  在特点的数据库引擎下才有，MyISAM

  快速定位数据

```sql
-- == 索引的使用 == --
-- 在创建表的时候给字段增加索引
-- 创建完毕后增加索引

-- 显示所有的索引信息
SHOW INDEX FROM `STUDENT`

-- 增加一个索引
ALTER TABLE `STUDENT` ADD FULLTEXT INDEX `STUDENTNAME`(`STUDENTNAME`); -- 索引名（列名）
-- 分析sql执行的状况
EXPLAIN SELECT * FROM `STUDENT`
EXPLAIN SELECT * FROM `STUDENT` WHERE MATCH(`STUDENTNAME`) AGAINST('') 
```



#### 测试索引

```sql
-- 插入100万条数据
DELIMITER $$ -- 写函数前必写，标志

CREATE FUNCTION mock_data()
RETURNS INT 
BEGIN 
	DECLARE NUM INT DEFAULT 100000;
	DECLARE I INT DEFAULT 0;
	
	WHERE I<NUM DO
		-- 插入语句
		INSERT INTO APP_USER(`NAME`,`EMAIL`,`PHONE`,`GENDER`,`PASSWORD`,`AGE`)
            VALUE(CONCAT('USER',I),'123456@qq.com'
            ,CONCAT('18',FLOOR(RAND()*((9999999-1000000)+1000000))))
            ,FLOOR(RAND()*2),UUID(),FLOOR(RAND()*100)
        	)
            
		SET I = I+1;
    END WHILE;
    RETURN I;
END;
SELECT mock_data();
```

```sql
-- 直接查找 -- 时间较久
SELECT * FROM APP_USR WHERE `NAME` = '100';

-- 索引查找 -- 提升极大
-- 添加索引（内存中新加一排数）
CREATE INDEX ID_APP_USER_NAME ON APP_USER(`NAME`)
SELECT * FROM APP_USR WHERE `NAME` = '100';
```

索引在大数据的时候区别十分明显

#### 索引原则

- 索引不是越多越好
- 不要对进程变动数据加索引
- 小数据量的表不需要加索引
- 索引一般加在常用来查询的字段上

> 索引的数据结构
>
> Hash 类型的索引
>
> Btree：InnoDB默认的类型

## 权限管理

#### 用户管理

> sql命令操作
>
> 实质：对user表进行增删改查

```sql
-- 创建用户
CREATE USER A IDENTIFIED BY '123456'

-- 修改密码（当前用户）
SET PASSWORD = PASSWORD('123456')

-- 修改密码（指定用户）
SET PASSWORD FOR A = PASSWORD('123456')

-- 重命名
RENAME USER A TO B

-- 用户授权(全部权限) 除了给别人授权都可以做
GRANT ALL PRIVILEGES ON *.* TO B

-- 查询权限
SHOW GRANTS FOR B
SHOW GRANTS FOR root@localhost

-- 撤销权限
REMOVE ALL PRIVILEGES ON *.* FROM B

-- 删除用户
DROP USER B

```



## MySQL备份

为什么要备份：

- 保证重要的数据不丢失

- 数据转移

MySQL数据库备份的方式

- 直接拷贝物理文件

- 使用sqlyog可视化导出

- 使用命令行导出mysqldump命令行使用

  ```sql
  # mysqldump -h 主机 -u 用户名 -p 密码 数据库 表1 表2 > 物理磁盘位置/文件名
  mysqldump -hlocalhost -uroot -p123456 school student >D:/a.sql
  
  # 导入
  source d:/
  ```

  

## 规范数据库设计

当数据库比较复杂的时候，我们就需要设计了

糟糕的数据库设计：

- 数据冗余，浪费空间
- 数据库插入和删除都会麻烦、异常（不使用物理外键）
- 程序的性能差



## 数据库的归约，三大范式

### 第一范式

原子性：保证每一列不可再分

#### 第二范式

满足第一范式

每张表只描述一件事情

#### 第三范式

每一列数据都和主键直接相关，而不能间接相关



阿里：关联的表不得超过三张

性能更为重要

# ***JDBC***

用java操作数据库

java操作数据库的规范

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";

        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM users";

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){

        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}

```

> 步骤：
>
> 加载驱动
>
> 连接数据库DriverManager
>
> 获得返回的结果
>
> 释放连接 
>
> ![截屏2023-11-08 17.29.04](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 17.29.04.png)
>
> ![截屏2023-11-08 17.29.54](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 17.29.54.png)
>
> ![截屏2023-11-08 17.32.27](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 17.32.27.png) 
>
> ![截屏2023-11-08 17.36.59](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 17.36.59.png)

![截屏2023-11-08 19.59.00](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 19.59.00.png)

![截屏2023-11-08 19.59.58](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 19.59.58.png)

![截屏2023-11-08 20.00.37](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 20.00.37.png)

![截屏2023-11-08 20.01.13](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 20.01.13.png)

![截屏2023-11-08 20.01.33](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 20.01.33.png)



> 代码实现：
>
> 提取工具类
>
> 编写增删改的方法，`excuteUpdate`
>
> 固定模版（增删改）
>
> ![](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 20.32.53.png)
>
> 编写查的方法![截屏2023-11-08 20.41.10](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 20.41.10.png)



## SQL注入问题![截屏2023-11-08 20.48.28](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 20.48.28.png)

## PreparedStatment

preparedStatement可以防止sql注入，效率更高![截屏2023-11-08 21.04.17](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 21.04.17.png)

![截屏2023-11-08 21.16.46](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-08 21.16.46.png)

## 事务(Transaction)

![截屏2023-11-09 09.53.52](/Users/wjord/Library/Application Support/typora-user-images/截屏2023-11-09 09.53.52.png)

## 数据库连接池

数据库连接--执行完毕--释放

连接--释放 十分浪费系统资源

**磁化技术：准备一些预先的资源，过来就连接预先准备好的**

编写连接池，实现一个接口DATASOURCE

> 开源数据源：
>
> DBCP
>
> C3P0
>
> Druid：阿里

使用这些数据库连接源后，我们在项目中就不需要编写连接数据库的代码

> DBCP

## (完结撒花)

