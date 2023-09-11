package shaoxiawjc;

public abstract class Animal {
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    public Animal(String n, int a, String s, double p) {
        this.name = n;
        this.age = a;
        this.sex = s;
        this.price = p;
    }


    public abstract String toString();

}
