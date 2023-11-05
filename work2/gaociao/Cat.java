public class Cat extends Animal {
    public Cat() {
        price = 200;
    }

    public Cat(String name, int age, double price, String sex) {
        super(name, age, 200, sex);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                ", sex='" + sex + '\'' +
                '}';
    }
}
