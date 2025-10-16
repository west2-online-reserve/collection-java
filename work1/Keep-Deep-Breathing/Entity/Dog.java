package Entity;

public class Dog extends Animal {
    boolean isVaccineInjected;

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    public Dog(String name, int age, int sex, boolean isVaccineInjected) {
        super(name, age, sex, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "宠物姓名：" +name +
                "\n宠物年龄：" + age +
                "\n宠物性别：" + sex +
                "\n宠物价格：" + price +
                "\n宠物是否打疫苗：" + isVaccineInjected;
    }
}
