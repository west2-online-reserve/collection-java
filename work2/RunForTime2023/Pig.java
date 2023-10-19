public class Pig extends Animal {
    Pig(String name, int age, char sex) {
        super(name, age, sex, 148.7);
    }

    public String toString() {
        String x = "Name:\t" + name + "\nAge:\t" + String.valueOf(age) + "\nSex:\t" + String.valueOf(sex) + "\nPrice:\t" + String.valueOf(price) + "\n";
        return x;
    }
}
