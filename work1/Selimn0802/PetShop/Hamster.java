package PetShop;
//鼠鼠类
public class Hamster extends Animal{
    public final String animalName = "鼠鼠";
    public Hamster(String name, String sex, int age) {
        super("鼠鼠", name, sex, age, 25, 50);
    }

    @Override
    public String toString() {
        return "鼠鼠 [姓名：" + name + ", 性别：" + sex + ", 年龄：" + age + ", 价格：" + sellPrice + "]";
    }
}
