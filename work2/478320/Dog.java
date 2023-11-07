package com.huayu.work02;

/**
 * Dog类继承Animal类表示狗的基本信息
 * <p>
 * 该类包含狗的基本信息和重写的toString方法
 *
 * @author yusiheng
 * @date 2023/10/04
 */
public class Dog extends Animal {
    private boolean isVaccineInjected;

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;

    }

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


