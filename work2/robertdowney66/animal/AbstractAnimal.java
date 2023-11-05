package com.west2.check02;

/***
 * 阿里的插件让我将抽象类名字前加上Abstract，所以该抽象类叫AbstractAnimal
 * @author yuyu
 */
public abstract class AbstractAnimal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AbstractAnimal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    /***
     *用于子类的重写
     * @return null
     */
    @Override
    public abstract String toString();

    public AbstractAnimal() {
    }
}
