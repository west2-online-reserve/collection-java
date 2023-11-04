public class Cat extends Animal{
    public double price = 200;
    public Cat() {
    }
    public Cat(double price) {
        super(price);
    }

    public Cat(String name, int age, String gender) {
        super(name, age, gender);
        price = this.getPrice();
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                ",price=" + getPrice() +
                '}';
    }
}
