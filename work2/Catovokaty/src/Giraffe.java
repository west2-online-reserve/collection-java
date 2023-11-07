class Giraffe extends Animal {
    public Giraffe() {
        super("Giraffe", 1, "girl", 1000);
        this.name = "Giraffe";
        this.price = 1000;
    }

    public Giraffe(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return "Giraffe{" + "name=" + name + ",age=" + age + ", gender=" + gender + ", price=" + price + '}';
    }
}
