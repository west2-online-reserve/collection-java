public class ChinesePastoralDog extends Animal{
    private boolean isVaccineInjected;

    public ChinesePastoralDog(){

    }

    public ChinesePastoralDog(String name, int age, String gender, boolean isVaccineInjected,double price) {
        super(name, age, gender,100);
        this.isVaccineInjected = isVaccineInjected;

    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        this.isVaccineInjected = vaccineInjected;
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
