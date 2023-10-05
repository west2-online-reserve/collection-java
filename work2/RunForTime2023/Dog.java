public class Dog extends Animal {
    private boolean isVaccineInjected;

    Dog(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = 100;
        this.isVaccineInjected = false;
    }

    public void setVaccineInjected() {
        isVaccineInjected = true;
    }

    public String toString() {
        String x = "Name:\t" + name + "\nAge:\t" + String.valueOf(age) + "\nSex:\t" + String.valueOf(sex) + "\nPrice:\t" + String.valueOf(price) + "\n";
        return x;
    }
}