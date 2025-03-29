package PetShop;
//猫猫类
public class Cat extends Animal {
    public final String animalName = "猫猫";
    public Cat(String name, String sex, int age) {
        super("猫猫",name, sex, age,100,200);
    }

    @Override
    public String toString() {
        return "猫猫 [姓名：" + name + ", 性别：" + sex + ", 年龄：" + age + ", 价格：" + sellPrice + "]";
    }
}
