package animalshop;

/**
 * 定义兔兔类
 *
 * 继承Animal类
 */
public class Rabbit extends Animal{

    /**
     * 继承Animal类的全参
     *
     * @param animalName 宠物名称
     * @param age 宠物年龄
     * @param sex 宠物性别
     */
    public Rabbit(String animalName, int age, char sex) {

        /**
         * 兔兔的进价为300元
         */
        super.price = 300;

        /**
         * 兔兔的售价为500元
         */
        super.sell = 500;

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
        System.out.println("兔兔的名字为："+this.animalName);
        System.out.println("兔兔的年龄为："+this.age);
        System.out.println("兔兔的性别为："+this.sex);
        System.out.println("兔兔的价格为："+this.price);
        return "name"+animalName+"age"+age+"sex"+sex+"price"+price;
    }
}
