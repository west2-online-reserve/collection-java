abstract class Animal{
    //构建变量
    protected String animalName;
    protected int animalAge;
    protected String animalGender;
    protected double animalPrice;
    protected double animalCost;
    protected String animalspecies;
    //全参的构造方法
    protected Animal(String animalName,int animalAge,String animalGender,double animalPrice,Double animalCost,String animalspecies){
        this.animalAge=animalAge;
        this.animalName=animalName;
        this.animalGender=animalGender;
        this.animalPrice=animalPrice;
        this.animalCost=animalCost;
        this.animalspecies=animalspecies;
    }
    public abstract String toString();
    public abstract double getAnimalPrice();
    protected abstract double getAnimalCost();

}