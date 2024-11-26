package work1;

import work1.Animal;

public class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;
    public ChineseRuralDog(String name, int age, String gender){
        super(name,age,gender,100.0);
        this.isVaccineInjected = false;
    }

    @Override
    public String toString() {
        return "ChineseRuralDog{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
