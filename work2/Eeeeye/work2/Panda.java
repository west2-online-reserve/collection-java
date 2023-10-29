package com.Eeeeye.base.work2;

public class Panda extends Animal{
    private double price = 1000;
    private double profit = 1000;

    public Panda() {
    }

    @Override
    public String toString() {
        return ("name=" + name + " age=" + age + " gender=" + gender + " price=" + price);
    }


    /**
     * 获取
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 获取
     * @return profit
     */
    public double getProfit() {
        return profit;
    }

    /**
     * 设置
     * @param profit
     */
    public void setProfit(double profit) {
        this.profit = profit;
    }
}

