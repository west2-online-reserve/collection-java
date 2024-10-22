/*定义动物大类*/
public abstract class Animal {
    protected String name;//姓名
    protected int age;//年龄
    protected String gender;//性别
    protected double price;//价格

    //全参构造方法
    public Animal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    //抽象toString()方法
    public abstract String toString();
}
