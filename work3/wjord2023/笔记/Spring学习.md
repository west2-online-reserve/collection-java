# **SSM框架**

# ***Spring6***



## Spring特点

#### 非侵入式

不需要依赖其他组件

#### **IoC**(Inverse of Control)

控制反转，反转资源获取方向

#### **AOP**(Aspect Oriented Programing)

面向切面编程，在不改变源代码的基础上增强代码功能

#### 容器

Spring IoC是一个容器，提高开发效率

#### 组件化

使用xml和java注解组合对象

#### 一站式

在IoC和AOP的基础上可以整合其他框架



## 入门

1. 创建maven聚合工程

   创建父工程再创建子模块

2. 引入spring相关依赖

   ```xml
   <dependencies>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-context</artifactId>
               <version>6.0.11</version>
           </dependency>
           <dependency>
               <groupId>org.junit.jupiter</groupId>
               <artifactId>junit-jupiter-api</artifactId>
               <version>5.6.3</version>
           </dependency>
       </dependencies>
   ```

   

3. 创建类，定义属性和方法

   ```java
   package com.wjord;
   
   public class User {
       public void add() {
           System.out.println("add...");
       }
   }
   ```

   

4. 按照spring要求创建配置文件（xml格式）

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
       <!--完成user对象创建
           bean标签的id属性：用于指定bean的唯一标识
           class属性：要创建对象所在类的全路径（包名称+类名称）
       -->
       <bean id="user" class="com.wjord.User"/>
   </beans>
   ```

   

5. 在spring配置文件配置相关信息

6. 进行最终测试

   ```java
   package com.wjord;
   
   import org.junit.jupiter.api.Test;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   public class TestUser {
   
       @Test
       public void testUserObject() {
           // 加载spring配置文件
           ApplicationContext context =
                   new ClassPathXmlApplicationContext("bean.xml");
           // 获取创建的对象
           User user = (User) context.getBean("user");
           System.out.println(user);
           // 使用对象调用方法进行测试
           user.add();
       }
   }
   
   ```

   1.之前创建对象，无参数构造执行？（会执行）
   
   2.不用new方式，还可以怎么创建对象？
   
   （反射）：
   
   1.加载bean.xml配置文件
   
   2.对xml文件进行解析操作
   
   3.获取xml文件bean标签属性值（id属性值和class属性值）
   
   4.使用反射根据类全路径创建对象
   
   3.创建的对象放到哪里？
   
   （Map<String,BeanDefinition>beanDefinitionMap）
   
   （Key:唯一标识；value:类的定义（描述信息））
   
   
   
   ## 启用Log4j2日志框架
   
   #### 日志信息的级别：TRACE<DEBUG<INFO<WARN<ERROR<FATAL
   
   TRANCE:追踪，最低，相当于追踪程序的执行
   
   DEBUG:调试，一般在开发中，都将其设置为最低的日志级别
   
   INFO:信息，输出重要的信息，使用较多
   
   WARN:警告
   
   ERROR:错误
   
   FATAL:严重错误
   
   级别高的会自动屏蔽级别低的
   
   #### 引入Log4j2依赖
   
   ```xml
   <!--log4j2-->
           <dependency>
               <groupId>org.apache.logging.log4j</groupId>
               <artifactId>log4j-core</artifactId>
               <version>2.19.0</version>
           </dependency>
           <dependency>
               <groupId>org.apache.logging.log4j</groupId>
               <artifactId>log4j-slf4j2-impl</artifactId>
               <version>2.19.0</version>
           </dependency>
   ```
   
   配置log4j2.xml
   
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!--Configuration后面的status，这个用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出-->
   <!--monitorInterval：Log4j能够自动检测修改配置 文件和重新配置本身，设置间隔秒数(最小是5秒钟)-->
   <configuration monitorInterval="5" status="warn">
       <!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
   
   
       <!--变量配置-->
       <Properties>
           <!-- 格式化输出：%date表示日期(可缩写成%d，后同)，%thread表示线程名，%-5level：级别从左显示5个字符宽度 %msg：日志消息，%n是换行符-->
           <!-- %logger{36} 表示 Logger 名字最长36个字符 -->
           <property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss,SSS} %highlight{%-5level} [%t] %highlight{%c{1.}.%M(%L)}: %msg%n" />
           <!-- 定义日志存储的路径 -->
           <property name="FILE_PATH" value="log" />
           <!--<property name="FILE_NAME" value="myProject" />-->
       </Properties>
   
       <!--此节点有三种常见的子节点：Console,RollingFile,File-->
       <appenders>
   
           <!--console节点用来定义输出到控制台的Appender-->
           <!--target:SYSTEM_OUT或SYSTEM_ERR,一般只设置默认:SYSTEM_OUT-->
           <console name="Console" target="SYSTEM_OUT">
               <!--输出日志的格式,默认为：%m%n,即只输出日志和换行-->
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <!--阈值过滤器，控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
           </console>
   
           <!--        &lt;!&ndash;文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，适合临时测试用&ndash;&gt;-->
           <!--        <File name="Filelog" fileName="${FILE_PATH}/test.log" append="false">-->
           <!--            <PatternLayout pattern="${LOG_PATTERN}"/>-->
           <!--        </File>-->
   
   
           <!-- 这个会打印出所有的debug及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileDebug" fileName="${FILE_PATH}/debug.log" filePattern="${FILE_PATH}/debug/DEBUG-%d{yyyy-MM-dd}_%i.log.gz">
               <!--阈值过滤器，控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="debug" onMatch="ACCEPT" onMismatch="DENY"/>
               <!--如果配置的是“%d{yyyy-MM}”，滚动时间单位就是月。“%d{yyyy-MM-dd}”，滚动时间单位就是天-->
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <!--指定滚动日志的策略，就是指定新建日志文件的时机-->
               <Policies>
                   <!--interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动-->
                   <TimeBasedTriggeringPolicy interval="1" modulate="true" />
                   <SizeBasedTriggeringPolicy size="100MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15">
                   <!--删除15天之前的日志-->
                   <Delete basePath="${FILE_PATH}" maxDepth="2">
                       <IfFileName glob="*/*.log.gz" />
                       <IfLastModified age="360H" />
                   </Delete>
               </DefaultRolloverStrategy>
           </RollingFile>
   
   
           <!-- 这个会打印出所有的warn及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileInfo" fileName="${FILE_PATH}/info.log" filePattern="${FILE_PATH}/info/INFO-%d{yyyy-MM-dd}_%i.log.gz">
               <!--阈值过滤器，控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <Policies>
                   <!--interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动-->
                   <TimeBasedTriggeringPolicy interval="1" modulate="true" />
                   <SizeBasedTriggeringPolicy size="100MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15"/>
           </RollingFile>
   
   
           <!-- 这个会打印出所有的error及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileError" fileName="${FILE_PATH}/error.log" filePattern="${FILE_PATH}/error/ERROR-%d{yyyy-MM-dd}_%i.log.gz">
               <!--阈值过滤器，控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <Policies>
                   <!--interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动-->
                   <TimeBasedTriggeringPolicy interval="1" modulate="true" />
                   <SizeBasedTriggeringPolicy size="100MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15"/>
           </RollingFile>
           <!--启用异步日志，阻塞队列最大容量为20000，超出队列容量时是否等待日志输出，不等待将直接将日志丢弃-->
           <Async name="Async" bufferSize="20000" blocking="true">
               <AppenderRef ref="Console"/>
               <AppenderRef ref="RollingFileDebug"/>
               <AppenderRef ref="RollingFileInfo"/>
               <AppenderRef ref="RollingFileError"/>
           </Async>
       </appenders>
   
   
       <!--Logger节点用来单独指定日志的形式，比如要为指定包下的class指定不同的日志级别等。-->
       <!--然后定义loggers，只有定义了logger并引入的appender，appender才会生效-->
       <loggers>
           <!--过滤掉spring和mybatis的一些无用的DEBUG信息-->
           <logger name="org.mybatis" level="info" additivity="false">
               <AppenderRef ref="Async"/>
           </logger>
           <!--监控系统信息-->
           <!--若是additivity设为false，则 子Logger 只会在自己的appender里输出，而不会在 父Logger 的appender里输出。-->
           <Logger name="org.springframework" level="info" additivity="false">
               <AppenderRef ref="Async"/>
           </Logger>
           <!--root 节点用来指定项目的根日志，level:日志输出级别，共有8个级别，按照从低到高为：All < Trace < Debug < Info < Warn < Error < Fatal < OFF.-->
           <root level="debug">
               <AppenderRef ref="Async" />
           </root>
       </loggers>
   
   
   </configuration>
   
   ```
   
   
   
   ## IoC容器
   
   #### Inversion of Control 控制反转
   
   Spring通过**IoC容器**来管理**所有Java对象的实例化和初始化，控制对象与对象之间的依赖关系**。
   
   我们将由IoC容器管理的Java对象称为Spring Bean,它与使用关键字new创建的java对象没有任何区别
   
   
   
   #### 过程
   
   容器放bean对象，使用map集合（key,value）放取方便
   
   xml配置文件（Bean定义信息（BeanDefinition））
   
   接口（BeanDefinitionReader）加载配置文件
   
   存储到IoC容器（Bean定义信息）->进行实例化(BeanFactory工厂+反射)->初始化->最终对象（context.getBean("")获取）
   
   
   
   ![image-20231114223112383](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231114223112383.png)

