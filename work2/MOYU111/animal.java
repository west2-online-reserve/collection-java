
public abstract class animal {
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    public animal(String n, int a, String s, double p) {
        this.name = n;
        this.age = a;
        this.sex = s;
        this.price = p;
    }


    public abstract String toString();

}
