# mysql初见面

## 注意事项

1. 在操作系统中一旦换为mysql那么windows下的命令就无法执行，比如net start mysql
2. mysql的命令需要；作为结尾
3. sc delete mysql,干掉mysql

在cmd里写命令行很痛苦，所以要安装一个可视化的软件

## 基本命令行操作

只需要了解

1. 连接数据库前要先开启服务器噢net start mysql这是在Windows下的命令不需要封号

2. 连接数据库mysql -u root -p;,输入密码。记得封号噢

3. sql注释--，多行/**/，在Navicat里也可以使用#来单行注释

4. 修改密码update mysql.user set巴拉巴拉，这里就说明了我们的密码是在这个user表下的（user在navicat里是关键字，要不出错最好使用``来框起来），我们查询这个表后会发现密码是乱码，这是加密过的密码，很少使用

5. 刷新权限，flush privileges在做了一些操作的时候需要用到，平时一般不用

6. 查看全部数据库，我们对数据库的操作大部分都花在查询，show datebases；mysql语句是大小写不敏感的不用担心大小写问题

7. 使用数据库，use 数据库名；出现datebase changed说明切换成功

8. 查看表，show tables;查看数据库中所有的表

9. 查看表信息describe（desc） 表名;实在打错了ctrl加c强制退出这一步，重写这一步就可以了

10. 创建表 create datebase 数据库名；有时候需要刷新才能出现我们创建的表不要慌

    ```sql
    CREATE DATABASE shop CHARACTER set utf8 COLLATE utf8_general_ci
    ```

    具体语句看这里

11. exit；退出连接返回windows

我们使用的sql语言主要是以下几种

Datebase xxx Language

DDl，DML，DQL，DCL

DDL数据库定义语言；define

DML数据库操作语言；manage

DQL数据库查询语言；question

DCL数据库控制语言；control

## 操作数据库(了解)

在可视化软件中，创建同名的数据库会报错，我们可以添加一个

create datebase if not exists 数据库名，增加一个if判断

drop datebase if exists 数据库名，移除一个数据库

show datebases查看数据库，或者某个数据库

我们可以对照历史记录来学习里面的代码

### 列的数据类型

>数值

tinyint 1字节，一般不会使用，不需要这么省内存

smallint 2个字节

mediumint 3个字节

int 4个字节一般用这个

bigint 8个字节

float 4个字节

double 8个字节

decimal 字符串形式的浮点数，精确

> 字符串

char 0~255一般不用

varchar 0~65535可变字符串，常用变量

tinytest 微型文本2^8-1

test 文本串2^16-1 保存大文本

> 时间

date YYYY-MM-DD 日期

time HH-mm-ss 时间

datetime YYYY-MM-DD-HH-mm-ss最常用的时间格式

timestamp 时间戳， 1970.1.1到现在的毫秒数！全国统一，较为常用

year 年份表示

> null

没有值，未知，

==***注意不要使用null进行运算，结果一定为null***==

### 数据库的字段属性（重点）

主键？自增 非空 zerofill？Unsigned无符号的

Unsigned：

- 无符号的整数
- 声明了该列不能声明为负数，year不能点，因为year本来就不能填负数没有点的必要

zerofill：

- 0填充
- 我使用了10位长度，那它就会让不足的位数使用0填充
- 我使用了三个长度的int，就会变成005

自增：

- 通常理解为自增，自增后就不能添加相同的数据了，自动在上一条基础上加以
- 通常用来设计唯一的主键，必须是整数类型
- 可以自定义设置主键自增的起始值和步长

这里有一个细节在非空数据中我只要点了，不是（null）会变成字符串""就没用，即使我们复制旁边的null也不会恢复为空

在制表里面会有几个必须要有的字段

id主键，``version``，乐观锁，is_delete伪删除，我们查数据时我们不希望这个数据被真正的删掉我们就用了一个标志位来判断，假设这个地方为1，就默认被删掉了，但管理员可以查看被删掉的东西，gmt_creat创建时间，gmt_update修改时间，每一个表都必须存在以下五个字段，先了解之后会遇到

int类型的后面的括号指的是宽度，和实际存储大小无关，和零填充有关，但是varchar就有关系了

### 创建数据库表

在建表的末尾使用英文括号，里面写我们的代码，在建表的时候，表的名称和字段，尽量使用``括起来

