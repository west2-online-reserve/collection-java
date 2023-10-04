public abstract class Animal {
    protected String name;
    protected int age;
    protected boolean sex;
    protected double price;
    protected Animal(String name, int age, boolean sex, double price){
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }
    //抽象方法
    public abstract String toString();
}
