create table order_manage_system.goods
(
    goodID   int unsigned auto_increment comment '商品编号'
        primary key,
    goodName varchar(255) not null comment '商品名称',
    price    double       not null comment '商品单价',
    restNum  int unsigned not null comment '剩余数量'
)
    collate = utf8mb4_general_ci;

create table order_manage_system.`order`
(
    orderID   int unsigned not null comment '订单编号'
        primary key,
    orderTime varchar(255) not null comment '下单时间',
    goodsName varchar(255) not null comment '商品名称',
    num       int unsigned not null comment '购买数量',
    manager   varchar(255) not null comment '负责人'
)
    collate = utf8mb4_general_ci;

