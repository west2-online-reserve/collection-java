class Cat extends Animal{
    public Cat() {
        super();
        this.name="Cat";
        this.price=200;
    }

    public Cat(String name, int age, boolean sex, double price) {
        super(name, age, sex, price);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", price=" + price +
                '}';
    }
}
