package work2;

public class Cat extends Animal{

    public Cat() {
        super.price =300;
    }

    public Cat(String name, int age, String gender, double price) {
        super(name, age, gender, price);
        super.price = 200;
    }

    @Override
    public String toString() {
        return "猫猫{" + "价格：" + price + ", 名字：" + name + ", 年龄：" + age + ", 性别：" + gender + '}';
    }
}
