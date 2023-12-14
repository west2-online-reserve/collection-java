package work2;

/**
 * @author FAN
 */
public class Bird extends AbstractAnimal {
    public Bird(String name, int age, int gender) {
        super(name, age, gender, 50);
    }

    @Override
    public String toString() {
        return "姓名:" + name + "\t年龄:" + age + "\t性别(0/1):" + gender + "\t价格:" + price;
    }
}
