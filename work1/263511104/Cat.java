package work1;

import work1.Animal;

public class Cat extends Animal {

    public Cat(String name, int age, String gender){
        super(name,age,gender,200.0);

    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
