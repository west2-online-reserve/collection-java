public class Cat extends Animal {
    Cat(String name, int age, char sex) {
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        String x = "Name:\t" + name + "\nAge:\t" + String.valueOf(age) + "\nSex:\t" + String.valueOf(sex) + "\nPrice:\t" + String.valueOf(price) + "\n";
        return x;
    }
}