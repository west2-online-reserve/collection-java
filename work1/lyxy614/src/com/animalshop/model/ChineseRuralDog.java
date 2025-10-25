package com.animalshop.model;

public class ChineseRuralDog extends Animal {
    protected boolean isVaccineInjected;
    public ChineseRuralDog(String name, int age, Gender sex, boolean isVaccineInjected){
        super(name, age, sex, 100);
        this.isVaccineInjected = isVaccineInjected;
    }
    @Override
    public String toString(){
        return String.format("中华田园犬 [名字: %s, 年龄: %d, 性别: %s, 价格: %.2f, 是否接种疫苗: %s]", name, age, sex, price, isVaccineInjected ? "是" : "否");
    }
}
