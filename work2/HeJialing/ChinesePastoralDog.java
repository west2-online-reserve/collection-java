package src.src;

public class ChinesePastoralDog extends AbstractAnimal {
    public ChinesePastoralDog() {

    }

    public ChinesePastoralDog(String animalName, int animalAge, String animalGender, double purchasingPrice, double sellingPrice) {
        super(animalName, animalAge, animalGender, purchasingPrice, sellingPrice);
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    private boolean isVaccineInjected;

    @Override
    public String toString() {
        String s = "animalName" + super.getAnimalName();
        s = s + "animalAge" + super.getAnimalAge();
        s += "aniamlPurchasingPrice" + super.getPurchasingPrice();
        s += "animalSellingPrice" + super.getSellingPrice();
        s += "animalGender" + super.isAnimalGender();
        return s;
    }


}
