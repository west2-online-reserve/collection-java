package Work1;

public class Hamster extends Animal {
    private double weight;

    public Hamster(String name, int age, String sex, double value, double weight) {
        super(name, age, sex, value);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "品种:" + "仓鼠"
                + "\n姓名:" + name
                + "\n年龄:" + age
                + "\n性别:" + sex
                + "\n重量:" + weight
                + "\n价格:" + value;
    }
}
