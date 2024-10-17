package pojo;

import base.AbstractAnimal;
//狗狗类 价格100
public class Dog extends AbstractAnimal {
    private boolean isVaccineInjected; //是否打了疫苗

    @Override
    public String toString() {
        return "姓名"+name+",年龄"+age+"岁,性别为"+gender+"的一只中华田园犬";
    }

    public Dog(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    public Dog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }


    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }
}
