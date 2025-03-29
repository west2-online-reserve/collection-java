package learn_java1;

public abstract class Animal implements Comparable<Animal> {
    protected String name;//名字
    protected int age;//年龄
    protected char gender;//性别
    protected double price;//价格
    protected double purchasePrice;


    public Animal(String name, int age, char gender, double price, double purchasePrice) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.purchasePrice = purchasePrice;
    }

    @Override//实现排序，输出时方便清楚
    public int compareTo(Animal o) {
        return Double.compare(this.price, o.price);
    }


    public String getName() {
        return name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public abstract String toString();//重写toString方法
}
