public abstract class Animal{
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    public Animal(String name,int age,String sex,double price)
    {
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=price;
    }
    public Animal(){};

    public abstract String toString();
}

class GardenDog extends Animal{
    private boolean isVaccineInjected;
    public GardenDog(String name,int age,String sex,boolean isVaccineInjected)
    {
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.isVaccineInjected=isVaccineInjected;
        this.price=100;
    }
    public String toString()
    {
        String a=isVaccineInjected?"是":"否";
        return String.format("动物名：%s，年龄：%d，性别：%s，价格：%.2f，是否注射狂犬疫苗：%s",name,age,sex,price,a);
    }
}

class Cat extends Animal{
    public Cat(String name,int age,String sex)
    {
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=200;
    }
    public String toString()
    {
        return String.format("动物名：%s，年龄：%d，性别：%s，价格：%.2f",name,age,sex,price);
    }
}