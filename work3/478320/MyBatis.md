# Mybatis

## 什么是Mybatis

是一款优秀的持久层框架，用于简化JDBC开发

持久层

* 负责将数据到保存到数据库的那一层代码
* JavaEE三层结构：表现层，业务层，持久层

页面展示，逻辑处理，持久化数据

## 框架’

就是一个半成品的软件，是一个通用的软件代码模型

在框架的基础之上构建软件编写更加高效，规范，通用，可扩展

使用Mybatis只能完成持久层的任务，将来在表现层和业务层的框架，我们都需要去学习

JDBC缺点

1. 硬编码
2. 操作繁琐

在注册驱动和创建数据库对象的时候有很多字符串，这些字符串就被称为硬编码，这些字符串信息将来可能发送变动，那就要改动代码，改动代码就意味着工作量，要重新编译打包运行，这个代码维护性就比较差一些了，在定义sql的地方也存在硬编码，如果将来？很多，手动设置参数也是一个比较繁琐的过程对结果遍历封装对象的时候也很烦

硬编码字符串写配置文件里就可以解决，sql语句和注册驱动都到配置文件里了，我们之前写的工具类可以帮助我们更好的理解

操作繁琐，我们需要让它自动完成，mybatis用一行代码就把设置参数和封装结果集搞定了，免除了几乎所有的JDBC代码，以及设置参数和获取结果集的工作，真的爽

## MyBatis快速入门

过程

1. 定义POJO类存用户对象

2. 加载核心配置文件，获取sqlSession Factory对象

3. 用这个对象执行sql语句

4. 释放资源

   加载配置文件编写坐标，我们通过下载的资源，配置了Maven环境，利用坐标加载了mybatis的核心配置，但是只有mybatis的核心配置是不够的，还需要mysql的驱动，还需要单元测试的坐标（Maven导入单元测试的坐标可以让Maven自动识别并执行单元测试。在Maven项目中，通常会使用JUnit等测试框架编写单元测试代码，将其放在src/test/java目录下。通过在pom.xml文件中指定单元测试的坐标，Maven会自动编译并执行这些测试代码，生成测试报告并输出测试结果。这样可以方便地进行自动化测试，提高项目的质量和稳定性。）还需要一个日志坐标（Maven导入日志的坐标通常指的是引入日志框架的依赖坐标，例如Log4j、SLF4J等。通过在Maven项目的pom.xml文件中指定日志框架的依赖坐标，可以让Maven自动下载并管理所需的日志框架库文件。这样可以在项目中使用日志框架来记录应用程序的运行日志，方便开发人员进行调试、排查问题和监控应用程序的运行状态。导入日志的坐标也可以让项目团队统一管理和配置日志框架，提高代码的可维护性和可扩展性。）日志框架需要有一个配置文件，放入对应的resource下面去第二步就完事了
   
   接下来我们要编写mybatis的核心配置文件，这个核心配置文件就是用来替换我们数据库连接信息的配置信息，我们要在resource下创建一个新的名为mybatis-config的xml文件（虽然文件名字可以自己取）但是为了和官网相符，不影响下面的步骤，我们就先使用这个文件名）在文件里我们使用从官网复制下来就可以了，但是这个文件是连接数据库的文件，我们需要修改Driver和用户名密码地下还有一个mappers是用来指定我当前sql映射文件的指定路径的，我们先记着加来要回来修改，这个映射文件我们也写在resource下，从官网，我们可以找到它的示例，这里的mapper里有一个名称空间namespace待会会重点去讲，我们现在随便叫一个名test，接下来的标签里面就可以定义sql语句了，将来增删改查写在这里,这里的select里面有id和resultType，id是这个sql语句的唯一标识，等会要去改一个值，resultType对应返回结果的类型，我现在要把数据包装成POJO的类型，那我们就先创建这个类，因为我们UserMapper和mybatis都在resource下并且同级别，所以我们在映射文件下写UserMapper.xml就可以了
   
   我们现在可以开始编码了，我们要在里面写对应的属性，属性就是表里的属性
   
   接下来我们就可以开始写mybatis核心的测试类了
   
   1. 加载核心配置文件，获取SqlSessionFactory对象
   
      这个代码将来只会写一次，所以这个代码我们去官网上复制过来就可以了，由于我们的mybatis-config.xml文件在resource的目录下，我们直接在resource里的路径直接写mybatis-config.xml就可以了，但是这里有个坑，新版idea要手动导入这些包了
   
      ![image-20231110135411952](C:\Users\余思衡\AppData\Roaming\Typora\typora-user-images\image-20231110135411952.png)
   
   2. 执行sql语句
   
      获取SqlSession对象，SqlSessionFactory里有一个方法，openSession，就会放回一个SqlSession对象，接下来我们就可以执行sql语句，在这个SqlSession对象里有一个方法，selectList，因为我们查询所有，前面的配置映射文件里写了，所以我们要使用这个方法返回一个List的集合，我们要查询一个也有方法
   
      selectList方法要我们传入对应表的唯一标识，就是我们的id（看自己是怎么设置的，通常都是id），但是唯一标识前面要根上所写的名称空间，我们就写test.selectAll就可以；这个test.selectAll就是我们前面在UserMapper里设置的id代名词，我们将来可能会有很多的Mapper，我们可以通过名称空间来区分每个Mapper对应的同名id
   
      selectList返回的List对象的泛型就是我们的User，一行代码秒杀查询很方便，我们不用每次都写，记得要释放噢，这个得我们自己写
   
      ```java
      sqlSession.close;
      ```
   
      

