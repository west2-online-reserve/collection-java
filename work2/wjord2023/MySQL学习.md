

# MySQL学习

###### MySQL：数据库（DataBase）-软件-安装在操作系统之上，SQL可储存大量数据，500万以上。

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



## 函数





