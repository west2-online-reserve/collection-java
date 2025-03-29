package PetShop;
//动物类
public abstract class Animal {
    protected String animalName;
    protected String name;
    protected String sex;
    protected int age;
    protected double purchasePrice;
    protected double sellPrice;

    public abstract String toString();

    public Animal() {}

    public Animal(String animalName, String name, String sex, int age, double purchasePrice, double sellPrice) {
        this.animalName = animalName;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
    }

    public String getAnimalName () {
        return animalName;
    }

    public void setAnimalName (String AnimalName) {
        this.animalName = AnimalName;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSex () {
        return sex;
    }

    public void setSex (String sex) {
        this.sex = sex;
    }

    public int getAge () {
        return age;
    }

    public void setAge (int age) {
        this.age = age;
    }

    public double getPurchasePrice () {
        return purchasePrice;
    }

    public void setPurchasePrice (double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellPrice () {
        return sellPrice;
    }

    public void setSellPrice (double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
