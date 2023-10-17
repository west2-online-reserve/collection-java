package com.west2.work2;

public class ChinesePastoralDog extends Animal {
    /**
     * 是否注射过狂犬病毒疫苗
     */
    private boolean isVaccineInjected;

    /**
     * 构造函数
     *
     * @param name              名字
     * @param old               年龄
     * @param gender            性别
     * @param isVaccineInjected 是否注射过狂犬病毒疫苗
     */
    public ChinesePastoralDog(String name, int old, char gender, boolean isVaccineInjected) {
        super(name, old, gender, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

    /**
     * 获取是否注射过狂犬病毒疫苗参数
     *
     * @return isVaccineInjected
     */
    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    /**
     * 设置是否注射过狂犬病毒疫苗参数
     *
     * @param vaccineInjected 是否注射过狂犬病毒疫苗
     */
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
