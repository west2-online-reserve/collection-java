package work2;

class Cat extends Animals {

    public Cat(String name, int age, String gender, double price) {
        super(name, age, gender, price);
        this.price = 200;
    }

    public Cat() {

    }


    @Override
    public String toString() {
        return "Cat:" + name +
                ", gender:" + gender +
                ", age:" + age +
                ", price:" + price + "\n";
    }
}
