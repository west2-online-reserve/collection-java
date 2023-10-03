abstract class Animal {
    // 成员变量
    protected String name;
    protected int age;
    protected String gender;
    protected double price;

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