CREATE TABLE IF NOT EXISTS `student`(
`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
`NAME` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
`pwd` VARCHAR(20) not null DEFAULT '123456' COMMENT '密码',
`sex` VARCHAR(2) NOT null DEFAULT '男' COMMENT '性别',
`birthday` datetime DEFAULT NULL COMMENT '出生日期',
`address` VARCHAR(100) DEFAULT null COMMENT '家庭住址',
`email` VARCHAR(18) DEFAULT NULL COMMENT '邮箱',
PRIMARY KEY(`id`)

)ENGINE=INNODB DEFAULT CHARSET=utf8

```sql
格式create table [if not exists] `表名`(
  `字段名` 列类型 [属性] [索引] [注释],
)[表的类型] [字符集设置] [注释]
```

SHOW CREATE TABLE student 查看创建表的语句

DESC student 查看表的结构

### 数据库引擎

数据表的类型
INNODB这是默认使用的

MYISAM早些年使用的

这两个有什么区别呢

|                                                              | MYISAM | INNODB                  |
| ------------------------------------------------------------ | ------ | ----------------------- |
| 事务支持（）sql要么同时成功要么同时失败否则提交不了          | 不支持 | 支持                    |
| 数据行锁定，两条sql插入一个表，会把表先锁住，INNODB就锁一行效率更高 | 不支持 | 支持                    |
| 外键约束，一张表能不能在数据库级别关联另外一张表             | 不支持 | 支持                    |
| 全文索引，在大文章找某个字段，比如百度搜索                   | 支持   | 不支持                  |
| 表空间的大小                                                 | 比较小 | 比较大，约为MYISAMd两倍 |

常规使用操作：

- MYISAM节约空间速度较快
- INNODB 安全性高，事务处理，多表多用户操作

> 在物理空间存在的位置

所有的数据库文件都存在date目录下，一个文件夹就对应一个数据库

本质还是文件的存储！

MYSQL引擎在物理文件的区别

- INNODB在数据库表中只有一个*.frm文件，已经上级目录下的ibdata文件1
- MYISAM对应文件

*.frm-表结构的定义文件

*.MYD 数据文件（data）

*.MYI 索引文件（index）

> 设置数据库表的字符集编码

```sql
CHARSET=utf8
```

不设置的话，会是mysql的默认的字符集编码Latin1，不支持中文

在my.ini中配置默认的编码，已经配置

但是这样虽然方便，但是会降低通用性，本来我和你的代码都能执行，但我现在把物理文件改了，在我这边能跑在你那边就不一定能跑，所以建议在建表时都选上

### 修改删除表

ALTER TABLE teacher RENAME AS teacher1 替换表名
ALTER TABLE teacher1 ADD `age` int(11) 增加数据
ALTER TABLE teacher1 MODIFY age VARCHAR(11)改变约束
ALTER TABLE teacher1 CHANGE age age1 VARCHAR(11)重命名
ALTER TABLE teacher1 DROP age1删除字段

所有的创建和删除操作尽量加上判断，以免报错

sql大小写不敏感建议使用小写

## mysql的数据管理（重点）

### 外键（了解即可）

> 方式1在创建表的时候，增加约束（麻烦比较复杂）

约束constraint，不建议这样玩，先了解一下

```sql
KEY `FK_gradeid` (`gradeid`),
CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)
```

创建一个值是gradeid的键为`FK_gradeid`（外键默认形式）

约束这个外键的类型是外键，这个外键引用grade表中的内容

被引用的表不可被删除，要删除这个表（主表）一定要先删除（从表）

> 我们先正常创建表，是没有外键关系的

```sql
ALTER TABLE `student` ADD CONSISTENT `FK_gradeid` FOREIGN KEY(`gradeid`) REFERENCES `grade`(`gradeid`)
```

建表的时候不要有约束，在想加外键的时候再加

ADD CONSTRAINT 约束名 FOREIGN KEY(`作为外键的列`) REFERENCES 哪个表（那个字段）不用记忆，手过一遍

以上的操作都是物理外键，数据库级别的外键，我们不建议使用，建表的时候很麻烦想删都删不掉，会很乱，避免数据库过多，造成困扰，了解即可

最佳实践

- 数据库就是单纯的表，只用来存数据，只有行和列
- 我们想使用多张表的数据，想使用外键，用程序去实现

为什么不使用外键在阿里巴巴的Java规范里有下面这一条

不得使用外键与级联，一切外键概念必须在应用层解决

使用外键真的很痛苦啊

### DML语言（全部记住）

数据库存在的意义：数据存储，数据管理

DML语言：数据操作语言

#### 添加insert

```sql
INSERT INTO `student`(`NAME`,`pwd`) VALUES('张三','aaaaa'),('李四','85656')

