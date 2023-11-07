package work2;

public class Rabbit extends Animal{

    public Rabbit() {
        super.setPrice(600);
    }

    public Rabbit(String name, int age, String gender, double price) {
        super(name, age, gender, price);
        super.setPrice(600);
    }

    @Override
    public String toString() {
        return "兔兔{" + "价格：" + getPrice() + ", 名字：" + getName() +  ", 年龄：" + getAge() + ", 性别：" + getGender() + '}';
    }
}
