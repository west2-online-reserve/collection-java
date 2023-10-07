package com.west2.work2;

public class ChinesePastoralDog extends Animal {
    private boolean isVaccineInjected;

    public ChinesePastoralDog(String name, int old, char gender, boolean isVaccineInjected) {
        super(name, old, gender, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        String s = "品种:中华田园犬,名字:" + this.name + ",年龄:" + this.old + ",性别:" + this.gender + ",价格:" + this.price + ',';
        if (isVaccineInjected) {
            s += "已注射狂犬病疫苗";
        } else {
            s += "未注射狂犬病疫苗";
        }
        return s;
    }


}