```

INSERT INTO 表名（[字段名1，字段2，字段3]）values（值1），（值2），（值3）

由于主键自增我们可以省略

如果不写表的字段，就会11匹配，一般不这么写，一般写插入语句一定要数据和字段一一对应，关系不匹配会报错的,除非你要全部一行都填，一个一个对应下去填

我们可以使用多个括号来一次插入多个数组一样的（在一个或者多个字段插入一个或多个数据）

#### 修改update

修改数据需要知道修改谁（条件），设置值就是set原来的值等于新值

```sql
UPDATE `student` SET `name`='huayu' WHERE id=1
```

不指定条件的情况下就会改变所有的表

UPDATE 表名 SET colunm_name = value where 条件[]

我想改多个也一样，在colunm_name = value后逗号再继续添加新的colunm_name = value

```sql
UPDATE `student` SET `name`='huayu',`email`='huayu@qq.com' WHERE id=1
```

重点要来看下条件：where字句 运算符id等于某个值，大于某个值，在某个区间内修改

| 操作符（返回布尔值） | 含义         | 范围        | 结果  |
| -------------------- | ------------ | ----------- | ----- |
| =                    | 等于         | 5=6         | false |
| <>或!=               | 不等于       | 5<>6        | true  |
| >                    |              |             |       |
| <                    |              |             |       |
| >=                   |              |             |       |
| <=                   |              |             |       |
| BETWEEN a AND b      | 在某个范围内 | [2,5]       |       |
| AND                  | 我和你&&     | 5>1 and 1>2 | false |
| OR                   | 我或你\|\|   | 5>1 or 1<2  | true  |

通过多个条件定位数据and or比如名字都一样，使用名字加id加性别等等等，无上限

注意

- colunm_name是数据库的列，尽量带上``
- 条件，筛选的条件，如果没有指定，则会修改所有的列
- value，是一个具体的值，也可以是一个变量，比如使用current_time就会变成现在的时间，一直刷新就会一直变，这个变量用的不多只有时间会用
- 多个设置的属性直接使用英文逗号隔开，这个在未来会比较麻烦，在Java写十个条件，就要使用循环，但是字符串很烦，这样子拼接，由于逗号总数比我们变量的数量少一个，我们就需要先循环拼接一个逗号加一个变量，最后删掉一个逗号，这就是trim标签，可以把多余的逗号干掉

#### 删除delete

> 语法:delete from 表名 [where()]

避免不写where会全部删除就寄了，要完全清理也不要用这个，不会影响自增，用下面的这个

> TRUNCATE命令

作用：完全清空一个数据库表，表的结构和索引不会变

```sql
TRUNCATE `表名`
```

那这两个有什么区别呢，都能删除数据，都不会删除表结构

不同：

- TRANCATE 重新设置自增列 计数器会归零
- 不会影响事务

了解即可：DELETE删除的问题，重启数据库，现象

- INNODB自增列会从一开始（存在内存当中的，没有持久化的话，断电即失）
- MYISAM 继续从上一个自增量开始（存在文件中，不会丢失）

### DQL查询数据（最重点）

#### DQL

（Date qurey LANGUAGE:数据查询语言）

- 所有的查询操作都用它 Select
- 简单的查询，复杂的查询它都能做
- ***数据库中最核心的语言，最重要的语句***
- 使用频率最高的语句

#### 指定查询字段

```sql
SELECT * from student
```

SELECT 字段 From 表

*是通配符表示所有字段

```sql
SELECT `studentNO`,`studentname` From student--查询多个
```

正常我们看到的表头肯定不是这么复杂的，为了做到见名知意，我们可以使用别名，给结果起一个名字，使用AS语句，后面不用''括起来了,AS还可以省略

```sql
SELECT `studentNO` AS 学号,`studentname` AS 学生姓名 From student
```

我们也可以给字段和表起别名

mysql里有很多函数我们也可以使用比如

concat（a,b）合并字符串

```sql
SELECT CONCAT('姓名：',studentname) AS 新名字 from student
新名字
姓名：张伟
姓名：赵强
```

上面都是简单查询都没设计到两张表直接的查询，也没涉及到关联查询，甚至没涉及排序分页

---

#### 去重distinct

比如在考试结果的一列，一个同学可以参加很多场考试，这么多场考试，这个同学的学号都一样，那么这时候我想知道有几个学生参加了考试，就需要使用去重

```sql
SELECT DISTINCT `studentno` AS 学号 FROM result
```

是一个比较重要的关键字

##### 杂项

SELECT VERSION()查询版本号（函数）

SELECT 100*3-1 AS 计算结果还可以用于计算（表达式）

SELECT @@auto_increment_increment As 步长 查询步长（变量）

SELECT `studentresult`+1 AS 成绩 FROM `result`加一分

#### Where条件字句

一个查询只能用一个where，否则用and去拼

作用：检索数据中符合条件的值

搜索的条件由一个或多个组成，返回值基本为布尔值

> 逻辑运算符

| 运算符  | 语法          | 描述   |
| ------- | ------------- | ------ |
| and &&  | a and b a&&b  | 逻辑与 |
| or \|\| | a or b a\|\|b | 逻辑或 |
| Not !   | not a  ! a    | 逻辑非 |

尽量使用英文字母来表示，可读性高

##### 模糊查询

> 本质是比较运算符

| 运算符      | 语法               | 描述                            |
| ----------- | ------------------ | ------------------------------- |
| is NUll     | a is Null          |                                 |
| is not NUll | a is not Null      |                                 |
| between     | a between and c    |                                 |
| ***Like***  | a like b           | sql匹配，如果a匹配到b，结果为真 |
| IN          | a in (a1,a2,a3...) | 假设a在（a1，a2,a3）结果为真    |

小明 like `小明`

我现在想查姓刘的同学，like结合 %（代表0到任意个字符）_(一个字符)

```sql
SELECT `studentno`,`studentName` FROM `student` WHERE studentname LIKE '张%'
```

百分号通配符只能在like里使用，in里要是一个具体的值

#### 联表查询

JOIN 对比

我现在要根据名字查一个学生的成绩，这个两个数据在两个表里，我该怎么查呢

思路：分析需求，分析查询的字段来自哪些表（连接查询）

2确定使用哪种连接查询 7种

left join被插入表的所有数据，和插入表与之对应的数据，没有的话就是null，结果是左表值与null

inner join只要被插入表和插入表一个没有与之对应的数据，结果就没有这一行

right join和左查询相反

确定交叉点，这两个表中哪个数据是相同的，

判断的条件，学生表中的studentno=成绩表studentNO

```sql
SELECT studentno,studentname,SUBJECTNO,studentResult
FROM student
```

直接这样输入的查询不到的，因为student表里没有，subjectno，所以我们要去另外的表查

```sq
INNER JOIN result
```

所以我们就在下面加入了result表

现在又有问题，两张表都有studentno 我该用哪张的我怎么知道，这时候通常的办法就是给表取别名，使用表名.属性来表示

```sql
SELECT s.studentno,studentname,SUBJECTNO,studentResult
FROM student as s
INNER JOIN result as r
WHERE s.studentno = r.studentno
```

变成这样，这样就会另这两个studentsno相等的时候，连接这两个表，就达到了合并的效果

---

ON和WHERE是用于筛选和连接表的条件语句，但它们在使用时有一些区别。

1. ON语句：

- ON语句用于连接两个或多个表，并指定连接条件。它通常用在JOIN操作中。
- ON语句在连接过程中筛选出满足连接条件的记录，将它们合并为一个结果集。
- ON语句的条件是在连接过程中执行的，它只影响连接的结果，不会影响到最终的查询结果。

示例：

```sql
SELECT Customers.CustomerName, Orders.OrderDate
FROM Customers
JOIN Orders ON Customers.CustomerID = Orders.CustomerID;
```

1. WHERE语句：

- WHERE语句用于在查询中筛选满足条件的记录。
- WHERE语句在查询结果集中筛选出满足条件的记录，将它们返回给用户。
- WHERE语句的条件是在查询过程中执行的，它会影响到最终的查询结果。

示例：

```sql
SELECT CustomerName, OrderDate
FROM Customers, Orders
WHERE Customers.CustomerID = Orders.CustomerID;
```

总结：

- ON语句用于连接两个或多个表，并指定连接条件，它在连接过程中起作用。
- WHERE语句用于在查询中筛选满足条件的记录，它在查询结果集中起作用。

需要注意的是，ON语句和WHERE语句可以一起使用，以更精确地筛选和连接表。在使用时，应根据具体的业务需求和数据关系来选择合适的条件语句。

以后可能就明白了

---

利用leftjoin rightjoin，我们就可以做到查询缺考的学生

join on 连接查询

where 等值查询

```sql
SELECT s.studentno,studentname,r.SUBJECTNO,studentResult,subjectname
FROM student s 
RIGHT JOIN result r
on s.studentno=r.studentno
INNER JOIN `SUBJECT` sub
ON r.subjectno=sub.subjectno
```

连接三张表查询

核心思路：我要查询哪些数据 select...

从哪几个表中查 from 表 xxx join 连接的表 on 交叉条件

假设存在一种多张表查询，慢慢来，先查询两张表然后再慢慢增加

##### 自连接（了解）

***自连接就是自己的表和自己的表连接，核心：一张表拆为两种一样的表即可***

原来的表：

pid   categoryName	categoryid

1		信息技术	 				2
1		软件开发					 3
3		数据库	    				4
1		美术设计	 				 5
3		web开发	 				 6
5		ps技术	   				7
2		办公信息						8

拆分成父类子类表

父类表

| categoryid | categoryName |
| ---------- | ------------ |
| 2          | 信息技术     |
| 3          | 软件开发     |
| 5          | 美术设计     |
|            |              |

子类

| pid  | categoryid | categoryName |
| ---- | ---------- | ------------ |
| 3    | 4          | 数据库       |
| 2    | 8          | 办公信息     |
| 3    | 6          | web开发      |
| 5    | 7          | ps技术       |

我们要联表查询只要子类的父id等于父类的categoryid就连起来了

操作，我们要查询父类对应的子类关系

| 父类     | 子类     |
| -------- | -------- |
| 信息技术 | 办公信息 |
| 软件开发 | 数据库   |
| 软件开发 | web开发  |
| 美术设计 | ps技术   |

我们想查询出来是这样，该怎么查呢

父子类的字段是一样的，所以我们查询的时候一定会有一个别名

```sql
select a.categoryname as '父栏目',`b.categroyName` as '子栏目'
from `category` a,`category` b
WHERE a.categoryid=b.pid
```

一张表当成两张来查

#### 分页和排序

limit 和 ORDER by

1. where
2. group by
3. Having
4. order by
5. Limit

这五个的顺序不能乱换，不能把limit写再order by上面有严格的语法要求

---

排序升序ASC和降序DESC

查询出来的年纪没有排序，我们要根据年纪的id来升序降序排序

语法通过哪个字段排序，怎么排序

```sql
SELECT subjectname,gradename
FROM `SUBJECT` s
join grade g
on g.gradeid=s.gradeid
ORDER BY s.gradeid desc 
--升序就用ASC
```

---

分页比如我查了一百万条数据，不能一下就显示在网页上，可以缓解数据库压力，你要第几页我再查，还可以给人一个好的体验，也有一些东西可以无限往下刷瀑布流，一拉就是无限加载，判断浏览器的进度条，比较暴力，一般图片才会使用瀑布流

现在我要分页，每页只显示三条数据

语法limit 起始值，页面大小，页面起始数据都是0，我们输入1，3就可以得到数据分成3条一页后的第一条数据到第四条数据，超出部分不会显示，会是空，这个值第一个是0要注意

但这不是我们想要的啊，网页应用当前，总页数，页面的大小。这才是我们想要的啊

第n页 （n-1）*pagesize，pagesize

总页数等于数据总数除以页面大小

#### 子查询

我们之前所有判断的条件都是再where里面值都是一个区间，都是固定的，我想让这个值是计算出来的就是子查询

本质：在where语句中嵌套一个子查询语句

where（select*from）比如我们想查询数据库结构1的所有考试结果（学号科目编号成绩），降序排列

> 方式1使用连接查询

```sql
SELECT s.studentno,sub.subjectno,studentresult
FROM student s 
join result r 
ON s.studentno=r.studentno
JOIN `SUBJECT` sub 
ON sub.subjectno=r.subjectno
Where sub.subjectname='高等数学-1'
ORDER BY studentresult DESC

