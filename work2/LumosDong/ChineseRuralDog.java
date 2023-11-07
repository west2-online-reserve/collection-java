package com.dong.westtwowork;

public class ChineseRuralDog extends AbstractAnimal {
    private boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public void setIsvaccineInjected (boolean isVaccineInjected) {

        this.isVaccineInjected = isVaccineInjected;
    }

   public boolean getIsvaccineInjected(){
        return isVaccineInjected;
   }

    @Override
    public String toString(){
        return "宠物种类：" + "Dog" + '\n' +
                "[" + "name: " + name + " " +
                "age: " + age + " " +
                "gender: " + gender + " " +
                "price: " + price + " " +
                "isVaccineInjected: " + isVaccineInjected + "]";
    }
}
