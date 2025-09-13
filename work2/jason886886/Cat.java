package work2;


/**
 * @author jason
 */
public class Cat extends AbstractAnimal {
    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "名字:" + this.name + "\n年龄:" + this.age + "\n性别:" + this.gender + "/n价格:" + this.price + "\n";
    }
}
