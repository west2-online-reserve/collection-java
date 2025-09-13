package LoveAndPetShop;

/**
 * 狐狸类的创建
 *
 * @author xumostar
 * @date 2024/10/26
 */

class Fox extends Animal{
    public Fox(String name, int age, String sex){
        super(name, age, sex, 350, 520);
    }

    @Override
    public String toString() {
        return "\n狐狸の名: " + this.animalName+"\n年龄："+this.animalAge+"\n性别："+this.animalSex+"\n价格："+this.sellPrice+"\n";
    }
}