package Test;

public class NaiLong extends Animal{

    public NaiLong(String name, int age, String gender) {
        super(name, age, gender, 250);
    }

    @Override
    public String toString() {
        return "NaiLong{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}
