package com.west2online.petshop;

public class ChineseDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseDog() {

    }

    public ChineseDog(String name, int age, String gender, String color, boolean isVaccineInjected) {
        super(name, age, gender, color, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean IsVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean VaccineInjected) {
        this.isVaccineInjected = VaccineInjected;
    }

    @Override
    public String toString() {
        return (
                "中华田园犬的姓名是:" + getName() +
                        " 年龄为:" + getAge() +
                        " 性别为" + getGender() +
                        " 价格为" + getPrice() +
                        " 颜色为" + getColor() +
                        " 注射疫苗:" + IsVaccineInjected()
        );
    }
}
