技术栈：

- Java 
- MySQL 
- JDBC

项目目录结构：

```
├── db
│   └── test.sql
├── Order.java
├── OrderManagementSystem.java
├── Product.java
├── README.md
└── JDBCUtil.java

```

- `db`目录：存放数据库初始化脚本。
- `Order.java`：订单实体类。
- `OrderManagementSystem.java`：订单管理系统主类。
- `Product.java`：商品实体类。
- `JDBCUtil.java`：JDBC工具类，用于获取数据库连接。

项目功能：

- 添加商品：向数据库中添加新商品。
- 删除商品：从数据库中删除指定商品。
- 分页查询所有商品：从数据库中查询所有商品并按照分页方式展示。
- 创建订单：将用户选购的商品添加到订单中，并存储到数据库中。
- 删除订单：从数据库中删除指定订单。
- 查询所有订单：从数据库中查询所有订单，并按照下单时间或总价排序展示。

项目亮点：

- 解决SQL注入问题
- 添加**事务管理**
- 使用面向对象的思想设计实现。
- 使用JDBC连接MySQL数据库进行数据的增删改查操作。
- 使用了Lambda表达式和Comparator接口进行列表排序。
- 使用了自定义异常类处理异常情况。

项目待改进点：

- 没有使用Maven或Gradle等构建工具管理依赖项。
- 没有进行单元测试。
- 没有使用mybatis等orm框架。

项目如何启动：

1. 使用datagrip在MySQL数据库中创建数据库。
2. 在`JDBCUtil.java`中修改数据库连接配置。
3. 运行idea`中的`Test2`启动程序。