```

> 方式2使用子查询

我们要查的这堆信息确实在result表里也有，我们确实可以不用合并，但是我们有一个判断条件，就是查询数据结果1的所有考试结果，我们就可以使用子查询来完成

```sql
SELECT studentno,subjectno,studentresult
FROM result
WHERE subjectno = (SELECT subjectno FROM `SUBJECT` WHERE subjectNAME='高等数学-1')
```

这个子查询也挺坑的查询的结果只能一行对应一行，如果一个人考了多个科目，我在子查询里查成绩大于八十的人就会报错，因为对应了多个结果行，一个子查询只能对应一个具体的结果，如果我们要查的不是一个具体的值，就不建议使用子查询（一对多情况不建议使用），好吧如果在一对多的情况下可以使用in来解决问题

子查询一环套一环，也可以达到和联表查询相似的效果，但是在面对需要左右联表的时候，也可以选择之前的方式，比如查询缺考的学生子查询就不适用，不够也是可以两个结合一起使用的

#### 额外

分组 group by 字段根据什么来分

聚合函数只会聚合一次，如果不用分组结果只会执行一次聚合，分组可以让每个组都使用一次聚合函数，从而输出我们想要的结果

函数不能使用where 函数不能判断聚合函数，要用having，having一定在分组后才可以使用

### mysql函数

#### 常用函数（其实并没有那么常用）

数学运算：

* ABS绝对值
* ceiling向上取整
* floor向下取整
* rand 0~1随机数
* sign 判断一个数的符号 0-0 -10--1 10-1

字符串函数：

* char_LENGTH 字符串长度
* concat（a,b）合并字符串
* INSERT(a,b,c,d)替换 a要插入的字符，b插入的地方，c插入的结尾，d插入的字符
* lower转小写
* upper转大写
* instr(a,b)返回第一次出现的字串的索引，a是原来的字符串，b是我们要选择的子字符串，返回这个子字符串在第几个索引出现
* replace(a,b,c)a原来字符串，b要改哪里，c改成什么
* substr (a,b,c)a原字符串，b截取初始，c截取几个
* reverse() 反转字符

时间日期函数：

* current_DATE() 获取当前日期等价curdate
* now 获取当前时间包括时分秒
* localtime（）本地时间
* sysdate系统时间
* YEAR（now（））获取现在的年或某时间的年
* hour minute second都一样

系统:

* system_User=user
* versions

#### 聚合函数（常用）

计算平均值最大值最小值这样的

| 函数名称 | 描述   |
| -------- | ------ |
| count    | 计数   |
| sum      | 求和   |
| AVG      | 平均值 |
| max      | 最大值 |
| min      | 最小值 |

count* count1 countstudentname 都可以查数

但是他们有个本质区别count+字段会忽略所有的null值

count*和count1不会忽略null值

执行效率上

列名为主键，count列会比count1快

如果不为主键count1快

多个列没主键count1优于count*

一个字段，count*最优

#### 扩展数据库级别的MD5加密

什么是MD5，是信息摘要算法，不可逆，具体值的MD5值是一样的

MD5破解网站的原理，背后有一个字典,MD5加密后的值 ，加密的值

测试MD5加密

```sql
UPDATE testmd5 SET pwd=MD5(pwd)
```

这样更新就全部都改了，加密再加密就直接二次加密了

我们一般在插入的时候就加密

```sql
INSERT INTO testmd5 VALUES(4,'zhaoliu',MD5('123456'))
```

如何校验：将用户传递进来的密码进行MD5加密，然后对比加密后的值

```sql
SELECT * FROM testmd5 where `NAME`='zhaoliu' and pwd = MD5('123456')
```

这样就可以保证用户的密码安全性

### 事务

什么是事务，要么都成功，要么都失败

假设存在两条sql，作为一组事务，比如a给b转账，假设a给b转的时候服务器崩了，a成功了b失败了，那钱就没了，

---

1. sql1 A给B转账 A1000 --->200 B200
2. sql2 b收到A的钱 A800--->B4000

---

核心，将一组sql放在一个批次中去执行

INNODB支持，MYIAM在最新版也支持

> 事务原则：ACID原则 原子性一致性隔离性持久性（脏读，幻读.....）

原子性：针对同一个事物，要么都完成，要么都不完成

一致性：最终一致性，针对一个事务操作前与操作后的状态一致，就是不管怎么转总价值一定是1000，如果超出说明事务出现问题

持久性：事务的结束后的数据不会随着外界原因导致数据丢失

转钱转一半，服务器断电，事务如果没有提交恢复到原状，事务已经提交，持久化到数据库，一旦提交就不可逆

隔离性：针对多个用户同时操作，排除其他事务对本次事务的影响

两个人同时转钱，不会造成数据混乱的结果，一旦隔离失败就会产生问题

事务的隔离级别

脏读：一个数据读取了一个数据未提交的数据，要一个事务结束后获取最新的数据，否则数据会出现问题

不可重复读：在一个事务读取表中的一行数据，多次读取结果不同，我读这行的时候数据突然变化，那我会读取变化后的，不会读取变化前的，不会重复读取变化前的

幻读，是指在一个事务内读取到了别的事务插入的数据，导致前后结果不一致

##### 执行事务

mysql是默认开启事务自动提交的

```sql
SET autocommit = 0
```

关闭事务自动提交，设置为1开启

现在随便写一个就会发现都会持久化到数据库，，我们要手动开启事务，关键字start

```sql
START TRANSACTION --标记事务的开始
```

从这里开始只会的sql都在同一个事务内，语句有一个失败了，就会使事务提交不上去
一般都是成功了就提交，失败了就回滚
commit
--提交，持久化，持久化后回滚就没用了
rollback
--会滚，回到原来的样子

我们因为一开始是自动提交的，我们要手动开启事务，就要先关掉自动提交，在一个事务结束后，要重新开启事务自动提交，如果一个事务特别长的化中间可以有一个保存点savepoint，设置一个事务的保存点先了解，然后rollback to savepoint（保存点名），这个保存点不会影响一个事务有一个失败就全部失败，游戏存档一样

删除保持点release释放保存点

### 索引

sql里创建索引的时候也就是主键，叫做主键索引，unique key唯一索引，还要key普通索引

本质：mysql官方对索引的定义是：索引是帮助MySQL高效获取数据的数据结构。提取句子主干，就可以得到索引的本质：索引就是数据结构

#### 索引的分类

- 主键索引（primary key）
  - 唯一的标识，主键不可重复，而且只能有一个列作为主键
- 唯一索引（unique key）
  - 避免重复的列出现，唯一索引可以重复，多个列都可以标识为唯一索引
- 常规索引（key/index）
  - 默认的index关键字或key关键字来设置
- 全文索引（fulltest）
  - 在特点的数据库引擎下才有
  - 快速定位数据

```sql
show index from student--显示索引
```

```sql
alter table student add fulltest index `studentname`(`studengt`)--添加索引
 	索引名		 	列名
