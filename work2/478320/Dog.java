package com.huayu.work02;

/**
 * Dog类继承Animal类表示狗的基本信息
 *
 * 该类包含狗的基本信息和重写的toString方法
 * @author yusiheng
 * @date 2023/10/04
 */
public class Dog extends Animal {
    protected boolean isVaccineInjected;

    public Dog(String animalName, int animalAge, String animalSex, double animalPrice, double animalImportPrice, boolean isVaccineInjected) {
        super(animalName, animalAge, animalSex, animalPrice, animalImportPrice);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "[name:" + animalName +
                " ;age: " + animalAge +
                " ;sex: " + animalSex +
                " ;price: " + animalPrice +
                " ;importprice " + animalImportPrice +
                " ;isVaccineInjected " + isVaccineInjected + "]";
    }
}


