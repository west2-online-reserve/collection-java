package work1;

public class Hamster extends Animal{
    private  final double price=80.0;
        //仓鼠

    public Hamster(String name, int age, String sex) {
        super(name, age, sex);
    }


    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  "宠物名："+getName()+" "+"年龄："+getAge()+" "+"性别："+getSex()+"价格："+getPrice();
    }
}
