package animalshop;

/**
 * 定义猫猫类
 *
 * 继承Animal类
 */
public class Cat extends Animal{

    /**
     * 继承Animal类的全参
     *
     * @param animalName 宠物名称
     * @param age 宠物年龄
     * @param sex 宠物性别
     */
    public Cat(String animalName, int age, char sex) {

        /**
         * 猫猫的进价为200元
         */
        super.price = 200;

        /**
         * 猫猫的售价为700元
         */
        super.sell = 700;

        /**
         * 动物名称、性别、年龄属性
         */
        super.animalName = animalName;
        super.age = age;
        super.sex = sex;

    }

    /**
     * 重写toString方法
     *
     * @return 该宠物的相关信息
     */
    @Override
    public String toString() {
        System.out.println("猫猫的名字为："+this.animalName);
        System.out.println("猫猫的年龄为："+this.age);
        System.out.println("猫猫的性别为："+this.sex);
        System.out.println("猫猫的价格为："+this.price);
        System.out.println("猫猫的售价为："+this.sell);
        return "name"+animalName+"age"+age+"sex"+sex+"price"+price+"sell"+sell;
    }

}
