class LyingCat extends Animal {
    public LyingCat() {
        super("LyingCat", 3, "girl", 200);
        this.name = "LyingCat";
        this.price = 200;
    }

    public LyingCat(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return "LyingCat{" + "name='" + name + ", age=" + age + ", gender=" + gender + ", price=" + price + '}';
    }
}
