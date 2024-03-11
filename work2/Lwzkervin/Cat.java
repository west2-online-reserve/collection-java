public class Cat extends Animal{
    public Cat(String animalName, int age, String gender) {
        super(animalName, age, gender, 100.0);
    }
    public String toString() {
        return "猫猫{" +
                "动物名='" + animalName + '\'' +
                ", 年龄=" + age +
                ", 性别='" + gender + '\'' +
                ", 价格=" + price +
                '}';
    }
}