## 依赖注入

### DI（Dependency Injection）

依赖注入实现了控制反转的思想 

指Spring创建对象的过程中，将对象依赖属性通过配置进行注入

Bean管理说的是：Bean对象的创建，以及Bean对象中属性的赋值（或者叫Bean对象之间关系的维护）

![image-20231114223954386](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231114223954386.png)

## 基于xml管理bean

获取Bean

```java
@Test
    public static void main(String[] args) {
        ApplicationContext context = new 	ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        User user = (User) context.getBean("user");
        System.out.println(user);
        // 根据类型获取bean对象
        User user1 = context.getBean(User.class);
        System.out.println(user1);
        // 根据类型+id获取bean对象
        User user2 = context.getBean("user", User.class);
        System.out.println(user2);

    }
```

![image-20231114230723580](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231114230723580.png)

> 先创建一个接口interface UserDao();
>
> 再创建实现类1 UserDaoImpl实现UserDao接口
>
> 如果组件实现了接口，可以根据接口类型获取bean
>
> 如果一个有多个实现类，每个类都配置了bean，不能根据接口类型获取bean因为bean不唯一	

依赖注入：setter注入和构造器注入

> 1、类有属性，创建对象过程中，向属性设置值

> 第一种方式：基于set方法完成
>
> 第一步 创建类，定义属性，生成set方法
>
> 第二步 在spring文件中添加配置

