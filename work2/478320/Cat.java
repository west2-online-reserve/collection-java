package com.huayu.work02;

public class Cat extends Animal {
//这是猫猫类，重写了输出方法很简单的一个猫猫类，我也给猫猫设置了进口价，用于计算利润

    public Cat(String animalName, int animalAge, String animalSex, double animalPrice, double animalImportPrice) {
        super(animalName, animalAge, animalSex, animalPrice, animalImportPrice);

        this.animalAge = animalAge;
        this.animalSex = animalSex;
        this.animalPrice = animalPrice;
        this.animalImportPrice = animalImportPrice;
        this.animalName = animalName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "animalName='" + animalName + '\'' +
                ", animalAge=" + animalAge +
                ", animalSex='" + animalSex + '\'' +
                ", animalPrice=" + animalPrice +
                ", animalImportPrice=" + animalImportPrice +
                '}';
    }
}
