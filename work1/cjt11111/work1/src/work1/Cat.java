package work1;

public class Cat extends Animal{
    private  double price=200.0;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cat() {
    }

    public Cat(String name, int age, String sex, double price) {
        super(name, age, sex, price);
    }

    @Override
    public String toString() {
        return  "宠物名："+getName()+" "+"年龄："+getAge()+" "+"性别："+getSex()+"价格："+getPrice();
    }
}
