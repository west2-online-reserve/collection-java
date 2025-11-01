public class Pig extends Animal {
    public Pig() {
        this(null, 0, null);
    }

    public Pig(String name, int age, String gender) {
        super(name, age, gender, 300);
    }

    @Override
    public String toString() {
        return "Pig{name='" + name + "', age='" + age + "', gender='" + gender + "', price='" + price + "'}";
    }
}
