package shop;

import static shop.MyAnimalShop.getRandom;

/**
 * @author HarveyBlocks
 * @date 2023/08/15 13:17
 **/
public class ChineseDog extends Animal{
    protected static int count;//动物的个数
    protected boolean isVaccineInjected;

    public ChineseDog(){}
    public ChineseDog(String name, int age, int sex) {
        super(name, age, sex, 100,80);
        switch (getRandom(1)){
            case 1:
                isVaccineInjected = true;
                break;
            case 2:
                isVaccineInjected = false;
                break;
        }
    }

    @Override
    public String toString() {
        return "ChineseDog{" +
                "name='" + name + "\'\t" +
                "age=" + age +"\t"+
                "sex=" + sexChange(sex) +"\t"+
                "price=" + price +"\t"+
                "cost=" + cost +"\t"+
                "isVaccineInjected=" + isVaccineInjected +"\t"+
                '}';
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return 100;
    }
}
