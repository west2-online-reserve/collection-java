package com.huayu.work02;

/**
 * Animal类表示系统中的动物信息
 * <p>
 * 该类包含动物的基本信息
 *
 * @aurhor yusiheng
 * @date 2023/10/04
 */
public abstract class Animal {
    protected String animalName;
    protected int animalAge;
    protected String animalSex;
    /**
     * 动物的出售价格
     */
    protected double animalPrice;
    /**
     * 动物的进口价格
     */
    protected double animalImportPrice;

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
        if (animalAge >= 0) {
            this.animalAge = animalAge;
        } else System.out.println("动物的年龄有误");
    }

    public String getAnimalSex() {
        return animalSex;
    }

    public void setAnimalSex(String animalSex) {
        if (animalSex.equals("male") || animalSex.equals("female")) {
            this.animalSex = animalSex;
        } else {
            System.out.println("动物的性别有误");
        }
    }

    public double getAnimalPrice() {
        return animalPrice;
    }

    public void setAnimalPrice(double animalPrice) {
        if (animalPrice >= 0) {
            this.animalPrice = animalPrice;
        } else {
            System.out.println("动物价格有误");
        }
    }

    public double getAnimalImportPrice() {
        return animalImportPrice;
    }

    public void setAnimalImportPrice(double animalImportPrice) {
        if (animalImportPrice >= 0) {
            this.animalImportPrice = animalImportPrice;
        } else {
            System.out.println("动物进价有误");
        }
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





