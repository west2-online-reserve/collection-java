package com.west2.javawork2;

public class Dog extends Animal{
    private boolean isVaccineInjected;



    public Dog(String name,int age,String sex,double price,boolean isVaccineInjected){
        super(name,age,sex,100);
        this.isVaccineInjected = isVaccineInjected;
    }
    public boolean isVaccineInjected(){
        return this.isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return  "狗子:" + getName()+
                ",年龄:" + getAge()+
                ",性别:" + getSex()+
                ",价格:" + getPrice()+
                ",注射疫苗："+ isVaccineInjected();
    }
}
