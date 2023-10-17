package com.west2.work2;

public abstract class Animal {
    /**
     * 动物名
     */
    protected String name;
    /**
     * 动物年龄
     */
    protected int old;
    /**
     * 动物性别
     */
    protected char gender;
    /**
     * 动物价格
     */
    protected double price;

    /**
     * 类全参构造函数
     *
     * @param name   动物名
     * @param old    动物年龄
     * @param gender 动物性别
     * @param price  动物价格
     */
    public Animal(String name, int old, char gender, double price) {
        this.name = name;
        this.old = old;
        this.gender = gender;
        this.price = price;
    }

    /**
     * 获取动物名
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置动物名
     *
     * @param name 动物名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取动物年龄
     *
     * @return old
     */
    public int getOld() {
        return old;
    }

    /**
     * 设置动物年龄
     *
     * @param old 动物年龄
     */
    public void setOld(int old) {
        this.old = old;
    }

    /**
     * 获取动物性别
     *
     * @return gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * 设置动物性别
     *
     * @param gender 动物性别
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * 获取动物价格
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置动物价格
     *
     * @param price 动物价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    // 动物抽象函数
    public abstract String toString();
}


