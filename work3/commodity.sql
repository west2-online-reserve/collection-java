create table commodity(
    commodity_id int auto_increment primary key comment ' 商品编号',
    commodity_name varchar(100) comment '商品名称',
    commodity_price double comment '商品价格'
)comment '商品表';

create table orders(
    order_id int auto_increment comment '订单编号' primary key,
    order_time varchar(20) comment '下单时间',
    totalPrice double comment '订单价格'
)comment '订单表';

create table commoditylinkorder(
    commoditylinkorder_id int auto_increment comment '商品订单关联编号' primary key,
    order_id int comment '订单编号',
    commodity_id int comment '商品编号',
    commodity_num int comment '商品数量',
    order_price double comment '订单价格'
)comment '商品订单关联表';

-- 添加外键
alter table commoditylinkorder add constraint fk_commoditylinkorder_commodity_id foreign key (commodity_id) references commodity(commodity_id) on update cascade on delete cascade;
alter table commoditylinkorder add constraint fk_commoditylinkorder_order_id foreign key (order_id) references orders(order_id) on update cascade on delete cascade;
-- 删除外键
alter table commoditylinkorder drop foreign key fk_commoditylinkorder_commodity_id;
alter table commoditylinkorder drop foreign key fk_commoditylinkorder_order_id;

-- 插入数据
insert into commodity values (1,'衣物',100.0),(2,'食物',150.0),(3,'出行',200.0);
insert into  orders values (1,'2023-11-30',1000),(2,'2023-11-30',2000),(3,'2023-12-1',3000);
insert into commoditylinkorder values (1,1,1,3,300),(2,2,3,4,800),(3,3,2,5,750);
insert into commoditylinkorder values (4,2,1,5,500);

select commoditylinkorder_id,orders.order_id,commodity.commodity_id,commodity_name,commodity_num,order_time,order_price,totalPrice from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id;

select commoditylinkorder_id,orders.order_id,commodity.commodity_id,commodity_name,commodity_num,order_time,order_price,totalPrice from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id order by totalPrice asc;

select commoditylinkorder_id,orders.order_id,commodity.commodity_id,commodity_name,commodity_num,order_time,order_price,totalPrice from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id order by order_time asc;

update commoditylinkorder set commodity_id = 3,commodity_num = 4,order_price = 344 where commoditylinkorder_id = 4;




-- 删除表
drop table commodity;
drop table  orders;
drop table commoditylinkorder;