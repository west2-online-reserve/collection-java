package com.Eeeeye.base.work2;

public class Cat extends Animal{
    private double price = 200;
    private double profit = 200;

    public Cat() {
    }

    public Cat(double price, double profit) {
        this.price = price;
        this.profit = profit;
    }

    @Override
    public String toString(){
        return (" name= "+name+" age= "+age+" gender= "+gender+" price= "+price);
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
