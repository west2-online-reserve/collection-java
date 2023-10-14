package com.west2.check02;

/***
 * @author yuyu
 */
public class Cats extends AbstractAnimal {
    public Cats(String name, int age, String gender) {
        super(name, age, gender, 200);
    }


    @Override
    public String toString() {
        return "宠物种类：" + "Cat" + '\n' +
                " [name：" + name + " " +
                " age：" + age + " " +
                " gender:" + gender + " " +
                " price:" + price + "]";
    }
}
