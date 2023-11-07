package com.huayu.work02;

/**
 * Cat类继承动物类，表示猫的信息
 *
 * 该类包含猫的信息和重写的toString方法
 * @author yusiheng
 * @date 2023/10/04
 */
public class Cat extends Animal {
    public Cat(String animalName, int animalAge, String animalSex, double animalPrice, double animalImportPrice) {
        super(animalName, animalAge, animalSex, animalPrice, animalImportPrice);
    }

    @Override
    public String toString() {
        return "[name: " + animalName +
                " ;age: " + animalAge +
                " ;sex: " + animalSex +
                " ;price: " + animalPrice +
                " ;importprice " + animalImportPrice + "]";
    }
}