## Mapper代理开发

我们在获取Session对象的时候，selectList方法传入的是具体的路径，这样又造成了硬编码的问题，所以我们要解决这个问题，而且在将来也不太方便，我要一直差这个id是什么，那个id是什么

那我们通过Mapper代理开发要怎么写呢

我们调用sqlSession的getMapper方法传入我们的配置文件，返回一个userMapper的对象，这个对象里的方法会和配置文件里的方法的id名字一一对应，之后我们就可以使用selectAll方法了，方法的内容就在这个配置文件中，写起来很方便，也是企业中最为常见的开发方式

使用Mapper代理四步走

1. 定义与sql映射文件同名的Mapper接口，并将Mapper接口和sql映射文件放到同一目录下，放在同一目录下有两种方法，第一种直接把我们之前写好的UserMapper文件移动到这个mapper包下，但是这一并不好，因为Maven项目要求配置文件和Java代码分开，要不然不方便管理，第二种方法在resource下创建一个一样的包的结构，但是我们这里不能创建包，只能创建文件，我们就不能使用包的命名方法，就不能用.要用/表示，虽然在idea里显示是一样的，但是这样就会被放到一起了

2. 设置sql映射文件的namespace属性为Mapper接口全限定名

3. 在Mapper接口中定义方法，方法名就是sql映射文件中sql语句的id，并保持参数类型和返回值一致，这里还需要判断要查一个还是很多个，如果是很多个，就需要返回List<User>对象

4. 编码调用这个方法获取对象，调用对应方法完成sql执行

   利用SqlSession的对象的getMapper方法传入对应的class文件来获取这个对象

5. 包扫描，在config文件下mapper中，我们原来是使用了resource，写下这个配置文件的具体路径，但是我们如果使用了mapper代理，我们就可以使用包扫描的方法，<package name="包名"这样就可以导入所有这个包下的配置文件，而不是只有一个，可以提高效率

## Mybatis核心配置文件

核心配置文件是mybatis-config.xml这个文件名任意起，但是官方都叫这个名，打开官网我们可以看到配置文件的很多信息

1. 环境配置environment，配置数据源，将来可以配置多个environment，将来可以匹配多个数据库，开发库，测试库等等，通过对应的default属性来切换不同的environment

2. transaction我们使用的是JDBC的形式管理事务，这就不用改了，将来我们学了更高级的框架，这个操作就被接管了

3. dataSource type就是数据库连接池，mybatis默认的是pooled，将来的数据源信息也会被接管

4. mappers前面讲过

5. type Aliases类型别名，我们在写UserMapper的时候，就有一个resultType，我们需要把包名类名都写一遍，比较麻烦，我们在核心配置文件中写上typeAliase在里面进行包扫描<package name="com.huayu.pojo"/>这样配完之后，相当于我给所有的Pojo包下的类起了一个别名，别名就是类的名字，就不需要大小写了，也不用带包的名称了，将来我们就可以使用user，我们在官网也可以找到单独配置的方法，最为方便还是包扫描，对于基本的类型也已经帮忙写了别名比如string，对于基本的类型就可以不区分大小写来写

