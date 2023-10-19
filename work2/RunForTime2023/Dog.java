public class Dog extends Animal {
    private boolean isVaccineInjected;

    Dog(String name, int age, char sex) {
        super(name, age, sex, 100);
        this.isVaccineInjected = false;
    }

    public void setVaccineInjected() {
        isVaccineInjected = true;
    }

    @Override
    public String toString() {
        String x = "Name:\t" + name + "\nAge:\t" + String.valueOf(age) + "\nSex:\t" + String.valueOf(sex) + "\nPrice:\t" + String.valueOf(price) + "\n";
        return x;
    }
}