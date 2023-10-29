package com.Eeeeye.base.work2;

import java.util.Objects;

public abstract class Animal {

    protected String name;
    protected int age;
    protected String gender;
    protected double price;
    protected double profit;

    public Animal() {
    }

    ;

    public Animal(String name, int age, String gender, double price, double profit) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.profit = profit;
    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置
     *
     * @param price
     */

    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * 获取
     *
     * @return profit
     */
    public double getProfit() {
        return profit;
    }

    /**
     * 设置
     *
     * @param profit
     */
    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return ("name" + name + "age" + age + "gender" + gender + "price" + price);//输出动物信息
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Double.compare(price, animal.price) == 0 && Double.compare(profit, animal.profit) == 0 && Objects.equals(name, animal.name) && Objects.equals(gender, animal.gender);
    }

}