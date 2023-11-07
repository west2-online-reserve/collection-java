public class Dog extends Animal {
    private boolean isVaccineInjected;

    public Dog(String name, int age, String gender, double price, boolean isVaccineInjected) {
        super(name, age, gender, price);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                ", isVaccineInjected=" + isVaccineInjected +
                '}';
    }
}
