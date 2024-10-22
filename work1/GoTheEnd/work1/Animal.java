public abstract class Animal{
    protected String name; //名字
    protected int age;  //年龄
    protected String gender;  //性别
    protected double price;  //价格
    protected String temperament;  //性情
    protected double cost;

    public Animal(String name, int age, String gender, double price, double cost, String temperament) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.temperament = temperament;
        this.cost = cost;
    }

    @Override
    public abstract String toString();

    public abstract void feed(); //喂食！

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTemperament() {
        return temperament;
    }

}

