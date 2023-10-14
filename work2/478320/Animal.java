package com.huayu.work02;

/**
 * Animal类表示系统中的动物信息
 *
 * 该类包含动物的基本信息
 * @aurhor yusiheng
 * @date 2023/10/04
 */
public abstract class Animal {
    private String animalName;
    private int animalAge;
    private String animalSex;
    /**
     * 动物的出售价格
     */
    private double animalPrice;
    /**
     * 动物的进口价格
     */
    private double animalImportPrice;

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(int animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalSex() {
        return animalSex;
    }

    public void setAnimalSex(String animalSex) {
        this.animalSex = animalSex;
    }

    public double getAnimalPrice() {
        return animalPrice;
    }

    public void setAnimalPrice(double animalPrice) {
        this.animalPrice = animalPrice;
    }

    public double getAnimalImportPrice() {
        return animalImportPrice;
    }

    public void setAnimalImportPrice(double animalImportPrice) {
        this.animalImportPrice = animalImportPrice;
    }

    public Animal(String animalName, int animalAge, String animalSex, double animalPrice, double animalImportPrice) {
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalSex = animalSex;
        this.animalPrice = animalPrice;
        this.animalImportPrice = animalImportPrice;
    }

    @Override
    public abstract String toString();


}





