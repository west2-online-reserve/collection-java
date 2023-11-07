public class Pig extends Animal {
    public Pig(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        price = 148.7;
    }

    public String toString() {
        String x = "Name:\t" + name + "\nAge:\t" + String.valueOf(age) + "\nSex:\t" + String.valueOf(sex) + "\nPrice:\t" + String.valueOf(price) + "\n";
        return x;
    }
}