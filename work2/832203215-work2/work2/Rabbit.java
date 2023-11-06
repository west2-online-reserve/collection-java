package work2;

public class Rabbit extends Animal{

    public Rabbit() {
        super.price = 600;
    }

    public Rabbit(String name, int age, String gender, double price) {
        super(name, age, gender, price);
        super.price = 600;
    }

    @Override
    public String toString() {
        return "兔兔{" + "价格：" + price + ", 名字：" + name +  ", 年龄：" + age + ", 性别：" + gender + '}';
    }
}
