package XWBN2;

import XWBN2.Animal;

public class Beer extends Animal {

    boolean isHealthy;

    public Beer(String name, int age, String sex, double aniPurPrice) {
        super(name, age, sex, 500, aniPurPrice);
    }

    public String toString() {
        return '\n' + "[动物名:" + getAnimalName() + "]" + '\n' + "[动物年龄:" + getAnimalAge() + "]" + '\n' + "[动物性别:" + getAnimalSex() + "]" + '\n' + "[动物进价:" + getAnimalPurPrice() + "]" + '\n' + "[动物售价:" + getAnimalSellPrice() + "]" + '\n';
    }

    public void setIsHealthy(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }
}
