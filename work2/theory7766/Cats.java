package com.west2.work2;

public class Cats extends Animal {

    /**
     * 猫猫类构造函数
     *
     * @param name   名字
     * @param old    年龄
     * @param gender 性别
     */
    public Cats(String name, int old, char gender) {
        super(name, old, gender, 200.0);
    }

    @Override
    public String toString() {
        return "品种:躺平小猫,名字:" + this.name + ",年龄:" + this.old
                + ",性别:" + this.gender + ",价格:" + this.price;
    }
}
