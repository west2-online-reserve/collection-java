package com.lovenndme.petstore.animal;


public class Bird extends Animal {

    public Bird() {
    }

    public Bird(String name, int age, int sex) {
        super(name, age, sex, 50.0);
    }

    @Override
    public String toString() {
        String genderStr = (this.getSex() == 0) ? "雌" : "雄";
        return "Bird{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + genderStr +
                ", price=" + getPrice();
    }
}
