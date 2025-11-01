public class Cat extends Animal {
    public Cat() {
        this(null, 0, null);
    }

    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "Cat{name='" + name + "', age='" + age + "', gender='" + gender + "', price='" + price + "'}";
    }
}
