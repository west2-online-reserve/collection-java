package petShop.animal;


public class Bird extends Animal {
    private static double BIRD_PRICE=300;

    private static double BIRD_PROFIT=70;

    public Bird(String name, int age, boolean gender) {
        super(name, age, gender, BIRD_PRICE, BIRD_PROFIT);
    }

    @Override
    public String toString() {
        return "{bird---"+"name:"+getName() + " age:" + getAge() + " gender:" + getGender() + " price:" + getPrice() + " id:" + getId()+"}";
    }
}
