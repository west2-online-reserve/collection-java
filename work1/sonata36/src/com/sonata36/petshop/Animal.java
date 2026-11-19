package com.sonata36.petshop;

/** 宠物店中的动物。 */
public abstract class Animal {
    private final String name;
    private final int age;
    private final String sex;
    private final double price;

    public Animal(String name, int age, String sex, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("动物名字不能为空");
        }
        if (age < 0) {
            throw new IllegalArgumentException("动物年龄不能为负数");
        }
        if (sex == null || sex.isBlank()) {
            throw new IllegalArgumentException("动物性别不能为空");
        }
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("动物价格必须是非负的有限数字");
        }
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    /** 动物售出时的固定售价。 */
    public double getPrice() {
        return price;
    }

    @Override
    public abstract String toString();
}
