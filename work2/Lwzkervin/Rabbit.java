public class Rabbit extends Animal{
    public Rabbit(String animalName, int age, String gender) {
        super(animalName, age, gender, 200.0);
    }
    public String toString() {
        return "兔兔{" +
                "动物名='" + animalName + '\'' +
                ", 年龄=" + age +
                ", 性别='" + gender + '\'' +
                ", 是否注射狂犬病疫苗=" +
                ", 价格=" + price +
                '}';
    }
}