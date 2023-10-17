/**
 * @author XiaoxiongMeng
 */
public abstract class BaseAnimal {
    public String name;
    // 姓名

    public int age;
    // 年龄

    public int sex;
    // 1代表公 0代表母

    public double price;
    // 价格

    public boolean isSold = false;

    public BaseAnimal(String name, int age, int sex, double price){
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    @Override
    public abstract String toString();
    /*返回动物的所有信息*/


}
