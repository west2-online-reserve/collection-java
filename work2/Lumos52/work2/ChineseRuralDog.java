package com.west2.work2;

public class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, double price, String sex, boolean isVaccineInjected) {
        super(name, age, price, sex);
        this.isVaccineInjected = isVaccineInjected;
    }

    /**
     * 获取是否注射疫苗
     *
     * @return isVaccineInjected
     */
    public boolean isIsVaccineInjected() {
        return isVaccineInjected;
    }

    /**
     * 设置是否注射疫苗
     *
     * @param isVaccineInjected
     */
    public void setIsVaccineInjected(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    public String toString() {
        return "这个名叫" + name + "性别是" + sex + "的小狗，" +
                "今年" + age + "岁了噢！它的价格是" + price + "元。欢迎把它带回家！";
    }

    public String toString1() {
        return "[" + name + "," + sex + "," + age + "," + price + "]";
    }

}
