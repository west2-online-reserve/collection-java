package animalshop;

/**
 * 定义中华田园犬类
 *
 * 继承Animal类
 */
public class ChineseRuralDog extends Animal{

    /**
     *中华田园犬属性：是否接种疫苗
     *
     * 默认未接种
     */
    private boolean isVaccineInjected;

    /**
     * 继承Animal类的全参
     *
     * @param animalName 宠物名称
     * @param age 宠物年龄
     * @param sex 宠物性别
     * @param isVaccineInjected 是否接种疫苗
     */
    public ChineseRuralDog(String animalName, int age, char sex, boolean isVaccineInjected) {
        super(animalName, age, sex, 100, 300);
        this.isVaccineInjected = isVaccineInjected;
    }

    /**
     * 重写get
     */
    @Override
    public String getAnimalName() {
        return super.getAnimalName();
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public char getSex() {
        return super.getSex();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public double getSell() {
        return super.getSell();
    }

    public boolean isVaccineInjected() {
        return this.isVaccineInjected = isVaccineInjected;
    }

    /**
     * 重写toString方法
     *
     * 输出该中华田园犬相关信息
     *
     * @return 该中华田园犬相关信息
     */
    @Override
    public String toString() {
        return "狗狗的名字为："+this.getAnimalName()
                +"\n狗狗的年龄为："+this.getAge()
                +"\n狗狗的性别为："+this.getSex()
                +"\n狗狗的价格为："+this.getPrice()
                +"\n狗狗的售价为："+this.getSell()
                +"\n狗狗是否接种疫苗"+this.isVaccineInjected;

    }
}
