package petStore;

public class Rabbit extends Animal {
    private static double PRICE = 50;

    public Rabbit() {
        super();
        this.setName("Rabbit");

    }

    public Rabbit(String name, int age, String gender) {
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
