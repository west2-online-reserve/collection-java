/*
MySQL Backup
Database: order
Backup Time: 2023-12-17 14:51:13
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `order`.`good`;
DROP TABLE IF EXISTS `order`.`ordergood`;
DROP TABLE IF EXISTS `order`.`totalorder`;
CREATE TABLE `good` (
  `goodid` int NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `goodname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `goodprice` double(10,2) NOT NULL COMMENT '商品价格',
  PRIMARY KEY (`goodid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
CREATE TABLE `ordergood` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '表的编号，无实义',
  `orderid` int NOT NULL COMMENT '订单编号',
  `goodid` int NOT NULL DEFAULT '0' COMMENT '订单内的其中之一的货物',
  `goodnum` int NOT NULL DEFAULT '0' COMMENT '该货物的个数',
  `goodprice` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '根据商品价格和商品个数算出来的价格（非订单总价）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
CREATE TABLE `totalorder` (
  `orderid` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `ordertime` datetime NOT NULL COMMENT '订单时间',
  `totalprice` double(10,2) unsigned zerofill DEFAULT '0000000.00' COMMENT '总价格',
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
BEGIN;
LOCK TABLES `order`.`good` WRITE;
DELETE FROM `order`.`good`;
INSERT INTO `order`.`good` (`goodid`,`goodname`,`goodprice`) VALUES (5, '桃子', 15.00),(6, 'fufu', 100.00),(7, '菠萝', 20.00);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `order`.`ordergood` WRITE;
DELETE FROM `order`.`ordergood`;
INSERT INTO `order`.`ordergood` (`id`,`orderid`,`goodid`,`goodnum`,`goodprice`) VALUES (10, 15, 6, 5, 500.00);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `order`.`totalorder` WRITE;
DELETE FROM `order`.`totalorder`;
INSERT INTO `order`.`totalorder` (`orderid`,`ordertime`,`totalprice`) VALUES (1, '2023-11-29 00:00:00', 0000000.00),(7, '2023-11-29 00:00:00', 0000000.00),(8, '2023-12-03 00:00:00', 0000000.00),(10, '2023-12-05 00:00:00', 0000000.00),(12, '2023-12-03 00:00:00', 0000000.00),(13, '2023-12-03 00:00:00', 0000000.00),(14, '2023-12-03 00:00:00', 0000000.00),(15, '2023-12-16 16:06:26', 0000500.00);
UNLOCK TABLES;
COMMIT;
