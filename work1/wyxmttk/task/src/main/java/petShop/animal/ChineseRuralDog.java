package petShop.animal;

public class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;

    private static double DOG_PRICE=100;

    private static double DOG_PROFIT=30;

    public ChineseRuralDog(String name, int age, boolean gender) {
        super(name, age, gender, DOG_PRICE,DOG_PROFIT);
    }

    public void injectVaccine(){
        isVaccineInjected=true;
    }

    @Override
    public String toString() {
        return "{dog---"+"name:"+getName() + " age:" + getAge() + " gender:" + getGender() + " price:" + getPrice()+ " isVaccineInjected:" + isVaccineInjected + " id:" +getId()+"}";
    }
}
