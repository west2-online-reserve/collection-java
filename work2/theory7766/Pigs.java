package com.west2.work2;

public class Pigs extends Animal {

    public Pigs(String name, int old, char gender) {
        super(name, old, gender, 150.0);
    }

    @Override
    public String toString() {
        return "品种:呆萌小猪,名字:" + this.name + ",年龄:" + this.old + ",性别:" + this.gender + ",价格:" + this.price;
    }
}
