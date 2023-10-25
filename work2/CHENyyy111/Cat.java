package AnimalShop;

public class Cat extends Animal {

    protected boolean isLazy;

    public Cat(String name, int age, String gender, boolean isLazy) {
        super(name, age, gender, 200);
        this.isLazy = isLazy;
    }

    public void setIsLazy(boolean isLazy) {
        this.isLazy = isLazy;
    }

    public boolean getIsLazy() {
        return isLazy;
    }

    @Override
    public String toString() {
        return "Cat{" + "name=" + name + ", age=" + age + ", gender=" + gender + ", price=" + price + ", isLazy=" + isLazy + "}";
    }
}
