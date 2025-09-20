[项目地址](https://github.com/LYanl7/west2-online-collection-java-work3-LYanl7/tree/master)

## 学习情况
- [x] 完成了商品的增删查改，订单的增删查，排序等功能
- [x] 对商品和订单的 service 层和 mapper 层有相对完整的单元测试
- [x] 使用 springboot 框架暴露路由
- [x] 利用 pagehelper 实现了分页查询
- [x] 使用 hikariCP 连接池
- [x] 使用 mybatis 框架
- [x] 利用 maven 管理项目依赖
- [x] 粗略地了解了一下 spring MVC 和spring framework， 最后被繁琐的配置击退了
- [ ] 第三方 API 调用

## 数据库设计
利用关联表存储订单相关的商品信息
``` sql
    CREATE TABLE order_items (
        id INT PRIMARY KEY AUTO_INCREMENT,
        order_id INT NOT NULL,
        product_id INT NOT NULL,
        price DECIMAL(10, 2) NOT NULL, # 单个商品的总价
        is_deleted TINYINT DEFAULT 0 
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
        update_time DATETIME DEFAULT
        CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        FOREIGN KEY(order_id) REFERENCES orders(id),
        FOREIGN KEY(product_id) REFERENCES products(id)
    )
```
商品表的设计
```sql
    CREATE TABLE products (
        id INT PRIMARY KEY AUTO_INCREMENT,
        price DECIMAL(10, 2) NOT NULL,
        name VARCHAR(100) NOT NULL,
        is_deleted TINYINT DEFAULT 0,
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
        update_time DATETIME DEFAULT
        CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    )
```
订单表的设计
```sql
    CREATE TABLE orders (
        id INT PRIMARY KEY AUTO_INCREMENT,
        total_price DECIMAL(10, 2) NOT NULL,
        is_deleted TINYINT DEFAULT 0,
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
        update_time DATETIME DEFAULT
        CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    )
```
为了处理删除已存在于订单中的商品，我采用了逻辑删除，删除这个商品时直接将 is_deleted 置1即可。