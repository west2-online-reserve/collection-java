package work2;

public class ChinesePastoralDog extends Animal {
    private boolean IsVaccineInjected;

    public ChinesePastoralDog(String name, int age, char gender, boolean IsVaccineInjected) {
        super(name, age, gender, 100);
        this.IsVaccineInjected = IsVaccineInjected;
    }

    public boolean getIsVaccineInjected() {
        return IsVaccineInjected;
    }

    public void setIsVaccineInjected(boolean IsVaccineInjected) {
        IsVaccineInjected = IsVaccineInjected;
    }

    @Override
    public String toString() {

        return "species: dog" + "\r\n" +
                "name: " + name + "\r\n" +
                "age: " + age + "\r\n" +
                "gender: " + gender + "\r\n" +
                "Price: " + Price + "\r\n";
    }
}
