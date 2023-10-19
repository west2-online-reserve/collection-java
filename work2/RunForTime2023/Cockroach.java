public class Cockroach extends Animal {
    Cockroach(String name, int age, char sex) {
        super(name, age, sex, 10);
    }

    @Override
    public String toString() {
        String x = "Name:\t" + name + "\nAge:\t" + String.valueOf(age) + "\nSex:\t" + String.valueOf(sex) + "\nPrice:\t" + String.valueOf(price) + "\n";
        return x;
    }
}
