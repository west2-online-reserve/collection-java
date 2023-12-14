package collection

public abstract class Animal {
    protected String name;
    protected int age;
    protected int amount;
    protected char sex;
    protected double price;
    public  Animal(String name,int age,char sex,double price){
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=price;
        amount=1;//销售手段，总会只剩下一只宠物，吸引顾客购买。

    }
    abstract public String toString();

}
