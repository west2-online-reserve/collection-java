public class Cat extends Animal {
    public Cat(String animalName, int age, String sex, double price) {
        super(animalName, age, sex, 200.0);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "animalName='" + animalName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", price=" + price +
                '}';
    }
}
