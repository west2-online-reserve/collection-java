package shop;

/**
 * @author HarveyBlocks
 * @date 2023/08/15 13:21
 **/
public class Cat extends Animal{
    protected static final int AGE_RANDOM = 15;
    protected static int count;//动物的个数
    public Cat(String name, int age, int sex) {
        super(name, age, sex, 200,160);
    }

    public Cat(){};


    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + "\'\t" +
                "age=" + age +"\t"+
                "sex=" + sexChange(sex) +"\t"+
                "price=" + price +"\t"+
                "cost=" + cost +"\t"+
                '}';
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return 200;
    }
}
