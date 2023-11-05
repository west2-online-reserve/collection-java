package work2;

class Cat extends Animals {

    public Cat(String name, int age, String gender, double price) {
        super(name, age, gender, price);
        this.setPrice(200);
    }

    public Cat() {

    }


    @Override
    public String toString() {
        return "Cat:" + getName() +
                ", gender:" + getGender() +
                ", age:" + getAge() +
                ", price:" + getPrice() + "\n";
    }
}
