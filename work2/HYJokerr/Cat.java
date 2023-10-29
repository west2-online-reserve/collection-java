public class Cat extends Animal {

    public Cat(String name, int age, String sex) {
        super(name, age, sex, 200);

    }


    @Override
    public String toString() {

        return "种类：猫\n"+"名字："+name+"\n年龄:"+age+"\n性别:"+sex+"\n价格:"+price;
    }
}
