package XWBN2;

public abstract class Animal {
    protected String animalName;
    protected int animalAge;
    protected String animalSex;
    protected double animalSellPrice;
    protected double animalPurPrice;

    public Animal(String name, int age, String sex, double sellPrice, double purPrice) {
        this.animalName = name;
        this.animalAge = age;
        this.animalSex = sex;
        this.animalSellPrice = sellPrice;
        this.animalPurPrice = purPrice;
    }

    //获取动物的名字
    public String getAnimalName() {
        return this.animalName;
    }

    //获取动物的年龄
    public int getAnimalAge() {
        return this.animalAge;
    }

    //获取动物的性别
    public String getAnimalSex() {
        return this.animalSex;
    }

    //获取动物的售价
    public double getAnimalSellPrice() {
        return this.animalSellPrice;
    }

    public void setAnimalSellPrice(double price) {
        this.animalSellPrice = price;
    }

    //获取动物的进价
    public double getAnimalPurPrice() {
        return this.animalPurPrice;
    }

    public void setAnimalPurPrice(double price) {
        this.animalPurPrice = price;
    }

    public abstract String toString();
}
