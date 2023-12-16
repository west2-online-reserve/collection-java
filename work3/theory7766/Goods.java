package com.west2.work3;

public class Goods {
    // 商品编号
    private int no;
    // 商品名字
    private String name;
    // 商品价格
    private double price;

    public Goods() {}

    public Goods(int sno, String name, double price) {
        this.no = no;
        this.name = name;
        this.price = price;
    }

    /**
     * 获取商品编号
     * @return sno
     */
    public int getSno() {
        return no;
    }

    /**
     * 设置商品编号
     * @param sno
     */
    public void setSno(int sno) {
        this.no = sno;
    }

    /**
     * 获取商品名字
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名字
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品价格
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "商品编号 : " + no + ", 商品名称 : " + name + ", 商品价格 : " + price;
    }
}
