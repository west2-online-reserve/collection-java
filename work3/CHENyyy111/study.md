# 1.获取Mybatis

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.14</version>
</dependency>
```

- 中文文档：https://mybatis.org/mybatis-3/zh/index.html













# 2.第一个Mybatis程序

## 2.1 在总的pom.xml里面配置环境

```xml
<?xml version="1.0" encoding="UTF8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>OrderManage</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>OrderProduct</module>
        <module>Work3</module>
    </modules>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <!--导入依赖-->
    <dependencies>
        <!--mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>
        <!--mybatis-->
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.14</version>
        </dependency>
        <!--junit-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
        </dependency>
    </dependencies>

    <!--在build中配置resources，来防止我们资源导出失败的问题-->
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
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
            </resource>
        </resources>
    </build>
</project>
```





## 2.2 模块的pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>OrderManage</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>Work3</artifactId>
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.26</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
```





## 2.3 resources

共有三点：db.properties	Mybatis-config.xml	接口的Mapper.xml

### db.properties

```xml
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT%2B8&useSSL=true&useUnicode=true&characterEncoding=utf8&useSSL=true
username=
password=
```



### Mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<!--configuration核心配置文件-->
<configuration>

    <!--引入外部配置文件-->
    <properties resource="db.properties"/>

<!--    <settings>-->
<!--        <setting name="logImpl" value="STDOUT_LOGGING"/>-->
<!--    </settings>-->

    <!--可以给实体类起别名-->
    <typeAliases>
        <package name="com.chenyyy111.pojo"/>
        <!--        <typeAlias type="com.chenyyy111.pojo.User" alias="User"/>-->
    </typeAliases>

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

    <!--绑定接口-->
    <mappers>
        <mapper class="com.chenyyy111.dao.OrderMapper"/>
        <mapper class="com.chenyyy111.dao.ProductMapper"/>
    </mappers>

</configuration>
```



### 接口的Mapper.xml

存放增删改查的sql语句，具体要与项目的需求有关





## 2.4 编写代码

主要包括三个方面：实体类、接口设计、工具类

工具类：MybatisUtils

```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis工具类
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputSteam = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputSteam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
```





## 2.5 测试

一般使用junit测试，可以结合日志进行debug













# 3. CRUD

数据库中的字段名如果和实体类的不一样，需要用到结果集映射resultMap

```xml
<resultMap id="ProductMap" type="Product">
    <id property="productId" column="product_id"/>
    <result property="productName" column="product_name"/>
    <result property="productPrice" column="product_price"/>
</resultMap>
```

其中，property对应实体类中的字段名，column对应数据库中的字段名



## 3.1 namespace

namespace中的包名要和Dao/mapper接口的包名一致！



## 3.2 select

```xml
<select id="getProductsList" resultMap="ProductMap">
    SELECT product_id, product_name, product_price FROM shop.product
</select>
```

id为接口名



## 3.3 insert

```xml
<insert id="addProduct" parameterType="Product">
    INSERT INTO shop.product (product_id, product_name, product_price) VALUES (#{productId}, #{productName}, #{productPrice});
</insert>
```



## 3.4 update

```xml
<update id="updateProduct" parameterType="Product">
    UPDATE shop.product SET product_name = #{productName}, product_price = #{productPrice} WHERE product_id = #{productId};
</update>
```



## 3.5 delete

```xml
<delete id="deleteProduct" parameterType="int">
    DELETE FROM shop.product WHERE product_id = #{produceId};
</delete>
```



## 🔺

**<font color = red>增删改一定要提交事务！！！</font>**













# 4. Lombok

使用步骤：

1. 在IDEA安装Lombok插件
2. 在项目中导入lombok的jar包

**注意，导入jar包的时候记得要解锁！**

```xml
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
    </dependency>
</dependencies>
```

3. 在实体类中加上注解就可以了

   ```java
   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   public class User {
       private int id;
       private String name;
       private String password;
   }
   ```



**<font color = red>常用的注解：</font>**

```java
@Getter and @Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Data
@Accessors
```

@Data：自动生成 无参构造，get、set、toString、hashcode、equals