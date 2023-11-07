public abstract class Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;

    public Animal(String theName, int theAge, String theGender, double thePrice) {
        name = theName;
        age = theAge;
        gender = theGender;
        price = thePrice;
    }

    public abstract String toString();

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
