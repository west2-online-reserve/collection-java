package CongWork2;

public abstract class Animals {
    public double cost;//进价
    private String name;//宠物种类
    private int age;//年龄
    private String gender;//性别
    private double price;//售价
    public Animals(String name,int age,String gender,double price){
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.price=price;
    }

    @Override
    public abstract String toString() ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
