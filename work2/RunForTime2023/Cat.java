public class Cat extends Animal {
    Cat(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = 200;
    }

    public String toString() {
        String x = "Name:\t" + name + "\nAge:\t" + String.valueOf(age) + "\nSex:\t" + String.valueOf(sex) + "\nPrice:\t" + String.valueOf(price) + "\n";
        return x;
    }
}