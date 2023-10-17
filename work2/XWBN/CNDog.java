package XWBN2;

import XWBN2.Animal;

public class CNDog extends Animal {

    boolean isVaccineInjected;

    public CNDog(String name, int age, String sex, double aniPurPrice) {
        super(name, age, sex, 100, aniPurPrice);
    }

    public String toString() {
        return '\n' + "[动物名:" + getAnimalName() + "]" + '\n' + "[动物年龄:" + getAnimalAge() + "]" + '\n' + "[动物性别:" + getAnimalSex() + "]" + '\n' + "[动物进价:" + getAnimalPurPrice() + "]" + '\n' + "[动物售价:" + getAnimalSellPrice() + "]" + '\n';
    }

    //获取CNDog是否打疫苗
    public Boolean getVaccineInjected() {
        return isVaccineInjected;
    }

    //设置CNDog疫苗注射情况
    public void setVaccineInjected(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

}
