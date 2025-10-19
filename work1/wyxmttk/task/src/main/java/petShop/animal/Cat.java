package petShop.animal;

public class Cat extends Animal {

    private static double CAT_PRICE=200;

    private static double CAT_PROFIT=50;

    public Cat(String name, int age, boolean gender) {
        super(name, age, gender, CAT_PRICE,CAT_PROFIT);
    }

    @Override
    public String toString() {
        return "{cat---"+"name:"+getName() + " age:" + getAge() + " gender:" + getGender() + " price:" + getPrice() + " id:" + getId()+"}";
    }
}
