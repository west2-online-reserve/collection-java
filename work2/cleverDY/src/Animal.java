public abstract class Animal {
    // 成员变量
    private String name;
    private int age;
    private String gender;
    private double price;

    // 全参构造方法
    public Animal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getPrice() {
        return price;
    }

    // 抽象方法
    public abstract String toString();
}
