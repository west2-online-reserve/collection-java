
abstract class Animal{
    //构建变量
    protected String animalName;
    protected int animalAge;
    protected String animalGender;
    protected double animalPrice;
    protected double animalCost;
    // 全参的构造方法
    protected Animal(String animalName,int animalAge,String animalGender,double animalPrice,Double animalCost){
        this.animalAge=animalAge;
        this.animalName=animalName;
        this.animalGender=animalGender;
        this.animalPrice=animalPrice;
        this.animalCost=animalCost;
    }
    // 抽象的tostring()方法
    @Override
    public abstract String toString();

    public abstract void setAnimalPrice(double animalPrice);

    public abstract double getAnimalPrice();
    public abstract double getAnimalCost();

}
