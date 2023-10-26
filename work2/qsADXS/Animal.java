public abstract class Animal {
    protected String name;
    protected int age;
    protected int gender;//0代表雌性，1代表雄性，2代表被嘎蛋的雄性

    protected double price;//售价
    protected double purchasingCost;//进价


    public Animal(String name, int age, int gender, double price, double purchasingCost) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.purchasingCost = purchasingCost;
    }
    public abstract String toString();
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public double getPrice() {
        return price;
    }

    public double getPurchasingCost() {
        return purchasingCost;
    }
}
