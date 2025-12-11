package Entity;

public class Cat extends Animal {
    public Cat(String name, int age, int sex){
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return "宠物姓名：" +name +
                "\n宠物年龄：" + age +
                "\n宠物性别：" + sex +
                "\n宠物价格：" + price;
    }
}
