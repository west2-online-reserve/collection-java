package com.west2online.petshop;

public class Bird extends Animal {
    private boolean isCanSpeak;

    public Bird() {

    }

    public Bird(String name, int age, String gender, String color, boolean isCanSpeak) {
        super(name, age, gender, color, 250.0);
        this.isCanSpeak = isCanSpeak;
    }

    public boolean isCanSpeak() {
        return isCanSpeak;
    }

    public void setCanSpeak(boolean canSpeak) {
        this.isCanSpeak = canSpeak;
    }

    @Override
    public String toString() {
        return (
                "鸟的姓名是:" + getName() +
                        " 年龄为:" + getAge() +
                        " 性别为" + getGender() +
                        " 价格为" + getPrice() +
                        " 颜色为" + getColor() +
                        " 是否能飞:" + isCanSpeak()
        );
    }
}
