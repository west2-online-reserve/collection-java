public class Dog extends Animal {

    private boolean isVaccineInjected;

    public Dog(String animalName, int animalAge, String animalSex, boolean isVaccineInjected) {
        //调用父类的构造函数
        super(animalName, animalAge, animalSex, 100);
        this.isVaccineInjected = isVaccineInjected;

    }

    @Override
    public String toString() {
        return "种类: 狗\n" + "名字: " + getAnimalName() + "\n年龄: " + getAnimalAge() + "\n性别: " + getAnimalSex() + "\n价格: " + getAnimalPrice() + "\n是否注射狂犬疫苗: " + isVaccineInjected;
    }
}
