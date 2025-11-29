package Pet.shop;

public class Rabbit extends Animal{
    private boolean isLop;

    public Rabbit(String name, int age, String gender, boolean isLop) {
        super(name, age, gender, 300);
        this.isLop = isLop;
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "isLop=" + isLop +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}
