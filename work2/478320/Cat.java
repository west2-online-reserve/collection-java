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
    public String getAnimalName() {
        return super.getAnimalName();
    }

    @Override
    public void setAnimalName(String animalName) {
        super.setAnimalName(animalName);
    }

    @Override
    public int getAnimalAge() {
        return super.getAnimalAge();
    }

    @Override
    public void setAnimalAge(int animalAge) {
        super.setAnimalAge(animalAge);
    }

    @Override
    public String getAnimalSex() {
        return super.getAnimalSex();
    }

    @Override
    public void setAnimalSex(String animalSex) {
        super.setAnimalSex(animalSex);
    }

    @Override
    public double getAnimalPrice() {
        return super.getAnimalPrice();
    }

    @Override
    public void setAnimalPrice(double animalPrice) {
        super.setAnimalPrice(animalPrice);
    }

    @Override
    public double getAnimalImportPrice() {
        return super.getAnimalImportPrice();
    }

    @Override
    public void setAnimalImportPrice(double animalImportPrice) {
        super.setAnimalImportPrice(animalImportPrice);
    }

    @Override
    public String toString() {
        return "[name: " + getAnimalName() +
                " ;age: " + getAnimalAge() +
                " ;sex: " + getAnimalSex() +
                " ;price: " + getAnimalPrice() +
                " ;importprice " + getAnimalImportPrice() + "]";
    }
}
