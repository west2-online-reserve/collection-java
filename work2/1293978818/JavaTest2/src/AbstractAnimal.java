/**
 * 作为狗、猫类的父类
 * @author 1293978818
 * @version 1.0
 */
public abstract class AbstractAnimal {

    /**
     * 变量
     */
    protected String animalName;
    protected int animalAge;
    protected String sex;
    protected double animaiPrice;

    /**
     * 一个全参构造方法
     * @param animalName
     * @param animalAge
     * @param Sex
     * @param animaiPrice
     */
    public AbstractAnimal(String animalName, int animalAge, String sex, double animaiPrice) {
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.sex = sex;
        this.animaiPrice = animaiPrice;
    }

    /**
     * 一个抽象的toString方法
     * @return 字符串
     */
    @Override
    public abstract String toString();

    public double getAnimalPrice(){
        return animaiPrice;
    }
}
