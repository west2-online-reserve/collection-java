public class ChinesePastoralDog extends Animal {
    protected boolean isVaccinelnjected;

    public ChinesePastoralDog() {
        super("ChinesePastoralDog", 7, "boy", 100);
        this.name = "ChinesePastoralDog";
        this.price = 100;
    }

    public ChinesePastoralDog(String name, int age, String gender, double price) {
        super(name, age, gender, price);
        isVaccinelnjected = true;
    }

    @Override
    public String toString() {
        return "ChinesePastoralDog{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + gender +
                ", price=" + price +
                "isVaccineInjected=" + isVaccinelnjected +
                '}';
    }
}
