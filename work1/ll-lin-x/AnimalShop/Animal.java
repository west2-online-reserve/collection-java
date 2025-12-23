package west2.task1.AnimalShop;

public abstract class Animal {
    private String Name;
    private int age;
    private int gender;
    protected double price;

    Animal(String name,int age,int gender,double price){
        this.Name=name;
        this.age=age;
        this.gender=gender;
        this.price=price;
    }
    double getPrice(){
        return this.price;
    }
    String  getName(){
        return this.Name;
    }
    @Override
    public abstract String toString();
}
