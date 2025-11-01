public class Brid extends Animal {
    public Brid() {
        this(null, 0, null);
    }

    public Brid(String name, int age, String gender) {
        super(name, age, gender,50);
    }

    @Override
    public String toString() {
        return "Brid{name='" + name + "', age='" + age + "', gender='" + gender + "', price='" + price + "'}";
    }
}
