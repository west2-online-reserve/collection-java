package com.west2.check02;

/***
 * @author yuyu
 */
public class ChineseRuralDogs extends AbstractAnimal {
    protected boolean isVaccineInjected;

    public ChineseRuralDogs(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "宠物种类：" + "Dog" + '\n' + " [name：" + name + " " + " age：" + age + " " + " gender:" + gender + " " + " price:" + price + " " + "isVaccineInjected:" + isVaccineInjected + "]";
    }
}
