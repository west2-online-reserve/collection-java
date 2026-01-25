package PetShop.model;

public class Cat extends Animal {
    public  Cat(String name,int age, boolean isMale) {
        super(name, age, isMale, 200,400);
    }
    @Override
    public String toString() {
        return "猫 {" +
                "名字='" + name + '\'' +
                ", 年龄=" + age +
                ", 性别='" + isMale + '\'' +
                ", 购入价格=" + buyPrice +
                ", 售出价格=" + sellPrice +
                '}';
    }
}