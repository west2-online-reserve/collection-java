package westwork2;

// 抽象动物类
public abstract class Animal {
        private String name;
        private int age;
        private char gender;
        private double price;

    Animal(String name, int age, char gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

 public abstract String toString();
}
