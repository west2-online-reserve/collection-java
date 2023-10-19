/**
 * @author XiaoxiongMeng
 */
public class Panda extends BaseAnimal{
    private final static int PRICE = 2147483647;
    public Panda(String name, int age, int sex) {
        super(name, age, sex, PRICE);
    }

    @Override
    public String toString() {
        return "无价之国宝大熊猫！\n" + super.toString();
    }
}
