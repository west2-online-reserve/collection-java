# 102300230的work3

## 总览

通过第三阶段的学习，我认识到了数据库的使用和用mysql语句实现数据库的增删改查，以及内连接等等的查询方式，使用Java语句操纵数据库（jdbc），第三方api的调用（还在学习中）

## Base部分

该项目是模拟订单系统进行操作，在数据库我将其分为了三张表，一张是商品表（**good**），一张是订单的总表用来记录订单总价，订单时间（最后一次修改的时间）以及订单编号（**totalorder**），最后是订单的目录表（某订单购买了某商品购买个数以及购买总价）（注释可能描述不是很清晰）（**ordergood**）

### 优点

运用了面向对象的思想，将订单，商品，目录打包为商品对象，将第一阶段和第二阶段的学习内容结合起来

### 缺点

没有使用接口

虽然有分页查询，但是不是对数据库进行的

没有使用事务管理

## bonus部分

bonus部分主要是对第三方api的调用。该项目调用了和风天气的api，通过输入城市名称，可以得到该城市的经纬度等信息和未来两天的天气情况，最后将他们存入数据库中

### 优点

可以通过城市名称查询天气情况，可以自定义添加城市

### 缺点

没有使用接口

没有使用事务管理

## 项目结构

```note
1293978818
├─ .vscode 
│  └─ settings.json //vscode的配置文件
├─ bin
├─ lib
│  ├─ commons-beanutils-1.8.0.jar //第三方api的依赖
│  ├─ commons-collections-3.2.1.jar
│  ├─ commons-lang-2.4.jar
│  ├─ commons-logging-1.1.1.jar
│  ├─ ezmorph-1.0.6.jar
│  ├─ json-lib-2.4-jdk15.jar
│  └─ mysql-connector-j-8.2.0.jar //mysql的依赖
├─ README.md
└─ src
   ├─ base
   │  ├─ domain
   │  │  ├─ Good.java //商品类
   │  │  ├─ OrderGood.java //订单目录类
   │  │  └─ TotalOrder.java //订单总表类
   │  ├─ order
   │  │  ├─ GoodFromToDataBase.java   //商品类的数据库操作
   │  │  ├─ OrderGoodFromToDataBase.java //订单目录类的数据库操作
   │  │  ├─ OrderManage.java //实现业务的业务类
   │  │  └─ TotalOrderFromToDataBase.java //订单总表类的数据库操作
   │  ├─ order.sql //数据库文件
   │  ├─ test
   │  │  └─ OrderManageTest.java //测试类
   │  └─ util
   │     └─ JdbcUtil.java //数据库连接类
   └─ bonus
      ├─ domain
      │  ├─ City.java //城市类
      │  └─ Weather.java //天气类
      ├─ test
      │  └─ WeatherTest.java //测试类
      ├─ util
      │  └─ JdbcUtil.java //数据库连接类
      ├─ weather
      │  ├─ CityInformationFromInternet.java //从第三方api获取城市信息
      │  ├─ CityInformationFromToDataBase.java //从数据库获取城市信息
      │  ├─ WeatherInformationFromInternet.java //从第三方api获取天气信息
      │  ├─ WeatherInformationFromToDateBase.java //从数据库获取天气信息
      │  └─ WeatherManage.java //实现业务的业务类
      └─ weather.sql //数据库文件

```

## 项目运行

### 1.数据库的建立

base部分在mysql中运行order.sql文件
bonus部分在mysql中运行weather.sql文件

### 2.配置文件

base部分在src的base目录下的util包中的JdbcUtil.java文件中修改数据库的用户名和密码
bonus部分在src的bonus目录下的util包中的JdbcUtil.java文件中修改数据库的用户名和密码

### 3.运行

base部分在order包中的test包中的OrderManageTest.java文件中运行main方法
bonus部分在weather包中的test包中的WeatherTest.java文件中运行main方法
