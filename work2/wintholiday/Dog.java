package work2;

public class Dog extends Animal {
    protected boolean isVaccineInjected;
    protected static final double PRICE = 100.0;

    public Dog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, PRICE);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }


    @Override
    public String toString() {
        return "Dog: " + getName() +
                ", Age: " + getAge() +
                ", Gender: " + getGender() +
                ", Price: " + getPrice() +
                ", Vaccine Injected: " + isVaccineInjected();
    }

}
