public class Lion extends Animal{
    public Lion(String name, int age, String sex, double price) {
        super(name, age, sex, 6666.6);
    }

    @Override
    public String toString() {
        return "Lion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", price=" + price +
                '}';
    }
}