```xml
<!--setter注入-->
    <bean id="book" class="com.wjord.di.Book">
        <property name="name" value="wjord"/>
        <property name="author" value="w"/>
    </bean>
```

>第二种方式：基于构造器完成
>
>第一步 创建类，定义属性,生成构造器
>
>第二步 配置文件

```xml
<!--构造器注入-->
    <bean id="book2" class="com.wjord.di.Book">
        <constructor-arg name="name" value="wjord"/>
        <constructor-arg index="1" value="w"/>
    </bean>
```

特殊类型注入

![image-20231115112222387](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115112222387.png)

![image-20231115112311946](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115112311946.png)

![image-20231115112349328](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115112349328.png)

![image-20231115112504541](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115112504541.png)

> 对象类型属性注入 
>
> 方法一：引用外部bean
>
> ```xml
> <!--
>         1.创建两个对象：dept和emp
>         2.在emp的标签里面，使用property标签为属性赋值
>     -->
>     <bean id="dept" class="com.wjord.ditest.Dept">
>         <property name="name" value="开发部"/>
>     </bean>
> 	
>     <bean id="emp" class="com.wjord.ditest.Emp">
>         <!--注入普通类型属性-->
>         <property name="name" value="wjord"/>
>         <property name="age" value="1"/>
>         <!--注入对象类型属性
>             ref:引用类型，指向bean的id
>             value:基本类型
>         -->
>         <property name="dept" ref="dept"/>
>     </bean>
> ```
>
> 方法二：内部bean
>
> ```xml
> <bean id="emp2" class="com.wjord.ditest.Emp">
>         <property name="name" value="wjord"/>
>         <property name="age" value="1"/>
> 
>         <property name="dept">
>             <bean id="dept2" class="com.wjord.ditest.Dept">
>                 <property name="name" value="开发部"/>
>             </bean>
>         </property>
>     </bean>
> ```
>
> 方法三：级联属性赋值
>
> ```xml
> <!--级联赋值-->
>     <bean id="dept" class="com.wjord.ditest.Dept">
>         <property name="name" value="开发部"/>
>     </bean>
>     <bean id="emp3" class="com.wjord.ditest.Emp">
>         <property name="name" value="wjord"/>
>         <property name="age" value="1"/>
> 
>         <property name="dept" ref="dept"/>
>         <property name="dept.name" value="发部"/>
>     </bean>
> ```

![image-20231115120700800](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115120700800.png)

![image-20231115121331853](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115121331853.png)

![image-20231115122420497](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115122420497.png)

![image-20231115124534059](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115124534059.png)

![image-20231115124652872](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115124652872.png)

### 外部文件引入

> 
>
> 实现过程：
>
> 1.引入数据库相关的依赖
>
> ```xml
> <dependency>
>             <groupId>mysql</groupId>
>             <artifactId>mysql-connector-java</artifactId>
>             <version>8.0.28</version>
>         </dependency>
>         <dependency>
>             <groupId>com.alibaba</groupId>
>             <artifactId>druid</artifactId>
>             <version>1.2.6</version>
>         </dependency>
> ```
>
> 
>
> 2.创建外部属性文件，properties格式，定义数据库信息：用户名 密码 地址等 
>
> ```properties
> jdbc.user=root
> jdbc.password=102302
> jdbc.url=jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
> jdbc.driver=com.mysql.cj.jdbc.Driver
> ```
>
> ```xml
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xmlns:context="http://www.springframework.org/schema/context"
>        xsi:schemaLocation="http://www.springframework.org/schema/context
>        http://www.springframework.org/schema/context/spring-context.xsd
>        http://www.springframework.org/schema/beans
>        http://www.springframework.org/schema/beans/spring-beans.xsd">
> ```
>
> 引入外部属性文件
>
> ```xml
> <context:property-placeholder location="classpath:jdbc.properties"/>
> ```
>
> 完成数据库信息的注入
>
> (原本做法)
>
> ```java
> public class TestJdbc {
>     @Test
>     public void test1() {
>         DruidDataSource dataSource = new DruidDataSource();
>         dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
>         dataSource.setUsername("root");
>         dataSource.setPassword("102302");
>         dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
>     }
> }
> ```
>
> （spring做法）
>
> ```xml
> <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
>         <property name="driverClassName" value="${jdbc.driver}"/>
>         <property name="url" value="${jdbc.url}"/>
>         <property name="username" value="${jdbc.user}"/>
>         <property name="password" value="${jdbc.password}"/>
>     </bean>
> ```
>
> ```java
>     @Test
>     public void test2() {
>         ApplicationContext context = new
>                 ClassPathXmlApplicationContext("bean-jdbc.xml");
>         DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
>         System.out.println(dataSource.getUrl());
>     }
> ```
>
> 3.创建spring配置文件，引入context命名空间引入属性文件。使用表达式完成注入

bean的作用域 

![image-20231115131922722](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115131922722.png)

bean的生命周期 

![image-20231115132128770](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115132128770.png)s

![image-20231115133038899](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115133038899.png)

> init-method为初始时会调用的方法，destroy-method为销毁时会调用的方法

### FactoryBean

![image-20231115153216630](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115153216630.png)

## 基于xml自动装配

