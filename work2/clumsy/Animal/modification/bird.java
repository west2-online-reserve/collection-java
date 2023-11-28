package modification;

public class bird extends Animal {
    public bird() {}

    public bird(String name, int age, String gender) {
        super(name,age,gender,100);

    }
    @Override
    public String toString() {
        return "鸟类：名字是"+name+"\n" +
                "年龄是"+age+"\n" +
                "性别是"+gender+"\n" +
                "售卖价格是"+price*2+"\n" +
                "__________________";
    }
    @Override//重载方法来区分进价和售价
    public String toString(String mi){
        return "鸟类：名字是"+name+"\n" +
                "年龄是"+age+"\n" +
                "性别是"+gender+"\n" +
                "进货价格是"+price+"\n" +
                "__________________";
    }
}
