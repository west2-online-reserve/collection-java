package work1;

import work1.Animal;

public class Pig extends Animal {

    public Pig(String name, int age, String gender){
        super(name,age,gender,200.0);

    }

    @Override
    public String toString() {
        return "Pig{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
