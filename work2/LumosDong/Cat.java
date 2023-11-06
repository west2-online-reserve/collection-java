package com.dong.westtwowork;

public class Cat extends AbstractAnimal {
    public Cat(String name, int age, String gender, double price) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "宠物种类" + "Cat" +'\n' +"[name: "+name + " " + "age: "+ age + " " + "gender: " + gender +" " + "price: " + price +"]";
    }
}
