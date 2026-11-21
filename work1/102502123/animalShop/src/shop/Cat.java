package shop;

public class Cat extends Animal {
    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200.0);
    }

    @Override
    public String toString() {
        return "猫！信息\n名字: " + getName() +
                "\n年龄: " + getAge() + "岁" +
                ",\n性别: " + getGender() +
                ",\n价格: " + getPrice() + "元";
    }
}