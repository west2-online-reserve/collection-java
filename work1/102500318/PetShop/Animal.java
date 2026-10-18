package oop.PetShop;
public abstract class Animal{
    protected String name;    // 动物名
    protected int age;        // 年龄
    protected String gender;  // 性别
    static int price;
    public Animal(){}
    // 全参构造方法
    public Animal(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
    public abstract int getPrice();

    // 抽象的 toString() 方法
    @Override
    public abstract String toString();
}