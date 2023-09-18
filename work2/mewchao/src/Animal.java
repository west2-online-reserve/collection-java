abstract class Animal {
    protected String name;
    protected int age;
    protected double price;
    protected boolean gender;
    public Animal(String name,int age,double price,boolean gender){
        this.name=name;
        this.age=age;
        this.price=price;
        this.gender=gender;
    }
    public Animal(){
    }
    abstract public String toString();
    abstract public double getPrice();
}
