# 1、初识mysql

JavaEE: 企业级开发 web

前端（页面：展示，数据！）

后台（连接点：连接数据库JDBC,链接）

数据库 （存数据 ，Txt ，Excel ，word）



## 1.1、为什么要学数据库

1、岗位需求

2、现在的世界，大数据时代，得数据者得天下

3、被迫需求：存数据

==4、数据库是所有软件体系中最核心的存在==    DBA



## 1.2、什么是数据库

数据库（DB，DataBase）

概念：数据仓库，软件，安装在操作系统之上！

作用：存储数据，管理数据



## 1.3、数据库分类

**关系型数据库：(SQL)**

* MySQL，Oracle，Sql Server，DB2，SQLite

* 通过表与表之间，行与列之间的关系进行数据的存储， 学员信息表，考勤表，....

**非关系型数据库： （NoSQL）Not Only**

* Redis，MongDB
* 非关系型数据库，对象储存，通过对象的自身的属性来决定。

==DBMS（数据库管理操作系统）==

* 数据库的管理系统，科学的有效管理我们的数据。维护和获取数据；
* MySQL，数据库管理系统



## 1.4、MySQL简介

MySQL是一个**关系型数据库管理系统**

前世：瑞典MySQLAB公司

今世：属于Oracle旗下产品

MySQL是最好的RDBMS（Relational DataBase Management System，关系型数据库管理系统）应用软件之一。



## 1.5、连接数据库

命令行连接

```sql
mysql -uroot -plwx720317 -- 连接数据库
-- 所有的语句都用;结尾
show databases; -- 查看所有的数据库

use school; -- 切换数据库 use 数据库名

show tables; -- 查看数据库中所有的表

describe student; -- 显示数据库中某个表的信息

create database westos; -- 创建一个数据库

exit; -- 退出数据库
```



**数据库xx语言**（CRUD增删改查）

DDL 定义

DML 操作（ Manipulatio）

DQL 查询（query）

DCL 操作

# 2、操作数据库

操作数据库 > 操作数据库中的表 > 操作表中数据

## 2.1、操作数据库

1、创建数据库

```sql
CREATE DATABASE IF NOT EXISTS westos;
```

2、删除数据库

```sql
DROP DATABASE IF EXISTS WESTOS;
```

3、使用数据库

```sql
USE `school`;
```

4、查看数据库

```sql
SHOW DATABASES  -- 查看所有的数据库
```





## 2.2、数据库的字段属性（重点）

==Unsigned==：

* 无符号的整数
* 声明了该列不能声明为负数

==zerpfill：（零填充）==：

* 不足的位数使用0填充， int（3）， 5 ---005

==自增==：

* 通常用来设计唯一的主键~index，必须为整数类型
* 可以自定义起始值和步长

==default==；

* 默认值

==主键==：

* 唯一标识符



## 2.3、创建数据库表（重点）



```sql
-- 创建一个表
CREATE TABLE IF NOT EXISTS `student`(
  `id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '学生姓名',
  `password` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `gender` VARCHAR(20) NOT NULL DEFAULT '女'  COMMENT '学生性别',
  `birthday` DATETIME  DEFAULT NULL COMMENT '出生日期',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '地址',
  `email` VARCHAR(50) DEFAULT NULL COMMENT'邮箱',
   PRIMARY KEY(`id`) -- 设置主键


)ENGINE=INNODB DEFAULT CHARSET=utf8mb4; -- 设置引擎与字符集
```

格式

```sql
CREATE TABLE IF NOT EXISTS 表名(
	'字段名' 列类型 [属性] [属性] [注释],
    '字段名' 列类型 [属性] [属性] [注释],
	....
    '字段名' 列类型 [属性] [属性] [注释]
)[表类型][字符集][注释]
```



常用命令

```sql
SHOW CREATE DATABASE school -- 查看创建数据库的语句
SHOW CREATE TABLE student -- 查看studnet数据表的定义语句
DESC student -- 显示表的结构
```





## 2.4、数据表的类型

|            | MYSIAM | INNODB         |
| ---------- | ------ | -------------- |
| 事务支持   | 不支持 | 支持           |
| 数据行锁定 | 不支持 | 支持           |
| 外键约束   | 不支持 | 支持           |
| 全文索引   | 支持   | 不支持         |
| 表空间大小 | 较小   | 较大，约为两倍 |

常规使用操作：

* MYISAM ：节约空间，速度较快
* INNODB ：安全性好，事务的处理，多表多用户操作





## 2.5 、修改删除表

> **修改表（ALTER）**

```sql
-- 修改表名 ：ALTER TABLE 旧表名 RENAME AS 新表名
ALTER TABLE teacher RENAME AS teacher1

-- 增加表的字段 ALTER TABLE 表名 ADD 字段名 列属性
ALTER TABLE teacher1 ADD age INT(11)

-- 修改表的字段 (重命名或修改约束) 
-- ALTER TABLE 表名 MODIFY 字段名 列属性[]
ALTER TABLE teacher1 MODIFY age VARCHAR(11) -- 修改约束
-- ALTER TABLE 表名 CHANGE 旧名字 新名字 列属性[]
ALTER TABLE teacher1 CHANGE age age1 INT(1) -- 字段重命名

-- 删除表的字段 ALTER TABLE 表名 DROP 字段名
ALTER TABLE teacher1 DROP age1 

