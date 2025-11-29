package PetShop;

public class Rabbit extends Animal{
    private String furColor;

    public Rabbit(String name, int age, String gender, String furColor) {
        super(name, age, gender, 50.0);
        this.furColor = furColor;
    }

    @Override
    public String toString() {
        return "兔子{" +
                "名字='" + name + '\'' +
                ", 年龄=" + age +
                ", 性别='" + gender + '\'' +
                ", 毛色='" + furColor + '\'' +
                ", 价格=" + price + "元}";
    }
}
