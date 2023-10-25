/**
 * @author XiaoxiongMeng
 */
public abstract class BaseAnimal {
    private String name;

    private int age;

    private int sex;  // 1代表公 0代表母

    private double price;


    public BaseAnimal(String name, int age, int sex, double price){
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "姓名：" + name +
                "\n年龄：" + age +
                "\n性别：" + (sex == 0 ? "雌性" : "雄性") +
                "\n价格：" + price;
    }


}
