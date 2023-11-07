package work2;

public class Cat extends Animal {
    @Override
    public String toString() {
        return "species: cat" + "\r\n" +
                "name: " + name + "\r\n" +
                "age: " + age + "\r\n" +
                "gender: " + gender + "\r\n" +
                "Price: " + Price + "\r\n";
    }
    public Cat(String name, int age, char gender) {
        super(name, age, gender, 200);
    }


}
