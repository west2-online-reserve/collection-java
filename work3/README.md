# 第三次任务

## 任务进度

* 基本完成了订单管理系统的功能
* 编写了jdbc工具类，实现了订单和商品的增删改查，订单排序功能。



## 用到的技术栈

* JAVA编程语言
* 数据库



## 项目目录结构介绍

**项目有四个主要部分**

* 工具”dao”，存放实现操作的jdbc工具类。

* 对象“entidy”，存放表格的实体类。

* 测试类”test“，实现项目的测试。

* ”Utils”，存放JDBC所需要的包

  

## 项目功能介绍

**test类，实现了菜单功能**



**dao包，有全部订单查询方法**

* 全部订单查询 selectAll

* 单笔订单查询 selectOne

* 商品查询 selectCommodityAll

* 单个商品查询 selectCommodityOne

* 添加订单 insertOrder和insertCommoditylinkOrder

* 添加商品 insertCommodity

* 修改订单 updateOrder

* 修改商品 updateCommodity

* 删除订单 deleteOrder

* 删除商品 deleteCommodity

* 订单按价格排序  SortOrdeByPrice

* 订单按下单时间排序 SortOrderByTime

  

# 项目待改进点

* 订单与商品再建一张关联表，实现多对多关系。对表进行的操作有点粗糙。不够便捷，但基本能实现其功能。
* 许多变量名称取得不够标准