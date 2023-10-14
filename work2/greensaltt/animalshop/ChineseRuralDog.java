package animalshop;

/**
 * 定义中华田园犬类
 *
 * 继承Animal类
 */
public class ChineseRuralDog extends Animal{

    /**
     * 继承Animal类的全参
     *
     * @param animalName 宠物名称
     * @param age 宠物年龄
     * @param sex 宠物性别
     * @param isVaccineInjected 是否接种疫苗
     */
    public ChineseRuralDog(String animalName, int age, char sex, boolean isVaccineInjected) {

        /**
         * 中华田园犬进价为100元
         */
        super.price = 100;

        /**
         * 中华田园犬售价为200元
         */
        super.sell = 200;

        /**
         * 动物名称、性别、年龄属性
         */
        super.animalName = animalName;
        super.age = age;
        super.sex = sex;
        this.isVaccineInjected = isVaccineInjected;
    }

    /**
     *中华田园犬属性：是否接种疫苗
     *
     * 默认未接种
     */
    boolean isVaccineInjected;

    /**
     * 重写toString方法
     *
     * 输出该中华田园犬相关信息
     *
     * @return 该中华田园犬相关信息
     */
    @Override
    public String toString() {
        System.out.println("中华田园犬的名字为："+this.animalName);
        System.out.println("中华田园犬的年龄为："+this.age);
        System.out.println("中华田园犬的性别为："+this.sex);
        System.out.println("中华田园犬的价格为："+this.price);
        System.out.println("中华田园犬是否接种疫苗："+this.isVaccineInjected);
        return "name"+animalName+"age"+age+"sex"+sex+"price"+price+"isVaccineInjected"+isVaccineInjected;
    }

}
