package AnimalShop;

public class GoldenRetriever extends Animal {

    private boolean isFat;

    public GoldenRetriever(String name, int age, String gender, boolean isFat) {
        super(name, age, gender, 300);
        this.isFat = isFat;
    }

    public void setFat(boolean isFat) {
        this.isFat = isFat;
    }

    public boolean getFat() {
        return isFat;
    }

    @Override
    public String toString() {
        return "GoldenRetriever{" + "name=" + name + ", age=" + age + ", gender=" + gender + ", price=" + price + "}";
    }
}
