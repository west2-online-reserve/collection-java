package animalshop;

/**
 * @author 102301412
 */
abstract public class BaseAnimal {
    protected String petName;
    protected int age;
    protected char gender;
    protected double price;
    protected double purchasePrice;

    public BaseAnimal() {
    }

    public BaseAnimal(String petName, int age, char gender, double price, double purchasePrice) {
        this.petName = petName;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.purchasePrice = purchasePrice;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * 输出动物信息
     */
    @Override
    abstract public String toString();
}

