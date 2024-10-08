package west2;

public class Cat extends Animal {
    public Cat(String name, int age, String gender) {
        super(name, age, 200.0, gender); 
    }

    @Override
    public String toString() {
        return "Cat{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", Gender='" + Gender + '\'' +
               ", price=" + price +
               '}';
    }
}
