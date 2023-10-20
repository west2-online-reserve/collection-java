package animalshop;

/**
 * @author 102301412
 */
public class Cat extends BaseAnimal {
    private static final double price = 200.0;

    public Cat() {
    }

    public Cat(String petName, int age, char gender) {
        super(petName, age, gender,price);
    }

    @Override
    public String toString() {
        return "{猫猫：" + "姓名：" + getPetName() + " 年龄：" + getAge() + " 性别：" + getGender() + '}';
    }
}
