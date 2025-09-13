public class Sheep extends Animal{
    public Sheep(String name,int age,String sex, double sellingPrice,double costPrice) {
        super(name,age,sex,  sellingPrice, costPrice);
    }

    @Override
    public String toString() {
        return "Sheep [name=" + getName() + ", age=" + getAge() + ", sex=" + getSex() + ", price=" + getSellingPrice() + "]";
    }
}
