package shop;

// Rabbit.java
public class Rabbit extends Animal {
    public Rabbit(String name, int age, String gender) {
        super(name, age, gender, 150.0);
    }

    @Override
    public String toString() {
        return "兔子{" +
                "名字='" + name + '\'' +
                ", 年龄=" + age +
                ", 性别='" + gender + '\'' +
                ",基础售价=" + price*2 +
                '}';
    }
}
