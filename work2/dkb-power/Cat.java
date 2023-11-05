public class Cat extends Animal {


    public Cat(String name, int age, String gender, double price) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "小猫姓名为" + getName() + " 年龄" + getAge() + " 性别" + getGender() + " 价格" + getPrice();
    }
}
