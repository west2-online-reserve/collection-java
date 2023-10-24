public class Dog extends Animal {
    private boolean isVaccineInjected;

    Dog(String name, int age, char sex) {
        super(name, age, sex, 100);
        this.isVaccineInjected = false;
    }

    public void setVaccineInjected() {
        isVaccineInjected = true;
    }

    public boolean getVaccineInjected() {
        return isVaccineInjected;
    }
    @Override
    public String toString() {
        String x = "Name:\t\t\t\t" + name + "\nAge:\t\t\t\t" + String.valueOf(age) + "\nSex:\t\t\t\t" + String.valueOf(sex) + "\nPrice:\t\t\t\t" + String.valueOf(price) + "\nVaccine Injected:\t";
        if(isVaccineInjected) {
            x+="Yes\n";
        }
        else {
            x+="No\n";
        }
        return x;
    }
}