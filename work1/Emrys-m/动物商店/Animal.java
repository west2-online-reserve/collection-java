package HomeWork;

public abstract class Animal {
    public String name;
    public int age;
    public String gender;
    public double price;
    public double purchasePrice;

    public Animal(String name,int age,String gender,double price,double purchasePrice) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.purchasePrice = purchasePrice;
    }

    public Animal(){};

    abstract public String toString();
}
