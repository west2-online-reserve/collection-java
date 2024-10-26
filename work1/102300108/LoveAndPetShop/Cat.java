package LoveAndPetShop;

/**
 * 猫猫类的创建
 *
 * @author xumostar
 * @date 2024/10/26
 */

class Cat extends Animal{
    public Cat(String name, int age, String sex){
        super(name, age, sex, 200, 350);
    }

    @Override
    public String toString() {
        return "\n猫咪の名: " + this.animalName+"\n年龄："+this.animalAge+"\n性别："+this.animalSex+"\n价格："+this.sellPrice+"\n";
    }
}