package example;

public class Cat extends Animal {
    public Cat(String name, int age, Gender sex) {
        super(name, age, sex, 200.0);
    }

    @Override
    public String toString() {

        return "{姓名:" + name +
                ", 类型:" + Cat.class.getSimpleName() +
                ", 性别:" + sex +
                ", 年龄:" + age
                + "}";
    }

}
