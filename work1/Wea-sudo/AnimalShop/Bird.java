package example;

public class Bird extends Animal{
    public Bird(String name, int age, Gender sex) {
        super(name, age, sex, 150, "鸟");
    }

    @Override
    public String toString() {
        return "{姓名:" + name +
                ", 类型:" + className +
                ", 性别:" + sex +
                ", 年龄:" + age
                + "}";
    }


}
