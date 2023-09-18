public class Cat extends Animal{
    double catPrice = super.price;//Access the price property of the parent class via the super keyword
    public Cat(String name,int age,double price,boolean gender){
        super(name,age,200,gender);
    }

    @Override
    public String toString(){
        return "";
    }
    public double getPrice(){
        return this.price;
    }
}