>1.编写controller
>
>定义service类型属性
>
>生成service属性set方法
>
>```java
>public class UserController {
>    private UserService userService;
>
>    public void setUserService(UserService userService) {
>        this.userService = userService;
>    }
>
>    public void addUserController(){
>        // 调用service层的方法
>        System.out.println("add user controller");
>        userService.addUserService();
>    }
>}
>```
>
>
>
>2.编写service的接口和实现类 
>
>dao类型属性
>
>生成dao类型属性set方法
>
>```java
>public class UserServiceImpl implements UserService{
>    private UserDao userDao;
>
>    public void setUserDao(UserDao userDao) {
>        this.userDao = userDao;
>    }
>
>    @Override
>    public void addUserService() {
>        System.out.println("add user service");
>        userDao.addUserDao();
>    }
>}
>```
>
>
>
>3.编写dao接口和实现类
>
>```java
>package com.wjord.auto.dao;
>
>public class UserDaoImpl implements UserDao{
>    @Override
>    public void addUserDao() {
>        System.out.println("add user dao");
>    }
>}
>
>```



## ***基于注解管理Bean***

> 注解：是代码中的一个特殊标记
>
> 格式：@注解名称（属性1= 属性...）
>
> 类上面，属性上面，方法上面都可以写注解
>
> 用注解开发人员可以在不改变原有代码和逻辑的情况下，在源代码中注入补充信息

第一步 引入相关依赖

第二步 开启组件扫描

```xml
<!--开启注解扫描-->
    <context:component-scan base-package="com.wjord"/>
```

> 排除

![image-20231115161711838](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115161711838.png)

> 只扫描一部分

![image-20231115161752377](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115161752377.png)

3.用注解定义Bean

![image-20231115162009813](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115162009813.png)

```java
@Component(value = "user")//<bean id=""(默认为方法名首字母小写) class="...">
public class User {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
    }
}
```

### @Autowired注入

##### 1.属性注入

单独使用默认是byTpye

> 1.bean对象创建
>
> 2.定义相关属性，在属性上面添加注释

```java
@Service
public class UserServiceImpl implements UserService{

    // 注入dao

    @Autowired
    private UserDao userDao;
    @Override
    public void add() {
        userDao.add();
        System.out.println("service add...");
    }
}

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void add() {
        System.out.println("dao add...");
    }
}

@Controller
public class UserController {

    // 注入service
    @Autowired //根据类型找到对应对象，完成注入
    private UserService userService;

    public void add(){
        userService.add();
        System.out.println("controller add...");
    }
}
```

##### 2.set注入

```java
private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
```

##### 3.构造器注入

```java
private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
```

##### 4.形参上注入 

```java
private UserDao userDao;
    
    public UserServiceImpl(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
```

##### 5.只有构造函数，无注解

```java
private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
```

> 只能写一个有参数构造，不能有无构造

##### 6.@Autowired和@Qualifier注释联合使用

```java
@Autowired
@Qualifier("userRedisDao")
private UserDao userDao; 
```

### @Resource注入

是JDK扩展包里的，更有通用性

![image-20231115165804140](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115165804140.png)

![image-20231115165817704](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115165817704.png)

```java
@Service("myuserService")
public class UserServiceImpl implements UserService {


    @Resource(name = "myuserDao")
    private UserDao userDao;
```

> 也可以不指定名称，则根据属性名称进行注入

> 根据类型配置（名字对不上时）

![image-20231115171520872](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115171520872.png)

## Spring全注解开发

> 不用再配置文件

用配置类来替代配置文件

```java
@Configuration
@ComponentScan("com.wjord.resouce")
public class SpringConfig {
}
```

```java
ApplicationContext context = new 
              AnnotationConfigApplicationContext(SpringConfig.class);
```



### 原理-手写IoC

##### 回顾Java反射

![image-20231115215203515](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115215203515.png)

## 面向切面编程（AOP）

### 代理模式

概念

![image-20231115220911977](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115220911977.png)

![image-20231115221126937](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115221126937.png)

###### 静态代理(不具有灵活性)

![image-20231115221507278](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115221507278.png)

![image-20231115221524519](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115221524519.png)

#### 动态代理

![image-20231115221726781](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115221726781.png)

> 先建代理类（只要动态创建一个）
>
> 核心操作前日志
>
> 核心操作后日志

```java
package com.wjord;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;

public class ProxyFactory {

    //目标对象
    private final Objects target;

    public ProxyFactory(Objects target) {
        this.target = target;
    }

    //返回代理对象
    public Object getProxy() {
        /*
         *Proxy.newProxyNewInstance()方法
         *有三个参数
         * 1.ClassLoader:动态生成代理类的类加载器
         * 2.Class[] interfaces:目标对象实现的所有接口的class类型数组
         * 3.InvocationHandler:设置代理对象，实现目标对象方法的过程
         */
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {

            //第一个参数：代理对象
            //第二个参数：需要重写目标对象的方法
            //第三个参数：method的方法里面参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //方法调用之前做个输出
                System.out.println("[动态代理][日志]" + method.getName() + "参数：" + Arrays.toString(args));
                // 调用目标方法
                Object result = method.invoke(target, args);
                System.out.println("[动态代理][日志]" + method.getName() + "结果：" + result);
                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }
}
```

```java
package com.wjord;

public class Test {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new UserServiceImpl());
        UserService userService = (UserService) proxyFactory.getProxyInstance();
        userService.add();
    }
}
```

