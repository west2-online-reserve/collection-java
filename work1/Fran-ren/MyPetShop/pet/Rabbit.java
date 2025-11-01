public class Rabbit extends Animal {
    public Rabbit() {
        this(null, 0, null);
    }

    public Rabbit(String name, int age, String gender) {
        super(name, age, gender, 50);
    }

    @Override
    public String toString() {
        return "Rabbit{name='" + name + "', age='" + age + "', gender='" + gender + "', price='" + price + "'}";
    }
}
