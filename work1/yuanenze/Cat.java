package petStore;

public class Cat extends Animal {

    private static double PRICE = 200;

    public Cat() {
        super();
        this.setName("Cat");
    }

    public Cat(String name, int age, String gender) {
        super(name, age, gender);
    }

    public double getPrice() {
        return PRICE;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("[" + this.getName() + "-" + this.getAge() + "-"
                + "-" + PRICE + "-"
                + this.getGender() + "]");
    }
}
