package work2;

public class Rabbit extends Animal {

    protected Rabbit(String name, int age, char gender) {
        super(name, age, gender, 150);
    }

    @Override
    public String toString() {
        return "species: rabbit" + "\r\n" +
                "name: " + name + "\r\n" +
                "age: " + age + "\r\n" +
                "gender: " + gender + "\r\n" +
                "Price: " + Price + "\r\n";
    }
}
