package com.WestTwo.work2;

public class ChineseRuralDog extends Animal {
    boolean isVaccineInjected;
    double price = 100;

    public ChineseRuralDog() {
    }

    public ChineseRuralDog(String name, int age, boolean sex, boolean isVaccineInjected) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ZhongHuaTianYuanQuan{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", age=" + age +
                ", sex=" + sex +
                ", isVaccineInjected=" + isVaccineInjected +
                '}';
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}