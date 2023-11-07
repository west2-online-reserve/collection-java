// 猫类
public class Cat extends Animal {

    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200.0);
    }

    @Override
    public String toString() {
        return "Meow meow！" +
                "name='" + this.getName() + '，' +
                "age=" + this.getAge() + '，' +
                "gender='" + this.getGender() + '，' +
                "price=" + this.getPrice();
    }
}