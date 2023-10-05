package shop;

/**
 * @author HarveyBlocks
 * @date 2023/08/15 13:22
 **/
public class Rabbit extends Animal{
    protected static int count;//动物的个数
    protected static final int AGE_RANDOM = 10;
    public Rabbit(){};
    public Rabbit(String name, int age, int sex) {
        super(name, age, sex, 50,40);
        count += 1;
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "name='" + name + "\'\t" +
                "age=" + age +"\t"+
                "sex=" + sexChange(sex) +"\t"+
                "price=" + price +"\t"+
                "cost=" + cost +"\t"+
                "}\n";
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public int getPrice() {
        return 50;
    }
}
