package PetShop.model;

public class Crow extends Animal {
    public  Crow(String name,int age, boolean isMale){
        super(name, age, isMale, 300,600);
    }
    @Override
    public String toString() {
        return "乌鸦 {" +
                "名字='" + name + '\'' +
                ", 年龄=" + age +
                ", 性别='" + isMale + '\'' +
                ", 购入价格=" + buyPrice +
                ", 售出价格=" + sellPrice +
                '}';
    }
}
