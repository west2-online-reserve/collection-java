-- 创建商品表
CREATE TABLE product (
    product_id INT(10) NOT NULL COMMENT '商品编号',
    product_name VARCHAR(260) NOT NULL COMMENT '商品名称',
    product_price DOUBLE DEFAULT 0 COMMENT '商品价格',
    PRIMARY KEY (`product_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8
INSERT INTO product (product_id, product_name, product_price) VALUES
(1, 'AAA', 12),
(2, 'BBB', 13);

-- 创建订单表
CREATE TABLE `order` (
    order_id INT(10) NOT NULL COMMENT '订单编号',
    order_time DATETIME NOT NULL COMMENT '下单时间',
    order_price DOUBLE DEFAULT 0 COMMENT '订单价格',
    PRIMARY KEY (`order_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8
INSERT INTO `order` (order_id, order_time, order_price) VALUES
(1, '2023-12-15 08:00:00', 0),
(2, '2023-12-11 07:10:30', 0),
(3, '2023-12-09 17:04:50', 0);

-- 关系表
CREATE TABLE order_product (
    order_id INT(10),
    product_id INT(10),
    PRIMARY KEY (`product_id`, `order_id`),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (order_id) REFERENCES `order`(order_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8
INSERT INTO order_product (order_id, product_id) VALUES
(1,2),
(1,1),
(2,1),
(3,2),
(4,2),
(4,1);

DELETE FROM product WHERE product_id=1;

SELECT o.order_id, o.order_time, o.order_price,  p.`product_id`, p.`product_name`, p.`product_price`
FROM `order` AS o, `product` AS p, `order_product` AS op
WHERE o.order_id = op.order_id AND p.`product_id` = op.product_id;

UPDATE `order` o
SET o.`order_price` = (
    SELECT SUM(p.`product_price`)
    FROM `product` p
    JOIN `order_product` op ON p.`product_id` = op.`product_id`
    WHERE op.`order_id` = o.`order_id`
)
WHERE o.`order_id` = 1;