package westwork2;

// 抽象动物类
abstract class Animal {
    String name;
    int age;
    char gender;
    double price;

    Animal(String name, int age, char gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public abstract String toString();
}
