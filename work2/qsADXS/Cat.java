public class Cat extends Animal{

    public Cat(String name, int age, int gender) {
        super(name, age, gender, 200.0,150);

    }

    public String toString() {
        String str;
        str = "这是一只小猫\n名字：" + super.getName() + "\n年龄：" + super.getAge() + "\n" + "性别：";
        if (super.getGender() == 0)
            str += "雌性";
        else if (super.getGender() == 1) {
            str += "雄性";
        } else if (super.getGender() == 2) {
            str += "被嘎蛋的雄性";
        }
        str += "\n售价：" + super.getPrice() + "\n进价: " + super.getPurchasingCost();

        return str;
    }

    public void mouseWreckers() {//逗猫
        System.out.println("mew!");
    }

}
