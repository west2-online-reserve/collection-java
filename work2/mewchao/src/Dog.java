public class Dog extends Animal {
    boolean sVaccineInjected = false;//没有注射狂犬疫苗
    double dogPrice = super.price;//Access the price property of the parent class via the super keyword

    //For any class constructor, the first line of statement must be the constructor that calls the parent class
    public Dog(String name, int age, double price, boolean gender) {
        super(name, age, 100, gender);
    }

    @Override
    public String toString() {
        return "";
    }
    public double getPrice(){
        return this.price;
    }


}
