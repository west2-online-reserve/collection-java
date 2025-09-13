package Test;

/**
 * 为了让Test文件夹简洁一些，我把相同的类放到一个文件夹
 * 实例变量访问修饰符已经改成private，并且添加get，set方法
 * 规定了动物的购入价格，售出价格，利润
 */

// 抽象父类：Animal
public abstract class Animal {

    protected String name;
    protected int age;
    protected String gender;
    protected double price;
    protected double cost;

    public Animal() {
    }

    public Animal(String name, int age, String gender, double price, double cost) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-5d %-6s %-10.2f %-10.2f", name, age, gender, price, cost);
    }
}

// Cat类：购入价格150，卖出价格350，利润200
class Cat extends Animal {

    public Cat(String name, int age, String gender) {
        super(name, age, gender, 350, 150);
    }

    @Override
    public String toString() {
        return String.format("%-5s %-10s %-5d %-6s %-10.2f %-10.2f", "Cat", name, age, gender, price, cost);
    }
}

// Dog类：购入价格150，卖出价格250，利润100
class Dog extends Animal {

    private boolean isVaccineInjected;

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    public Dog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 250, 150);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-10s %-5d %-6s %-10.2f %-10.2f %-5s",
                "Dog", name, age, gender, price, cost, isVaccineInjected);
    }
}