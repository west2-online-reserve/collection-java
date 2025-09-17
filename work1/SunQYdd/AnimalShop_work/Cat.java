public class Cat extends Animal {

    public Cat(String name, int age, char sex) {
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return "Cat [name=" + getName() + ", age=" + getAge() + ", sex=" + getSex() + ", price=" + getPrice() +
                "元]";
    }
}
