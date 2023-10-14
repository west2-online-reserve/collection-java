package com.west2.check02;

/***
 * 这个动物是豹纹守宫，一种超可爱的壁虎
 * @author yuyu
 */
public class LeopardGeckos extends AbstractAnimal {
    public LeopardGeckos(String name, int age, String gender) {
        super(name, age, gender, 3000);
    }

    @Override
    public String toString() {
        return "宠物种类：" + "LeopardGecko" + '\n' +
                " [name：" + name + " " +
                " age：" + age + " " +
                " gender:" + gender + " " +
                " price:" + price + "]";
    }
}
