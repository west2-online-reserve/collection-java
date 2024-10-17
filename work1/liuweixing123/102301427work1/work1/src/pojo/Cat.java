package pojo;

import base.AbstractAnimal;
//猫猫类，价格为200
public class Cat extends AbstractAnimal {

    public Cat() {
    }

    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "姓名"+name+",年龄"+age+"岁,性别为"+gender+"的一只猫猫";
    }
}