```



> **删除表(DROP)**

```sql
-- 删除表，如果表存在
DROP TABLE IF EXISTS teacher1
```



注意点：

*  v``字段名，使用这个包裹
* 注释 -- /**/
* sql关键字大小写不敏感
* 所有的符号用英文

# 3、MySQL数据管理

## 3.1、外键（了解即可）

> **方式一：在创建表的时候，增加约束**

```sql
CREATE TABLE `grade`(
   `gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级id',
   `gradename` VARCHAR(50)NOT NULL COMMENT '年级名称',
   PRIMARY KEY(`gradeid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- 学生表的grade字段 要去引用年纪表的gradeid
-- 定义外键key
-- 给这个外键添加约束（执行引用） referrence 引用
CREATE TABLE IF NOT EXISTS `student`(
  `id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '学生姓名',
  `password` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `gender` VARCHAR(20) NOT NULL DEFAULT '女'  COMMENT '学生性别',
  `birthday` DATETIME  DEFAULT NULL COMMENT '出生日期',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '地址',
  `email` VARCHAR(50) DEFAULT NULL COMMENT'邮箱',
  `gradeid` INT(10) NOT NULL COMMENT '学生的年级',  
   PRIMARY KEY(`id`),
   KEY `FK_gradeid` (`gradeid`), -- 定义外键
   CONSTRAINT `FK_gradeid` FOREIGN KEY(`gradeid`) REFERENCES `grade`(`gradeid`)


)ENGINE=INNODB DEFAULT CHARSET=utf8mb4
```

注意：要删除有外键关系的表的时候，必须要先删除引用别人的表（从表），再删除被引用的表（主表）

> 方式二 : 创建表成功后增加约束

```sql
CREATE TABLE `grade`(
   `gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级id',
   `gradename` VARCHAR(50)NOT NULL COMMENT '年级名称',
   PRIMARY KEY(`gradeid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- 学生表的grade字段 要去引用年纪表的gradeid
-- 定义外键key
-- 给这个外键添加约束（执行引用） referrence 引用
CREATE TABLE IF NOT EXISTS `student`(
  `id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '学生姓名',
  `password` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `gender` VARCHAR(20) NOT NULL DEFAULT '女'  COMMENT '学生性别',
  `birthday` DATETIME  DEFAULT NULL COMMENT '出生日期',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '地址',
  `email` VARCHAR(50) DEFAULT NULL COMMENT'邮箱',
  `gradeid` INT(10) NOT NULL COMMENT '学生的年级',  
   PRIMARY KEY(`id`)
  

)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- 创建表的时候没有外键关系
ALTER TABLE `student`ADD CONSTRAINT `KF_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)
-- ALTER TABLE 表 ADD CONSTRAINT 约束名 FOREIGN KEY(作为外键的列) references 那个表（那个字段）
```

==最佳实践==

* 数据库就是单纯的表，只用来存放数据，只有行（数据）和列（字段）
* 我们想使用多张表的数据，想使用外键（程序去实现）



## 3.2、DML语言（全部记住）

**数据库意义：**数据存储，数据管理

DML语言：数据操作语言

* insert
* update
* delete



## 3.3、添加(insert)

> insert

```sql
-- 插入语句（添加）
-- intsert into 表名 （字段名1,字段名2,字段名3....）values(值1,值2,值3....)
INSERT INTO `student` (`id`,`name`)
VALUES (1,'张三');

-- 也可以一次性插入多行
INSERT INTO `student` (`id`,`name`,`password`)
VALUES(2,'路明非','1234567'),(3,'李四','aaaaa');

-- 总之你插入的值必须与前面一一匹配，且非空的值必须插入值
INSERT INTO  `student` (`id`,`name`,`password`,`gender`,`email`)
VALUES(4,'haha','wwww','男','sssss');

-- 主键可以忽略，他会自增
INSERT INTO `student` (`name`,`password`,`birthday`,`address`,`email`)
VALUES('傻狗','lwx123',CURRENT_TIME,'地球','33');
```



语法：==insert into 表名 （字段名1，字段名2...）values （值1，值2....）==

注意事项

* 非空数据必须填



## 3.4、修改(update)

> update 修改哪张表  set 原来的值=新值   条件

```sql
-- 修改学员名字
UPDATE `student` SET `name`='狂神' WHERE id=1;

-- 不指定条件 ，会改动所有表！
UPDATE `student` SET `name`='牛牛' ;

-- 修改多个属性，逗号隔开
UPDATE `student` SET  `name`='大哥', `gender`='大帅哥' WHERE id=2

-- 语法：
-- update 表名 set column_name =值 where 条件
```



> update 修改那张表 add column  字段  

```sql
-- 增加字段
ALTER TABLE students ADD COLUMN birth VARCHAR(10) NOT NULL;
```



条件 （结果返回布尔值）

| 操作符            | 含义       |
| ----------------- | ---------- |
| =                 | 等于       |
| <> 或！=          | 不等于     |
| >                 |            |
| <                 |            |
| >=                |            |
| <=                |            |
| between ...and... | 在某个范围 |
| and               | &&         |
| or                | \|\|       |

```sql
-- 通过多个条件定位数据
update student set `name` ='牛牛' where name='狂神'and gender='女'
```



注意点：

* column_name  是数据库的列，尽量带上``

* 条件，筛选的条件，如果没有指定，则会修改所有的列




## 3.5、删除(delete and truncate)

> **delete 命令**

语法：delete from 表名 where 条件

```sql
-- 删除数据
DELETE FROM `student` WHERE id=1
```



> truncate 命令

作用：完全清空一个数据库表

```sql
-- 清空 student 表
TRUNCATE `student`
```



> delete 和 truncat 的区别

* 相同点：都能删除数据，都不会删除表结构
* 不同点：
  * truncate 重新设置自增列 计数器会归零
  * truncate 不会影响事务





# 4、DQL查询数据（最重点）

==**select完整语法**==

```sql
select  distinct  字段
from 表1  as 表1的别名,  表2 as 表2的别名 ....
left (right or inner) join  表 as  表别名  -- 联表查询
on 等值判断...
where ...-- 结果需满足的条件    
group by... -- 指定结果按照哪几个字段来分组
having.... -- 过滤分组后的记录必须满足的次要条件（过滤分组后的信息）
order by... -- 排序
limit ... -- 分页

```



## 4.1、指点查询字段

```sql
-- 查询全部的学生 select 字段 from 表
SELECT * FROM student

-- 查询指点字段
SELECT `studentno`,`studentname` FROM `student`

-- 别名 
SELECT `studentno` AS 学号 ,`studentname` AS 学生姓名 FROM `student`

-- 函数
SELECT CONCAT('姓名：',`studentname`)AS 新名字 FROM student
```



**语法：select   字段 1,字段2......  from   表**



> **去重：distinct**

```sql
-- 去重(distinct),重复的数据只显示一条
SELECT DISTINCT `studentno`FROM result
```



> **数据库的列**

```sql
SELECT 100+1 AS 计算结果 -- 用来计算（表达式）

SELECT @@auto_increment_increment -- 查看自增的步长（变量）

SELECT VERSION() -- 查询系统版本（函数）

SELECT `studentno`,`studentresult`+1 AS 提分后 FROM result -- 学员考试成绩+1分查看
```



==数据库中的表达式：文本值，列，null，函数，计算表达式，系统变量==

**select**   ==表达式==  **from**  表





## 4.2、where 条件

作用：检索数据中==符合条件==的数据

搜索的条件是由一个或多个表达式组成，结果返回布尔值

> 逻辑运算符

| 运算符      | 语法 | 描述   |
| ----------- | ---- | ------ |
| and      && |      | 短路与 |
| or     \|\| |      | 短路或 |
| not      !  |      | 逻辑非 |



> 模糊查询：比较运算符

| 运算符         | 语法                  | 描述                                          |
| -------------- | --------------------- | --------------------------------------------- |
| is null        | a  is  null           | 如果操作符为null，结果为真                    |
| is not  null   | b   is  not   null    | 如果操作符不为null，结果为真                  |
| between    and | A  between b  and  c  |                                               |
| like           | a  like b             | sql匹配  ，如果a匹配b，则结果为真             |
| in             | a  in  (a1,a2,a3....) | 假设a在a1,a2,a3....的其中某一个值中，结果为真 |

```sql
-- 模糊查询 
SELECT `studentno`,`studentresult`FROM result 
WHERE `studentresult` BETWEEN 95 AND 100


-- 查询姓赵的同学
-- like结合  %（代表0到任意一个字符）  _(代表一个字符)
SELECT `studentno`,`studentname`FROM student
WHERE `studentname`LIKE '赵%'

-- 查询姓张的同学,名字后面只有一个字
SELECT `studentno`,`studentname`FROM student
WHERE `studentname`LIKE'张_'


-- ======in(具体的一个或多个值)=====
-- 查询1000,1001号,1002学员
SELECT `studentno`,`studentname`FROM student
WHERE `studentno`IN(1000,1001,1002)

```





## 4.3、联表查询

- 左查询 LEFT JOIN  -- 以左表为基准
- 右查询 RIGHT JOIN  -- 以右表为基准
- 交叉查询 INNER JOIN -- 查询两表都有的数据



| **操作**   | ** 描述**                                  |
| ---------- | ------------------------------------------ |
| inner join | 如果两表都匹配, 就返回行，取交集           |
| left join  | 会从左表中返回所有的值, 即使右表中没有匹配 |
| right join | 会从右表中返回所有的值, 即使左表中没有匹配 |



```sql
-- 1. 查询参加了考试的同学 (查询列 : 学号, 姓名, 科目编号, 分数) -- 两表
-- 上述字段分别来自 student 表 (学号,姓名) 和 result 表 (科目编号,分数)
-- 因为需要查询参加考试的同学, 而学生表中有些学生是没有参加考试的, 所以以成绩表为基准, 所以使用右连接查询.
-- RIGHT JOIN
SELECT s.StudentNo,StudentName,SubjectNo,StudentResult
FROM `student` s
RIGHT JOIN `result` r
ON s.StudentNo = r.StudentNo;


-- 2.  查询缺考的同学 (学号, 姓名, 科目编号, 分数) -- 两表
-- 上述字段分别来自 student 表 (学号,姓名) 和 result 表 (科目编号, 分数)
-- 因为题目需要查询未参加考试的同学, 而成绩表中只有参加了考试的同学, 所以以左表为基准, 所以使用左连接查询.
-- LEFT JOIN
SELECT s.StudentNo,StudentName,SubjectNo,StudentResult
FROM `student` s
LEFT JOIN `result` r
ON s.StudentNo = r.StudentNo
WHERE StudentResult IS NULL;

-- 3. 查询参加考试的同学信息（学号, 学生姓名, 科目名称, 分数）-- 三表
/* 上述字段分别来自 student 表 (学号,姓名) ,result 表 (分数) 和 subject 表 (科目名称)
先查 student , rersult 两张表, 再连 subject 第三张表.
对于 student 和 result 表, 要查询参加考试的同学, 使用右连接, 而对于这两张表查询出来的结果和 subject 进行连表查询时, 没有以哪张表为基准, 所以使用 inner join 就可以了. */
SELECT s.StudentNo '学号',StudentName '姓名',
	SubjectName '科目名称',StudentResult '考试成绩'
FROM student s
RIGHT JOIN result r
ON s.StudentNo = r.StudentNo
INNER JOIN `subject` sub
ON sub.SubjectNo = r.SubjectNo;

```



整体查询思路 ： 

1. 我要查询哪些数据 -> SELECT ...

2. 需要从哪几张表中查数据 -> FROM 表 XXX Join 连接的表 on 交叉条件.

3. 假设存在多张表 (> 2) 的查询, 先连接两张表进行查询, 再慢慢增加其他表.





> **自连接**

```sql
-- 自连接
CREATE TABLE category(
   `categoryid` INT(10) NOT NULL   AUTO_INCREMENT  COMMENT '主题id',
   `pid` INT (10) NOT NULL COMMENT '父类id',
   `categoryname` VARCHAR(20)  NOT NULL COMMENT'主题名字',
   PRIMARY KEY (`categoryid`)
)ENGINE =INNODB DEFAULT CHARSET =utf8mb4

INSERT INTO `category`(`categoryid`,`pid`,`categoryname`)
VALUES(2,1,'信息技术'),
(3,1,'软件开发'),
(4,3,'数据库'),
(5,1,'美术设计'),
(6,3,'web开发'),
(7,5,'ps技术'),
(8,2,'办公信息')


SELECT a.categoryname  AS 父栏目,b.categoryname  AS 子栏目
FROM category AS a,
category AS b
WHERE a.categoryid=b.pid   -- 子类的父id=父类的id

```





| 父栏目   | 子栏目   |
| -------- | -------- |
| 软件开发 | 数据库   |
| 软件开发 | web开发  |
| 美术设计 | ps技术   |
| 信息技术 | 办公信息 |



## 4.4、分页和排序



>  **排序 ：**asc升序，desc降序
>
>  **分页：** limit 

```sql
-- 排序：升序asc ，降序 desc

SELECT s.`studentno`,`studentname`,`subjectname`,`studentresult`
FROM student s
RIGHT JOIN result r
ON s.`studentno`=r.`studentno`
INNER JOIN `subject` sub
ON r.`subjectno`=sub.`subjectno`
WHERE `subjectname`='数据库'
ORDER BY studentresult DESC

-- 分页：每页只显示五条数据
-- 语法：limit 起始，步长
SELECT s.`studentno`,`studentname`,`subjectname`,`studentresult`
FROM student s
RIGHT JOIN result r
ON s.`studentno`=r.`studentno`
INNER JOIN `subject` sub
ON r.`subjectno`=sub.`subjectno`
ORDER BY studentresult DESC
LIMIT 0,5  -- 第一条数据到第五条数据为第一页
```



## 4.5、子查询



本质：在where 语句中再嵌套一个select语句

```sql
-- 子查询 (由里及外)
SELECT `studentno`,r.`subjectno`,`studentresult`
FROM result r
WHERE `subjectno`=(
	SELECT `subjectno` 
	FROM `subject`
	WHERE `subjectname`='数据库'
)
ORDER BY `studentresult` ASC


-- 使用子查询 查询c语言前两名的学生的信息（学号，姓名，分数）
SELECT r.`studentno`,s.`studentname`,r.`studentresult`
FROM  student s,result r
WHERE `subjectno`=(
	SELECT `subjectno`
	FROM `subject`
	WHERE `subjectname`='c语言'
)AND r.`studentno`=s.`studentno`
ORDER BY `studentresult` ASC
LIMIT 0,2
```





## 4.6、分组和过滤

```sql
SELECT r.subjectno,`subjectname`,AVG(studentresult) 平均分,MAX(studentresult) 最高分,MIN(studentresult) 最低分
FROM result r
INNER JOIN `subject` sub
ON sub.subjectno=r.subjectno
GROUP BY r.subjectno -- 通过什么字段分组（如果不分组的话，平均分那些会将所有科目分数用来计算，而不是根据某个科目计算）
HAVING 平均分 >=80
```



==**GROUP BY**：==

- **目的**：将结果集按照一个或多个列进行分组。
- **使用场景**：当你需要对数据进行**聚合操作**，例如计算每个部门的员工数量或计算每个部门的平均薪水时，你会使用`GROUP BY`。

==**HAVING**：==

- **目的**：过滤分组后的结果集。与`WHERE`子句不同，`HAVING`是用于过滤分组后的数据。
- **使用场景**：当你需要基于聚合函数的结果（如COUNT、SUM、AVG等）来过滤分组数据时，你会使用`HAVING`。







# 5、MySQL函数



## 5.1、常用函数

```sql
-- ========常用函数========

-- 数学函数
SELECT ABS(-8) -- 绝对值
SELECT CEILING(9.4) -- 向上取整
SELECT FLOOR(9.4)-- 向下取整
SELECT SIGN(-2) -- 判断一个数的正负性 负数返回-1  0返回0  正数返回1


-- 字符串函数
SELECT CHAR_LENGTH('123456')-- 计算字符串长度
SELECT CONCAT('hello','world','java') -- 拼接字符串
SELECT INSERT('我爱编程helloworld',2,3,'大帅比')-- 查询，替换（第二个字符开始连续三个）
SELECT UPPER('lwx666') -- 小写转大写
SELECT LOWER('NIUBI') -- 大写转小写
SELECT INSTR('kaungshen','shen') -- 返回第一次出现的字串的位置
SELECT REPLACE('坚持就能成功','坚持','努力') -- 替换出现的字符串
SELECT SUBSTR('狂神说坚持就能成功啊',4,6) -- 返回指定的字符串
SELECT REVERSE('我喜欢你') -- 字符串反转

-- 查询，将姓赵的同学改为姓刘
SELECT REPLACE (`studentname`,'赵','刘')
FROM student 
WHERE `studentname`LIKE '赵%'

-- 时间和日期函数
SELECT CURRENT_DATE() -- 获取当前时间函数
SELECT CURDATE() -- 获取当前日期
SELECT NOW() -- 获取当前时间
SELECT LOCALTIME() -- 本地时间
SELECT YEAR(NOW())
SELECT MONTH(NOW())
SELECT DAY(NOW())

-- 系统
SELECT SYSTEM_USER()
SELECT USER()
SELECT VERSION()

```













## 5.2、聚合函数



```sql
SELECT COUNT(`studentname`)FROM student -- count(指定列),会忽略null
SELECT COUNT(*)FROM student -- count(*)，计算行数
SELECT COUNT(1)FROM student -- count(1)，计算行数

SELECT SUM(`studentresult`) AS 总和 FROM result  -- sum求和函数
SELECT AVG(`studentresult`) AS 平均分 FROM result -- avg求平均值函数
SELECT MAX(`studentresult`) AS 最高分 FROM result -- 求最大值函数
SELECT MIN(`studentresult`) AS 最低分 FROM result -- 求最小值函数


-- 计算每个科目的平均分，最高分，和最低分
SELECT r.subjectno,`subjectname`,AVG(studentresult) 平均分,MAX(studentresult) 最高分,MIN(studentresult) 最低分
FROM result r
INNER JOIN `subject` sub
ON sub.subjectno=r.subjectno
GROUP BY r.subjectno -- 通过什么字段分组
```









## 5.3、数据库级别的md5加密（扩展）

什么是md5？

主要增强算法复杂度和不可逆性。

md5不可逆 ，具体的值是和md5一样的

md5破解网站的原理，背后有一个字典，md5加密后的值，加密前的值

```sql
-- =======测试md5加密=======

CREATE TABLE testmd5(
    `id` INT(4) NOT NULL AUTO_INCREMENT  COMMENT '用户id',
    `name` VARCHAR(20) NOT NULL COMMENT'用户姓名',
    `psw` VARCHAR(50) NOT NULL COMMENT'用户密码',
    PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4

INSERT INTO testmd5
VALUES(1,'朱瞻基','123456'),
(2,'于谦','123456'),
(3,'樊忠','123456')

-- 加密
UPDATE testmd5 SET psw=MD5(psw) WHERE id=1

INSERT INTO testmd5 VALUES(4,'朱棣',MD5(123456))

```









# 6、事务



## 6.1、什么是事务

==要么都成功，要么都失败==

举个例子：

比如A用户给B用户转账100操作，过程如下：

从A账户扣100
给B账户加100
如果在事务的支持下，上面最终只有2种结果：

操作成功：A账户减少100；B账户增加100
操作失败：A、B两个账户都没有发生变化
如果没有事务的支持，可能出错：A账户减少了100，此时系统挂了，导致B账户没有加上100，而A账户凭空少了100。



## 6.2、事务ACID原则

> **==事务原则==**：ACID原则：原子性，一致性，隔离性，持久性  

参考博客链接：[事务的【ACID】四大原则 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/382569958)

**原子性（Atomicity）**

原子性是指一个事务是一个不可分割的工作单位，其中的操作要么都做，要么都不做

**一致性（Consistency）**

事务执行的前后都是合法的数据状态。

**隔离性（Isolation）**

隔离性是指，事务内部的操作与其他事务是隔离的，并发执行的各个事务之间不能互相干扰。

**持久性（Durability)**

持久性是指事务一旦提交，它对数据库的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。







## 6.3、脏读，不可重复读，幻读

>  若不保证事务的隔离性，则有可能会出现数据的【**脏读、不可重复读和幻读**】

1）**脏读**：当前事务(A)中可以读到其他事务(B)未提交的数据（脏数据），这种现象是脏读

举例如下（以账户余额表为例）：

![img](https://pic3.zhimg.com/80/v2-62447627d423da0f3def3c98280bcfea_1440w.webp)

（2）**不可重复读**：在事务A中先后两次读取同一个数据，两次读取的结果不一样，这种现象称为不可重复读

脏读与不可重复读的区别在于：前者读到的是其他事务未提交的数据，后者读到的是其他事务已提交的数据。举例如下：

![img](https://pic1.zhimg.com/80/v2-627a25296df794750a5e9a95136528c8_1440w.webp)

（3）**幻读**：在事务A中按照某个条件先后两次查询数据库，两次查询结果的条数不同，这种现象称为幻读

不可重复读与幻读的区别可以通俗的理解为：前者是数据变了，后者是数据的行数变了。举例如下：

![img](https://pic2.zhimg.com/80/v2-e19ec984395f37d5aad3e76c056d8e4d_1440w.webp)





## 6.4、模拟事务转账

```sql
- =======事务=======

-- mysql是默认开启事务自动提交的
SET autocommit =0 -- 关闭
SET autocommit =1 -- 开启

-- 手动处理事务

-- 事务开启(transaction)
START TRANSACTION -- 标记一个事务的开启，之后的sql操作都是属于一个事务

INSERT xx
INSERT xx

-- 提交 ：持久化（成功！）
COMMIT 
-- 回滚：回到原来的样子（失败！）
ROLLBACK

-- 事务结束
SET autocommit =1 -- 开启自动提交

-- 了解
SAVEPOINT 保存点名 -- 设置一个事务的保存点
ROLLBACK TO SAVEPOINT 保存点名 -- 回滚到保存点
RELEASE SAVEPOINT 保存点名 -- 撤销保存点


-- ============模拟转账========
CREATE DATABASE shop CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci

CREATE TABLE account (
   `id` INT(10) NOT NULL AUTO_INCREMENT ,
   `name`VARCHAR(30) NOT NULL ,
   `money` DECIMAL(10,2)  ,
   PRIMARY KEY(`id`)
  

)ENGINE =INNODB  CHARSET=utf8mb4


INSERT INTO account(`name`,money) VALUES('A',2000),('B',10000)

-- 模拟转账
SET autocommit=0 -- 关闭自动提交
START TRANSACTION -- 开启一个事务
UPDATE account SET money =money-500 WHERE `name`='A';
UPDATE account SET money =money+500 WHERE `name`='B';

COMMIT -- 提交事务
ROLLBACK -- 回滚

SET account =1 -- 恢复默认
```













# 7、索引

- 官方上面说索引是帮助MySQL高效获取数据的数据结构，通俗点的说**，数据库索引好比是一本书的目录**，可以直接根据页码找到对应的内容，目的就是为了加快数据库的查询速度。
- 索引是对数据库表中一列或多列的值进行排序的一种结构，使用索引可快速访问数据库表中的特定信息。
- 一种能帮助mysql提高了查询效率的数据结构：**索引数据结构**。
- **以空间换时间**



## 7.1、索引的分类

> 在一个表中，主键索引只能有一个，唯一索引可以有多个

* 主键索引（primary key）
  * 唯一的表示，主键不可重复，主键索引列值不能为空（Null）。
* 唯一索引（unique key）
  * 索引列的值必须唯一，但允许有空值（Null），但只允许有一个空值（Null）。
* 常规索引（key /index）
  * 默认的，index，key关键字来设置
* 全文索引（fulltext）
  * Full Text（MySQL5.7之前，只有MYISAM存储引擎引擎支持全文索引）。
  * 全文索引类型为FULLTEXT，在定义索引的列上支持值的全文查找允许在这些索引列中插入重复值和空值。全文索引可以在**Char、VarChar** 上创建。



基础语法

```sql
-- 索引的使用
-- 1、在创建表的时候给字段增加索引
-- 2、创建完毕后，添加索引
-- 显示索引信息
SHOW INDEX FROM student

-- 增加一个全文索引 (索引名) 列名
UPDATE TABLE `student` ADD FULLTEXT INDEX `studentname` (`studentname`)

-- 分析sql执行的状况
EXPLAIN SELECT * FROM student -- 非全文索引

EXPLAIN SELECT * FROM student WHERE MATCH(studentname) AGAINST('张')
```





## 7.2、测试索引

```sql
-- 插入一百万条数据
DELIMITER $$ -- 写函数之前必须要写，标志
CREATE FUNCTION mock_data1()
RETURNS INT DETERMINISTIC
BEGIN
      DECLARE num INT DEFAULT 1000000;	
      DECLARE i INT DEFAULT 0;
      
      WHILE i<num DO
	   INSERT INTO app_user(`name`,`eamil`,`phone`,`gender`,`password`,`age`)VALUES(CONCAT('用户',i),'3382286836@qq.com',18859081452,FLOOR(RAND()*2),UUID(),FLOOR(RAND()*100));
	   SET i=i+1;
      END WHILE;
      RETURN i;
END;


SELECT mock_data1(); -- 执行函数
EXPLAIN SELECT* FROM app_user WHERE id=999999

SELECT * FROM app_user WHERE `name` ='用户99999'

EXPLAIN SELECT * FROM app_user WHERE `name`='用户9999'

-- id_表名_字段名
-- create index 索引名 on 表（字段）
CREATE INDEX id_app_user_name ON app_user(`name`)

```



## 7.3、索引原则

* 索引不是越多越好
* 不要对进程变动数据加索引
* 小数据量的表不需要加索引
* 索引一般加在常用来查询的字段









# 8、权限管理和备份



## 8.1、用户管理

> sql 命令操作

用户表：mysql.user

本质：对这张表进行增删改查

```sql
-- 创建用户 create user 用户名 identified by 密码
CREATE USER kuangshen IDENTIFIED BY '123456'

-- 修改密码 
ALTER USER root@localhost IDENTIFIED BY '123456'

-- 重命名 rename 原来的名字 to 新的名字 
RENAME USER lwx@127.0.0.1 TO liuweixing

-- 用户授权 all privileges 全部的权限
GRANT ALL PRIVILEGES ON *.* TO liuweixing 

-- 查询权限
SHOW GRANTS FOR liuweixing
SHOW GRANTS FOR root@localhost

-- 撤销权限 
REVOKE ALL PRIVILEGES ON *.* FROM liuweixing

-- 删除用户
DROP USER liuweixing
```













## 8.2、MySQL备份













# 9、规范数据库设计



## 9.1、为什么需要设计

 

* 节省内存空间
* 保存数据库的完整性
* 方便我们开发系统

**设计数据库的步骤**（个人博客）

* 收集信息
  * 用户表
  * 分类表
  * 文章表
  * 友链表
  * 自定义表
* 标识实体 （把需求落地到每个字段）
* 标识实体之间的关系



## 9.2、三大范式



## 1. 简介

三大范式是 Mysql 数据库设计表结构所遵循的规范和指导方法，目的是为了减少冗余，建立结构合理的数据库，从而提高数据存储和使用的性能。

三大范式之间是具有依赖关系的，比如第二范式是在第一范式的基础上建设的、第三范式是在第二范式的基础上建设的。

当然 Mysql 数据库的范式不止三大范式，除了三大范式，还有巴斯-科德范式（BCNF）、第四范式(4NF）、第五范式（5NF，又称“完美范式"）。

而本篇文章，我们只介绍范式中常用的三大范式。

虽然，遵循范式能使我们的数据库结构更合理，但是也不是一成不变的，偶尔我们也要学会在范式的基础，根据实际应用场景，作出相应的变通。

## 2. 第一范式 - 1NF

遵循原子性。即，**表中字段的数据，不可以再拆分**。

先看一个不符合第一范式的表结构，如下：

| 员工编码 | 姓名       | 年龄 |
| -------- | ---------- | ---- |
| 001      | 销售部小张 | 28   |
| 002      | 运营部小黄 | 25   |
| 003      | 技术部小高 | 22   |

在这一个表中的，姓名 字段下的数据是可以再进行拆分的，因此它不符合第一范式，那怎么样才符合第一范式呢？如下：

| 员工编码 | 部门   | 姓名 | 年龄 |
| -------- | ------ | ---- | ---- |
| 001      | 销售部 | 小张 | 28   |
| 002      | 运营部 | 小黄 | 25   |
| 003      | 技术部 | 小高 | 22   |

那是否遵循第一范式就一定是好的呢？如下：

| 员工编码 | 姓名 | 地址               |
| -------- | ---- | ------------------ |
| 001      | 小张 | 江西省南昌市东湖区 |
| 002      | 小黄 | 广东省佛山市禅城区 |
| 003      | 小高 | 湖北省武汉市新洲区 |

通过观察上述表结构，我们发现，地址是可以再进一步拆分的，比如：

| 员工编码 | 姓名 | 省     | 市     | 区     |
| -------- | ---- | ------ | ------ | ------ |
| 001      | 小张 | 江西省 | 南昌市 | 东湖区 |
| 002      | 小黄 | 广东省 | 佛山市 | 禅城区 |
| 003      | 小高 | 湖北省 | 武汉市 | 新洲区 |

虽然拆分后，看上去更符合第一范式了，但是如果项目就只需要我们输出一个完整地址呢？那明显是表在没拆分的时候会更好用。

所以范式只是给了我们一个参考，我们更多的是要根据项目实际情况设计表结构。

## 3. 第二范式 - 2NF

在满足第一范式的情况下，遵循唯一性，消除部分依赖。即，**表中任意一个主键或任意一组联合主键，可以确定除该主键外的所有的非主键值。**

再通俗点讲就是，**一个表只能描述一件事情**。

我们用一个经典案例进行解析。

| 学号 | 姓名 | 年龄 | 课程名称 | 成绩 | 学分 |
| ---- | ---- | ---- | -------- | ---- | ---- |
| 001  | 小张 | 28   | 语文     | 90   | 3    |
| 001  | 小张 | 28   | 数学     | 90   | 2    |
| 002  | 小黄 | 25   | 语文     | 90   | 3    |
| 002  | 小黄 | 25   | 语文     | 90   | 3    |
| 003  | 小高 | 22   | 数学     | 90   | 2    |

我们先分析一下表结构。

\1. 假设学号是表中的唯一主键，那由学号就可以确定姓名和年龄了，但是却不能确定课程名称和成绩。

\2. 假设课程名称是表中的唯一主键，那由课程名称就可以确定学分了，但是却不能确定姓名、年龄和成绩。

\3. 虽然通过学号和课程名称的联合主键，可以确定除联合主键外的所有的非主键值，但是基于上述两个假设，也不符合第二范式的要求。



那我们应该**如何调整表结构**，让它能复合第二范式的要求呢？

我们可以**基于上述的三种主键的可能，拆分成 3 张表，保证一张表只描述一件事情**。

\1. 学生表 - 学号做主键

| 学号 | 姓名 | 年龄 |
| ---- | ---- | ---- |
| 001  | 小张 | 28   |
| 002  | 小黄 | 25   |
| 003  | 小高 | 22   |

\2. 课程表 - 课程名称做主键

| 课程名称 | 学分 |
| -------- | ---- |
| 语文     | 3    |
| 数学     | 2    |

\3. 成绩表 - 学号和课程名称做联合主键

| 学号 | 课程名称 | 成绩 |
| ---- | -------- | ---- |
| 001  | 语文     | 90   |
| 001  | 数学     | 90   |
| 002  | 语文     | 90   |
| 002  | 语文     | 90   |
| 003  | 数学     | 90   |



这时候我们可能会想，为什么我们就要遵循第二范式呢？**不遵循第二范式会造成什么样的后果呢**？

\1. 造成整表的数据冗余。

如，学生表，可能我就只有2个学生，每个学生都有许多的信息，比如，年龄、性别、身高、住址......如果与课程信息放到同一张表中，可能每个学生有3门课程，那数据总条数就会变成6条了。但是通过拆分，学生表我们只需要存储 2 条学生信息，课程表只需要存储 3 条课程信息，成绩表就只需保留学号、课程名称和成绩字段。

\2. 更新数据不方便。

假设，课程的学分发生了变更，那我们就需要把整表关于该课程的学分都要更新一次，但如果我们拆分出课程表，那我们就只需要把课程表中的课程信息更新就行。

\3. 插入数据不方便或产生异常。

① 假设主键是学号或课程名称，我们新增了某个课程，需要把数据插入到表中，这时，可能只有部分人有选修这门课程，那我们插入数据的时候还要规定给哪些人插入对应的课程信息，同时可能由于成绩还没有，我们需要对成绩置空，后续有成绩后还得重新更新一遍。

② 假设主键是学号和课程名称的联合主键。同样也是新增了某课程，但是暂时没有人选修这门课，缺少了学号主键字段数据，会导致课程信息无法插入。

## 4. 第三范式 - 3NF

在满足第二范式的情况下，消除传递依赖。即，**在任一主键都可以确定所有非主键字段值的情况下，不能存在某非主键字段 A 可以获取 某非主键字段 B**。

仍然用一个经典例子来解析

| 学号 | 姓名 | 班级          | 班主任 |
| ---- | ---- | ------------- | ------ |
| 001  | 小黄 | 一年级（1）班 | 高老师 |

这个表中，学号是主键，它可以唯一确定姓名、班级、班主任，符合了第二范式，但是在非主键字段中，我们也可以通过班级推导出该班级的班主任，所以它是不符合第三范式的。

那怎么设计表结构，才是符合第三范式的呢？

\1. 学生表

| 学号 | 姓名 | 班级          |
| ---- | ---- | ------------- |
| 001  | 小黄 | 一年级（1）班 |

\2. 班级表

| 班级          | 班主任 |
| ------------- | ------ |
| 一年级（1）班 | 高老师 |

通过把班级与班主任的映射关系另外做成一张映射表，我们就成功地消除了表中的传递依赖了。

## 总结

不知道读者们有没有发现，以上所介绍的范式的最终目的都是为了减少我们的工作量呢？所以说，尽管范式是一种很好的指导规范，但在实际应用中，我们也不需要太局限在范式中，更多的是应该从项目中出发，设计出合理的表结构。

以下是本篇三范式的简单总结：

- 第一范式（1 NF）：字段不可再拆分。
- 第二范式（2 NF）：表中任意一个主键或任意一组联合主键，可以确定除该主键外的所有的非主键值。
- 第三范式（3 NF）：在任一主键都可以确定所有非主键字段值的情况下，不能存在某非主键字段 A 可以获取 某非主键字段 B。







# 10、JDBC



## 10.1 什么是JDBC

#### 	JDBC的全称是Java数据库连接(Java Database connect)，它是一套用于执行SQL语句的Java API。应用程序可通过这套API连接到关系数据库，并使用SQL语句来完成对数据库中数据的查询、更新和删除等操作。





## 10.2、JDBC连接数据库步骤

### 1、导入jar包

 	1.创建lib目录（package）

​     2.将jar包粘贴到 lib目录  

​	 3.右键点击jar包 add as liabray 然后ok

![image-20240306113501227](C:\Users\33822\AppData\Roaming\Typora\typora-user-images\image-20240306113501227.png)









2、写代码

```java
public class JDBCDemo {
    public static void main(String[] args) throws Exception{ //抛出异常
        //1.注册驱动(mysql 5.0后的版本可不用这一步)
        Class.forName("com.mysql.cj.jdbc.Driver") ;

        //2.获取连接
        String url = "jdbc:mysql://localhost:3306/school"; //连接路径
        String username="root"; 
        String password="123456";
        Connection conn = DriverManager.getConnection(url , username , password ) ;

        //3.定义sql语句
        String sql="UPDATE student SET `name` = \"coke\" WHERE id=1";

        //4.获取执行sql对象 Statement
        Statement stmt =conn.createStatement();

        //5.执行sql
        int count=stmt.executeUpdate(sql);//返回一个影响的行数

        //6处理结果
        System.out.println(count);

        //7.释放资源 先开后释放
        stmt.close();
        conn.close();
    }
}
```









## 10.3、DriverManger



* DriverManger（驱动管理类）作用

​			1.注册驱动

​			2.获取数据库连接（getConnection方法）



* 获取连接

参数：

1.url：连接路径

==语法：== jdbc : mysql :// ip地址 :端口号/数据库名称?参数键值对1&参数键值对2....

==实例：==String url = "jdbc:mysql://localhost:3306/school"; //连接路径

==细节：==  1. 如果连接是本机mysql服务器，并且mysql服务器默认是3306，则url可以简写为：jdbc::mysql:///数据库名称?参数键值对

​			  2.配置useSSL =false 参数，禁用安全连接方式，解决安全警告 String url = "jdbc:mysql://localhost:3306/school？useSSL=false"

2.user：用户名

3.password：密码





## 10.4、Connection 

**Connecttion（数据库连接对象）作用：**

>  ==1、获取执行sql的对象==

* 普通执行sql对象

```java
Statement  stmt=  conn.createStatement(sql);
```



* 预编译sql的执行对象：防止sql注入

```sql
PreparedStatement pstmt= conn.preparStatement(sql);
```



>  ==2、管理事务==

```java
开启事务：conn.setAutoCommit(boolean autoCommit) //true 为自动提交事务  false 为手动提交事务
提交事务:conn.commit()
回滚事务:conn.rollback();
try {
            conn.setAutoCommit(false); // 开启手动提交事务
            //5.执行sql
            int count1=stmt.executeUpdate(sql1);//返回一个影响的行数
            System.out.println(count1);
    
            int a=3/0;
    
            int count2=stmt.executeUpdate(sql2);//返回一个影响的行数
            System.out.println(count2);

            conn.commit();//提交事务
        } catch (Exception e) {
            conn.rollback(); //回滚事务
            throw new RuntimeException(e);
        }
```













## 10.5、Statement

```java
Statement
* 作用：执行sql语句
* int executeUpdate(sql) :执行DML(数据)、DDL（表）语句
* 返回值：（1）DML语句影响的行数 (2)DDL语句执行后，执行成功也可能是0
*
* ResultSet executeQuery(sql):执行DQL语句
* 返回值：ResultSet结果集对象
```







## 10.6、ResultSet

**Result（结果集对象）**

作用：1、封装了DQL查询语句的结果

​			2、获取查询结果

```java
boolean next() /*1.将光标从当前位置移动一行 2.判断当前行是否有数据
返回值：
true：有效行，当前行有数据
false：无效行，当前行没有数据
    
xxx  getXxx（参数）：获取数据
    xxx：数据类型 如：int getInt（参数）
    参数：
        int：列的编号，从1开始
    	String：列的名称
 */   
    
    String sql = "select * from student";

        //4.获取执行sql对象 Statement
        Statement stmt = conn.createStatement();

        //5. 执行sql
        ResultSet rs= stmt.executeQuery(sql);

        //6. 处理结果，遍历rs中所有的数据
        //6.1 光标向下移动一行，然后判断是否有数据
        while (rs.next()) {
            //6.2 获取数据
            int id=rs.getInt("id");  // int id=rs.getInt(1);
            String name=rs.getString("name");// String name=rs.getString(2);
            String psw =rs.getString("psw");//String psw =rs.getString(3);

            System.out.println(id);
            System.out.println(name);
            System.out.println(psw);

            System.out.println("--------------");
        }


        //7. 释放资源
        rs.close();
        stmt.close();
        conn.close(); 
```







## 10.7、PreparedStatement

**sql注入**

```java
       //演示sql注入
        String name="dfjeiwe9ufu9";
        String psw="'or '1'= '1";// and `psw` ='' or '1'= '1'

        String sql="select * from student where `name`='"+name+"'and `psw` ='"+psw+"' ";//在SQL查询中，字符串值必须被单引号（'）包围。
        System.out.println(sql);//select * from student where `name`='dfjeiwe9ufu9'and `psw` =''or '1'= '1' 
```





**PreparedStatement**

作用：预编译sql并执行sql语句

```java
//1.获取PreparedStatement对象
String sql= "select * from user where username=? and password = ?"//参数用？占位符代替
PreparedStatement pstmt =conn.prepareStatement(sql);

//2.设置参数值
PreparedStatement.setXxx(参数1，参数2);//给？赋值
//Xxx : 数据类型;setInt(参数1，参数2)
//参数1：？的位置编号
//参数2：？的值

//3.执行sql
pstmt.executeUpdate; pstmt.executeQuery; //不需要再传递sql

//举例
  String sql="select * from student where `name`=? and `psw` =?";
        System.out.println(sql);
        // 1.创建PreparedStatement（pstmt）对象
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        // 2.传递参数
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,psw);

        // 3.执行sql
        ResultSet rs=preparedStatement.executeQuery();

```