##### AOP概念及相关术语

概述

![image-20231115230339640](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115230339640.png)

相关术语

> 1.横切关注点  2.通知（增强）3.切面  4.目标  5.代理  6.连接点  7.切入点

![image-20231115230914590](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231115230914590.png)

## 基于注解的AOP

> 动态代理分类：JDK动态代理和cglib动态代理
>
> （有接口，使用JDK的动态代理，生成接口实现类代理对象->代理对象和目标对象都实现了同样的接口）
>
> (没有接口，使用cglib动态代理，生成子类代理对象->通过继承代理的目标类)
>
> AspectJ：是AOP框架，spring只是借用了AspectJ的注解

第一步 引入aop相关依赖

```xml
<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>6.0.11</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>6.0.11</version>
        </dependency>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--开启注解扫描-->
    <context:component-scan base-package="com.wjord.annoaop"/>

    <!--开启aspectj自动代理，为目标对象生成代理-->
    <aop:aspectj-autoproxy/>
    
</beans>
```



第二步 创建目标资源

（1）接口

（2）实现类

第三步 创建切面类并配置

（1）切入点

（2）通知类型 

```java
package com.wjord.annoaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Aspect//标注这个类是一个切面
@Component//放到容器中
public class LogAspect {
    // 设置切入点和通知类型
    // 通知类型：@Before、@After、@AfterReturning、@AfterThrowing、@Around
    // 前置通知（方法执行前通知）、后置通知（方法执行后通知）、返回通知（方法返回结果后通知）、异常通知（方法抛出异常后通知）、环绕通知（动态代理，手动推进目标方法执行）

    //切入点表达式：execution(访问权限符[public] 返回值类型[int,*(任意)] 方法全类名(参数表)[..表示任意参数类型])
    //前置@Before（value=“切入点表达式配置切入点”）
    @Before(value = "execution(public int com.wjord.annoaop.CalculateImpl.*(..))")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("before..."+name);
    }

    @After(value = "execution(public int com.wjord.annoaop.CalculateImpl.*(..))")
    public void after(JoinPoint joinPoint){
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        // 获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println("after..."+name+"..."+args);
    }

    @AfterReturning(value = "execution(public int com.wjord.annoaop.CalculateImpl.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        // 获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println("afterReturning..."+name+"..."+args+"..."+result);
    }

    //目标出现异常时执行，可以获取到异常信息
    @AfterThrowing(value = "execution(public int com.wjord.annoaop.CalculateImpl.*(..))",throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint,Exception exception){
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        // 获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println("afterThrowing..."+name+"..."+args+"..."+exception);
    }

    @Around(value = "execution(public int com.wjord.annoaop.CalculateImpl.*(..))")
    public Object around(ProceedingJoinPoint joinPoint){
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        // 获取参数
        Object[] args = joinPoint.getArgs();
        String arg = args.toString();
        System.out.println("around..."+name+"..."+arg);
        Object proceed = null;
        try {
            // 前置通知
            System.out.println("around before...");
            // 执行目标方法
            proceed = joinPoint.proceed(args);
            // 返回通知
            System.out.println("around returning...");
        } catch (Throwable throwable) {
            // 异常通知
            System.out.println("around throwing...");
            throwable.printStackTrace();
        } finally {
            // 后置通知
            System.out.println("around after...");
        }
        return proceed;
    }
}

```



#### 重用切入点表达式

```java
@Pointcut(value = "execution(public int com.wjord.annoaop.CalculateImpl.*(..))")
    public void pointCut(){}

    @After(value = "pointCut()")
    public void after2(JoinPoint joinPoint){
        // 获取方法名
        String name = joinPoint.getSignature().getName();
        // 获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println("after2..."+name+"..."+args);
    }
```



#### 切面的优先级

![image-20231116163140765](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231116163140765.png)



#### 基于XML的AOP

![image-20231116164219784](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231116164219784.png)



## 单元测试：JUnit

```xml
<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>6.0.11</version>
        </dependency>
```

```java
package com.wjord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:bean.xml")
public class TestJunit5 {
    @Autowired
    private User user;

    @Test
    public void test1() {
        System.out.println(user);
        user.add();
    }

}

```

![image-20231116165311526](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231116165311526.png)

也可以（但不常用）

![image-20231116165521078](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231116165521078.png)

用在junit4



## 事务

JdbcTemplate

![image-20231116170603868](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231116170603868.png)

> ##### 1.概述和环境准备

```xml
<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>6.0.11</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.6</version>
        </dependency>
```

(创建 jdbc.properties)

```properties
jdbc.user=root
jdbc.password=root
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/spring?characterEncoding=utf8&userSSL=false
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--引入外部属性文件，创建数据源对象-->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}"/>
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="username" value="${jdbc.user}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    
    <!--创建JdbcTemplate对象-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="druidDataSource"/>
    </bean>
</beans>
```



> ###### 2.对数据库curd操作

