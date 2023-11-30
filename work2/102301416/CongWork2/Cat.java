package CongWork2;

public class Cat extends Animals{
    private double cost;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Cat(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return "宠物名："+getName()+" "+"年龄："+getAge()+" "+"性别："+getGender()+"售价："+getPrice();
    }
}
