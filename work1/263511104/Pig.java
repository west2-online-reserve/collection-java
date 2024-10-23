package work1;

import work1.Animal;

public class Pig extends Animal {

    public Pig(String name, int age, String gender, double price){
        super(name,age,gender,price);
        setPrice(200);
    }

    @Override
    public String toString() {
        return "work1.Pig{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
