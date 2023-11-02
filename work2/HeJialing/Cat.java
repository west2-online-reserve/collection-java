package src;

public class Cat extends AbstractAnimal {
    public Cat(){

    }
    public Cat(String animalName, int animalAge, String animalGender, double purchasingPrice, double sellingPrice){
        super(animalName,animalAge,animalGender,purchasingPrice,sellingPrice);
    }


    @Override
    public String toString() {
        String s="animalName"+super.getAnimalName();
        s=s+"animalAge"+super.getAnimalAge();
        s+="aniamlPurchasingPrice"+super.getPurchasingPrice();
        s+="animalSellingPrice"+super.getSellingPrice();
        s+="animalGender"+super.isAnimalGender();
        return s;
    }
}
