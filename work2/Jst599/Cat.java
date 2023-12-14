package PetShop;

import PetShop.Animal;

/**
 * Cat类表示宠物店的猫
 *
 * 该类包含猫的价格
 * @author Jst599
 * @date 2023/10/17
 */
public class Cat extends Animal {
    public Cat() {}

    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200, 150);
    }

    @Override
    public String toString() {
        return "Cat{" + "name = " + name + ";"+
                "age = " + age + ";" +
                "gender = " + gender + ";" +
                "price = " + price + ";" +
                "}";
    }
}
