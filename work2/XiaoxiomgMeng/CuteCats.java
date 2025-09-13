/**
 * @author XiaoxiongMeng
 */
public class CuteCats extends BaseAnimal{
    private final static int PRICE = 200;

    public CuteCats(String name, int age, int sex) {
        super(name, age, sex, PRICE);
    }

    @Override
    public String toString() {
        return "可爱的猫咪！\n" + super.toString();
    }
}
