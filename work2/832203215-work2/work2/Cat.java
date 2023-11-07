package work2;

public class Cat extends Animal{

    public Cat() {
        super.setPrice(300);
    }

    public Cat(String name, int age, String gender, double price) {
        super(name, age, gender, price);
        super.setPrice(200);
    }

    @Override
    public String toString() {
        return "猫猫{" + "价格：" + getPrice() + ", 名字：" + getName() + ", 年龄：" + getAge() + ", 性别：" + getGender() + '}';
    }
}