```java
package com.wjord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Objects;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class jdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testUpdate() {
        //1.添加操作
        //第一步先编写sql语句
        String sql = "insert into `t_emp` value (null,?,?,?)";
        //第二步调用jdbcTemplete对象里面的方法实现,传入相关参数
        Object[] params = {"张三", 18, "男"};
        int update = jdbcTemplate.update(sql, "王五", 20, "男");
        int update1 = jdbcTemplate.update(sql, params);
        System.out.println(update);
        System.out.println(update1);
        
        //2.修改操作
        String sqlUpdate = "update t_emp set name=? where id=?";
        int update = jdbcTemplate.update(sqlUpdate, "李四", 1);
        System.out.println(update);
        
        //3.删除
        String sqlDelete = "delete from t_emp where id=?";
        int delete = jdbcTemplate.update(sqlDelete, 1);
        System.out.println(delete);
    }
}

```

```java
@Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSelect(){
        //4.查询
        //查询返回对象
        String sqlSelect = "select * from t_emp where id=?";
        Emp emp = jdbcTemplate.queryForObject(sqlSelect, new
                BeanPropertyRowMapper<>(Emp.class), 2);
        System.out.println(emp);
        //查询返回集合
    @Test
    public void testSelect() {
        String sqlSelect = "select * from t_emp";
        List<Emp> list = jdbcTemplate.query(sqlSelect, new
                BeanPropertyRowMapper<>(Emp.class));
        System.out.println(list);
    }
        //查询返回单个值
        @Test
    public void testSelect(){
        String sqlSelect = "select count(*) from t_emp";
        int count = jdbcTemplate.queryForObject(sqlSelect, Integer.class);
        System.out.println(count);
    }

    }
```



##### 声明式事务概念

要么都成功要么都失败

ACID四个特性

##### 基于注解事务概念

> 添加配置

```xml
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="druidDataSource"/>
    </bean>

    <tx:annotation-driven transaction-manager="transactionManager"/>
```

```java
@Transactional
@Service
public class BookServiceImpl implements BookService {
```

> 1.只读：设置只读，只能查询，不能修改添加删除
>
> ```java
> @Transactional(readOnly = true)
> ```
>
> 2.超时：设置超时时候之内没有完成，抛出异常回滚
>
> ```java
> @Transactional(timeout = 3)
> ```
>
> 3.回滚策略：设置哪些异常不回滚
>
> ```java
> Transactional(noRollbackFor = ArithmeticException.class)
> ```
>
> 4.隔离级别：读问题
>
> ```java
> @Transactional(isolation = Isolation.DEFAULT)
> ```
>
> ![image-20231117141634286](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117141634286.png)
>
> ![image-20231117141654171](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117141654171.png)
>
> ![image-20231117141712031](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117141712031.png)
>
> 5.传播行为：事务方法之间调用，事务如何使用	
>
> ```java
> @Transactional(propagation = Propagation.REQUIRED)
> ```
>
> ![image-20231117141822899](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117141822899.png)
>
> 比较常用required和required_new
>
> 添加checkoutService层
>
> ```java
> @Service
> public class CheckoutServiceImpl implements CheckoutService{
> 
>     @Autowired
>     private BookService bookService;
>     //买多本书的方法
>     @Transactional
>     @Override
>     public void checkout(int userId, int[] bookIds) {
>         for(int bookId : bookIds) {
>             bookService.buyBook(bookId, userId);
>         }
>     }
> }
> ```
>
> 如果是required如果余额不足一本都买不成功
>
> 如果是required_new可以成功买直到超出余额的书买不了
>
> ![image-20231117143603480](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117143603480.png)

##### 全注解配置事务

```java
package com.wjord.tx.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.wjord.tx")
@EnableTransactionManagement
public class config {

    @Bean
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///wjord");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(getDataSource());
        return dataSourceTransactionManager;
    }
}

```



##### 基于XML的声明式事务

 ![image-20231117145732725](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117145732725.png)

![image-20231117145955569](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117145955569.png)



### 资源操作：Resources

##### Resource接口

![image-20231117150622914](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117150622914.png)

访问url，file

```java
package com.wjord;

import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;

//访问网络资源
public class UrlResourceDemo {

    public static void main(String[] args) throws IOException {
        loadUrlResource("http://www.baidu.com");
		
        //在根文件下
        loadUrlResource("file:1.txt");
    }

    //访问前缀http,file开头
    public static void loadUrlResource(String path) throws IOException {
        //创建Resource实现类的对象UrlResource
        UrlResource urlResource = new UrlResource(path);

        //获取资源信息
        System.out.println(urlResource.getFilename());
        System.out.println(urlResource.getDescription());
        System.out.println(urlResource.getInputStream().read());
        System.out.println(urlResource.getURL());

    }
}

```

访问类路径下的资源

```java
package com.wjord;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

public class ClassPathResourceDemo {
    //访问类路径下的资源
    public static void main(String[] args) throws IOException {
        //在resource下
        loadClassPathResource("1.txt");
    }

    public static void loadClassPathResource(String path) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(path);

        System.out.println(classPathResource.getFilename());
        System.out.println(classPathResource.getDescription());
        InputStream inputStream = classPathResource.getInputStream();
        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1) {
            System.out.println(new String(bytes));
        }

    }
}

```

FileSystemResource访问系统资源

```java
package com.wjord;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

public class FileSystemResouceDemo {
    public static void main(String[] args) {

        loadFileSystemResource("c://1.txt");//相对路径和绝对路径都可以
    }

    public static void loadFileSystemResource(String path) {
        FileSystemResource fileSystemResource = new FileSystemResource(path);

        System.out.println(fileSystemResource.getFilename());
        System.out.println(fileSystemResource.getDescription());
        try {
            InputStream inputStream = fileSystemResource.getInputStream();
            byte[] bytes = new byte[1024];
            while (inputStream.read(bytes) != -1) {
                System.out.println(new String(bytes));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

```

