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
         * 确定猫猫进价与售价
         */
        super(animalName, age, sex, 200, 700);
    }

    /**
     * 重写get方法
     * @return
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


    /**
     * 重写toString方法
     *
     * @return 该宠物的相关信息
     */
    @Override
    public String toString() {
        return "猫猫的名字为："+super.getAnimalName()
                +"\n猫猫的年龄为："+this.getAge()
                +"\n猫猫的性别为："+this.getSex()
                +"\n猫猫的价格为："+this.getPrice()
                +"\n猫猫的售价为："+this.getSell();
    }


}
