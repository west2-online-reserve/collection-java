package com.github.lpx.model.pet;

public class Dog extends Animal {
    private boolean isVaccineInjected;

    public Dog(String name, int age, String sex, Double price, String color, boolean isVaccineInjected) {
        super(name, age, sex, price, color);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "狗狗" + getName() + " 颜色:" + getColor() + " 年龄:" + getAge() + " 性别:" + getSex() + " 价格:" + getPrice() + " 是否打过疫苗:" + isVaccineInjected;
    }

    @Override
    public void petShow() {
        System.out.println("汪汪汪，把哥带回家，哥当你的专属舔狗");
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }
}
