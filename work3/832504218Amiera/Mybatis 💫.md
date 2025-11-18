# **Mybatis** 💫

---

![mybatis 开发自定义插件，你学废了吗 - 知乎](https://picx.zhimg.com/v2-fc47e92d755541a24a768737fe9ffab9_720w.jpg?source=172ae18b)

> [!TIP]
>
> 1. 先掌握Mysql、Java以及IDEA的基础使用
> 2. 学习任何SMM框架时，先查官网，后续也要一边看官网一边学习**[MyBatis中文网](https://mybatis.net.cn/index.html)**
> 3. 推荐Mybatis学习视频[【狂神说Java】Mybatis最新完整教程IDEA版通俗易懂_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1NE411Q7Nx?spm_id_from=333.788.videopod.episodes&vd_source=8b1f1e8782121348afdfc5381b06240b)

---

> MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

---

## 1. 持久层 📊

### 1.1 数据持久化

#### 定义

- **数据持久化**：将程序中的数据在**持久状态**和**瞬时状态**之间转化的过程
- **持久状态**：数据存储在持久化存储介质中，如数据库、文件系统等
- **瞬时状态**：数据存储在内存中，程序运行结束或断电后数据丢失

#### 数据存储层次对比
| 存储介质     | 特点     | 持久性 | 访问速度 |
| ------------ | -------- | ------ | -------- |
| **内存**     | 断电即失 | 临时   | 快       |
| **数据库**   | 持久保存 | 永久   | 中等     |
| **文件系统** | 持久保存 | 永久   | 慢       |

#### 持久化方式
- **关系型数据库**：MySQL、Oracle、PostgreSQL等
- **文件存储**：XML、JSON、Properties文件等
- **NoSQL数据库**：Redis、MongoDB等
- **对象关系映射**：Mybatis、Hibernate等框架

### 1.2 持久层介绍

#### 概念定义
- **持久层**：专门负责完成持久化工作的代码层
- **职责**：封装所有数据访问细节，为业务逻辑层提供统一的数据访问接口

#### 架构位置
```
表现层 (Controller)
    ↓
业务逻辑层 (Service)  
    ↓
持久层 (Dao/Mapper)
    ↓
数据库
```

#### 设计原则
- **单一职责**：只关注数据存取，不涉及业务逻辑
- **接口分离**：定义清晰的数据访问接口
- **数据封装**：隐藏底层数据存储细节
- **事务管理**：保证数据操作的原子性和一致性

### 1.3 为什么选择Mybatis？ 🤔

#### JDBC的痛点
```java
// 传统JDBC代码示例
Connection conn = null;
PreparedStatement ps = null;
ResultSet rs = null;
try {
    // 1. 加载驱动
    Class.forName("com.mysql.jdbc.Driver");
    // 2. 创建连接
    conn = DriverManager.getConnection(url, username, password);
    // 3. 创建Statement
    ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
    ps.setInt(1, userId);
    // 4. 执行查询
    rs = ps.executeQuery();
    // 5. 处理结果集
    while(rs.next()) {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        // ... 更多字段映射
    }
} catch (Exception e) {
    e.printStackTrace();
} finally {
    // 6. 关闭资源
    try { if(rs != null) rs.close(); } catch (Exception e) {}
    try { if(ps != null) ps.close(); } catch (Exception e) {}
    try { if(conn != null) conn.close(); } catch (Exception e) {}
}
```

#### Mybatis的优势

1. **简化开发** ✂️
   - **SQL与代码分离**：SQL写在XML配置文件中，与Java代码解耦
   - **自动参数映射**：自动将Java对象属性映射到SQL参数
   - **结果集自动映射**：自动将查询结果映射到Java对象

2. **灵活性强** 🎯
   - **手写SQL控制**：开发者可以精确控制SQL语句
   - **动态SQL**：支持条件判断、循环等动态SQL功能
   - **存储过程支持**：轻松调用数据库存储过程

3. **性能优化** ⚡
   - **连接池管理**：内置连接池，提高数据库连接效率
   - **缓存机制**：提供一级缓存和二级缓存，减少数据库访问
   - **懒加载**：支持关联对象的延迟加载

4. **易于集成** 🔗
   - **Spring集成**：与Spring框架无缝集成
   - **多种数据库**：支持主流关系型数据库
   - **插件扩展**：支持自定义插件扩展功能

#### Mybatis vs 其他ORM框架

| 特性         | Mybatis             | Hibernate          | JDBC Template     |
| ------------ | ------------------- | ------------------ | ----------------- |
| **SQL控制**  | 完全控制            | 自动生成           | 完全控制          |
| **学习曲线** | 平缓                | 陡峭               | 平缓              |
| **灵活性**   | 高                  | 中                 | 高                |
| **性能**     | 优秀                | 良好               | 优秀              |
| **适用场景** | 复杂SQL、高性能需求 | 简单CRUD、快速开发 | 简单项目、原生SQL |

#### 实际应用价值
- **提高开发效率**：减少大量模板代码
- **提升可维护性**：SQL集中管理，便于优化和调试
- **降低耦合度**：数据访问层与业务逻辑层分离
- **便于测试**：可以Mock持久层进行单元测试

这样的持久层设计让开发者能够更专注于业务逻辑，而不必过多关心底层数据访问的细节。

#### Mybatis核心组件关系图

![image-20251105101057801](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251105101057801.png)

---

## 2. 第一个Mybatis 🚀

### 2.1 搭建环境

#### 2.1.1 创建Maven项目
- 新建一个maven项目

  ![image-20251031185918228](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251031185918228.png)

#### 2.1.2 导入依赖
- 在pom.xml导入依赖：Mysql，Mybatis，junit

```xml
<!-- 引入Mybatis依赖 -->
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.13</version>
    </dependency>
    <!-- 导入mysql驱动 -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
    <!-- 导入junit测试依赖 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
    </dependency>
</dependencies>
```

> [!NOTE]
> 如果依赖报红，刷新Maven即可

#### 2.1.3 编写Mybatis核心工具类

> 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例。

**创建核心配置文件**：
1. 在resources下新建mybatis-config.xml文件（名字是小乌龟的屁股）

   ![image-20251031192817711](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251031192817711.png)

2. 在xml文件中输入配置(记得修改property行内容)

   ![image-20251031192817711](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251031192817711.png)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <!-- 事务管理器配置 -->
            <transactionManager type="JDBC"/>
            <!-- 数据源配置 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/firsttry?USEUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=true"/>
                <property name="username" value="root"/>
                <property name="password" value="syx1433223"/>
            </dataSource>
        </environment>
    </environments>
</configuration>
```

> [!NOTE]
>
> - **transactionManager type="JDBC"**：使用JDBC的事务管理机制，支持commit和rollback
> - **dataSource type="POOLED"**：使用连接池管理数据库连接，提高性能
> - **driver**：MySQL 8.0+需要使用`com.mysql.cj.jdbc.Driver`
> - **url参数说明**：
>   - `useUnicode=true&characterEncoding=utf-8`：支持中文字符
>   - `useSSL=true`：启用SSL加密连接
>   - `serverTimezone=Asia/Beijing`：建议添加时区设置

**编写Mybatis工具类**：

```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// sqlSessionFactory工具类
public class utils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
    // SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
```

### 2.2 编写代码

#### 2.2.1 实体类

1. 连接数据库

   ![image-20251031201247214](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251031201247214.png)

2. 编写用户类（在工具包下）

```java
public class Users {
    private String name;
    private int age;
    
    // 构造有参和无参方法
    public Users() {
    }

    public Users(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // get和set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    // 重写toString()方法
    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

> [!CAUTION]
>
> **实体类设计原则**：
>
> - 属性使用private修饰，提供getter/setter方法
> - 必须有无参构造函数（Mybatis反射创建对象时需要）
> - 建议重写toString()方法便于调试
> - 实现Serializable接口（可选，便于网络传输）

#### 2.2.2 Dao接口

```java
import java.util.List;

public interface UserDao {
    List<Users> getUsersList();
}
```

#### 2.2.3 接口实现类由*接口mpl*变为Mapper配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 绑定你的接口 -->
<mapper namespace="com.amiera.utils.UserDao">
    <!-- 
        id: 接口中的方法名
        resultType: 返回结果类型（全限定类名）
    -->
    <select id="getUsersList" resultType="com.amiera.utils.Users">
        select * from users
    </select>
</mapper>
```

### 2.3 开始测试（最害怕的一集） 🧪

> [!IMPORTANT]
>
> #### 写在测试前（建议先看第一部分再回来看这个）：
>
> 在mybatis-config.xml中少了mapper配置！
>
> ![image-20251101085823356](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251101085823356.png)
>
> ```xml
> <!--添加Mapper的路径-->
> <mappers>
>  <mapper resource="com/amiera/utils/UsersMapper.xml"/>
> </mappers>
> ```

#### 2.3.1 junit测试

- 注意**包是绿的**（根据junit规范），创建时能看到这个选项，结构最好完全复制**蓝色的Java文件**~~（问就是小乌龟的屁股）~~

![image-20251101090721873](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251101090721873.png)

```java
import com.amiera.utils.UserDao;
import com.amiera.utils.Users;
import com.amiera.utils.utils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void test() {
        // 获取SqlSession对象
        SqlSession sqlSession = utils.getSqlSession();
        // 执行sql - 获取Mapper接口的代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Users> usersList = userDao.getUsersList();
        for (Users users : usersList) {
            System.out.println(users);
        }
        // 关闭sqlSession
        sqlSession.close();
    }
}
```

#### 2.3.2 解决报错 🔧

这时候你可能会遇到报错，我们来逐步解决：

**配置资源导出**：
- 在pom.xml中build配置resources，防止资源导出失败

![image-20251101091139098](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251101091139098.png)

```xml
<!--在build中配置resources-->
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

- 为了防止再次折磨，你可以在target目录下pom.xml再放一份

![image-20251101091418807](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251101091418807.png)

**验证配置**：

- 刷新Maven后，在target目录下应该能看到xml文件

![image-20251101092500426](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251101092500426.png)

> [!WARNING]
>
> 如果还有问题：
> - **检查表名是不是Users**，如果不是，在UsersMapper中修改
> - **注意后面所有select查表，我给的表名都是test而不是users**，注意修改
> - 其他问题可以询问TRAE或查看错误日志

**成功输出**：
- 如果输出正常，说明配置成功！

![image-20251101093518378](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251101093518378.png)

---

## 3. CRUD操作 📝

<u>**后续所有内容只需修改UsersMapper接口和xml文件以及测试类**</u>

**UsersMapper.xml中的绑定接口名确认下，别写错了**

```xml
<!--namespace绑定接口-->
<mapper namespace="com.amiera.utils.UserDao">
```

### 3.1 select查询

**基本语法**：
- `id`：重写的方法名
- `resultType`：Sql语句返回的执行结果类型
- `parameterType`：参数类型

```xml
<select id="getUsersList" resultType="com.amiera.utils.Users">
    select * from Test
</select>
```

#### 3.1.1 条件查询实现

**接口添加方法**：
```java
// 根据name查询用户
Users getUserByName(String name);
```

**Mapper配置**：
```xml
<!--根据name查询用户-->
<select id="getUserByName" parameterType="java.lang.String" resultType="com.amiera.utils.Users">
    select * from Test where name = #{name}
</select>
```

> [!NOTE]
>
> **#{} 和 ${} 的区别：**
>
> **#{}**：预编译参数，防止SQL注入，会自动添加引号
>
> **${}**：字符串替换，有SQL注入风险，直接拼接到SQL中
>
> ```sql
> select * from user where name = #{name}  
> <!-- 编译为：select * from user where name = ? -->
> ```
>
> ```sql
> select * from user order by ${orderBy}
> <!-- 编译为：select * from user order by name -->
> ```

**测试代码**：
```java
@Test
public void testGetUserByName() {
    SqlSession sqlSession = utils.getSqlSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    Users users = userDao.getUserByName("张三");
    System.out.println(users);
    sqlSession.close();
}
```

### 3.2 insert插入（类似select）

**Mapper配置**：

```xml
<insert id="addUser" parameterType="com.amiera.utils.Users">
    insert into Test (name, age) values (#{name}, #{age})
</insert>
```

**重要提醒**：
**<u>增删改需要提交事务，别忘了！</u>**

**测试代码**：
```java
@Test
public void testAddUser() {
    SqlSession sqlSession = utils.getSqlSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    Users users = new Users("王五", 10);
    userDao.addUser(users);
    // 提交事务 - 重要！
    sqlSession.commit();
    sqlSession.close();
}
```

<!--别把接口的方法忘了233-->

### 3.3 update更新

**Mapper配置**：
```xml
<!--修改用户-->
<update id="updateUser" parameterType="com.amiera.utils.Users">
    update Test set age = #{age} where name = #{name}
</update>
```

### 3.4 delete删除

**Mapper配置**：
```xml
<!--删除用户-->
<delete id="deleteUser" parameterType="java.lang.Integer">
    delete from Test where age = #{age}
</delete>
```

### 3.5 Map方法替代 🗺️

使用Map可以让Sql中的列名不用和表中对应，简化参数传递

**接口方法**：
```java
int addUser2(Map<String, Object> map);
```

**Mapper配置：我们能看到values中参数并不是表的参数名~~（我乱填的）~~**

```xml
<!--添加用户,使用map-->
<insert id="addUser2" parameterType="java.util.Map">
    insert into Test (name, age) values (#{NOname}, #{NOage})
</insert>
```

**测试代码**：
```java
@Test
public void testAddUser2() {
    SqlSession sqlSession = utils.getSqlSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    Map<String, Object> map = new HashMap<>();
    map.put("NOname", "赵六");
    map.put("NOage", 12);
    userDao.addUser2(map);
    sqlSession.commit();
    sqlSession.close();
}
```

**参数传递总结**：
- **Map传递参数**：直接在sql中取出key
  ```xml
  parameterType="java.util.Map"
  ```
- **对象传递参数**：直接在sql中取出对象属性
  ```xml
  parameterType="Object"
  ```
- **只有一个基本类型参数**：可以直接在sql中取，不用写parameterType
- **多个参数**：用Map或者注解

### 3.6 模糊查询 🔍

#### 3.6.1 Java代码传递通配符

**Mapper配置**：
```xml
<!--模糊查询-->
<select id="getUserLike" parameterType="java.lang.String" resultType="com.amiera.utils.Users">
    select * from Test where name like #{value}
</select>
```

**测试代码**：
```java
@Test
public void testGetUserLike() {
    SqlSession sqlSession = utils.getSqlSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    List<Users> usersList = userDao.getUserLike("%三%");
    for (Users users : usersList) {
        System.out.println(users);
    }
    sqlSession.close();
}
```

#### 3.6.2 SQL中拼接通配符

对于Sql行，你可以写死value参数，即在sql中拼接通配符

**Mapper配置**：
```xml
<select id="getUserLike" parameterType="java.lang.String" resultType="com.amiera.utils.Users">
    select * from Test where name like "%"#{value}"%"
</select>
```

---

## 4. 配置解析 ⚙️

### 4.0 写在解析之前

> The content of element type "configuration" must match "(properties?,settings?,typeAliases?,typeHandlers?,objectFactory?,objectWrapperFactory?,reflectorFactory?,plugins?,environments?,databaseIdProvider?,mappers?)"

> [!IMPORTANT]
>
> **<u>这种报错你以后遇到的绝对不少，在xml中，每个配置都有固定顺序，这个报错内容已经把顺序说的很清楚了</u>**

### 4.1 核心配置文件 mybatis-config.xml

> MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。

**配置文件结构**：
```xml
configuration（配置）
├── [properties（属性）]
├── [settings（设置）]
├── [typeAliases（类型别名）]
├── [typeHandlers（类型处理器）]
├── [objectFactory（对象工厂）]
├── [plugins（插件）]
├── environments（环境配置）
│   ├── environment（环境变量）
│   │   ├── transactionManager（事务管理器）
│   │   └── dataSource（数据源）
├── [databaseIdProvider（数据库厂商标识）]
└── [mappers（映射器）]
```

在开始前，先重新创一个文件，记得复制下内容：

![image-20251101190709422](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251101190709422.png)

### 4.2 环境配置（environments）

> MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中。
>
> **不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

#### 4.2.1 environments 默认格式：

```xml
<environment id="test">
    <transactionManager type="JDBC"/>
    <dataSource type="POOLED">
        <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/firsttry?USEUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=true"/>
        <property name="username" value="root"/>
        <property name="password" value="syx1433223"/>
    </dataSource>
</environment>
```

#### 4.2.2 事务管理器（transactionManager）

- 在 MyBatis 中有两种类型的事务管理器：
  - **JDBC** - 这个配置直接使用了 JDBC 的提交和回滚设施
  - **MANAGED** - 这个配置几乎没做什么。它从不提交或回滚一个连接，而是让容器来管理事务的整个生命周期

#### 4.2.3 数据源（dataSource）

- 大多数 MyBatis 应用程序会按示例中的例子来配置数据源。虽然数据源配置是可选的，但如果要启用延迟加载特性，就必须配置数据源。

- 有三种内建的数据源类型：
  - **UNPOOLED** - 每次请求时打开和关闭连接
  - **POOLED** - 利用"池"的概念将 JDBC 连接对象组织起来
  - **JNDI** - 为了能在如 EJB 或应用服务器这类容器中使用

> [!NOTE]
> 实际开发中并没有太多复杂配置，主要需要掌握如何配置多个环境

### 4.3 属性（properties）

我们可以使用properties属性来实现引用配置文件

> 这些属性可以在外部进行配置，并可以进行动态替换。你既可以在典型的 Java 属性文件中配置这些属性，也可以在 properties 元素的子元素中设置。

#### 4.3.1 创建配置文件

- 在resources文件下创建db.properties文件并编写配置文件：

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/firsttry?USEUnicode=true&characterEncoding=utf-8&useSSL=true
username=root
password=syx1433223
```

#### 4.3.2 引入配置文件

- 在核心配置文件中引入配置文件，注意配置文件必须在最前面**（第一个）**，这时，我们可以发现property下的四个属性已经不需要实际参数了

```xml
<!--引入外部配置文件-->
<properties resource="db.properties"/>

<environments default="development">
    <environment id="development">
        <transactionManager type="JDBC"/>
        <dataSource type="POOLED">
            <property name="driver" value="${driver}"/>
            <property name="url" value="${url}"/>
            <property name="username" value="${username}"/>
            <property name="password" value="${password}"/>
        </dataSource>
    </environment>
</environments>
```

#### 4.3.3 混合配置方式

你甚至可以各写一点~~（闲的）~~

```xml
<properties resource="db.properties">
    <property name="username" value="root"/>
    <property name="password" value="syx1433223"/>
</properties>
```

> [!CAUTION]
>
> 在外部配置文件和内部文件冲突时，优先执行外部文件，即假设property和db.properties中都传了password参数，优先执行db.properties中的

### 4.4 类型别名（typeAliases） 🔤

> 类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，意在降低冗余的全限定类名书写。

#### 4.4.1 单个类型别名配置

typeAliases在xml第三个，如果你没有settings（第二个），那就放在第二个~~（废话）~~

```xml
<typeAliases>
    <typeAlias alias="User" type="com.amiera.utils.User"/>
</typeAliases>
```

#### 4.4.2 包扫描方式配置

扫描实体类的包，他的默认别名就是这个类的类名首字母小写

```xml
<typeAliases>
    <package name="com.amiera.utils"/>
</typeAliases>
```

#### 4.4.3 注解方式自定义别名

```java
import org.apache.ibatis.type.Alias;

// 实体类
@Alias("Users")
public class User {
    // ...
}
```

#### 4.4.4 使用别名

配置后Mapper文件中的resultType就可以简化了：

```xml
<select id="getUsersList" resultType="Users">
    select * from Test
</select>
```

**配置建议**：
- **实体类少**：建议用第一种，可以自定义别名
- **实体类多**：建议用第二种，默认别名为类名首字母小写
- **需要特殊命名**：使用`@Alias`注解

### 4.5 设置（settings） ⚒️

> 这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。

**常用设置**：

| 设置名                 | 描述                                                     | 有效值                            | 默认值 |
| ---------------------- | -------------------------------------------------------- | --------------------------------- | ------ |
| **cacheEnabled**       | 全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。 | true \| false                     | true   |
| **lazyLoadingEnabled** | 延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 | true \| false                     | false  |
| **logImpl**            | 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。    | SLF4J \| LOG4J2 \| JDK_LOGGING 等 | 未设置 |

**配置示例**：
```xml
<settings>
    <setting name="cacheEnabled" value="true"/>
    <setting name="lazyLoadingEnabled" value="false"/>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

### 4.6 映射器（mappers） 🗺️

> 映射器注册的作用是**告诉MyBatis在哪里可以找到SQL映射文件或映射接口**，这样MyBatis才能知道如何执行你定义的SQL语句

**每一个Mapper.xml都需要在核心配置文件中注册**

> [!CAUTION]
> 如果使用方法二和三，xml与对应接口需要在同一个包内，并且名字要一样，我这样就是错的，接口名最好是*UsersMapper*

![image-20251101202342876](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251101202342876.png)

#### 4.6.1 方式一：资源引用（推荐）

```xml
<!-- 使用相对于类路径的资源引用 -->
<mappers>
  <mapper resource="com/amiera/utils/UserMapper.xml"/>
  <mapper resource="com/amiera/utils/BlogMapper.xml"/>
</mappers>
```

#### 4.6.2 方式二：类名注册

```xml
<!-- 使用映射器接口实现类的完全限定类名 -->
<mappers>
  <mapper class="com.amiera.utils.UserMapper"/>
  <mapper class="com.amiera.utils.BlogMapper"/>
</mappers>
```

#### 4.6.3 方式三：包扫描

```xml
<!-- 将包内的映射器接口全部注册为映射器 -->
<mappers>
  <package name="com.amiera.utils"/>
</mappers>
```

### 4.7 作用域（Scope）和生命周期 ⏳

> 作用域和生命周期类别是至关重要的，因为错误的使用会导致非常严重的**并发问题**。

![image-20251102091306321](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251102091306321.png)

#### 4.7.1 SqlSessionFactoryBuilder

- **作用**：用于创建SqlSessionFactory
- **生命周期**：一旦创建了SqlSessionFactory，就不需要它了
- **作用域**：方法作用域（局部变量）

#### 4.7.2 SqlSessionFactory

- **作用**：数据库连接池
- **生命周期**：SqlSessionFactory一旦创建就应该在应用运行期间一直存在
- **作用域**：应用作用域，使用**单例模式**或者静态单例模式

#### 4.7.3 SqlSession

- **作用**：代表一次数据库会话
- **生命周期**：每个线程都应该有它自己的 SqlSession 实例
- **作用域**：请求或方法作用域（不是线程安全的）

> [!IMPORTANT]
> **绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行**

**<u>总的来说，一个SqlSessionFactory对应多个SqlSession，一个SqlSession对应多个Mapper，这里的Mapper就是一个具体的业务</u>**

---

## 5. 解决属性名和字段名不一样的问题 🔄

### 5.0 引出问题

- 开始前我们先搞一个新项目，把User类中的参数名字改掉

![image-20251102092952663](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251102092952663.png)

```java
public class Users {
    private String difname;  // 数据库字段是name，这里改成了difname
    private int age;
}
```

- 这时候如果你根据age参数（没改名的）去查对象，会发现输出的名字为空

![image-20251102095235541](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251102095235541.png)

> 如果你还是查出来了，那么你要检查下，在Users类中，你是否将属性名修改为了`difname`，但数据库字段仍然是`name`，这导致MyBatis的映射出现混乱。

### 5.1 第一种方法：起别名 🏷️

- 直接在SQL查询中通过**起别名**的方式解决映射问题

```xml
<select id="getUserByAge" resultType="com.amiera.utils.Users">
    select age, name as difname from Test where age = #{age}
</select>
```

### 5.2 第二种方法：结果集映射（ResultMap） 🗺️

使用**<u>结果集映射resultMap（ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了）</u>**，这里的**column**（列）就是数据库中的数据，我们通过映射将它转化为通过**property**（实体类属性）

> `resultMap` 元素是 MyBatis 中最重要最强大的元素。它可以让你从 90% 的 JDBC `ResultSets` 数据提取代码中解放出来，并在一些情形下允许你进行一些 JDBC 不支持的操作。

```xml
<!--结果集映射，显式使用外部的 resultMap-->
<resultMap id="UsersMap" type="Users">
    <!--普通字段映射-->
    <result column="name" property="difname"/>
    <result column="age" property="age"/>
</resultMap>

<!--根据age查询用户-->
<select id="getUserByAge" resultMap="UsersMap">
    select * from Test where age = #{age}
</select>
```

**ResultMap参数说明**：
- `id`：结果映射的唯一标识
- `type`：映射到的Java类型
- `column`：数据库表中的列名
- `property`：Java对象中的属性名

> [!CAUTION]
>
> 上述语句只是简单地将所有的列映射到 HashMap 的键上，这由 resultType 属性指定。虽然在大部分情况下都够用，但是 HashMap 并不是一个很好的领域模型。你的程序更可能会使用 JavaBean 或 POJO（Plain Old Java Objects，普通老式 Java 对象）作为领域模型。MyBatis 对两者都提供了支持

- ***如果这个世界总是这么简单就好了。（摘自官网，官网后面介绍了高级结果映射，我们后面再介绍）***

---

## 6. 日志 📝

### 6.1 日志工厂

如果一个数据库操作出现异常，我们需要排错，这时候就需要日志了。

#### 6.1.1 支持的日志实现

还记得settings的loglmpl吗：

![image-20251102102351500](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251102102351500.png)

> [!TIP]
> 先看标准日志实现STDOUT_LOGGING

### 6.2 标准日志输出（STDOUT_LOGGING）

#### 6.2.1 配置标准日志

```xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

#### 6.2.2 日志输出分析

运行test后，观察日志输出：

![image-20251102103251464](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251102103251464.png)

**日志顺序**：
1. **基本设置** - 连接信息、事务配置
2. **传入参数** - SQL参数值
3. **查询结果** - 返回的数据
4. **返回连接池** - 资源释放

### 6.3 Log4j2 日志配置 🪵

> Log4j是Apache的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是控制台、文件、GUI组件，甚至是套接口服务器、NT的事件记录器、UNIX、守护进程等；我们也可以控制每一条日志的输出格式；通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。

#### 6.3.1 导入依赖

```xml
<!--引入log4j2的包-->
<dependencies>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.17.1</version>
    </dependency>
    <!-- 添加log4j2-api依赖 -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.17.1</version>
    </dependency>
</dependencies>
```

> [!NOTE]
> 如果你比较懒，直接用AI的话~~（面向AI编程）~~版本可能是因为漏洞被枪毙的版本（你重载Maven后IDEA也会提示你），所以自己手动修改下

#### 6.3.2 创建配置文件

在resources下创建log4j2.properties文件，内容如下，直接CV：

```properties
# ========================
# Log4j2 基础配置
# ========================

# 配置状态级别
status = error
dest = err
name = PropertiesConfig

# 监控间隔（秒）
monitorInterval = 30

# ========================
# 过滤器配置
# ========================
filter.threshold.type = ThresholdFilter
filter.threshold.level = debug

# ========================
# Appenders 配置
# ========================

# 控制台输出
appender.console.type = Console
appender.console.name = console
appender.console.target = SYSTEM_OUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n

# ========================
# Log4j2 基础配置
# ========================

# 配置状态级别
status = error
dest = err
name = PropertiesConfig

# 监控间隔（秒）
monitorInterval = 30

# ========================
# 过滤器配置
# ========================
filter.threshold.type = ThresholdFilter
filter.threshold.level = debug

# ========================
# Appenders 配置
# ========================

# 控制台输出
appender.console.type = Console
appender.console.name = console
appender.console.target = SYSTEM_OUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n

# 彩色控制台输出（如果终端支持）
appender.console_color.type = Console
appender.console_color.name = console_color
appender.console_color.target = SYSTEM_OUT
appender.console_color.layout.type = PatternLayout
appender.console_color.layout.pattern = %d{yyyy-MM-dd HH:mm:ss.SSS} %style{[%t]}{magenta} %highlight{%-5level}{FATAL=bg_red, ERROR=red, WARN=yellow, INFO=green, DEBUG=blue, TRACE=cyan} %style{%logger{36}}{cyan} - %msg%n

# 主日志文件 - 所有级别
appender.file_all.type = RollingFile
appender.file_all.name = file_all
appender.file_all.fileName = logs/mybatis-app.log
appender.file_all.filePattern = logs/mybatis-app-%d{yyyy-MM-dd}-%i.log.gz
appender.file_all.layout.type = PatternLayout
appender.file_all.layout.pattern = %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
appender.file_all.policies.type = Policies
appender.file_all.policies.time.type = TimeBasedTriggeringPolicy
appender.file_all.policies.time.interval = 1
appender.file_all.policies.time.modulate = true
appender.file_all.policies.size.type = SizeBasedTriggeringPolicy
appender.file_all.policies.size.size = 100MB
appender.file_all.strategy.type = DefaultRolloverStrategy
appender.file_all.strategy.max = 30

# 错误日志文件 - 只记录 ERROR 级别
appender.file_error.type = RollingFile
appender.file_error.name = file_error
appender.file_error.fileName = logs/mybatis-app-error.log
appender.file_error.filePattern = logs/mybatis-app-error-%d{yyyy-MM-dd}-%i.log.gz
appender.file_error.layout.type = PatternLayout
appender.file_error.layout.pattern = %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
appender.file_error.filter.threshold.type = ThresholdFilter
appender.file_error.filter.threshold.level = error
appender.file_error.policies.type = Policies
appender.file_error.policies.time.type = TimeBasedTriggeringPolicy
appender.file_error.policies.time.interval = 1
appender.file_error.policies.time.modulate = true
appender.file_error.policies.size.type = SizeBasedTriggeringPolicy
appender.file_error.policies.size.size = 50MB
appender.file_error.strategy.type = DefaultRolloverStrategy
appender.file_error.strategy.max = 30

# MyBatis SQL 日志文件
appender.sql_file.type = RollingFile
appender.sql_file.name = sql_file
appender.sql_file.fileName = logs/sql.log
appender.sql_file.filePattern = logs/sql-%d{yyyy-MM-dd}-%i.log.gz
appender.sql_file.layout.type = PatternLayout
appender.sql_file.layout.pattern = %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
appender.sql_file.policies.type = Policies
appender.sql_file.policies.time.type = TimeBasedTriggeringPolicy
appender.sql_file.policies.time.interval = 1
appender.sql_file.policies.time.modulate = true
appender.sql_file.policies.size.type = SizeBasedTriggeringPolicy
appender.sql_file.policies.size.size = 50MB
appender.sql_file.strategy.type = DefaultRolloverStrategy
appender.sql_file.strategy.max = 30

# ========================
# Loggers 配置
# ========================

# Root Logger
rootLogger.level = info
rootLogger.appenderRef.console.ref = console_color
rootLogger.appenderRef.file_all.ref = file_all
rootLogger.appenderRef.file_error.ref = file_error

# MyBatis 相关日志配置
logger.mybatis.name = org.apache.ibatis
logger.mybatis.level = debug
logger.mybatis.additivity = false
logger.mybatis.appenderRef.console.ref = console_color
logger.mybatis.appenderRef.sql_file.ref = sql_file

# SQL 日志（具体 Mapper 包路径）
logger.sql.name = com.example.mapper
logger.sql.level = debug
logger.sql.additivity = false
logger.sql.appenderRef.console.ref = console_color
logger.sql.appenderRef.sql_file.ref = sql_file

# JDBC 连接日志
logger.jdbc.name = java.sql
logger.jdbc.level = debug
logger.jdbc.additivity = false
logger.jdbc.appenderRef.console.ref = console_color
logger.jdbc.appenderRef.sql_file.ref = sql_file

logger.jdbc_connection.name = java.sql.Connection
logger.jdbc_connection.level = debug
logger.jdbc_connection.additivity = false
logger.jdbc_connection.appenderRef.console.ref = console_color
logger.jdbc_connection.appenderRef.sql_file.ref = sql_file

logger.jdbc_statement.name = java.sql.Statement
logger.jdbc_statement.level = debug
logger.jdbc_statement.additivity = false
logger.jdbc_statement.appenderRef.console.ref = console_color
logger.jdbc_statement.appenderRef.sql_file.ref = sql_file

logger.jdbc_resultset.name = java.sql.ResultSet
logger.jdbc_resultset.level = debug
logger.jdbc_resultset.additivity = false
logger.jdbc_resultset.appenderRef.console.ref = console_color
logger.jdbc_resultset.appenderRef.sql_file.ref = sql_file

# Spring 框架日志（如果使用）
logger.spring.name = org.springframework
logger.spring.level = info
logger.spring.additivity = false
logger.spring.appenderRef.console.ref = console_color

# 应用代码日志
logger.app.name = com.example
logger.app.level = debug
logger.app.additivity = false
logger.app.appenderRef.console.ref = console_color
logger.app.appenderRef.file_all.ref = file_all
```

#### 6.3.3 配置MyBatis使用Log4j2

```xml
<settings>
    <setting name="logImpl" value="LOG4J2"/>
</settings>
```

#### 6.3.4 在代码中使用Log4j2

<!--会让你导包，记得别导成一代了-->

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDaoTest {
    // 获取日志记录器
    private static final Logger logger = LogManager.getLogger(UserDaoTest.class);
    
    @Test
    public void testLog4j2() {
        logger.info("这是一条info日志");
        logger.debug("这是一条debug日志");
        logger.error("这是一条error日志");
    }
}
```

直接测试，你会发现有些内容和标准日志差不多，但多了很多前缀（我只截一部分）

![image-20251102112307497](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251102112307497.png)

**日志级别**：

- `TRACE` < `DEBUG` < `INFO` < `WARN` < `ERROR` < `FATAL`

---

## 7. 分页 📄

分页就是为了减少数据量，提高查询效率。

### 7.1 SQL中的LIMIT分页

**基本语法**：
```sql
SELECT * FROM user LIMIT startIndex, pageSize;
```

**示例**：
```sql
SELECT * FROM user LIMIT 0, 10;  -- 从第0条开始，取10条数据
SELECT * FROM user LIMIT 10;     -- 取前10条数据（简写形式）
```

### 7.2 使用Mybatis实现分页

#### 7.2.1 接口定义

```java
// 分页查询
List<Users> getUserByLimit(Map<String, Object> map);
```

#### 7.2.2 Mapper配置

```xml
<!--分页查询-->
<select id="getUserByLimit" parameterType="map" resultType="Users">
    select * from Test limit #{startIndex}, #{pageSize}
</select>
```

**参数说明**：
- `startIndex`：表示查询结果的起始位置，也就是从哪一条数据开始获取。它通常是一个非负整数，**从0或1开始计数**（具体取决于框架或API的设计）
- `pageSize`：表示每页显示的数据条数，即一次查询最多返回多少条数据。它决定了每页数据的容量大小

#### 7.2.3 测试代码

```java
@Test
public void getUserByLimit() {
    SqlSession sqlSession = utils.getSqlSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);

    HashMap<String, Object> map = new HashMap<>();
    map.put("startIndex", 0);   // 从第0条开始
    map.put("pageSize", 2);     // 每页2条数据
    
    List<Users> usersList = userDao.getUserByLimit(map);
    for (Users users : usersList) {
        System.out.println(users);
    }
    sqlSession.close();
}
```

当然以上本质上还是在写SQL，~~那我学Mybatis干嘛？~~

### 7.3 RowBounds分页（了解为主） 🎯

**核心：面向对象分页**

#### 7.3.1 接口定义

```java
// 分页查询 - RowBounds方式
List<Users> getUserByRowBounds();
```

#### 7.3.2 Mapper配置

```xml
<select id="getUserByLimit" resultType="Users">
    select * from Test  <!-- 注意：这里没有limit语句 -->
</select>
```

#### 7.3.3 测试代码

```java
@Test
public void getUserByRowBounds() {
    SqlSession sqlSession = utils.getSqlSession();
    
    // RowBounds实现 - 参数：offset, limit
    RowBounds rowBounds = new RowBounds(0, 10);
    
    // 通过java实现分页
    List<Users> usersList = sqlSession.selectList(
        "com.amiera.utils.UserDao.getUserByLimit", 
        null, 
        rowBounds
    );
    
    for (Users users : usersList) {
        System.out.println(users);
    }
    sqlSession.close();
}
```

### 7.4 分页插件 🔌

对于复杂的分页需求，可以使用分页插件：

**推荐插件**：
- [PageHelper](https://github.com/pagehelper/Mybatis-PageHelper) - Mybatis通用分页插件

**优势**：
- 支持多种数据库
- 使用简单，功能强大
- 社区活跃，文档完善

---

## 8. 使用注解开发 🖊️

### 8.1 面向接口编程

<!--整点文的-->

#### 8.1.1 什么是面向接口？

- 大家之前都学过面向对象编程，也学习过接口，但在真正的开发中，很多时候我们会选择面向接口编程。
- **根本原因：** **<u>解耦</u>**、可拓展、提高复用性。在分层开发中，上层不用关心具体的实现，大家都遵守共同的标准，使得开发变得容易，规范性更好。

- 在一个面向对象的系统中，系统的各种功能是由许许多多的不同对象协作完成的。在这种情况下，各个对象内部是如何实现自己的对系统设计人员来说不那么重要了。

- 而各个对象之间的协作关系则成为系统设计的关键。小到不同类之间的通信，大到各模块之间的交互，在系统设计之初都是要着重考虑的，这也是系统设计的主要工作内容。面向接口编程就是指按照这种思想来编程。

#### 8.1.2 接口的理解

- 接口从更深层次的理解，应是定义（规范，约束）与实现（职责分离的原则）的分离。
- 接口的本质反映了系统设计人员对系统的抽象理解。

- 接口应有两类：
  - 第一类是对一个个体的抽象，它可对应为一个抽象体(abstract class)；
  - 第二类是对一个个体某一方面的抽象，即形成一个抽象面（interface）；
  - 一个体有可能有多个抽象面，抽象体与抽象面是有区别的。

#### 8.1.3三个面向的区别

- **面向对象：** 指我们考虑问题时，以对象为单位，考虑它的属性及方法。

- **面向过程：** 指我们考虑问题时，以一个具体的流程（事务过程）为单位，考虑它的实现。

- **接口设计与非接口设计：** 是针对复用技术而言的，与面向对象（过程）不是一个问题，更多的体现就是对系统整体的架构。

### 8.2 通过注解调用SQL

**本质：反射；底层：动态代理**

#### 8.2.1 准备工作

删除之前的XML配置，保持接口干净：

```java
// UserMapper接口
package com.amiera.utils;

public interface UserMapper {
}
```

在mybatis-config.xml中改用class绑定：

```xml
<!--绑定接口-->
<mappers>
    <mapper class="com.amiera.utils.UserMapper"/>
</mappers>
```

#### 8.2.2 使用注解查询

```java
import org.apache.ibatis.annotations.Select;
import java.util.List;

// 使用注解调用SQL查找所有用户
public interface UserMapper {
    @Select("select * from test")
    List<Users> getAllUsers();
}
```

#### 8.2.3 测试代码

```java
@Test
public void getAllUsers() {
    SqlSession sqlSession = utils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    List<Users> usersList = userMapper.getAllUsers();
    for (Users users : usersList) {
        System.out.println(users);
    }
    sqlSession.close();
}
```

- 我们看下输出栏，可以注意到PART5中提到的名称不一致问题又出现了，原因是当时我们使用了结果集映射Map，但现在我们全部删删删了，这个问题在使用注解下很难解决

  ![image-20251104203144684](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251104203144684.png)

> 使用注解来映射简单语句会使代码显得更加简洁，但对于稍微复杂一点的语句，Java 注解不仅力不从心，还会让本就复杂的 SQL 语句更加混乱不堪。 因此，如果你需要做一些很复杂的操作，最好用 XML 来映射语句。

![image-20251104210802925](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251104210802925.png)

### 8.3 Mybatis流程总结 🔄

**执行流程**：
1. **Resources获取加载全局配置文件**

   ```xml
   <mapper resource="com.amiera.utils.UserMapper"/>
   ```

2. **SqlSessionFactoryBuilder实例化**
   - 解析文件流XMLConfigBuilder

   - Configuration所有配置信息

     ```java
     sqLSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
     ```

3. **SqlSessionFactory实例化**

   - transaction事务管理
   - 创建executor执行器

4. **创建SqlSession**

5. **实现CRUD**

6. **事务提交/回滚**

7. **关闭资源**

### 8.4 注解下的CRUD

#### 8.4.1 自动提交事务配置

我们可以在工具类创建的时候实现自动提交事务，我们可以看到，和之前代码相比，我们在openSession中加入了参数，事实上，当我们填入参数时，IDEA会给我们提示**autoCommit**

```java
public static SqlSession getSqlSession() {
    // 设置autoCommit为true，自动提交事务
    return sqlSessionFactory.openSession(/*autoCommit*/true);
}
```

#### 8.4.2 多参数传递 - @Param注解

在接口中，原来使用查询时，只能使用一个参数，现在我们可以使用**@Param**完成多参数录入

```java
// 使用@Param注解传递多个参数
Users getUserById(@Param("age") int age, @Param("difname") String difname);
```

> [!CAUTION]
> 使用注解调用select时，查询参数是根据`@Param()`括号中的名称来的，而不是方法参数名，即如果表中没有括号中的参数，就会报错（而不是看int后面的age）

#### 8.4.3 完整的CRUD注解

```java
// 通过注解实现CRUD
public interface UserMapper {
    
    @Select("select * from test where age = #{age}")
    Users getUserById(@Param("age") int age);
    
    @Insert("insert into test(age, name) values(#{age}, #{name})")
    int insertUser(@Param("age") int age, @Param("name") String name);
    
    @Delete("delete from test where age = #{age}")
    int deleteUser(@Param("age") int age);
    
    @Update("update test set name = #{name} where age = #{age}")
    int updateUser(@Param("name") String name, @Param("age") int age);
}
```

#### 8.4.4 测试示例

- 其他功能也差不多，我们直接总结下格式

  > [!NOTE]
  >
  > @CRUD对应功能键(sql语句)
  >
  > 参数类型  方法名(表);

- 举个例子，我们使用注释实现修改

  ```java
  @Update("update test set name = #{difname} where age = #{age}")
  int updateUser(@Param("difname") String difname, @Param("age") int age);
  ```

  这时在test语句中，我们可以发现短了不少，整个sql功能调用实际只用了一行 `userMapper.updateUser("张三", 18);`

  ```java
  @Test
  public void testUpdateUser() {
      SqlSession sqlSession = utils.getSqlSession();
      UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
      userMapper.updateUser("张三", 18);  // 一行代码完成更新
      sqlSession.close();
  }
  ```

- 总结下：

  ```java
  //通过注解实现CRUD
   @Select("select * from test where age = #{age}")
   Users getUserById(@Param("age") int age);
  
   @Insert("insert into test(age,name) values(#{age},#{name})")
   int insertUser(@Param("age") int age, @Param("name") String name);
  
   @Delete("delete from test where age = #{age}")
   int deleteUser(@Param("age") int age);
  
   @Update("update test set name = #{name} where age = #{age}")
   int updateUser(@Param("name") String name, @Param("age") int age);
  ```

> [!CAUTION]
> **关于@Param注解**：
>
> - 需要加上基本类型参数或者String类型
> - 引用类型不需要加
> - 如果只有一个基本类型，可以忽略，但最好加上
> - 在SQL中使用的就是`@Param("")`中设定的属性名

---

## 9. Lombok开发 🛠️

> Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java. Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
>
> Project Lombok 是一款 Java 库，它能够自动集成至您的编辑器与构建工具，从而优化 Java 开发体验。通过简单注解，您无需再手动编写 getter 或 equals 方法；仅需一个注解即可为类生成功能齐全的构建器，自动创建日志变量，并实现诸多其他便捷功能。

我们在插件中查找，可以看到已经自动下好了（如果你是比较新版本的IDEA）

![image-20251105190758369](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251105190758369.png)

### 9.1 使用步骤

#### 9.1.1 导入依赖

- 在Maven网站中搜索Lombok，得到依赖代码（或者直接用AI）

<!--记得移动</dependencies>位置以及重载Maven-->

```xml
<!-- 添加lombok依赖 -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.42</version>
    <scope>provided</scope>
</dependency>
```

#### 9.1.2 常用注解说明

**常用注解**：
- `@Getter` and `@Setter`：自动生成getter/setter方法
- `@ToString`：自动生成toString方法
- `@EqualsAndHashCode`：自动生成equals和hashCode方法
- `@AllArgsConstructor`：生成全参构造函数
- `@NoArgsConstructor`：生成无参构造函数
- `@Data`：集合了以上所有常用功能

#### 9.1.3 使用示例

**改造前的Users类**：
```java
public class Users {
    private String name;
    private int age;
    
    // 构造方法、getter、setter、toString等需要手动编写
}
```

**使用Lombok后的Users类**：

现在我们重写Users类，加上注解

**<u>我们甚至不用写任何方法！！！</u>**

```java
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  // 全参构造
@NoArgsConstructor   // 无参构造
public class Users {
    private String name;
    private int age;
}
```

> [!CAUTION]
>
> 我们发现缺失无参构造，当添加有参构造`@AllArgsConstructor`时，无参方法消失了，所以我们需要再添加无参构造`@NoArgsConstructor`

#### 9.1.4 验证生成的方法

使用**Alt+7**查看类结构，可以看到Lombok已经帮我们生成了所有方法：

![image-20251105192122805](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251105192122805.png)

### 9.2 对于Lombok的评价 ⚖️

#### **Lombok 的优点** ✅

1.  **提高开发效率**：通过注解自动生成常用的方法，如 getter、setter、equals、hashCode、toString 等，减少了大量的重复编码工作。
2.  **使代码更简洁**：消除了模型类（如 POJO）中的样板代码，让业务逻辑更加清晰易读。
3.  **提升代码可维护性**：当类新增字段时，无需手动修改相关方法（如 toString 和 equals），Lombok 会在编译时自动更新，减少了出错的可能。

#### **Lombok 的缺点与注意事项** ❌

1.  **依赖 IDE 插件**：团队成员必须在他们的 IDE（如 IntelliJ IDEA 或 Eclipse）中安装对应的 Lombok 插件，否则代码将显示编译错误或大量红色警告，影响开发。
2.  **强侵入性**：项目必须依赖 Lombok 的 Jar 包才能正常编译和运行。这在一定程度上绑架了项目技术栈。
3.  **工作原理特殊**：Lombok 并非通过常规的 API 调用工作，而是在**编译时**直接修改程序的**抽象语法树（AST）** 来注入代码。这种“黑魔法”式的工作机制可能让一些开发者感到困惑，并可能带来潜在的兼容性风险。
4.  **可能隐藏细节**：自动生成代码虽然方便，但也让新手开发者忽略了这些基础方法（如 equals）的正确写法，不利于理解底层原理。

---

## 10. 多对一处理 👥➡️👤

### 10.1 复杂查询环境搭建

#### 10.1.1 数据库表设计

根据以下表结构分析关系：
- **学生表**：多个学生关联一个老师（多对一）
- **教师表**：一个老师集合很多学生（一对多）

```sql
-- 创建教师表
CREATE TABLE `teacher` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 插入教师数据
INSERT INTO teacher(`id`, `name`) VALUES (1, '秦老师');

-- 创建学生表
CREATE TABLE `student` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  `tid` INT(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fktid` (`tid`),
  CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 插入学生数据
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (1, '小明', 1); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (2, '小红', 1); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (3, '小张', 1); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (4, '小李', 1); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES (5, '小王', 1);
```

#### 10.1.2 项目结构搭建

重新创建项目，拷贝后删除原来的接口和实体类：

![image-20251105203751890](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251105203751890.png)

**Student实体类**：
```java
import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    
    // 学生关联老师 - 多对一关系
    private Teacher teacher;
}
```

**Teacher实体类**：
```java
import lombok.Data;

@Data
public class Teacher {
    private int id;
    private String name;
}
```

**TeacherMapper接口**：
```java
package com.amiera.Mapper;

import com.amiera.users.Teacher;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface TeacherMapper {
    @Select("select * from teacher")
    List<Teacher> getTeacher();
}
```

**TeacherMapper.xml**：
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.amiera.Mapper.TeacherMapper">
</mapper>
```

**核心配置文件**：
```xml
<mappers>
    <mapper class="com.amiera.Mapper.StudentMapper"/>
    <mapper class="com.amiera.Mapper.TeacherMapper"/>
</mappers>
```

**测试类**：
```java
package com.amiera.utils;

import com.amiera.Mapper.TeacherMapper;
import com.amiera.users.Teacher;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        SqlSession sqlSession = utils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> teacher = teacherMapper.getTeacher();
        System.out.println(teacher);
        sqlSession.close();
    }
}
```

### 10.2 关联查询实现

现在我们想实现通过查询学生信息得到对应老师信息。

#### 10.2.1 常规查询的问题

**StudentMapper接口**：
```java
List<Student> getStudents();
```

**Mapper配置**：
```xml
<mapper namespace="com.amiera.Mapper.StudentMapper">
    <select id="getStudents" resultType="com.amiera.users.Student">
        select * from student
    </select>
</mapper>
```

**测试代码**：
```java
@Test
public void testStudents() {
    SqlSession sqlSession = utils.getSqlSession();
    StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
    List<Student> students = studentMapper.getStudents();
    for (Student student : students) {
        System.out.println(student);
    }
    sqlSession.close();
}
```

**问题**：输出中teacher字段为null

![image-20251105211438689](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251105211438689.png)

#### 10.2.2 解决方案一：子查询嵌套处理 🧩

**核心思路**：
1. 查询所有学生信息
2. 根据查询的学生的tid，查找对应老师
3. 在MyBatis中，`<association>`标签主要用于处理**对象关联映射**，也就是解决Java对象之间的一对一关系（one-to-one）。它允许你在一个对象中嵌套另一个对象，并将数据库中的关联数据正确映射到这些对象中。

**Mapper配置**：
```xml
<select id="getStudents" resultMap="studentResultMap">
    select * from student
</select>

<resultMap id="studentResultMap" type="com.amiera.users.Student">
    <!-- 映射Student基本属性 -->
    <id property="id" column="id"/>
    <result property="name" column="name"/>
    
    <!-- 
        association: 处理多对一关联
        property: Java对象中的属性名
        column: 传递给子查询的数据库列名
        javaType: 关联对象的Java类型
        select: 子查询的ID
    -->
    <association property="teacher" column="tid" javaType="com.amiera.users.Teacher"
                 select="getTeacherById"/>
</resultMap>

<select id="getTeacherById" resultType="com.amiera.users.Teacher">
    select * from teacher where id = #{id}
</select>
```

> [!NOTE]
> ### property和column属性的含义
>
> #### 1. property属性
>
> `property`属性表示**Java实体类中的属性名**，用于指定要将查询结果映射到哪个Java对象的哪个属性上。这里的`property="teacher"`表示：将查询到的老师数据映射到`Student`类的`teacher`属性上。
>
> ```xml
> <association property="teacher" column="tid" javaType="com.amiera.users.Teacher">
> ```
>
> #### 2. column属性
>
> `column`属性表示**数据库表中的列名**，用于指定从数据库中哪个列获取数据，以便进行关联查询或映射。在代码中，`column="tid"`表示：使用`student`表中的`tid`列（老师ID）来关联`teacher`表中的对应数据。

#### 10.2.3 解决方案二：结果嵌套处理 🔗

**核心思路**：
- 通过SQL的多表关联一次性查询出所有需要的数据
- 使用别名避免字段冲突
- 在resultMap中通过`<association>`标签映射关联对象

**Mapper配置**：
```xml
<!-- 按照结果嵌套处理 -->
<select id="getStudents" resultMap="studentResultMap">
    select 
        s.id as student_id, 
        s.name as student_name, 
        t.id as teacher_id,
        t.name as teacher_name
    from student s, teacher t
    where s.tid = t.id
</select>

<resultMap id="studentResultMap" type="com.amiera.users.Student">
    <!-- 映射Student基本属性 -->
    <id property="id" column="student_id"/>
    <result property="name" column="student_name"/>
    
    <!-- 映射Teacher关联对象 -->
    <association property="teacher" javaType="com.amiera.users.Teacher">
        <id property="id" column="teacher_id"/>
        <result property="name" column="teacher_name"/>
    </association>
</resultMap>
```

---

## 11. 一对多处理 👤➡️👥

### 11.1 环境搭建

#### 11.1.1 修改实体类

一个老师拥有多个学生，对于老师而言，就是一对多的关系。

**Student类**：

```java
import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private int tid;  // 教师ID
}
```

**Teacher类**：

此时student类是被对应关系，只需要正常的参数，而teacher类则需要对应学生（因为学生不止一个，所以我们使用集合）：

```java
import lombok.Data;
import java.util.List;

@Data
public class Teacher {
    private int id;
    private String name;
    // 一对多关系：一个老师对应多个学生
    private List<Student> students;
}
```

#### 11.1.2 基础查询测试

其他的就是在teacher的文件下（接口，xml）添加正常环境（前面都给了，自己改下参数）我们这里可以把@test改的更短

**TeacherMapper接口**：
```java
public interface TeacherMapper {
    Teacher getTeacher(@Param("tid") int tid);
}
```

**测试代码**：
```java
@Test
public void testTeacher() {
    SqlSession sqlSession = utils.getSqlSession();
    TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
    Teacher teacher = teacherMapper.getTeacher(1);
    System.out.println(teacher);
    sqlSession.close();
}
```

**问题**：输出中students字段为null

![image-20251107185030634](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251107185030634.png)

### 11.2 结果集映射解决方案

#### 11.2.1 结果嵌套处理方案

我们使用看起来更阳间的思路二即结果嵌套处理，总体大差不差，注意因为集合的缘故，我们引入了一个新参数`ofType`

**Mapper配置**：
```xml
<!-- 按照结果嵌套处理 -->
<select id="getTeacher" resultMap="teacherResultMap">
    select 
        t.id as teacher_id, 
        t.name as teacher_name, 
        s.id as student_id, 
        s.name as student_name
    from teacher t, student s
    where s.tid = t.id and t.id = #{tid}
</select>

<resultMap id="teacherResultMap" type="com.amiera.users.Teacher">
    <id property="id" column="teacher_id"/>
    <result property="name" column="teacher_name"/>
    
    <!-- 
        collection: 处理一对多关联
        property: Java对象中的集合属性名
        ofType: 集合中元素的类型
    -->
    <collection property="students" ofType="com.amiera.users.Student">
        <id property="id" column="student_id"/>
        <result property="name" column="student_name"/>
        <result property="tid" column="teacher_id"/>
    </collection>
</resultMap>
```

#### 11.2.2 子查询嵌套处理方案

但是⑩还是要吃，下面是子查询嵌套处理方法：

**Mapper配置**：
```xml
<!-- 按照子查询处理 -->
<select id="getTeacher" resultMap="teacherResultMap">
    select * from teacher where id = #{tid}
</select>

<resultMap id="teacherResultMap" type="com.amiera.users.Teacher">
    <id property="id" column="id"/>
    <result property="name" column="name"/>
    
    <!--
        collection: 一对多关联映射
        property: 集合属性名
        javaType: 集合类型
        ofType: 集合元素类型  
        column: 传递给子查询的参数
        select: 子查询ID
    -->
    <collection property="students" javaType="java.util.List" 
                ofType="com.amiera.users.Student" column="id" 
                select="getStudentByTeacherId"/>
</resultMap>

<select id="getStudentByTeacherId" resultType="com.amiera.users.Student">
    select * from student where tid = #{tid}
</select>
```

> [!CAUTION]
> 注意：在子查询方案中，如果查询语句没有包含老师id，输出中老师的id会为默认值0
>
> ![image-20251107192355840](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251107192355840.png)

### 11.3 关联关系映射总结 📚

#### 11.3.1 核心概念对比

##### 关联（association） - 多对一关系

- **应用场景**：多个学生关联一个老师（多方关联一方）

- **配置方式**：

  ```xml
  <!-- 结果嵌套处理 -->
  <association property="teacher" javaType="Teacher">
      <result property="name" column="teacher_name"/>
  </association>
  
  <!-- 子查询嵌套处理 -->
  <association property="teacher" column="tid" javaType="Teacher" 
               select="getTeacherById"/>
  ```

- **核心属性**：

  - `property`：实体类中的属性名
  - `javaType`：关联对象的类型
  - `column`：传递给子查询的列名
  - `select`：子查询的ID

##### 集合（collection） - 一对多关系

- **应用场景**：一个老师拥有多个学生（一方包含多方）

- **配置方式**：

  ```xml
  <!-- 结果嵌套处理 -->
  <collection property="students" ofType="Student">
      <result property="name" column="student_name"/>
  </collection>
  
  <!-- 子查询嵌套处理 -->
  <collection property="students" ofType="Student" column="id" 
              select="getStudentsByTeacherId"/>
  ```

- **核心属性**：

  - `property`：实体类中的集合属性名
  - `ofType`：集合中元素的类型
  - `column`：传递给子查询的参数列
  - `select`：子查询的ID

#### 11.3.2 javaType vs ofType 深度解析

**javaType的使用场景**：
- 用于`association`标签中，指定关联对象的类型
- 用于普通属性的类型指定

```xml
<association property="teacher" javaType="com.example.Teacher">
```

**ofType的使用场景**：
- 专门用于`collection`标签，指定集合中元素的类型
- 处理List、Set等集合的泛型类型

```xml
<collection property="students" ofType="com.example.Student">
```

#### 11.3.3 实际开发建议 💡

**SQL可读性优化**：

- 多表关联查询时使用明确的别名
- 复杂查询拆分为多个简单查询

```sql
-- 推荐写法：使用明确的别名和JOIN语法
SELECT 
    s.id as student_id, 
    s.name as student_name, 
    t.name as teacher_name
FROM student s 
INNER JOIN teacher t ON s.tid = t.id
```

**属性名与字段名映射**

- 使用`resultMap`明确指定映射关系

- 避免因命名不规范导致的映射失败

- **解决方案**：

  ```xml
  <resultMap id="StudentMap" type="Student">
      <result property="studentName" column="name"/>
      <result property="studentAge" column="age"/>
  </resultMap>
  ```

**性能优化建议**：

- 对于大数据量的关联查询，考虑使用延迟加载
- 复杂关联关系考虑分步查询
- 合理使用缓存减少数据库访问

**常见问题排查**：
- 检查`resultMap`的`type`属性是否正确
- 验证`property`与实体类属性名是否一致
- 确认`column`与数据库列名是否匹配

> [!IMPORTANT]
> **最佳实践总结**：在多对一和一对多关系中，正确使用`association`和`collection`标签，配合适当的`javaType`和`ofType`配置，可以大大简化复杂对象关系的映射工作。

---

## 12. 动态SQL ⚡

> 动态 SQL 是 MyBatis 的强大特性之一。如果你使用过 JDBC 或其它类似的框架，你应该能理解根据不同条件拼接 SQL 语句有多痛苦，例如拼接时要确保不能忘记添加必要的空格，还要注意去掉列表最后一个列名的逗号。利用动态 SQL，可以彻底摆脱这种痛苦。
>
> 使用动态 SQL 并非一件易事，但借助可用于任何 SQL 映射语句中的强大的动态 SQL 语言，MyBatis 显著地提升了这一特性的易用性。

### 12.1 环境搭建

#### 12.1.1 创建数据表

```sql
CREATE TABLE `blog`(
  `id` VARCHAR(50) NOT NULL COMMENT '博客id',
  `title` VARCHAR(100) NOT NULL COMMENT '博客标题',
  `author` VARCHAR(30) NOT NULL COMMENT '博客作者',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `views` INT(30) NOT NULL COMMENT '浏览量'
) ENGINE=INNODB DEFAULT CHARSET=utf8
```

#### 12.1.2 项目结构

然后就是实体类，接口，xml，测试类（依旧四件套）

![image-20251107195334527](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251107195334527.png)

然后是具体代码，别忘记修改xml下的路径

**Blog实体类**：

```java
import lombok.Data;
import java.util.Date;

@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date createTime;
    private int views;
}
```

**BlogMapper接口**：
```java
public interface BlogMapper {
    int addBlog(Blog blog);
}
```

**BlogMapper.xml**：
```xml
<insert id="addBlog" parameterType="com.amiera.pojo.Blog">
    insert into blog (id, title, author, create_time, views)
    values (#{id}, #{title}, #{author}, #{createTime}, #{views});
</insert>
```

**IDUtils工具类**：

另外，我们需要在untils包下再建一个IDuntils,用于后面生成随机数ID

```java
import java.util.UUID;

//压制警告（掩耳盗铃这块）
@SuppressWarnings("all")
public class IDUtils {
    // 生成随机ID，去除UUID中的横线
    public static String getID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    @Test
    public void test(){
        System.out.println(IDUtils.getID());
    }
}
```

**测试代码**：
```java
import com.amiera.Mapper.BlogMapper;
import com.amiera.pojo.Blog;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.Date;

public class MyTest {


    @Test
    public void addBlogTest() {
        SqlSession sqlSession = utils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(IDutils.getID());
        blog.setTitle("Mybatis");
        blog.setAuthor("狂神说");
        blog.setCreateTime(new Date());
        blog.setViews(9999);

        mapper.addBlog(blog);

        blog.setId(IDutils.getID());
        blog.setTitle("Java");
        mapper.addBlog(blog);

        blog.setId(IDutils.getID());
        blog.setTitle("Spring");
        mapper.addBlog(blog);

        blog.setId(IDutils.getID());
        blog.setTitle("微服务");
        mapper.addBlog(blog);

        sqlSession.close();

    }
}
```

### 12.2 动态SQL基础标签

#### 12.2.1 `<where>` 标签

**问题场景**（没有 `<where>` 标签时）：
```xml
<!-- 问题代码 -->
SELECT * FROM BLOG
WHERE
<if test="state != null"> state = #{state} </if>
<if test="title != null"> AND title like #{title} </if>
```

**会出现什么尴尬情况？**

1. **所有条件都为空时**：

   ```sql
   SELECT * FROM BLOG WHERE  -- 只有WHERE，没有条件，语法错误！
   ```

2. **第一个条件为空，第二个不为空时**：

   ```sql
   SELECT * FROM BLOG WHERE AND title like 'xxx'  -- WHERE后面直接跟AND，语法错误！
   ```

##### 解决方案：使用 `<where>` 标签：

```xml
<!-- 智能解决方案 -->
SELECT * FROM BLOG
<where>
  <if test="state != null"> state = #{state} </if>
  <if test="title != null"> AND title like #{title} </if>
</where>
```

**`<where>` 标签的智能之处**：
- **自动判断**：只有子条件至少有一个成立时，才插入 `WHERE` 关键字
- **自动修剪**：自动去掉开头多余的 `AND` 或 `OR`
- **安全无忧**：永远不会生成语法错误的 SQL

**实际效果举例：**

- 只有 `title` 条件成立 → `SELECT * FROM BLOG WHERE title like 'xxx'`
- 所有条件都不成立 → `SELECT * FROM BLOG` （没有 WHERE）
- 所有条件都成立 → `SELECT * FROM BLOG WHERE state = 'ACTIVE' AND title like 'xxx'`

#### 12.2.2 `<set>` 标签

**问题场景**（更新语句的烦恼）：
```xml
<!-- 问题代码 -->
update Author
set
  <if test="username != null">username=#{username},</if>
  <if test="password != null">password=#{password},</if>
  <if test="email != null">email=#{email},</if>
```

**会出现的问题**：
如果最后一个条件 `email` 为空，SQL会变成：`update Author set username=?, password=?,`（末尾多逗号，语法错误！）

**解决方案**：
```xml
<!-- 智能解决方案 -->
update Author
<set>
  <if test="username != null">username=#{username},</if>
  <if test="password != null">password=#{password},</if>
  <if test="email != null">email=#{email},</if>
</set>
where id=#{id}
```

**`<set>` 标签的智能之处**：
- **自动插入 SET**：只有有更新字段时才添加 `SET` 关键字
- **自动去逗号**：智能去掉最后一个多余的逗号
- **完美拼接**：保证生成的 SQL 语法正确

#### 12.2.3 `<trim>` 标签

**自定义 `<where>` 功能**：
```xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ...
</trim>
```

**参数解释：**

- `prefix="WHERE"` → 在整个内容前加上 `WHERE`
- `prefixOverrides="AND |OR "` → 去掉开头多余的 `AND` 或 `OR`

**自定义 `<set>` 功能**：

```xml
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
```

**参数解释：**

- `prefix="SET"` → 在整个内容前加上 `SET`
- `suffixOverrides=","` → 去掉末尾多余的逗号

**`<trim>` 的完整能力**：

```xml
<trim 
  prefix="前缀" 
  suffix="后缀" 
  prefixOverrides="要删除的前缀" 
  suffixOverrides="要删除的后缀">
  你的动态内容
</trim>
```

**实际应用场景：**

- 需要更复杂的字符串处理时
- 现有的 `<where>` 和 `<set>` 不能满足需求时
- 想要自定义前后缀规则时

#### 12.2.4总结对比

| 标签      | 主要用途         | 智能功能                  | 相当于 trim 的写法                                  |
| --------- | ---------------- | ------------------------- | --------------------------------------------------- |
| `<where>` | 动态 WHERE 条件  | 自动加WHERE，去开头AND/OR | `<trim prefix="WHERE" prefixOverrides="AND \|OR ">` |
| `<set>`   | 动态 UPDATE 字段 | 自动加SET，去末尾逗号     | `<trim prefix="SET" suffixOverrides=",">`           |
| `<trim>`  | 万能字符串处理   | 自定义前后缀和修剪规则    | -                                                   |

**使用建议：**

- 优先使用 `<where>` 和 `<set>`，因为它们更简洁
- 只有在需要特殊处理时才使用 `<trim>`
- 记住这些标签都是为了让你的动态 SQL 更安全、更简洁！

### 12.3 SQL中的if条件判断

#### 12.3.1 基本用法

##### **核心功能解析：**

1. **条件判断机制**：`if`标签用于**根据条件动态拼接SQL片段**，类似于Java中的`if`语句
   - 当`test`属性中的表达式求值为`true`时，就会包含`if`标签内的SQL片段
   - 当表达式求值为`false`时，就会忽略该SQL片段
2. **并列执行逻辑**：多个`if`标签之间是**并列关系**，每个`if`标签都会独立进行条件判断
   - 只要条件满足，就会拼接对应的SQL片段
   - 所有满足条件的`if`标签都会被执行，而不是只执行第一个
3. **test属性表达式**：
   - `test`属性中使用的是OGNL表达式语言
   - 可以访问传入的参数对象的属性（如示例中的`title`和`author`）
   - 支持常见的比较操作符（`==`, `!=`, `>`, `<`等）和逻辑操作符（`&&`, `||`, `!`等）

**Mapper配置**：
```xml
<select id="queryBlog" parameterType="map" resultType="com.amiera.pojo.Blog">
    select * from blog
    <where>
        <!-- if标签：条件成立时拼接SQL片段 -->
        <if test="title != null">
            and title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </where>
</select>
```

**测试代码**：**通过map.put，程序输出title、author中指定的参数**

```java
@Test
public void queryBlogTest() {
    SqlSession sqlSession = utils.getSqlSession();
    BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
    
    Map map = new HashMap();
    map.put("title", "Mybatis");
    map.put("author", "狂神说");
    
    List<Blog> blogs = mapper.queryBlog(map);
    for (Blog blog : blogs) {
        System.out.println(blog);
    }
    sqlSession.close();
}
```

**输出结果**：
![image-20251108102641427](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251108102641427.png)

### 12.4 SQL中的choose选择语句

> 有时候，我们不想使用所有的条件，而只是想从多个条件中选择一个使用。针对这种情况，MyBatis 提供了 choose 元素，它有点像 Java 中的 switch 语句。

#### 12.4.1 核心功能解析

**选择性执行机制**：`choose`标签提供了"**只选其一**"的条件判断机制，类似于Java中的`switch-case`语句

- `choose`标签提供"**只选其一**"的条件判断机制
- 按顺序评估各个`<when>`标签中的条件
- **只要有一个`<when>`条件满足**，就执行该条件对应的SQL片段，并**忽略后续所有条件**

**结构组成**：
- `<choose>`：主标签，条件选择容器
- `<when>`：子标签，定义条件分支（类似case）
- `<otherwise>`：可选的子标签，所有条件都不满足时执行（类似default）

**与if标签的区别**：
- `if`标签：**并列关系**，满足条件就会拼接
- `choose`标签：**选择关系**，只会执行第一个满足条件的分支

#### 12.4.2 使用示例

**Mapper配置**：
```xml
<select id="queryBlogChose" parameterType="map" resultType="com.amiera.pojo.Blog">
    select * from blog
    <where>
        <choose>
            <!-- 第一个满足的条件会执行，后续条件忽略 -->
            <when test="title != null">
                and title = #{title}
            </when>
            <when test="author != null">
                and author = #{author}
            </when>
            <otherwise>
                and views = #{views}
            </otherwise>
        </choose>
    </where>
</select>
```

**测试代码**：

下面我们修改test中的方法名和map.put参数

```java
Map map = new HashMap();
map.put("title", "Mybatis");
map.put("author", "狂神说");
map.put("views", 9999);
```

**执行逻辑**：
- 当`title`不为空时：只执行title条件
- 当`title`为空，`author`不为空时：执行author条件  
- 当所有条件都为空时：执行otherwise条件

### 12.5 SQL片段复用 🔄

有时候我们可以将SQL中的片段抽取出来复用，像是java中的方法。

#### 12.5.1 定义SQL片段

```xml
<!-- 使用sql标签抽取公共部分 -->
<sql id="selectBlog">
    select * from blog
</sql>

<sql id="blogColumns">
    id, title, author, create_time, views
</sql>
```

#### 12.5.2 引用SQL片段

```xml
<select id="queryBlogById" parameterType="map" resultType="com.amiera.pojo.Blog">
    <!-- 使用include标签引用SQL片段 -->
    <include refid="selectBlog"/>
    <where>
        <include refid="blogConditions"/>
    </where>
</select>

<sql id="blogConditions">
    <if test="title != null">and title = #{title}</if>
    <if test="author != null">and author = #{author}</if>
</sql>
```

> [!CAUTION]
> 最好基于单表来定义SQL片段，另外不要存在where标签（where的优化无法实现复用）

### 12.6 SQL中的foreach循环

> *foreach* 元素的功能非常强大，它允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。它也允许你指定开头与结尾的字符串以及集合项迭代之间的分隔符。

#### 12.6.1 foreach标签属性说明

| 属性           | 说明                            | 示例               |
| -------------- | ------------------------------- | ------------------ |
| **collection** | 要遍历的集合或数组的名称        | `collection="ids"` |
| **item**       | 遍历过程中当前元素的别名        | `item="id"`        |
| **open**       | 整个循环生成的SQL片段的开始部分 | `open="id in ("`   |
| **close**      | 整个循环生成的SQL片段的结束部分 | `close=")"`        |
| **separator**  | 各个循环项之间的分隔符          | `separator=","`    |
| **index**      | 遍历的索引（可选）              | `index="index"`    |

#### 12.6.2 使用示例

**Mapper配置**：
```xml
<select id="queryBlogById" parameterType="map" resultType="com.amiera.pojo.Blog">
    select * from blog
    <where>
        <foreach collection="ids" item="id" open="id in (" close=")" separator=",">
            #{id}
        </foreach>
    </where>
</select>
```

**测试代码**：

由于输出内容不止一列，test我们用列表

```java
@Test
public void queryBlogByIdTest() {
    SqlSession sqlSession = utils.getSqlSession();
    BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
    
    HashMap map = new HashMap();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ids.add(123);  // 添加查询条件
    map.put("ids", ids);
    
    List<Blog> blogs = mapper.queryBlogById(map);
    for (Blog blog : blogs) {
        System.out.println(blog);
    }
    sqlSession.close();
}
```

运行后会输出所有列，因为我们并没有在ids集合中添加内容，这会触发<where>的机制：若无条件符合，<where>中内容自动省略，所以执行了`select * from firsttry.blog`全部遍历，而当我们为集合添加内容后，程序自然就会遍历输出符号条件的列

```java
//输出id中有123的列
ArrayList<Integer> ids = new ArrayList<Integer>();
ids.add(123);
```

![image-20251109180549539](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251109180549539.png)

#### 12.7.1 核心思想回顾

**动态SQL = 智能SQL拼接**
- 根据参数动态生成不同的SQL语句
- 一份配置适应多种查询场景
- 避免写大量相似的SQL方法

#### 12.7.2 标签使用速查表

| 场景         | 推荐标签            | 关键特性           |
| ------------ | ------------------- | ------------------ |
| **可选条件** | `<if>`              | 条件满足就拼接     |
| **多选一**   | `<choose>`          | 类似switch-case    |
| **条件组**   | `<where>`           | 智能处理WHERE和AND |
| **更新字段** | `<set>`             | 智能处理SET和逗号  |
| **循环遍历** | `<foreach>`         | IN查询、批量操作   |
| **代码复用** | `<sql>`+`<include>` | 提取公共片段       |

#### 12.7.3 实用开发建议

**1. SQL先行原则**
```sql
-- 先在MySQL中验证SQL正确性
SELECT * FROM user WHERE name LIKE '%张%' AND age > 18;
-- 再转化为动态SQL
```

**2. 空值安全处理**
```xml
<!-- 推荐写法 -->
<if test="name != null and name != ''">
<if test="list != null and list.size() > 0">
<if test="map != null and !map.isEmpty()">
```

**3. 性能优化要点**
- 避免`WHERE 1=1`这种写法，用`<where>`标签替代
- 大数据量IN查询考虑分页或EXISTS
- 模糊查询注意索引失效问题

**4. 维护性建议**

- 复杂动态SQL添加注释说明业务逻辑
- 按功能模块组织`<sql>`片段
- 保持XML格式整洁，适当换行和缩进

#### 12.7.4 常见避坑指南

❌ **错误示范**
```xml
<if test="name != null">
    WHERE name = #{name}  <!-- 可能多个WHERE -->
</if>
<if test="age != null">
    AND age = #{age}      <!-- WHERE缺失时语法错误 -->
</if>
```

✅ **正确写法**
```xml
<where>
    <if test="name != null">
        AND name = #{name}  <!-- <where>智能处理 -->
    </if>
    <if test="age != null">
        AND age = #{age}
    </if>
</where>
```

#### 12.7.5 调试技巧

**日志查看：**

- 开启MyBatis日志，查看最终执行的SQL
- 验证参数绑定和SQL拼接结果
- 使用`Log4j2`或`STDOUT_LOGGING`

**测试策略：**

- 覆盖所有条件分支
- 测试边界情况（空值、空集合等）
- 验证生成的SQL在数据库中直接执行

> [!IMPORTANT]
>
> **记住：动态SQL让代码更灵活，但也需要更全面的测试来保证质量！**

---

## 13. 缓存 💾

### 13.1 缓存简介

由于每次查询数据库都需要连接，十分消耗资源，所以一次查询的结果，给它暂存在一个可以直接取走的地方：内存（缓存），当我们再次查询相同数据的时候，直接走缓存，就不用走数据库了

#### 13.1.1 什么是缓存 [Cache]？

**缓存的核心概念**：
- 存在内存中的临时数据，作为数据的"快速通道"
- 将用户经常查询的数据放在缓存（内存）中，用户查询时直接从缓存获取
- 避免每次都要从磁盘上的数据库文件查询，大大提高查询效率，解决了高并发系统的性能问题

**类比理解**：
> 就像超市的货架 vs 仓库
> - 缓存 = 货架上摆放的热销商品（快速取用）
> - 数据库 = 后方仓库（数据源头，存取较慢）

#### 13.1.2 为什么使用缓存？

**三大核心价值**：
1. **性能提升** - 内存读取速度远快于磁盘I/O
2. **压力分担** - 减少数据库直接访问，降低数据库负载
3. **响应加速** - 用户获得更快的查询响应体验

**实际效果对比**：
```
无缓存：用户请求 → 应用层 → 数据库查询 → 返回结果 (耗时：50ms)
有缓存：用户请求 → 应用层 → 缓存命中 → 返回结果 (耗时：5ms)
```

#### 13.1.3 什么样的数据适合缓存？

**适合缓存的数据特征**：
- ✅ **读多写少** - 频繁查询但很少修改的数据
- ✅ **实时性要求不高** - 允许短暂的数据延迟
- ✅ **计算成本高** - 复杂查询或聚合计算结果
- ✅ **热点数据** - 被大量用户频繁访问的数据

**不适合缓存的数据**：
- ❌ **实时性要求极高** - 股票价格、秒杀库存
- ❌ **写多读少** - 频繁更新的数据
- ❌ **数据一致性要求严格** - 资金账户余额

### 13.2 Mybatis缓存机制

#### 13.2.1 缓存体系概览

MyBatis提供了强大的两级缓存架构：

> [!IMPORTANT]
> MyBatis缓存体系
> ├── 一级缓存 (本地缓存)
> │   ├── 作用范围：SqlSession级别
> │   ├── 默认状态：自动开启
> │   └── 生命周期：随SqlSession创建和关闭
> │
> └── 二级缓存 (全局缓存)
>  ├── 作用范围：namespace级别  
>  ├── 默认状态：需要手动开启
>  └── 生命周期：应用级别，可跨SqlSession共享

#### 13.2.2 一级缓存详解

**核心特性**：
- **自动生效** - 无需任何配置，默认开启
- **会话级别** - 同一个SqlSession内有效
- **自动管理** - MyBatis自动处理缓存的存储和失效

**工作流程**（记得开启日志）：

默认下，同时查询多次相同记录，系统会直接从缓存获取，如果你将user1与user2比较，会发现结果为True，即两个变量地址相同

```java
@Test
public void testFirstLevelCache() {
    SqlSession sqlSession = utils.getSqlSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    
    // 第一次查询 - 访问数据库
    Users user1 = userDao.getUserById(1); 
    System.out.println("第一次查询: " + user1);
    
    // 第二次查询相同数据 - 直接从缓存获取
    Users user2 = userDao.getUserById(1);
    System.out.println("第二次查询: " + user2);
    
    // 验证是否是同一个对象（缓存命中）
    System.out.println("是否是同一个对象: " + (user1 == user2)); // true
    
    sqlSession.close();
}
```

**缓存失效时机**：
- 查询不同的数据
- 执行insert、update、delete操作，可能会改变原来的数据，所以必定会刷新缓存
- 调用`sqlSession.clearCache()`，手动清理缓存
- 不同的SqlSession之间缓存不共享

> [!CAUTION]
> **一级缓存默认开启，也只在一次SqlSession中有效，也就是在拿到连接到关闭连接这个时间段。一级缓存相当于一个map**

#### 13.2.3 二级缓存详解

- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存
- 基于namespace级别的缓存，一个名称空间，对应一个二级缓存

**工作机制**：
1. 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中
2. 如果当前会话关闭了，这个会话对应的一级缓存就没了；但是我们想要的是，会话关闭了，一级缓存的数据被保存到二级缓存中
3. 新的会话查询信息，就可以从二级缓存中获取内容
4. 不同的mapper查出的数据会放在自己对应的缓存（map）中

**配置开启步骤**：

1. **核心配置启用**（mybatis-config.xml）：
   ```xml
   <settings>
       <!-- 开启全局缓存 -->
       <setting name="cacheEnabled" value="true"/>
   </settings>
   ```

2. **Mapper文件配置**：
   ```xml
   <mapper namespace="com.amiera.utils.UserMapper">
       <!-- 简单开启二级缓存 -->
       <cache/>
   </mapper>
   ```

   **高级缓存配置**：
   ```xml
   <cache
     eviction="FIFO"              <!-- 回收策略：FIFO/LRU/SOFT/WEAK -->
     flushInterval="60000"        <!-- 刷新间隔：60秒 -->
     size="512"                   <!-- 引用数目：最多缓存512个对象 -->
     readOnly="true"/>            <!-- 只读：true/false -->
   ```

   > [!NOTE]
   > 可用的清除策略有：
   > - `LRU` – 最近最少使用：移除最长时间不被使用的对象。
   > - `FIFO` – 先进先出：按对象进入缓存的顺序来移除它们。
   > - `SOFT` – 软引用：基于垃圾回收器状态和软引用规则移除对象。
   > - `WEAK` – 弱引用：更积极地基于垃圾收集器状态和弱引用规则移除对象。

3. **实体类序列化**：
   ```java
   public class User implements Serializable {
       // 实体类需要实现Serializable接口
   }
   ```

> [!CAUTION]
> - 只要开启了二级缓存，在同一个Mapper下就有效
> - 所有的数据都会先放在一级缓存中
> - 只有当会话提交，或者关闭的时候，才会提交到二级缓存中

#### 13.2.4 缓存执行顺序

**查询时的缓存查找顺序**：
```
1. 二级缓存查找 → 2. 一级缓存查找 → 3. 数据库查询
```

**数据更新时的缓存处理**：
```
数据库更新 → 清空二级缓存 → 清空一级缓存
```

**缓存原理**：

![image-20251109203455475](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251109203455475.png)

#### 13.2.5 实际应用建议

**适用场景**：
- **一级缓存**：单次会话内的重复查询优化
- **二级缓存**：跨会话的共享数据，如系统配置、字典数据

**注意事项**：
- 分布式环境下慎用二级缓存（可能产生数据不一致）
- 频繁更新的数据不适合缓存
- 注意缓存的内存占用和过期策略

**性能监控**：
- 通过日志观察缓存命中率
- 监控缓存的内存使用情况
- 定期评估缓存策略的有效性

### 13.3 自定义缓存-ehcache

> EhCache是一个纯Java的进程内缓存框架，具有快速、精干的特点

#### 13.3.1 自定义缓存概述

**为什么要自定义**？
- 默认缓存无法满足分布式需求
- 需要集成专业缓存组件（Redis、Ehcache等）
- 需要持久化或高级缓存特性

#### 13.3.2 实现方式

**先导包（pom.xml）**
```xml
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>2.10.6</version>
</dependency>
```

**在resources下创建ehcache.xml**

> [!NOTE]
> #### Ehcache 配置文件参数详解
>
> ##### 1. 基本配置和命名空间
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>    xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
>    updateCheck="false">
> ```
> - **xmlns:xsi**：定义 XML Schema 实例命名空间
> - **xsi:noNamespaceSchemaLocation**：指定 XML Schema 位置，用于验证配置文件格式
> - **updateCheck**：设置为 `false` 表示禁止 Ehcache 自动检查更新
>
> ##### 2. 磁盘存储配置
> ```xml
> <diskStore path="./tmpdir/Tmp_EhCache"/>
> ```
> - **diskStore**：定义缓存数据溢出到磁盘时的存储路径
> - **path**：指定磁盘存储目录，这里设置为项目相对路径 `./tmpdir/Tmp_EhCache`
>
> ##### 3. 默认缓存配置
> ```xml
> <defaultCache
>         eternal="false"
>         maxElementsInMemory="10000"
>         overflowToDisk="false"
>         diskPersistent="false"
>         timeToIdleSeconds="1800"
>         timeToLiveSeconds="259200"
>         memoryStoreEvictionPolicy="LRU"/>
> ```
> 这些参数定义了默认缓存策略：
> - **eternal**：缓存是否永久有效，`false` 表示缓存会过期
> - **maxElementsInMemory**：内存中最大缓存对象数量
> - **overflowToDisk**：内存不足时是否溢出到磁盘
> - **diskPersistent**：JVM 重启后是否保持磁盘缓存数据
> - **timeToIdleSeconds**：对象空闲时间（秒）
> - **timeToLiveSeconds**：对象生存时间（秒）
> - **memoryStoreEvictionPolicy**：内存缓存淘汰策略
>
> ##### 4. 自定义缓存配置
>
> ```xml
> <cache
>         name="cloud_user"
>         eternal="false"
>         maxElementsInMemory="5000"
>         overflowToDisk="false"
>         diskPersistent="false"
>         timeToIdleSeconds="1800"
>         timeToLiveSeconds="1800"
>         memoryStoreEvictionPolicy="LRU"/>
> ```
>
> 这是一个名为 `cloud_user` 的自定义缓存配置，包含与默认缓存相同的参数，但可以设置不同的值：
>
> - **name**：缓存名称，在应用程序中通过此名称引用该缓存配置
> - **maxElementsInMemory**：设置为 5000 个，比默认缓存小
> - **timeToLiveSeconds**：设置为 1800 秒（30分钟），比默认缓存的 3 天短
> - 其他参数与默认缓存含义相同
>
> #### 核心参数说明
>
> 1. **内存与磁盘存储相关**
>    - `maxElementsInMemory`：控制内存使用量，防止内存溢出
>    - `overflowToDisk`：决定是否使用磁盘作为二级存储
>    - `diskStore.path`：指定磁盘缓存位置
> 2. **缓存过期策略**
>    - `eternal`：控制是否永久有效
>    - `timeToIdleSeconds`：基于访问时间的过期策略
>    - `timeToLiveSeconds`：基于创建时间的过期策略
>    - 这三个参数中，如果 `eternal` 为 `true`，则后两个参数无效
> 3. **缓存淘汰策略**
>    - `memoryStoreEvictionPolicy`：当达到内存上限时，如何淘汰现有对象
>    - 常见值：`LRU`（最近最少使用）、`LFU`（最少使用频率）、`FIFO`（先进先出）
> 4. **持久化相关**
>    - `diskPersistent`：控制缓存是否跨 JVM 实例持久化
>
> #### 配置总结
>
> 该配置文件设置了两个缓存策略：
>
> 1. **默认缓存**：适用于大多数场景，较大的内存容量和较长的过期时间
> 2. **cloud_user 缓存**：专为用户相关数据设计，较小的内存容量和较短的过期时间
>
> 这种配置方式使应用程序能够为不同类型的数据定制不同的缓存策略，平衡性能和资源占用。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">

    <diskStore path="./tmpdir/Tmp_EhCache"/>

    <defaultCache
            eternal="false"
            maxElementsInMemory="10000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="1800"
            timeToLiveSeconds="259200"
            memoryStoreEvictionPolicy="LRU"/>

    <cache
            name="cloud_user"
            eternal="false"
            maxElementsInMemory="5000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="1800"
            timeToLiveSeconds="1800"
            memoryStoreEvictionPolicy="LRU"/>
</ehcache>
```

**核心接口：**

```java
public interface Cache {
  String getId();
  int getSize();
  void putObject(Object key, Object value);
  Object getObject(Object key);
  boolean hasKey(Object key);
  Object removeObject(Object key);
  void clear();
}
```

#### 13.3.3 Redis缓存示例

**基础实现：**

```java
//将该方法继承接口
public class RedisCache implements Cache {
    private String id;
    private Jedis jedis;
    
    public RedisCache(String id) {
        this.id = id;
        this.jedis = new Jedis("localhost", 6379);
    }
    
    @Override
    public void putObject(Object key, Object value) {
        jedis.set(key.toString(), serialize(value));
    }
    
    @Override
    public Object getObject(Object key) {
        String value = jedis.get(key.toString());
        return value != null ? deserialize(value) : null;
    }
    
    @Override
    public void clear() {
        // 清理当前命名空间缓存
    }
    
    // 序列化方法省略...
}
```

#### 13.3.4 配置使用Ehcache

**Mapper中配置**：

```xml
<!-- 使用Ehcache作为二级缓存实现 -->
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```

#### 13.3.5 常用缓存组件

- **Ehcache**：`<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>`
- **Redis**：需要自定义实现Cache接口
- **Caffeine**：`<cache type="org.mybatis.caches.caffeine.CaffeineCache"/>`

#### 13.3.6 使用建议

**适用场景**：
- 分布式系统部署
- 高并发读取需求
- 缓存数据需要持久化

**注意事项**：
- 确保缓存数据一致性
- 合理设置过期时间
- 监控缓存命中率和内存使用

> **提示**：大多数项目使用默认缓存+Redis等专业缓存中间件即可满足需求，无需深度自定义。

---

## 14. 完结撒花💐，但绝非终点

### 14.1 MyBatis学习总结 🎯

#### 14.1.1 核心技术点回顾

**基础核心**：
- ✅ **环境搭建** - Maven配置、核心配置文件
- ✅ **CRUD操作** - 增删改查的基本使用
- ✅ **配置解析** - 属性、别名、映射器配置
- ✅ **结果映射** - 解决字段名属性名不一致

**进阶特性**：
- ✅ **日志配置** - 调试和问题排查
- ✅ **分页处理** - LIMIT和RowBounds
- ✅ **注解开发** - 简化XML配置
- ✅ **关联查询** - 多对一、一对多处理
- ✅ **动态SQL** - 智能SQL拼接
- ✅ **缓存机制** - 性能优化利器

#### 14.1.2 实际开发建议

**配置最佳实践**：
```xml
<!-- 推荐的核心配置顺序 -->
<configuration>
    <properties resource="db.properties"/>
    <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="false"/>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
    <typeAliases>
        <package name="com.example.pojo"/>
    </typeAliases>
    <environments>
        <!-- 环境配置 -->
    </environments>
    <mappers>
        <package name="com.example.mapper"/>
    </mappers>
</configuration>
```

**代码规范建议**：
- 使用`#{}`防止SQL注入
- 实体类实现Serializable接口
- 合理使用缓存，注意缓存失效时机
- 复杂SQL使用XML配置，简单SQL使用注解

### 14.2 常见问题解决方案 🛠️

#### 14.2.1 配置类问题

**问题1：Mapper文件找不到**
```xml
<!-- 解决方案：检查resource路径 -->
<mapper resource="com/amiera/mapper/UserMapper.xml"/>

<!-- 或者使用包扫描 -->
<package name="com.amiera.mapper"/>
```

**问题2：属性名字段名不一致**
```xml
<!-- 解决方案：使用resultMap -->
<resultMap id="UserMap" type="User">
    <result column="db_name" property="javaName"/>
</resultMap>
```

#### 14.2.2 运行时问题

**问题3：事务未提交**
```java
// 解决方案：手动提交事务
sqlSession.commit();
// 或者设置自动提交
sqlSessionFactory.openSession(true);
```

**问题4：缓存相关问题**
```java
// 清理一级缓存
sqlSession.clearCache();

// 在Mapper中配置缓存策略
<cache eviction="LRU" flushInterval="60000"/>
```

### 14.3 学习资源推荐 📚

#### 14.3.1 官方资源
- **[MyBatis官方文档](https://mybatis.org/mybatis-3/zh/index.html)** - 最权威的参考资料
- **[MyBatis Spring集成](https://mybatis.org/spring/zh/index.html)** - 企业级开发必备

#### 14.3.2 视频教程
- **[狂神说MyBatis](https://www.bilibili.com/video/BV1NE411Q7Nx)** - 通俗易懂的入门教程
- **[尚硅谷MyBatis](https://www.bilibili.com/video/BV1VP4y1c7j7)** - 系统全面的进阶教程

#### 14.3.3 实践项目
- 博客系统
- 商品管理系统  
- 用户权限系统

### 14.4 个人学习心得 💭

作为没有系统学习Java文件读取的人，开始学习MyBatis时确实遇到了不少困难：

**初期的挑战**：
- Maven依赖管理和项目配置
- XML配置文件的编写和理解
- 各种配置参数的记忆和运用

**突破后的收获**：
- 深刻理解了ORM框架的价值
- 掌握了数据库访问层的最佳实践
- 学会了如何优化SQL性能

**这份笔记的价值**：
> 这份笔记不仅是对学习过程的记录，更是经过多轮优化的自学指南。即使完全忘记了MyBatis，通过这份笔记也能重新掌握核心概念和实践技能。

---

## 15. 下一站：Spring 🌱

### 15.1 Spring框架简介

[Spring | Home](https://spring.io/)

**现代Java开发现状**：
现在的Java开发可以说已经从"面向对象编程"演进到了"**面向Spring编程**"。Spring框架的强大功能和生态体系让它成为了Java企业级开发的事实标准。

### 15.2 Spring核心模块 🏗️

![image-20251109211807984](C:\Users\A\AppData\Roaming\Typora\typora-user-images\image-20251109211807984.png)

**主要模块**：
- **Spring Core** - 核心容器，依赖注入
- **Spring MVC** - Web框架
- **Spring Data** - 数据访问
- **Spring Security** - 安全框架
- **Spring Boot** - 快速开发

### 15.3 MyBatis与Spring集成 🔄

**集成优势**：
- 更好的事务管理
- 简化的配置方式
- 强大的AOP支持
- 便捷的测试支持

**基础集成配置**：
```java
@Configuration
@MapperScan("com.example.mapper")
public class MyBatisConfig {
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
}
```

### 15.4 学习路线建议 🗺️

**推荐学习顺序**：
1. **Spring Core** - IoC容器、依赖注入
2. **Spring MVC** - Web开发基础
3. **Spring与MyBatis集成** - 数据访问层优化
4. **Spring Boot** - 快速开发实践
5. **Spring Cloud** - 微服务架构

**技能进阶**：
- 深入理解Spring设计思想
- 掌握Spring Boot自动配置原理
- 学习Spring Cloud微服务架构
- 实践分布式系统开发

### 15.5 最后的寄语 🌟

**给坚持到这里的你**：
恭喜你完成了MyBatis的学习之旅！🎉 这只是Java后端开发道路上的一个重要里程碑。MyBatis让你掌握了数据访问层的核心技术，为学习更复杂的Spring生态系统打下了坚实基础。

**记住**：
- 技术的学习永无止境
- 实践是最好的老师
- 遇到问题不要害怕，每个开发者都经历过
- 保持好奇心，持续学习

**我们在Spring的世界再见！** 🚀

---

## 附录：快速查询手册 📖

### A. 常用配置速查

#### A.1 核心配置
```xml
<!-- 数据库配置 -->
<dataSource type="POOLED">
    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/db"/>
    <property name="username" value="root"/>
    <property name="password" value="password"/>
</dataSource>

<!-- 别名配置 -->
<typeAliases>
    <package name="com.example.pojo"/>
</typeAliases>
```

#### A.2 缓存配置
```xml
<!-- 二级缓存 -->
<cache eviction="LRU" flushInterval="60000" size="512" readOnly="true"/>
```

### B. 常见注解速查

#### B.1 CRUD注解
```java
@Select("SELECT * FROM user WHERE id = #{id}")
User getUserById(@Param("id") int id);

@Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
int addUser(User user);

@Update("UPDATE user SET name=#{name} WHERE id=#{id}")
int updateUser(User user);

@Delete("DELETE FROM user WHERE id=#{id}")
int deleteUser(int id);
```

### C. 动态SQL标签速查

#### C.1 条件判断
```xml
<if test="condition">SQL片段</if>
<choose>
    <when test="condition1">SQL1</when>
    <when test="condition2">SQL2</when>
    <otherwise>默认SQL</otherwise>
</choose>
```

#### C.2 循环处理
```xml
<foreach collection="list" item="item" open="(" close=")" separator=",">
    #{item}
</foreach>
```

---

这份完整的MyBatis笔记就此结束，希望它能在你的技术成长道路上发挥重要作用！🎓