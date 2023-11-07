package com.Eeeeye.base.work2;

public class Dog extends Animal {

   private boolean VaccineInjected = true;//默认有打狂犬疫苗
   private double price = 100;
   private double profit = 100;

    public Dog() {
    }

    public Dog(boolean VaccineInjected) {
        this.VaccineInjected = VaccineInjected;

    }



    /**
     * 获取
     * @return VaccineInjected
     */
    public boolean getisVaccineInjected() {
        return VaccineInjected;
    }

    /**
     * 设置
     * @param VaccineInjected
     */
    public void setVaccineInjected(boolean VaccineInjected) {
        this.VaccineInjected = VaccineInjected;
    }


    public String toString() {
        return "Dog{VaccineInjected = " + VaccineInjected + ", price = " + price + ", name = " + name + ", age = " + age + ", gender = " + gender;
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