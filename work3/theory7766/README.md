1. MapHandler.java:查询单行数据，执行SQL查询到的数据存储到Map<String,Object>,字段名存储到key，value中存储字段对应的数据
2. MapListHandler.java:查询多行数据，执行SQL查询到的数据存储到List<Map<String,Object>>,将上面单行Map数据，存储到List集合
3. ArrayHandler.java:查询单行数据，执行SQL查询到的数据存储到Object[]数组。
4. 查询多行数据，将上面每单行Object[]数据存储到List集合List<Object[]>
5. Goods.java商品类
6. OrderForm.java订单类
7. ResultSetHandler接口用于实现查询的结果集
8. test.java为测试集
9. JdbcUtils.java为JDBC工具类
10. db.properties为驱动相关信息
# 学习心得

数据类型：https://www.runoob.com/sql/sql-datatypes.html
1） 单行/多行书写，分号结尾；不区分大小写，关键字使用大写；单行注释：-- （空格)注释内容或者#注释内容 多行注释：/* */
2）分类：

2.1）DDL：
操作数据库：CRUD/
1）创建create：create database 数据库名;

	如果不存在db1则创建db1，并且将指定字符集为gbk
	create database if not exists db1 character set gbk;

2）查询retrieve：

	查询所有数据库的名称：
	show databases；
	查看某个数据库的字符集utf8/创建语句
	show create database 数据库名称;

3）修改update：

	alter database数据库名称character set字符生名称
4）删除delete: 

	DROP DATABASE 数据库名；  
	drop database if exists db3;
5）使用数据库：
查询当前正在使用的数据库名称 

	select database();
使用数据库 

	use 数据库名；
2.2）操作表
use game;#使用该数据库
1）创建表：

	create table [IF NOT EXISTS] `表名`（
	`字段名` 列类型 [属性] [索引] [注释COMMENT " "],
	)[表类型][字符集设置][注释]

设置默认值：https://www.cnblogs.com/mysqljs/p/14462211.html

数据类型：int整数 double(5,2)小数,共5位，保留到小数点后两位，
date 日期（yyyy-MM-dd),datetime(yyyy-MM-dd HH:mm:ss)
timestamp时间戳（年月日时分秒) ，不赋值或者为null则使用当前系统时间
varchar(20)可变字符串,最大二十个字符

2）查询表结构：DESC 表名；
查询某个数据库中所有表名称：show tables；

3）修改列表数据结构：

	ALTER table 表名MODIFY COLUMN 字段名(如:name) 新数据类型（如：VARCHAR(200))；
修改表名：

	ALTER TABLE 表名 RENAME COLUNMN 字段名(如:name) to 新字段名；
修改表某字段默认值：

	ALTER table 表名MODIFY 字段名 数据类型 DEFAULT 1；//默认为1
添加新字段名：

	ALTER TABLE 表名 ADD COLUMN 新字段名(如:last) 新数据结构(DATETIME)；
删除字段：

	ALTER TABLE 表名 DROP COLUMN 字段名；
删除整个表：

	DROP TABLE 表名；
2.3）操作数据

	INSERT INTO 表名 （字段名，隔开）VLALUES （数值，隔开）（数值，隔开）//即可插入多条数据
若字段顺序与表中数据结构定义相同则可省略，若只设置部分列数值，则使用默认值

	SELECT * from 表名；#从该表查询所有数据
	UPDATE 表名 set level =1 where name = ‘李四’;#修改李四的等级为1级
	UPDATE 表名 set level =1 ，gold=0；#修改全部
	DELETE FROM 表名 where gold=0;#删除表中全部gold为0的数据
2.4)数据的导入导出
使用命令行导出数据到具体文件：
mysqldump -u root -p 数据库名称 表名称（省略则到处所有数据库数据）> game.sql(文件名）
导入数据：mysql -u root -p 数据库名< 文件名
2.5）常用语句
	NOT>AND>OR
	where lever IN(1,3,5)#在1，3，5之间
	where lever between 1 AND 10;
NOT 取反 LIKE 模糊查询：%表示任意个字符，_任意一个字符
	where name LIKE ‘王%’#王姓玩家‘%王%’#名字中包含王 ’王_’
正则表达式 
	where name REGEXP ‘^王.$’#查找姓王并且名字只有两个字的玩家
’王’#名字中包含王字
‘[王张]’或者’王|张’#名字中包含王字或张字
查找空结果：

	where email is null 或者where email <=> null(mysql专用）
查找非空结果：

	where email is NOT null
查找空字符串：

	where email = ’‘
排序：ORDER BY 

	SELECT * FROM 表名 OEDER BY 字段名；#升序排列
	SELECT * FROM 表名 OEDER BY 字段名 DESC；#降序排列
Eg. SELECT * FROM player OEDER BY 字level DESC,exp ASC;#按等级降序，经验升序
SELECT * FROM 表名 OEDER BY 5 DESC；#按第5列降序排名
聚合函数
AVG（）集合中的平均值；COUNT（）项目数；MAX（）；MIN（）；SUM（）；

	SELECT COUNT（*）FROM 表名；
	SELECT AVG（level) FROM 表名；
分组：

	SELECT sex,count(*) FROM 表名 GROUP BY sex；
	SELECT level，count(level)  from 表名 group by level；#每个等级的玩家数
HAVING: 筛选分组后的数据
	SELECT level，count(level)  from 表名 group by level HAVING count(level)>4；#玩家数量大于4的等级
	SELECT level，count(level)  from 表名 group by level HAVING count(level)>4 order by count(level) DESC;
分页查询：Limit(查询起始下标，pageSize)

LINIT 3#只返回前三名的姓氏
LINIT 3,3#第一个参数是偏移量，第二个参数是返回的数量。此为4~6
去重：DISTINCT

	SELECT DISTINCT sex from player；# 返回去重后的结果
并集UNION （默认去除重复记录）ALL
INTERSECT 交集
EXCEPT 差集
2.6）子查询：
	
	select * from 表名 where  level > (select AVG(level) from 表名）
	select level，ROUND （select AVG(level) from 表名） as average(别名）from 表名 
创建新表 

	create table new_player select * from player where lever<6
	select exists (select * from player where level >10)#返回值只有1或者0
2.7）表关联（相同字段）:笛卡尔积+条件过滤
内连接（两表中都有的数据）INNER JOIN

	select * from player inner join equip on player.id=equip.player_id(关联玩家表与装备表）
左连接（左表中的所有数据+右表匹配数据，右表无用NULL填充）left JOIN
右连接 right join

	select * from player ,equip where player.id=equip.player_id
2.8）索引
创建索引

	create [unique(唯一索引)|fulltext(全文索引)|special(空间索引)] index index_name on table_name(字段名，隔开）
查看索引：

	show index from table_name
删除索引：

	drop index 索引名 on table_name
修改表结构时创建索引：

	alter table table_name add index on 索引名（字段）
2.9）视图（数据动态）
创建视图:

	create view view_name as select * from player order by level desc limit 10;
查询视图：

	select * from view_name；
修改视图:

	alter view view_name as select * from player order by level limit 10;
删除视图：

	drop view view_name;