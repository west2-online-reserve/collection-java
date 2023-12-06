# 项目地址

github:https://github.com/shaoxiawjc/ShopForWestOnlineExam.git



# 技术栈

* mysql
* mybatis
* spring



# 学习进度

目前已经学到spring boot了，现在正在写一个web项目练springboot，所以学习暂时停了





# 项目目录结构

## Controller层

（不是web项目，放这边空着）



## pojo层

实体类

* 订单类
  * 订单id
  * 订单总价
* 商品类
  * 商品id
  * 商品总价
* 商品订单关联类
  * 关联表id
  * 订单id
  * 商品id
  * 商品数量



查询结果类

* 结果集订单一对多商品类

  * 订单id

  * 订单总价

  * 商品

  * 商品数量



## dao(mapper)层

基础（其实很奇怪）的增删改查

* OrderMapper
* ProductMapper
* OrderProductMapper



## service 层（功能）

复杂的业务操作

* OrderService
  * 查询所有订单  List<ResultOrderProduct> selectAllOrder();
  * 查询所有订单商品关联 List<OrderProduct> selectAllOrderProducts();
  * 通过订单id查询订单 List<ResultOrderProduct> selectOrderById(int id);
  * 通过订单商品关联表id查询关联表 List<OrderProduct> selectOrderProductByOrderId(int orderId);
  * 增加一份订单 int insertOrder(OrderProduct orderProduct);
  * 删除一份订单 int deleteOrder(int id);
  * 修改一份订单 int updateOrder(OrderProduct orderProduct);
* ProductService
  * 查询所有商品 List<Product> selectAllProduct();
  * 查询指定商品 Product selectProductById(@Param("id") int id);
  * 增加商品 int insertProduct(Product product);
  * 删除商品 int deleteProduct(@Param("id") int id);
  * 更新商品 int updateProduct(Map map);









# 项目亮点

* 用到了spring整合mybatis应该可以算一点吧，其实后面已经把springboot的基本使用学了，但是感觉必须得是web项目，就没有用boot

* 使用service层来进行复杂的操作，感觉有些特定的业务再dao层里不是能很完美的完成，但是没有什么是加一层不能解决的^v^
* 关联表不知道算不算一点，其实本来之前是用联合主键当索引的，但是后面听了学长的建议，还是废除了之前的方案





# 待改进点

1. 代码的整洁度吧，感觉代码太乱了，不知道学长学姐们能不能看的舒服
2. 其实在dao（mapper）层里的mapper忘记放到resource下了，但是后面写完了才发现，因为怕出bug就没移动了，以后一定注意
3. dao层的增删改并不只是简单的增删改，比如订单的增其实是增加一个总价为0.00的空订单，然后再service里的复杂业务添加商品，再更新总价，这是我能想到的一个方法，但是确实并不知道应该怎么简单的增加订单
4. 查询结果没有优化，结果集里很多都是0，但是我想的是如果是一个web项目，返回到前端的话让前端来处理要显示的数据就好了





# 项目如何启动

目前我只知道web项目怎么启动，我这个不知道什么的项目也就在test里启动启动吧......







