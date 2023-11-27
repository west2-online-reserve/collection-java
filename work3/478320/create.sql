CREATE DATABASE `order_manage` CHARACTER set utf8 COLLATE utf8_general_ci;
USE order_manage;
CREATE TABLE IF NOT EXISTS `tb_goods`(
                                         `id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
                                         `NAME` VARCHAR(30) NOT NULL DEFAULT '无' COMMENT '商品名',
                                         `price` decimal(10,2) Not null COMMENT '商品价格',
                                         PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tb_order`(
                                         `id` INT(4) NOT NULL COMMENT '订单编号',
                                         `time` datetime Not null COMMENT '下单时间',
                                         `price` decimal(10,2) Not null COMMENT '订单价格',
                                         PRIMARY KEY(`id`)

)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tb_order_goods`(
                                                  `id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '交易编号',
                                                  `order_id` INT(4) NOT NULL COMMENT '订单编号',
                                                  `goods_id` INT(4) NOT NULL COMMENT '商品编号',
                                                  `quantity` int(4) Not null COMMENT '商品数量'
)ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `tb_goods`(`NAME`,`price`) VALUES('鼠标','52.99'),('键盘','99.00'),('电脑','5000.99'),('笔','10.32'),('打印机','1469.00'),('摄像头','158.30'),('文件夹','14.00');