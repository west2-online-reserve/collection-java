/**
 * @author aozne
 * @date 2023/10/23 21:12
 **/
abstract class Animal{
    //构建变量
    protected String animalName;
    protected int animalAge;
    protected String animalGender;
    protected double animalPrice;
    protected double animalCost;
    protected String animalspecies;
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

    public void setAnimalPrice(double animalPrice) {
        this.animalPrice = animalPrice;
    }

    public abstract double getAnimalPrice();
    protected abstract double getAnimalCost();

}
