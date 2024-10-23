package work1;

import work1.Animal;

public class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;
    public ChineseRuralDog(String name, int age, String gender, double price){
        super(name,age,gender,price);
        setPrice(100);
    }

    @Override
    public String toString() {
        return "work1.ChineseRuralDog{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