6. 细节，配置各个标签时要遵守前后顺序，别名放到下面写是会报错的，这就是所说的xml的约束

   MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 配置文档的顶层结构如下：

   - configuration（配置）
     - [properties（属性）](https://mybatis.org/mybatis-3/zh/configuration.html#properties)
     - [settings（设置）](https://mybatis.org/mybatis-3/zh/configuration.html#settings)
     - [typeAliases（类型别名）](https://mybatis.org/mybatis-3/zh/configuration.html#typeAliases)
     - [typeHandlers（类型处理器）](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)
     - [objectFactory（对象工厂）](https://mybatis.org/mybatis-3/zh/configuration.html#objectFactory)
     - [plugins（插件）](https://mybatis.org/mybatis-3/zh/configuration.html#plugins)
     - environments（环境配置）
       - environment（环境变量）
         - transactionManager（事务管理器）
         - dataSource（数据源）
     - [databaseIdProvider（数据库厂商标识）](https://mybatis.org/mybatis-3/zh/configuration.html#databaseIdProvider)
     - [mappers（映射器）](https://mybatis.org/mybatis-3/zh/configuration.html#mappers)

## 配置文件完成增删改查

### 准备环境

我的UserMapper的方法名字和映射文件要一一对应，方法多了就很麻烦去找，使用MybatisX之后将来有一个按钮，点以下就可以跳转到对应的Mapper接口对应的方法的位置

将来这两个名字可能会写错，装了MybatisX会自动生成对应的名字，就不会写错了，多了一些鸟我服了

还可以一键生成sql

## 正式编写

### 查询所有

完成一个sql就只有与之对应的三步

记得还要在配置文件里加载对应的mapper

1. 编写接口方法：Mapper接口
2. 编写sql语句
3. 执行方法,测试

我们会发现，有些数据被封装了，但是也有些数据还是null，主要原因是我们的brand使用变量的是驼峰命名法，而数据库用的是下划线，这样就不能自动的帮助我们封装了，所以我们需要手动的设置，第一种方案是对sql字段的查询名称起别名，这次不查询*了，查询全部数据，再给每个不一样的名称利用as起别名，起为和驼峰命名法一样的内容，但是这里有一个问题，将来要做很多的查询，每一个都要起一个别名很不方便，我可以定义一个sql片段

```java
<sql id="brand_column"></sql>
```

代表我们要写的查询的内容的一串，我们把它放到两个sql标签中间就可以了

```java
<include refid="brand_column"/>
```

引用这个sql片段

使用sql片段也有局限性，有没有可能有的sql语句我要查两个，查三个，那就要定义一排sql片段，又有缺点，不灵活，套娃呢

使用resultMap就可以灵活解决这些问题，也是将来最为常用的功能

我们使用resultMap标签

```java
<resultMap id="brandResultMap" type="Brand">
        <result column="brand_name" property="brandName"/>
        <result column="company_name" property="companyName"/>
    </resultMap>
```

id随便起，类型就是我们的要查的类（支持别名），主键使用id映射，其他类使用result映射，映射的column表示列名，property表示属性名，对应上，最后再再我们的mapper里的resultType改为resultMap，等于我们定义的id，这样就非常的灵活了

### 查询详情

mybatis里where中如何接收 id = 多少？以前我们写的是问号，但是现在使用mybatis框架后没有这步操作，来看看该怎么做

```java
select * from tb_brand where id=#{id};
```

#{}里面的内容要和方法中给的参数名字保持一致，只有一个参数其实爱写什么都可以，应该是为了规范吧

#### 参数占位符

在mybatis里有两种形式，第一种是#{}第二种是${}

#{}会将其替换为？为了防止sql注入

${}没有替换，会存在sql注入问题

使用时机

* 参数传递的时候用#{}
* 动态查询某张表，表名列名不固定的情况下可以使用，那就一定存在sql注入的问题

#### 参数类型

正常来说可以在id，和resultMap中间加入parameterType可以指定参数类型，但是这个值可以省略，一般不写，没有什么影响，因为我们在方法里已经定义了类型限制，我们后面只要传什么赋值什么就可以了，每什么必要

#### 特殊字符处理

将来在where中使用条件的时候我们可能不写等号，我们写小于号就会报错，因为我们的sql语句是写在xml文件下的，而xml文件的小于号是标签的意思，这不符合xml的语法规则，要解决这个特殊字符有两种方式

1. 转义 小于号为&lt；
2. CDATA区 CD回车补齐CDATA区把小于号写里面就可以了，一般特殊字符比较少的时候用转义字符，特殊字符多的时候使用CDATA区

### 条件查询

#### 多条件查询

1. 条件表达式 status=  company_name like ？ brand_name like ?
2. 如何连接 and连接

现在由于有多个参数mybatis不知道我要把哪个值传给sql语句中的哪一个占位符，所以我们就要用@Param("status")来注解一下，这个status就传递给status的占位符，这是第一种方式散装参数

如果这三种参数都属于一个对象的话，它可以直接把他封装成一个对象，将来把对象传递到这个方法里面来，这个对象的值就会传过去，sql就会从对象里去找getStatus方法然后获取这个对象里面的status的值，获取过来，所以这个时候#{}里的名称要和属性的名称一样，要不然就找不到对应的get方法了

第三种封装成一个Map集合，键的名称要和这三个参数占位符保持一致

现在演示第一种方法，我们首先要定义这些status等等的参数，由于我们使用的模糊查询，我们还要对参数进行处理，通常也是在代码里对这个参数利用字符串拼接来完成，我们不在方法里直接传入参数是因为用户肯定会传入不带%的参数，是我们要处理，不是让用户来处理，所以不在方法里写参数

第二种方法要先new一个brand对象利用set方法设置值，然后再传入这个对象，设置这些值的方法看起来很麻烦，但在将来都是自动完成的不用我们去写，其实不是那么麻烦，但是参数处理是需要我们来处理的，因为不同的业务有不同的需求

第三种方法

```javav
Map map =new HashMap();
        map.put("status",status);
        map.put("companyName",companyName);
        map.put("brandName",brandName);
```

将来用户可能不会输入三个值，可能只输入一个值，那就查询不出来了当前这个程序不灵活，我们必须要求用户把所有条件都输入才可以查询，哪怕少一个都查询不出来，所以我们就需要动态查询，来优化这个功能

### 动态条件查询

我在sql里的where中写上if语句就可以实现，mybatis提供了很多的标签来支持我们完成动态sql

if,if标签里有一个test属性这个属性就是Java里的if判断，我们在标签里加上对应的结果就可以了，但是这里不能用Java里的方法了

```java
<if test="status != null">
    status=#{status}
</if>
<if test="companyName != null and company != '' ">
    and company_name like #{companyName}
</if>
如上所示，我们要判断的是得到值的属性，而不是未知的companyName
```

发现问题，我们不能一下where马上and，所以我们的status条件被限制一定要查询，后期应该也可以处理

有两种解决方式

1. 我先让每一个都有and，我在where后面加一个1=1恒等式就可以解决这一个问题，但这个方式有一点笨，这个问题它早就想到了，使用where标签，可以替换where关键字

where，使用这个where标签后，里面每一个都加and就不会出错了，自动把and给去掉了

### 单条件动态条件查询

用户可以选择当前状态，企业名称，品牌名称来进行单条件查询，用户选哪个是不知道的

在mybatis里提供了choose（when，otherwise）来进行单条件的动态查询，和Java里的的switch很相似，when就和case很类似，otherwise就和default很类似

这样还是会有一点小问题的，将来可能用户一个都不选直接点搜索，这时候查询就会报错，我们就可以使用otherwise，可以用恒等式来保底，但是写otherwise不是特别方便我们可以把他干掉，where标签就可以动态的把sql的语法调整正确，where标签会自动把where省略掉

### 添加

实现添加功能，我们不应该让用户填写这个id的值，所以在执行添加的时候我们要添加除了id外的所有数据

添加功能我们实现完了，运行也是正常的，但是我们对应的mybatis事务的问题，自动开启了事务，由于没有手动提交事务回滚了事务，所以我们要使用sqlSession.commit方法来提交

如果我们不想手动的提交事务的话，我们就可以在获取sqlSession的时候传个true就会自动提交事务

主键返回，在数据添加成功后，有的时候需要获取插入数据库数据的值，比如添加订单和订单项，在没有做任何改动的情况下，我使用brand的get方法获取id的值是获取不出来的，因为我们的brand对象里这个属性可还没赋值，就会导致一些问题，比如我们在获取一些关联数据的时候就会出现问题了，比如订单和订单项，那我要知道单号才可以查订单项啊，我现在id都不知道怎么搞。这种情况怎么解决呢我们可以在insert标签里加useGeneratedKeys=“true” keyProperty="id"这样就可以把这个id的值拿出来设置到属性上，这样一设置再通过addOrder对象的属性值来获取到这个id，这样我们就可以使用get方法获取到这个id了，这就是我们所说的主键返回

### 修改

#### 修改全部字段

返回结果void 参数所有数据，也可以使用int返回受到影响的行数

#### 修改动态字段

```xml
set brand_name = #{brandName},
    company_name = #{companyName},
    ordered = #{ordered},
    description = #{description},
    status = #{status}
where id=#{id}
```

我如果设置只传入了密码，那么其他的值就都会被设置成null了，这样不好，所以我们要使用动态的sql，使用if来包裹set语句，输入什么我们改什么，现在又出现了和之前一样的问题，就是逗号的问题，如果status将来不存在，就又会导致sql语句出错，所以要使用set标签

### 删除

#### 删除一个

#### 批量删除

封装id数组，遍历这个数组，根据数组里每一个id的值来删除

书写是delete from tb_brand where id in(?,?,?,?)

问题出现，我们怎么知道要写几个问号呢？也就意味这个将来这个sql要写成动态sql，那我就要遍历这个数组，mybatis里提供了foreach标签，里面有三个属性，connection遍历哪个数组（但是这个数组不能直接写ids，mybatis会将数组参数封装为一个map集合，这个集合里有key默认是array，所以要写array，那我就要写ids，就可以用@Param注解来改变默认key的名称） item遍历哪个属性，使用之后会发现sql还是有错，因为每个占位符要用逗号隔开，在foreach标签里还有一个属性separator分隔符，我们让它等于，就可以了，我们还可以改进，in的两个括号看起来不那么和谐，我们就可以使用另外的属性，open开始拼个什么，close结束后拼个什么，我们写上括号就可以了

## 参数传递

我们可以接收单个参数和多个参数

单个参数：

POJO类型，保证属性名和参数占位符保持一致，对于Pojo类型的不会封装为map，可以直接使用

Map集合，也可以直接使用，键名和参数占位符一致

Collection：

List：

Array：

其他类型：

Mybatis底层提供了一个ParamNameResolver类来进行参数封装

我们通过查看这个类的源码来封装这些参数

多个参数的处理：会将这个参数封装为Map集合，Mybatis会自动写上一些默认的键的名称，值就是我们传入的值

map.put("",参数值1)第一个参数键的名称是arg0，第一个参数还对应另外一个键的名称Param1

map.put("arg0",参数值1)

map.put("Param1",参数值1)

也就是如果我们不写Param注解的话，我们可以使用对应的param1，param2来获取对应的参数1和2的值，那我们现在在mapper的xml文件里的#{}替换为arg0，arg1来就可以设置上，就可以查到了，用param1和param2也可以，但是不推荐这么做，这样的参数名称很难阅读，所以我们可以使用Param注解来设置参数的名称，它其实上是来替换map集合中默认的arg的键名，结论都使用param注解，可读性更高

使用collection，首先判断有几个参数，判断为一个后，会进入一个判断是否是collection的方法，判断后创建一个map对象，创建一个键名是collection的属性放到map里，最后判断这个参数是不是list，如果是再传一个list的键，不是继续，然后再创建一个键arg0所以最少会有两个键

这里还有如果放的是array就会放一个array键，再去put arg0和这个数组，将来我们都不要使用默认的值，都使用param去去替换默认的arg键名

使用mybatis时，我给我的select方法传入了一个map对象，但这个map对象里是user对象，我的mapper文件里有userName和password两个属性，两个占位符，我该怎么书写这两个占位符使其合法

如果你的Mapper文件中有userName和password两个占位符，并且你要从一个Map对象中获取这两个值，你可以在Mapper文件中这样书写：

```xml
<select id="selectUser" parameterType="map" resultType="User">
  SELECT * FROM users WHERE username = #{user.userName} AND password = #{user.password}
</select>
```

在这个例子中，假设你的Map对象中有一个名为"user"的键，对应的值是一个User对象。在SQL语句中，你可以使用`#{user.userName}`和`#{user.password}`来引用User对象中的属性。

当你调用这个selectUser方法时，你可以将一个Map对象作为参数传入，其中包含了"user"键和对应的User对象。MyBatis会从这个User对象中获取userName和password属性的值，并将其填充到SQL语句中的占位符中。

希望这能帮到你解决问题！

## 注解完成增删改查

@Select（sql）语句唯一区别，就是sql语句写道xml去，注解完成就是把sql写到注解里去，官方说注解更加方便，很好理解，可以秒杀

但是这个注解我们一般完成一些简单的内容，对于稍微复杂的语句，Java注解力不从心事倍功半，让你本来就复杂的sql混乱不堪

简单用注解，复杂用xml
