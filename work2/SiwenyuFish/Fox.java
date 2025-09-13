package work2;

public class Fox extends Animal{
    String blood;

    public Fox(String name, int age, String sex, double price, String blood) {
        super(name, age, sex, price);
        this.blood = blood;
    }

    @Override
    public String toString() {
        return "Fox{" +
                "blood='" + blood + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", price=" + price +
                '}';
    }
}
