public class Cat extends Animal {

    public Cat(String animalName, int animalAge, String animalSex) {
        super(animalName, animalAge, animalSex, 200);
    }


    @Override
    public String toString() {
        return "种类: 猫\n" + "名字: " + getAnimalName() + "\n年龄: " + getAnimalAge() + "\n性别: " + getAnimalSex() + "\n价格: " + getAnimalPrice();
    }
}
