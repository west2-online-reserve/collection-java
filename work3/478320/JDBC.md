# 第一个JDBC程序

1. 创建测试数据库

2. 导入数据库驱动，创建一个lib目录，导入驱动，右键add as Library再把驱动导入External Library

3. 编写测试代码

4. ```java
   //加载驱动
   Class.forName("com.mysql.cj.jdbc.Driver");//固定写法
   //用户信息和url
   String url="jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSl=true";//数据库名，字符编码，字符集，安全连接
    String username="root";
    String password="Yusiheng666666";//用户名和密码
   //连接成功返回数据库对象
    Connection connection = DriverManager.getConnection(url, username, password);
   //connection代表数据库       
   //执行sql的对象
    Statement statement = connection.createStatement();
   //用于执行sql的对象,不过这个对象是不安全的，后面还会教
   //用执行sql的对象执行sql，可能存在结果，返回结果
    String sql = "select * from users";
   ResultSet resultSet = statement.executeQuery(sql);
   statement后加查询，更新（插入和删除都是更新）括号中写上要执行的语句，并把这个结果返回给resultSet，是一个链表的形式
    while(resultSet.next()){
               System.out.println("id=" + resultSet.getObject("id"));
               System.out.println("name=" + resultSet.getObject("Name"));
               System.out.println("pwd=" + resultSet.getObject("PASSWORD"));
               System.out.println("email=" + resultSet.getObject("email"));
               System.out.println("birthday=" + resultSet.getObject("birthday"));
           }如果能查出来说明我们没写错
   //释放连接
         resultSet.close();
           statement.close();
           connection.close();
   关闭这三个
   ```

1. driverManage，可以注册驱动，加载驱动，获取连接

2. url唯一定位符组成公式看成url="协议://主机地址:端口号/数据库名?参数1&参数2&参数3，mysql默认是3306，oralce默认是1521 jdbc:oracle:thin:@localhost:1521:sid

3. connect代表数据库，可以设置自动提交，事务提交，事务回滚

   有这些方法setAutoCommit，rollback，commit

4. statement执行sql的对象，还有一个prepareStatement

   它就可以执行查询返回result，执行更新返回受影响的行数，execute执行任何sql，有一个判断的过程，效率就会低一点

5. 执行对象前要编写具体的sql

6. resultset，查询的结果集，封装了所有的查询结果

   getXXX类型；在不知道类型的情况就用Object，如果知道列的类型就会使用指定的类型，里面有一个next方法，那肯定有一个指针，可以遍历，就有before，after，移动指针next移动到下一个

   resultset.previous;移动到前一行，absolute（row)移动到指定行

7. 释放资源必须去完成，尤其是connection连接非常的耗资源，一定要关掉

## statement对象

jdbc中的statement对象用于向数据库发送sql语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可

实现代码我们就需要提取工具类，因为大量代码都是重复的

读取配置文件

先创建一个命名为db.properties的文件

```
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true
username=root
password=Yusiheng666666
```

