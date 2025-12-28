package Instance.Animals;

import Instance.Animal;

public class Cat extends Animal {
    public static final double PRICE=200;
    public Cat(String name, int age, String gender) {
        super(name,age,gender,PRICE);
    }
    @Override
    public String toString() {
        return "猫猫姓名：" + name + ',' +
                "年龄：" + age + "岁" +
                "性别：" + gender + ',' +
                "价格：" + price + "元"
                ;
    }
}

