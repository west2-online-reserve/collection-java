class Rabbit extends Animal{
    public Rabbit() {
        super();
        this.name="Rabbit";
        this.price=150;
    }

    public Rabbit(String name, int age, boolean sex, double price) {
        super(name, age, sex, price);
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", price=" + price +
                '}';
    }
}