##### Resource的实现类

![image-20231117152716797](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117152716797.png)



## ResourceLoader接口

可以得到Resource实例

![image-20231117152838628](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117152838628.png)

```java
package com.wjord;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceLoader {

    @Test
    public void demo01() {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Resource resource = context.getResource("1.txt");
        System.out.println(resource.getFilename());
    }

    @Test
    public void demo02() {
        ApplicationContext context = new FileSystemXmlApplicationContext();
        Resource resource = context.getResource("1.txt");
        System.out.println(resource.getFilename());
    }
}

```

![image-20231117153621023](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117153621023.png)

![image-20231117153634808](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117153634808.png)

![image-20231117153653434](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117153653434.png)

### ResouceLoaderAware接口

  ```java
  package com.wjord.loader;
  
  import org.springframework.context.ResourceLoaderAware;
  import org.springframework.core.io.ResourceLoader;
  
  public class TestBean implements ResourceLoaderAware {
  
      private ResourceLoader resourceLoader;
      @Override
      public void setResourceLoader(ResourceLoader resourceLoader) {
          this.resourceLoader = resourceLoader;
      }
  
      public ResourceLoader getResourceLoader(){
          return this.resourceLoader;
      }
  }
  
  ```

（上面代码耦合度高，不方便修改）



### 使用Resource作为属性

代码中获取Resource实例

使用依赖注入

```xml
<bean id="resourceBean" class="com.wjord.di.ResourceBean">
        <property name="resource" value="classpath:com/wjord/di/test.txt"/>
    </bean>
```

```java
package com.wjord.di;

import org.springframework.core.io.Resource;

public class ResourceBean {

    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void parse() {
        System.out.println(resource.getFilename());
    }
}

```



##### 应用程序上下文和资源路径 

 ![image-20231117162026716](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117162026716.png)



### 国际化i18n

![image-20231117164413590](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117164413590.png)

![image-20231117164533596](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117164533596.png)

配置文件命名规则：

basename_language_country.properties

![image-20231117165328744](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117165328744.png)

![image-20231117165927640](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117165927640.png)



### 数据校验（Validation）

> 第一步：引入依赖
>
> ```xml
> <dependency>
>             <groupId>org.hibernate.validator</groupId>
>             <artifactId>hibernate-validator</artifactId>
>             <version>7.0.5.Final</version>
>         </dependency>
>         <dependency>
>             <groupId>org.glassfish</groupId>
>             <artifactId>jakarta.el</artifactId>
>             <version>4.0.2</version>
>         </dependency>
> ```
>
> 
>
> 第二步：创建实体类，定义属性，创建对应set和get方法
>
> ![image-20231117171216423](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117171216423.png)
>
> ```java
> public class Person {
> 
>     private String name;
>     private int age;
> ```
>
> 
>
> 第三步：创建类，实现接口，实现接口的方法，编写校验逻辑
>
> ```java
> package com.wjord;
> 
> import org.springframework.validation.Errors;
> import org.springframework.validation.ValidationUtils;
> import org.springframework.validation.Validator;
> 
> public class PersonValidator implements Validator {
>     @Override
>     public boolean supports(Class<?> clazz) {
>         return false;
>     }
> 
>     @Override
>     public void validate(Object target, Errors errors) {
>         //校验规则
>         //校验name不能为空
>         ValidationUtils.rejectIfEmpty(errors,"name",
>                 "name.empty","name不能为空");
> 
>         //校验age不能为空，不能小于0，大于120
>         Person person = (Person) target;
>         if(person.getAge() < 0) {
>             errors.rejectValue("age", "age.empty", "age不能<0");
>         } else if (person.getAge() > 120) {
>             errors.rejectValue("age", "age.empty", "age不能>120");
>         }
>     }
> }
> 
> ```
>
> 
>
> 第四步：最终测试
>
> ```java
> package com.wjord;
> 
> import org.springframework.validation.BindingResult;
> import org.springframework.validation.DataBinder;
> 
> public class TestPerson {
>     public static void main(String[] args) {
>         //创建Person对象
>         Person person = new Person();
>         //创建person对应的dataBinder对象
>         DataBinder dataBinder = new DataBinder(person);
>         //设置校验器
>         dataBinder.setValidator(new PersonValidator());
>         //调用方法执行校验
>         dataBinder.validate();
>         //输出校验结果
>         BindingResult bindingResult = dataBinder.getBindingResult();
>         System.out.println(bindingResult.getAllErrors());
>     }
> }
> 
> ```
>
> 



## 基于Bean Validation注解实现

