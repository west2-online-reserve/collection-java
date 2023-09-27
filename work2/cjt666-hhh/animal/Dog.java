package animal;

public class Dog extends Animal {

    boolean isVaccineInjected;

    public Dog(int age, String name, boolean isMale, double cost) {
        super(age, name, isMale, cost);
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        System.out.println("isVaccineInjected"+isVaccineInjected);
        return super.toString();


    }
}
