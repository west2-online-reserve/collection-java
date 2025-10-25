package com.petshop.entity;

class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, char sex, boolean isVaccineInjected) {
        super(name, age, sex, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return this.isVaccineInjected;
    }

    public void setVaccineInjected(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "中华田园犬" + " 动物名：" + name + " 年龄：" + age + " 性别：" + sex + " 价格：" + price + " 是否打狂犬疫苗：" + isVaccineInjected;
    }
}