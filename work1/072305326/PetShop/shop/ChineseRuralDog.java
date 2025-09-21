package com.cyx.Amimals;

public class ChineseRuralDog extends Animal{
    private boolean isVaccineInjected;// 是否注射疫苗
    private final double purchasePrice = 80; //  中华田园犬买入价格
    private final double sellingPrice = 100; //  中华田园犬卖出价格

    public ChineseRuralDog(String name, int age, String gender,boolean vaccineInjected) {
        super(name, age, gender, 80, 100);
        isVaccineInjected = vaccineInjected;

    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public double getSellingPrice() {
        return sellingPrice;
    }

    @Override
    public String toString() {
        return "中华田园犬"
                + "，名字为：" + getName()
                + "，年龄为：" + getAge()
                + "，性别为：" + getGender()
                + "，购买价格为：" + getPurchasePrice()
                + "，出售价格为：" + getSellingPrice()
                + "，是否注射疫苗：" + isVaccineInjected;

    }
}
