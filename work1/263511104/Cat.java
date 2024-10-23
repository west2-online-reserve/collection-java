package work1;

import work1.Animal;

public class Cat extends Animal {

    public Cat(String name, int age, String gender, double price){
        super(name,age,gender,price);
        setPrice(200);
    }

    @Override
    public String toString() {
        return "work1.Cat{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