```

除了主键外其他的索引都有它自己的索引名

删除drop加索引类型与索引名就可以删除索引

```sql
explain SELECT * FROM student ;
```

使用explain来分析sql语句

where match(加全文索引的字段)  against('刘')

执行后会发现什么都没有，这个索引在数据量比较少的时候是没有用的

#### 测试索引

现在插入一百万条数据，我们查用户9999条就要耗时接近一秒多，没加索引，就查了几十万条才找到它，如果我们加入索引，create id_表名 _字段名 on 表名（字段名）创建一个索引 这是第三种创建索引方式了

添加索引在内存中就又创建了一个数，会让查询变很快时间很短，查了一条就直接查出来，直接定位索引

索引在小数据量的时候用处不大，但在大数据的时候差别十分明显

#### 索引原则

- 索引不是越多越好
- 不要对进程变动数据加索引
- 小数据量的表不需要加索引
- 索引一般加在常用来查询的字段上

> 索引默认的是类型是hash

但是INNODB默认的是btree

### 数据库备份和权限管理

#### 用户管理

Nacicat上面有一个小人，是用户管理

先看看可视化管理

我们创建用户的时候可以看到用户的权限，我们在这里设置用户的权限，说白了就是能不能创建表，能不能创建用户视图，grant授权，如果选择全部这个用户就可以干很多事情了

我们也可以删除用户

> sql命令操作

用户表：mysql数据库下的user表

本质：对这张表进行增删改查

表里全是权限

```sql
--创建用户    用户名               密码
create user huayu IDENTIFIED by 123456 
修改密码（修改当前用户密码）(已经被淘汰)
set PASSWORD = PASSWORD('12345')
修改密码（修改指定用户密码）
set PASSWORD for huayu = PASSWORD('111111')
没什么意义
--重命名
rename user huayu to huayu2
--用户授权(授予全部权限)，库和表 *.*就是全部的库和表
grant all PRIVILEGES on *.* TO huayu2
但是就算授权全部也不会有root高，如果有这行命令就可以删库跑路了
但是这个用户有一个权限不在表中，grant权限，这个用户是不能给别人授权的
除了给别人授权其他都能干
--查询权限
show grants for huayu2
--查询管理员权限
show grants for root@localhost
在公司我们不一定能拿到root权限，可能可以拿到一个开发者账号，用于获取某一个库的权限，权限就可以防止删库跑路
--撤销权限revork 哪些权限，在哪个库撤销，给谁撤销
revoke All PRIVILEGES on *.* FROM huayu2
--删除用户
drop user huayu2
```

#### mysql备份

- 保证重要的数据不丢失
- 数据转移

MySQL数据库备份的方式

- 直接拷贝物理文件 把date直接拷贝走

- 使用navicat右键数据库就有一个备份和导出，在一张表上也有，在可视化工具中手动导出，有一个sql转储

- 使用命令行导出

  ```sql
  mysqldump在命令行使用
  # mysqldump -h 主机 -u用户名 -p密码 数据库 表名1 表2 表3 > 物理磁盘位置/文件名
  mysqldump -hlocalhost -uroot -pYusiheng666666 school student >D:/a.sql
  出现警告就是导出完毕了
  这个主机的地址是可以换的，所以可以远程操作
  
  导入数据，先登录，换数据库，source d:/a.sql，当然导入数据库直接不用切换了
  就可以道路数据
  这样的话就可以保证数据库数据不丢失
  没有登陆就用MySQL -u用户名 -p密码 库名<备份文件
  建议使用用上面的，比较安全
  ```

直接拉过来就可以使用了

假设我要备份数据库，防止数据丢失，把数据库给朋友，sql文件给别人即可

### 数据库规约和三大范式

#### 规范数据库设计

##### 设计数据库的步骤

以个人博客来举例

- 收集信息，分析需求

  - 用户表（用户登录注销，用户的个人信息，写博客，创建分类）

  - 分类表（文章分类，谁创建的）

  - 文章表（文章的信息）

  - 友链表（友链信息）

  - 自定义表（系统信息某个关键的字，或者一些主字段）key: value

    ---

    还可以有说说表

    我们还可以加头像（保存地址）

    粉丝表（id user_id follow_id )

- 标识实体（把需求落地到每个字段）
- 标识实体之间的关系
  - 写博客user-->blog
  - 创建分类user-->categroy
  - 关注user-->user
  - 友链links
  - 评论user-user_blog

#### 三大范式

为什么需要数据规范化

- 信息会重复
- 更新会导致异常
- 插入异常
  - 无法正常显示信息
- 删除异常
  - 丢失有效信息

> 三大范式

1. 第一范式（原子性）

   要求数据库表的每一个列都是不可分割的原子数据项

   一个字段不可再分，比如学校信息 硕士，研二就可以再分，就不行

2. 第二范式

   前提满足第一范式

   每张表只做（描述）一件事情，确保数据库的每一列都和主键相关，而不能只与主键的某一部分相关，如果描述了两个事情就要分成两张表

3. 第三范式

   满足第一范式和第二范式为前提

   消除传递依赖，要求确保数据表的每一列都和主键直接相关，而不能间接相关，要拆成两张表，并保持联系

规范性 和 性能的问题

关联查询的表不得超过三张表，按照三大范式的操作就很容易让表很多，考虑商业化的需求和目标（成本，用户体验），所以有的时候，数据库的性能更加重要

在规范性能问题的时候，需要适当的考虑以下规范性

有时候我们会故意给某些表增加一些冗余的字段（从多表查询变为单表查询）

故意增加一些计算列（从大数据量，降低为小数据量的查询：增加索引）

### JDBC

用Java操作数据库

#### 数据库驱动

驱动：声卡，显卡，数据库

驱动可以让应用程序连接到数据库，每个厂商有每个不同的驱动，由数据库厂商提供

我们的程序会通过数据库驱动，和数据库打交道，但是我们如果有十种驱动就要写十套程序，明显不显示

所以我们就来了JDBC

#### JDBC

sun公司为了简化开发人员的操作，提供了一个Java操作数据库的规范，俗称JDBC，这些规范的实现由具体的厂商去做

对于开发人员来说，我们只需要掌握JDBC接口的操作即可，在架构里面，没有什么是加一层解决不了的，如果有...

我们需要知道两个包

1. Java.sql
2. javax.sql

还需要导入一个数据库驱动包

```
public static void addOrder(TbOrderGoodsDO...orderGoods){
    try {
        connection=getConnection();
        connection.setAutoCommit(false);
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO `tb_order_goods`(`order_id`,`goods_id`,`quantity`) VALUES(?,?,?)");
        for (int i = 0; i < orderGoods.length-1; i++) {
            stringBuilder.append(",").append("(?,?,?)");
        }
        String sql1=stringBuilder.toString();
        String sql2= "INSERT INTO `tb_order`(`id`,`time`,`price`) VALUES(?,?,(select sum(`price`*`quantity`) from `tb_goods` as g " +
                "INNER join `tb_order_goods` as og where g.id=og.goods_id and og.order_id=?))" ;
        preparedStatement=connection.prepareStatement(sql1);
        for (int i = 0; i < orderGoods.length; i++) {
            preparedStatement.setInt(3*i+1,orderGoods[i].getOrderId());
            preparedStatement.setInt(3*i+2,orderGoods[i].getGoodsId());
            preparedStatement.setInt(3*i+3,orderGoods[i].getQuantity());
        }
        preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setInt(1,orderGoods[0].getOrderId());
        preparedStatement2.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
        preparedStatement2.setInt(3,orderGoods[0].getOrderId());
        preparedStatement.executeUpdate();
        int i=preparedStatement2.executeUpdate();
        connection.commit();
        if(i>0){
            System.out.println("插入成功");
        }else {
            System.out.println("订单已经存在或订单号不合法");
        }
        release(connection,preparedStatement,resultSet);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
```
