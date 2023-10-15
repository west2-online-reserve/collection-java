public abstract class Animal {
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    public Animal(){};

    public Animal(String name,int age,String sex,double price){
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=price;
    }

    public double getPrice() {
        return price;
    }

    public abstract String toString();



}
