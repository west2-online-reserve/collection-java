package com.lovenndme.petstore.animal;


public class Dog extends Animal {
    protected boolean isVaccineInjected;

    public Dog(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    //构造方法
    public Dog(String name, int age, int sex, boolean isVaccineInjected) {
        super(name, age, sex, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

    //getter setter方法
    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        String genderStr = (this.getSex() == 0) ? "雌" : "雄";
        String vaccineStatus = this.isVaccineInjected ? "Yes" : "No";
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + genderStr +
                ", price=" + getPrice() +
                ", isVaccineInjected=" + vaccineStatus +
                '}';
    }
}
