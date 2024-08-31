package com.lovenndme.petstore.animal;


public class Cat extends Animal {

    public Cat() {
    }

    public Cat(String name, int age, int sex) {
        super(name, age, sex, 200.0);
    }

    @Override
    public String toString() {
        String genderStr = (this.getSex() == 0) ? "雌" : "雄";
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + genderStr +
                ", price=" + getPrice();
    }
}

