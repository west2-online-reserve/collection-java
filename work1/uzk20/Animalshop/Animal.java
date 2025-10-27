package work1.Animalshop;

abstract class Animal {
    //动物名(String)
    //年龄(int)
    //性别
    //价格(double)

    protected String name;
    protected int age;
    protected String sex;
    protected double purchasePrice;
    protected double sellingPrice;

    public Animal(String n,int a,String s,double purchase,double sell){
        this.name=n;
        this.age=a;
        this.sex=s;
        this.purchasePrice=purchase;
        this.sellingPrice=sell;
    }

    public abstract String toString();

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getSex(){
        return sex;
    }

    public double getPurchasePrice(){
        return purchasePrice;
    }

    public double getSellingPrice(){
        return sellingPrice;
    }
}
