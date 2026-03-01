package MyPetShop;

public class Dog extends Animal{

    private String isVaccineInjected;

    Dog(String name, int age, char gender, String vaccine) {
        super(name, age, gender, 60, 100);
        isVaccineInjected = vaccine;
    }

    @Override
    public String toString() {
        return "宠物姓名:"+nameAnimal+"\n宠物年龄:"+ageAnimal+"\n宠物性别:"+genderAnimal+"\n宠物价格:"+sellPriceAnimal+"\n疫苗注射情况:"+isVaccineInjected+"\n";
    }

}
