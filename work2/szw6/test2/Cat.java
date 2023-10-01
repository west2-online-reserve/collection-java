package szw.test2;

public class Cat extends Animal{

    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200.0);
    }

    @Override
    public String toString() {
        return "猫猫: " + getName() +
                ", 年龄: " + getAge() +
                ", 性别: " + getGender() +
                ", 价格: " + getPrice() ;
     }

}
