# Order Management System
## 项目简介
### 通过使用mysql数据库、JDBC、mybatis框架、druid连接池来实现了一个订单系统基础功能的实现。
### 项目结构：
![](images/屏幕截图 2025-09-25 190736.png)
### 结构分析：
- druid包：用于mybatis识别druid连接池。
- mappers包：包含mapper接口，mapper.xml文件，mapper接口是mybatis框架的核心，负责将sql语句映射到java对象，实现CURD功能。
- pojo包：就是用于映射数据库表的字段的实体类的实现
- utils包：mybatisUtils类，Update接口是用来在对一个已存在的订单进行修改时，更新数据库中相应的字段的接口。
- resources文件：包含配置文件，mybatis-config.xml，db.properties,log4j.properties.
- test文件：用来测试mapper接口的方法是否正确。
### 设计思路：
- 首先，先确定需要几个表、每个表的字段以及如何关联它们。
- 然后，根据创建的表来设计实体类用于映射数据库表的字段。
- 然后，就是开始项目的创建，配置好相关的文件，如在pom.xml文件中导入相关依赖，在mybatis-config.xml文件中配置mybatis的相关信息，在db.properties文件中配置数据库的相关信息，在log4j.properties文件中配置日志的相关信息。
- 接着，就是设计mapper接口，要实现CURD功能，并在mapper.xml文件中编写相应的sql语句。
- 最后，就是在test文件中编写测试类，使用了juint依赖，可以测试mapper接口的方法是否正确。
### 遇到的问题
- 刚开始对xml编写CURD语句时候与sql语句的一些不同点，如：可以使用trim、choose、if、where等标签。
- 对配置文件中的一些字段代表的意思以及独自配置的文件是遇到的文件路径问题。

### 收获与心得
- 学习了JDBC的使用以及JDBC工具类的编写。
- 学习了mybatis框架的使用，用它来简化JDBC复杂的代码编写，以及如何使用mybatis框架实现CURD功能。
- 学习了druid连接池的使用，以及如何配置druid连接池。
- 学会了mybatis.xml文件的配置