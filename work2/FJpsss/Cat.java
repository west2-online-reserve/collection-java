public class Cat extends Animal {

    public Cat(String animalName, int animalAge, String animalSex) {
        super(animalName, animalAge, animalSex, 200);
    }

    @Override
    public String toString() {
        return "种类: 猫\n" + "名字: " + animalName + "\n年龄: " + animalAge + "\n性别: " + animalSex + "\n价格: " + animalPrice;
    }
}
