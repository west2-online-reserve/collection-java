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
        super(animalName, age, sex, 150, 600);
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

    /**
     * 重写toString方法
     *
     * @return 该宠物的相关信息
     */
    @Override
    public String toString() {
        return "兔兔的名字为："+this.getAnimalName()
                +"\n兔兔的年龄为："+this.getAge()
                +"\n兔兔的性别为："+this.getSex()
                +"\n兔兔的价格为："+this.getPrice()
                +"\n兔兔的售价为："+this.getSell();
    }
}
