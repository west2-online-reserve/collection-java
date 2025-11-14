import java.util.Scanner;

public class Dog extends Animal {
    private boolean isVaccineInjected;

    Scanner sc = new Scanner(System.in);

    public Dog() {
        this(null, 0, null, false);
    }

    public Dog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', age='" + age + "', gender='" + gender + "', price='" + price
                + "' isVaccineInjected='" + isVaccineInjected + "'}";
    }

    @Override
    public void inputPet() {
        super.inputPet();
        System.out.println("注射狂犬疫苗了吗?");
        isVaccineInjected = sc.nextBoolean();
    }
}
