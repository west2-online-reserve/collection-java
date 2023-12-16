package CongWork2;

public class Fox extends Animals{
    public Fox(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return "宠物名："+getName()+" "+"年龄："+getAge()+" "+"性别："+getGender()+"售价："+getPrice();
    }


}