> 第一步 创建配置类，配置LocalValidationFactoryBean
>
> ![image-20231117173333012](./%E5%9B%BE%E7%89%87%E5%AD%98%E5%82%A8/image-20231117173333012.png)
>
> ```java
> package com.wjord.beanValidation;
> 
> import org.springframework.context.annotation.Bean;
> import org.springframework.context.annotation.ComponentScan;
> import org.springframework.context.annotation.Configuration;
> import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
> 
> @Configuration
> @ComponentScan("com.wjord.beanValidation")
> public class ValidationConfig {
> 
>     @Bean
>     public LocalValidatorFactoryBean validator() {
>         return new LocalValidatorFactoryBean();
>     }
> }
> 
> ```
>
> 
>
> 第二步 创建实体类，定义属性，生成get和set方法，
>
> ```java
> package com.wjord.beanValidation;
> 
> import jakarta.validation.constraints.Max;
> import jakarta.validation.constraints.Min;
> import org.jetbrains.annotations.NotNull;
> 
> public class User {
>     @NotNull
>     private String name;
> 
>     @Min(0)
>     @Max(100)
>     private int age;
> 
>     public String getName() {
>         return name;
>     }
> 
>     public void setName(String name) {
>         this.name = name;
>     }
> 
>     public int getAge() {
>         return age;
>     }
> 
>     public void setAge(int age) {
>         this.age = age;
>     }
> }
> 
> ```
>
> 
>
> 在属性上面使用注解设置校验规则
>
> ###### 使用jdk自带
>
> ```java
> package com.wjord.beanValidation;
> 
> import jakarta.validation.ConstraintViolation;
> import jakarta.validation.Validator;
> import org.springframework.beans.factory.annotation.Autowired;
> import org.springframework.stereotype.Service;
> 
> import java.util.Set;
> 
> @Service
> public class MyValidation {
> 
>     @Autowired
>     private Validator validator;
> 
>     public boolean validatorByUser(User user){
>         Set<ConstraintViolation<User>> validate = validator.validate(user);
>         return validate.isEmpty();
>     }
> 
> }
> 
> ```
>
> ###### 用spring
>
> ```java
> package com.wjord.beanValidation;
> 
> import org.springframework.beans.factory.annotation.Autowired;
> import org.springframework.stereotype.Service;
> import org.springframework.validation.BindException;
> import org.springframework.validation.Validator;
> 
> 
> @Service
> public class MyValidation1 {
> 
>     @Autowired
>     private Validator validator;
> 
>     public boolean validatorByUserTwo(User user){
>         BindException bindException = new BindException(user,user.getName());
>         validator.validate(user,bindException);
>         return bindException.hasErrors();
>     }
> 
> }
> 
> ```
>
> 
>
> 第三步 设置校验器
>
> ```java
> package com.wjord.beanValidation;
> 
> import org.junit.jupiter.api.Test;
> import org.springframework.context.ApplicationContext;
> import org.springframework.context.annotation.AnnotationConfigApplicationContext;
> 
> public class TestUser {
> 
>     @Test
>     public void test1() {
>         ApplicationContext context = new
>                 AnnotationConfigApplicationContext(ValidationConfig.class);
>         MyValidation bean = context.getBean(MyValidation.class);
>         User user = new User();
>         user.setAge(-1);
>         boolean b = bean.validatorByUser(user);
>         System.out.println(b);
>     }
>     @Test
>     public void test2() {
>         ApplicationContext context = new
>                 AnnotationConfigApplicationContext(ValidationConfig.class);
>         MyValidation1 bean = context.getBean(MyValidation1.class);
>         User user = new User();
>         user.setName("wjord");
>         user.setAge(20);
>         boolean b = bean.validatorByUserTwo(user);
>         System.out.println(b);
>     }
> 
> 
> }
> 
> ```
>
> 
>
> 第四步 完成最终测试

##### 基于方法进行校验

```java
package com.wjord.beanValidation.method;


import org.hibernate.validator.internal.engine.MethodValidationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@ComponentScan("com.wjord.beanValidation")
public class ValidationConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}

```

```java
package com.wjord.beanValidation.method;

import jakarta.validation.constraints.*;

public class User {

    @NotNull
    private String name;

    @Min(0)
    @Max(100)
    private int age;

    // 校验电话号码
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$",message = "电话号码格式不正确")
    @NotBlank(message = "电话号码不能为空")
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

```

```java
package com.wjord.beanValidation.method;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class Myservice {

    public String testMethod(@NotNull @Valid User user){
        return user.toString();
    }
}

```

```java
package com.wjord.beanValidation.method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUser {

    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(ValidationConfig.class);
        Myservice bean = context.getBean(Myservice.class);
        User user = new User();
        user.setName("wjord");
        user.setAge(20);
        user.setPhone("1234567890");
        bean.testMethod(user);
    }
}

```



### 实现自定义校验

```java
package com.wjord.beanValidation.method;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CanNotBlankValidator.class})
public @interface CanNotBlank {
    String message() default "不能包含空格";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        CanNotBlank[] value();
    }

}

```

```java
package com.wjord.beanValidation.method;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CanNotBlankValidator implements ConstraintValidator<CanNotBlank,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value!=null&&value.contains(" ")){
            String defaultConstraintMessageTemplate = context.getDefaultConstraintMessageTemplate();
            System.out.println(defaultConstraintMessageTemplate);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("不能包含空格").addConstraintViolation();
            return true;
        }
        else {
            return false;
        }
    }

}

```



### 提前编译：AOT（Spring6）

> AOT相关概念：
>
> 1.JIT：（JUST IN TIME），动态编译（实时），边运行边编译在程序运行的时候，动态生成代码，但启动比较慢，编译时候需要占用运行时候资源
>
> 程序运行过程中，把字节码转换硬盘上直接运行机器码，部署到运行环境
>
> 2.AOT：（AHEAD OF TIME），运行前编译，提前编译，可以把原大代码直接转换机器码，启动快，内存占用低，运行时候不能优化，程序安装时间变长
>
> 在程序运行前，就把字节码转换为机器码 