```java
package com.huayu.lesson02.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcUtils {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;
    
    static {
        try{
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

插入后就持久化了，再次插入就会报错了，因为有id主键，主键只能唯一且只有一个

## sql注入问题

sql存在漏洞，会被攻击导致数据泄露

我们登录一个用户，也就是查询这个用户是否存在，那我们就要写一个登录方法，方法里传入两个String的参数，我们使用statement对象传入sql语句时，我们不能直接在""里面写这两个参数，直接写就会变成真的传的是这个两个字符，而不是对象，所以我们就需要使用字符串拼接来解决这个问题

如果输入正确就会返回查询的结果，反之则没有结果

如果我们非正常登录，使用逻辑运算符（用户名为空or'1=1'）作为用户，这个运算符结果如果正确(永远成立)，就会盗出所有密码等于我们输入密码的用户，这就是Sql注入，网站就存在漏洞，会被攻击

sql注入，是指web应用程序对用户输入数据的合法性没有判断或过滤不严

我们之所以会出现上面情况，是因为把不合法的字符串也接收过去了，攻击者可以在web应用程序中事先定义好查询语句的结尾添加额外的sql语句，在管理员不知情的情况下，实现非法操作，以此来实现欺骗数据库服务器执行非授权的任意查询，从而进一步获取信息

几乎所有的数据库都会存在sql注入漏洞，Java有一个类可以天然屏蔽它，所以之后我们不会用statement来操作

## PrepareStatement对象

本质区别，可以防止sql注入，并且效率更高

我们之前使用statement对象的sql看起来不好看，很复杂，预编译statement对其进行了优化

1. 新增

   第一个区别，connection不需要create preparestatement

   ```java
   直接preparestatement=connection.prepareStatement();括号里面要传入参数，一个预编译参数，先写sql不执行
       String sql= "Insert into users(id,`name`,`password`,email,birthday) values(?,?,?,?,?)";
   使用？占位符代替参数
       然后预编译一下，这个时候还没有执行
       接着我们使用Preparestatement对象的set方法来给？赋值
   传入两个值，一个参数下标，一个具体的值，第一个下标是1，第二个是2...，注意一个细节Date是util包下的date不然等下会报错
       preparedStatement.setDate(5,new java.sql.Date(new Date().getTime()));
   获得时间后，我们还要把他转换成mysql支持的时间类
       到这里预编译就完毕了，还需要执行，执行的时候不需要传参，会返回一个受影响的行数
       这种方式更安全，并且可以避免sql注入
   ```

2. 删除

3. 更改

4. 查询

它是如何防止sql注入的呢，使用preparation后，再尝试sql注入，就会发现程序正常运行，且不会报错，但是没有结果

本质：把传递进来的参数当作字符，传进来的东西都会用引号包裹起来，假设其中存在转义字符，就直接忽略，比如说''会被直接转义，转义完后就不对了，所以我们推荐使用preparestatement

PreparedStatement是一种预编译的SQL语句，它可以有效地防止SQL注入攻击。其本质在于将SQL语句与参数分开处理，使得参数值不会被解释为SQL语句的一部分，从而避免了SQL注入攻击。

具体来说，PreparedStatement在执行之前会将SQL语句发送给数据库进行预编译，这样可以将SQL语句与参数分开处理。在执行时，PreparedStatement会将参数值绑定到SQL语句中，而不是将参数值直接插入到SQL语句中。这样，即使参数值中包含有恶意代码，也不会被解释为SQL语句的一部分，从而避免了SQL注入攻击。

另外，PreparedStatement还会对参数进行类型检查，确保参数值的类型与SQL语句中的参数类型匹配，这也可以有效地防止SQL注入攻击。

总之，PreparedStatement的本质在于将SQL语句与参数分开处理，避免了参数值被解释为SQL语句的一部分，从而避免了SQL注入攻击。

我们之后要学一个框架Mybatis

## 使用IDEA连接数据库

点击右侧的Datebase，点击加号，Datesource，选中mysql

连接成功后可以选择数据库，点击设置回到之前的页面，点击Schemas选择我所需要的数据库，这时候我们所有东西就可以在这里面看了，双击数据库就可以查看数据库里的内容，使用ALT+8可以得到输出行，在这里可以看到我们的sql

我们可以修改密码，会发现上面灰色的箭头变成绿色，这个就是提交，必须要点提交才会改成功

可以点击右上角一个长得和终端一样的小软件，这就是我们写sql的地方

在写sql的地方右上角还有一个当前数据库的名字，在这里可以切换数据库

在连接数据库的时候会有一些坑，第一个我们在连接的时候，驱动不会自动加载，需要在左上角的Driver手动找到MySQL选择连接

## 事务

```java
connection.setAutoCommit(false);//关闭自动提交，这个方法会自动开启一个事务，不需要我们再写了
回滚也可以不写，默认会回滚
```

```java
package com.huayu.lesson04;

import com.huayu.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args){
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        try {
            connection= JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            String sql1="update account set money = money-? where id=?;";
            String sql2="update account set money = money+? where id=?;";
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement1.setFloat(1,100);
            preparedStatement1.setInt(2,1);
            preparedStatement2.setFloat(1,100);
            preparedStatement2.setInt(2,2);
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            connection.commit();
            System.out.println("转账成功");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.release(connection,preparedStatement1,null);
                JdbcUtils.release(null,preparedStatement2,null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
```

具体代码如下

## 数据库连接池

数据库连接执行完毕---释放，是十分浪费系统资源的

池化技术：准备一些预先的资源，过来就连接预先准备好的

预先准备几个可用的connect，过来就能用，用完也不释放，放回去

假设银行，开门服务关门，很麻烦，现在变成开门等待，来人服务，等待服务，只有服务器关闭他们才下线，现在涉及到一个问题，一个银行要有多少个人呢？，就涉及到最小连接数

常用连接数几个就写最小连接数几个

最小连接数：五个也可以，十个也可以，一百个也可以

最大连接数：100 业务最高承载上限

超出的人数排队等待

等待超时：100ms就会断掉

数据库连接池是一种管理数据库连接的技术，它可以在应用程序和数据库之间建立一组预先分配的数据库连接，并且可以在需要时动态地分配和回收这些连接。连接池的最小连接数和最大连接数是连接池中可以保持的最小和最大连接数量。

最小连接数指的是连接池中始终保持的最小连接数量。即使没有活动的数据库操作，连接池也会保持这些连接处于活动状态，以便在需要时立即使用。

最大连接数指的是连接池中可以同时拥有的最大连接数量。当连接池中的连接数量达到最大连接数时，新的数据库连接请求将被阻塞或者被拒绝，直到有连接被释放回连接池。

设置最小连接数和最大连接数可以帮助优化数据库连接的利用率和性能。如果设置的最小连接数太低，可能会导致应用程序在需要时无法及时获取数据库连接；如果设置的最大连接数太低，可能会导致数据库连接请求被阻塞或者被拒绝。因此，合理地设置最小连接数和最大连接数非常重要。

编写连接池只要实现一个接口DataSource，市面上比较知名的实现类，开源数据源实现，DBCP，C3P0，Druid：阿里巴巴

使用了这些数据库连接池之后，我们在项目开发中就不需要书写连接数据库的代码了

> DBCP

c3p0

用xml文件来配

不用导入会自动匹配
