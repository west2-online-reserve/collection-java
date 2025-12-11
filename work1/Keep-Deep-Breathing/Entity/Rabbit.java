package Entity;

public class Rabbit extends Animal {
    public Rabbit(String name, int age, int sex){
        super(name, age, sex, 150);
    }
    @Override
    public String toString() {
        return "宠物姓名：" +name +
                "\n宠物年龄：" + age +
                "\n宠物性别：" + sex +
                "\n宠物价格：" + price;

    }
}
