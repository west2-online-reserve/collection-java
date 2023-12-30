# Java 第三轮考核

## 项目地址：https://github.com/wyxbnbl555/work3

## 技术栈

运用了运用jdbc搭建框架连接MySQL和idea，运用MySQL数据库语言

## 项目介绍

主要进行了对商品和订单增删改查的功能实现

## 项目目录

实体类

商品类
 商品id
 商品name
 商品price

订单类
订单id
订单time
订单中的商品id

订单关联商品类
关联id
订单id
订单time
商品id
商品name
商品price

业务操作
findAllProducts()查询所有商品
findProductsById()查询指定商品
findAllOrder()查询所有订单
findOrderById()查询指定订单
productInsert()添加商品
OrderInsert()添加订单
productDelete()删除商品
orderDelete()删除订单
productUpdate()修改商品
orderUpdate()修改订单
sortOrderByTime()按时间排列订单
sortOrderByPrice()按价格排列订单

## 项目亮点
 
暂时无亮点，全是基础

## 待改进点

每个订单只能对应一件商品，功能不够完善，会在接下来的学习中慢慢改进







    
