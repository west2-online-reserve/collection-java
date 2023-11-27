package com.huayu.work3;

import com.huayu.work3.pojo.TbGoodsDO;
import com.huayu.work3.pojo.TbOrderDO;
import com.huayu.work3.pojo.TbOrderGoodsDO;
import com.huayu.work3.utils.JdbcUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *工具类的测试类
 */
public class WorkTest {
    public static void main(String[] args) {
        /*TbOrderDO order = new TbOrderDO();
        Date date = Date.valueOf("2022-01-01");
        Time time = Time.valueOf("12:31:00");
        Timestamp timestamp = new Timestamp(date.getTime() + time.getTime());
        order.setId(10011);
        order.setTime(timestamp);
        TbOrderGoodsDO orderGoods1 = new TbOrderGoodsDO();
        orderGoods1.setGoodsId(3);
        orderGoods1.setOrderId(10011);
        orderGoods1.setQuantity(3);
        TbOrderGoodsDO orderGoods2 = new TbOrderGoodsDO();
        orderGoods2.setGoodsId(4);
        orderGoods2.setOrderId(10011);
        orderGoods2.setQuantity(6);
        JdbcUtils.addOrder(order, orderGoods1, orderGoods2);*/

        /*TbOrderDO order = new TbOrderDO();
        Date date = Date.valueOf("2022-01-01");
        Time time = Time.valueOf("12:30:00");
        Timestamp timestamp = new Timestamp(date.getTime() + time.getTime());
        order.setId(1001);
        order.setTime(timestamp);
        TbOrderGoodsDO orderGoods1 = new TbOrderGoodsDO();
        orderGoods1.setGoodsId(3);
        orderGoods1.setOrderId(1002);
        orderGoods1.setQuantity(3);
        TbOrderGoodsDO orderGoods2 = new TbOrderGoodsDO();
        orderGoods2.setGoodsId(2);
        orderGoods2.setOrderId(1003);
        orderGoods2.setQuantity(6);
        JdbcUtils.addOrder(order, orderGoods1, orderGoods2);*/

        /*JdbcUtils.selectAllOrder();*/

        /*TbOrderDO order = new TbOrderDO();
        order.setId(4);
        JdbcUtils.selectOrderById(order);*/

        /*TbOrderDO order = new TbOrderDO();
        order.setId(5);
        JdbcUtils.selectGoodsByOrderId(order);*/

        /*TbOrderDO order = new TbOrderDO();
        order.setId(5);
        JdbcUtils.deleteOrderById(order);*/

        /*TbOrderGoodsDO orderGoods1 = new TbOrderGoodsDO();
        orderGoods1.setGoodsId(3);
        orderGoods1.setOrderId(5);
        JdbcUtils.deleteGoodsInOrderByOrderAndGoodsId(orderGoods1);*/

        /*TbOrderGoodsDO orderGoods1 = new TbOrderGoodsDO();
        orderGoods1.setGoodsId(7);
        orderGoods1.setOrderId(5);
        orderGoods1.setQuantity(20);
        JdbcUtils.addGoodsInOrderByOrderAndGoodsId(orderGoods1);*/

        /*TbOrderGoodsDO orderGoods1 = new TbOrderGoodsDO();
        orderGoods1.setGoodsId(7);
        orderGoods1.setOrderId(5);
        orderGoods1.setQuantity(50);
        JdbcUtils.updateQuantityByOrderAndGoodsId(orderGoods1);*/

        /*JdbcUtils.selectAllGoods();*/

        /*TbGoodsDO goods = new TbGoodsDO();
        goods.setName("电");
        JdbcUtils.selectGoodsByName(goods);*/

        /*TbGoodsDO goods = new TbGoodsDO();
        goods.setName("");
        JdbcUtils.selectGoodsByName(goods);*/

        /*TbGoodsDO goods = new TbGoodsDO();
        JdbcUtils.selectGoodsByName(goods);*/

        /*TbGoodsDO goods1 = new TbGoodsDO();
        BigDecimal price1 = new BigDecimal("229.00");
        goods1.setName("耳机");
        goods1.setPrice(price1);
        TbGoodsDO goods2 = new TbGoodsDO();
        BigDecimal price2 = new BigDecimal("1129.00");
        goods2.setName("米fan投影仪");
        goods2.setPrice(price2);
        JdbcUtils.addGoods(goods1, goods2);*/

        /*TbGoodsDO goods1 = new TbGoodsDO();
        goods1.setId(15);
        TbGoodsDO goods2 = new TbGoodsDO();
        goods2.setId(16);
        JdbcUtils.deleteGoodsById(goods1, goods2);*/

        /*JdbcUtils.selectAllOrderPriceDesc();*/

        /*JdbcUtils.selectAllOrderPriceAsc();*/

        /*JdbcUtils.selectAllOrderTimeDesc();*/

        /*JdbcUtils.selectAllOrderTimeAsc();*/

        /*JdbcUtils.selectAllGoodsPriceAsc();*/

        /*JdbcUtils.selectAllGoodsPriceDesc();*/

        /*TbGoodsDO goods = new TbGoodsDO();
        goods.setName("电");
        JdbcUtils.selectGoodsByNamePriceAsc(goods);*/

        /*TbGoodsDO goods = new TbGoodsDO();
        goods.setName("电");
        JdbcUtils.selectGoodsByNamePriceDesc(goods);*/
    }

}
