package MyPetShop;

public abstract class Animal {
    protected String nameAnimal;
    protected int ageAnimal;
    protected char genderAnimal;
    protected double purchasePriceAnimal;
    protected double sellPriceAnimal;
    private String master;

    Animal(String name, int age, char gender, double purchasePriceAnimal, double sellPriceAnimal){
        nameAnimal = name;
        ageAnimal = age;
        genderAnimal = gender;
        this.purchasePriceAnimal = purchasePriceAnimal;
        this.sellPriceAnimal = sellPriceAnimal;
    }

    public void getMaster(String nameCustomer) {
        master = nameCustomer;
        System.out.println("宠物主人:"+master);
    }

    public String getAnimalInformation() {
        return "宠物姓名:"+nameAnimal+"\n宠物年龄:"+ageAnimal+"\n宠物性别:"+genderAnimal+"\n宠物购入价格:"+purchasePriceAnimal+"\n宠物售出价格:"+sellPriceAnimal;
    }

    public String toString() {
        return "宠物姓名:"+nameAnimal+"\n宠物年龄:"+ageAnimal+"\n宠物性别:"+genderAnimal+"\n宠物价格:"+sellPriceAnimal+"\n";
    }

}
