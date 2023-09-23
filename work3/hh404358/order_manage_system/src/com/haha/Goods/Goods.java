package com.haha.Goods;

public class Goods {
    int goodsID;
    String goodsName;

    double price;
    int restNum;

    public Goods() {
    }

    public Goods(int goodsID, String goodsName, double price, int restNum) {
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.price = price;
        this.restNum = restNum;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(int goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRestNum() {
        return restNum;
    }

    public void setRestNum(int restNum) {
        this.restNum = restNum;
    }
}
