package utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class helperClass {
    // 商品实体类
    class Product {
        private int id;
        private String name;
        // 金额字段改为 BigDecimal
        private BigDecimal price;

        // 构造方法适配 BigDecimal
        public Product(int id, String name, BigDecimal price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        // 重载构造方法（兼容传入 double 的场景，自动转为 BigDecimal 并保留两位小数）
        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        // Getters and Setters（适配 BigDecimal）
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }
        // 重载 setter 兼容 double 输入
        public void setPrice(double price) {
            this.price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        @Override
        public String toString() {
            // 输出时保留两位小数
            return "Product [id=" + id + ", name=" + name + ", price=" + price.setScale(2, BigDecimal.ROUND_HALF_UP) + "]";
        }
    }

    // 订单实体类
    public static class Order {
        private int id;
        private LocalDateTime orderTime;
        // 订单总价改为 BigDecimal
        private BigDecimal orderPrice;
        private List<orderInfo> itemList;

        // 构造方法适配 BigDecimal
        public Order(int id, LocalDateTime orderTime, BigDecimal orderPrice) {
            this.id = id;
            this.orderTime = orderTime;
            this.orderPrice = orderPrice;
            this.itemList = new ArrayList<>();
        }

        // 重载构造方法（兼容 double 输入）
        public Order(int id, LocalDateTime orderTime, double orderPrice) {
            this.id = id;
            this.orderTime = orderTime;
            this.orderPrice = BigDecimal.valueOf(orderPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
            this.itemList = new ArrayList<>();
        }

        public Order() {
            this.id = 0;
            this.orderTime = null;
            this.orderPrice = BigDecimal.ZERO; // 初始化为 0（BigDecimal 类型）
            this.itemList = new ArrayList<>();
        }

        // Getters and Setters（适配 BigDecimal）
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public LocalDateTime getOrderTime() { return orderTime; }
        public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }
        public BigDecimal getOrderPrice() { return orderPrice; }
        public void setOrderPrice(BigDecimal orderPrice) { this.orderPrice = orderPrice; }
        // 重载 setter 兼容 double 输入
        public void setOrderPrice(double orderPrice) {
            this.orderPrice = BigDecimal.valueOf(orderPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        public List<orderInfo> getItemList() { return itemList; }
        public void setItemList(List<orderInfo> itemList) { this.itemList = itemList; }
    }

    // 订单信息类
    static class orderInfo {
        private int orderId;
        private int goodsId;
        private int goodsNum;
        private String goodsName;
        // 商品小计改为 BigDecimal
        private BigDecimal totalPrice;

        // 构造方法适配 BigDecimal
        public orderInfo(int orderId, int goodsId, int goodsNum, String goodsName, BigDecimal totalPrice) {
            this.orderId = orderId;
            this.goodsId = goodsId;
            this.goodsNum = goodsNum;
            this.totalPrice = totalPrice;
            this.goodsName = goodsName;
        }

        // 重载构造方法（兼容 double 输入）
        public orderInfo(int orderId, int goodsId, int goodsNum, String goodsName, double totalPrice) {
            this.orderId = orderId;
            this.goodsId = goodsId;
            this.goodsNum = goodsNum;
            this.goodsName = goodsName;
            this.totalPrice = BigDecimal.valueOf(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        public orderInfo() {
            this.orderId = 0;
            this.goodsId = 0;
            this.goodsNum = 0;
            this.goodsName = "";
            this.totalPrice = BigDecimal.ZERO; // 初始化为 0（BigDecimal 类型）
        }

        // Getters and Setters（适配 BigDecimal）
        public int getOrderId() { return orderId; }
        public void setOrderId(int orderId) { this.orderId = orderId; }
        public int getGoodsId() { return goodsId; }
        public void setGoodsId(int goodsId) { this.goodsId = goodsId; }
        public int getGoodsNum() { return goodsNum; }
        public void setGoodsNum(int goodsNum) { this.goodsNum = goodsNum; }
        public BigDecimal getTotalPrice() { return totalPrice; }
        public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
        // 重载 setter 兼容 double 输入
        public void setTotalPrice(double totalPrice) {
            this.totalPrice = BigDecimal.valueOf(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        public String getGoodsName() { return goodsName; }
        public void setGoodsName(String goodsName) { this.goodsName = goodsName; }
    }
}