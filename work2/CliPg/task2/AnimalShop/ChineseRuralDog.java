package com.PeanutJava.task2;

public class ChineseRuralDog extends Animal{

     protected boolean isVaccineInjected;


     public ChineseRuralDog() {
     }

     public ChineseRuralDog(String name, int age, String gender,boolean isVaccineInjected) {
          super("中华田园犬",name,age,gender,100);
          this.isVaccineInjected = isVaccineInjected;
     }

     public boolean isIsVaccineInjected() {
          return isVaccineInjected;
     }

     public void setIsVaccineInjected(boolean isVaccineInjected) {
          this.isVaccineInjected = isVaccineInjected;
     }

     public String toString() {
          return "ChineseRuralDog{" +
                  "name=" + getName() + '\'' +
                  ", age=" + getAge() +
                  ", gender='" + getGender() + '\'' +
                  ", price=" + getPrice() +
                  '}';
     }
}

