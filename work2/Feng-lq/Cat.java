package work2;

/**
 * @author FAN
 */
public class Cat extends AbstractAnimal {

    public Cat(String name, int age, int gender) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "姓名:" + name + "\t年龄:" + age + "\t性别(0/1):" + gender + "\t价格:" + price;
    }
}
