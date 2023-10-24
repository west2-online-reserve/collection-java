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





