public abstract class Animal {
    protected String animalname;
    protected int age;
    protected int gentle;// 公用0表示，1表示母
    protected double price;

    public Animal(String animalname,int age,int gentle,double price)
    {
        this.animalname=animalname;
        this.age=age;
        this.gentle=gentle;
        this.price=price;
    }

    @Override
    public abstract String toString();//抽象方法
}
