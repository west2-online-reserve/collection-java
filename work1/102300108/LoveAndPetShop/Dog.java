package LoveAndPetShop;

/**
 * 中华田园犬类的创建
 *
 * @author xumostar
 * @date 2024/10/26
 */

class Dog extends Animal{
    public Dog(String name, int age, String sex, boolean vaccine) {
        super(name, age, sex, 100, 225); //调用父类构造函数
        isVaccineInjected=vaccine; //初始化vaccine字段
    }
    
    @Override
    public String toString() {
        return "\n小狗の名: " + this.animalName
                +"\n年龄："+this.animalAge
                +"\n性别："+this.animalSex
                +"\n价格："+this.sellPrice+"\n"
                +"疫苗接种情况："+isVaccineInjected+"\n";
    }

    private boolean isVaccineInjected;
    
}
