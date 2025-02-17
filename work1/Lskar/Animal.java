abstract class Animal{
    protected String name;
    protected int age;
    protected double price;
    public abstract String toString();
    public Animal(String name, int age, double price){
        this.name = name;
        this.age = age;
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
}