package HomeWork;
import HomeWork.Animal;

public class Cat extends Animal {
    public Cat() {
    }

    public Cat(String name, int age, char gender) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "猫的名字是"+name+", "+"年龄是"+age+", "+"性别是"+gender+", "+"价格是"+price;
    }
}
