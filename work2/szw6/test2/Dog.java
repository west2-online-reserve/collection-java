package szw.test2;

public class Dog extends Animal{

    private boolean isVaccineInjected;
    public Dog(String name, int age, String gender,boolean isVaccineInjected) {
        super(name, age, gender, 100.0);
        this.isVaccineInjected=isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }


    @Override
    public String toString() {

        return "狗狗: " + getName() +
                ", 年龄: " + getAge() +
                ", 性别: " + getGender() +
                ", 价格: " + getPrice() +
                ", 狂犬疫苗: " + isVaccineInjected();
    }
}
