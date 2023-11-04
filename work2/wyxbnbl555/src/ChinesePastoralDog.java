public class ChinesePastoralDog extends Animal{
    private boolean isVaccineInjected;
    public double price = 100;

    public ChinesePastoralDog() {
    }
    public ChinesePastoralDog(double price){
        super(price);
    }

    public ChinesePastoralDog(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    public ChinesePastoralDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender);
        this.isVaccineInjected = isVaccineInjected;
        price = this.getPrice();

    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "ChinesePastoralDog{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                ",price=" + getPrice() +
                "isVaccineInjected=" + isVaccineInjected +
                '}';
    }
}
