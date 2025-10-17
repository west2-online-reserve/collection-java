package example;

public class Cat extends Animal {
    public Cat(String name, int age, Gender sex) {
        super(name, age, sex, 200, "猫");
    }

    @Override
    public String toString() {

        return "姓名:" + name +
                ", 类型:" + className +
                ", 性别:" + sex +
                ", 年龄:" + age;
    }

}
