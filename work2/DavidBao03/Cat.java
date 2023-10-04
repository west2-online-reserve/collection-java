class Cat extends Animal {
    protected boolean isFat;

    public Cat(String name, int age, String gender, boolean isFat) {
        super(name, age, gender, 200);
        this.isFat = isFat;
    }

    public boolean isFat() {
        return isFat;
    }

    public void setFat(boolean fat) {
        isFat = fat;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "isFat=" + isFat +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}
