package bean;

public abstract class Animal {
    private String name;//名字
    private int age;//年龄
    private String  gender;//性别
    private double price;//价格

    public Animal(String name, int age, String gender, double price) {//一个全参的构造方法
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public Animal() {
    }

    @Override
    public abstract String toString();//一个抽象的toString方法

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
