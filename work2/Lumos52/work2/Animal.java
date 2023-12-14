package com.west2.work2;

/**
 * @author Chen Shining
 * @Date 2023/11/4
 */
public abstract class Animal {
    /**
     * 动物名
     */
    protected String name;
    /**
     * 年龄
     */
    protected int age;
    /**
     * 价格
     */
    protected double price;
    /**
     * 性别
     */
    protected String sex;

    public Animal() {
    }

    public Animal(String name, int age, double price, String sex) {
        this.name = name;
        this.age = age;
        this.price = price;
        this.sex = sex;
    }

    public String toString() {
        return "姓名为：" + name + ",年龄：" + age + ",性别为 = " + sex + ", 价格是" + price + "}";
    }

    public String toString1() {
        return "姓名为：" + name + ",年龄：" + age + ",性别为 = " + sex + ", 价格是" + price + "}";
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
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置
     *
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

}
