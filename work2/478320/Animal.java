package com.huayu.work02;

public abstract class Animal {//这是我的动物类
    String animalName;
    int animalAge;
    String animalSex;
    double animalPrice;
    double animalImportPrice;//我考虑到了动物需要有进价的合理性，所以在我的动物类中添加了进口价格

    public Animal(String animalName, int animalAge, String animalSex, double animalPrice, double animalImportPrice) {
    }

    @Override
    public abstract String toString();


}
