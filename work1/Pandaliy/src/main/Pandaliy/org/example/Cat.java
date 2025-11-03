package org.example;

public class Cat extends Animal {
    public Cat(String n, int a, int s) {
        super(n, a, s, 200);
    }

    public String toString() {
        String message = ("这只猫是" + (sex == 1 ? "雄性" : "雌性") + "，它的名字是" + name
                + "，它的年龄是" + age + "岁，它的价格是" + price + "元\n");
        return message;
    }
}
