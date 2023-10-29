/**
 * @author aozne
 * @date 2023/10/23 21:12
 **/
class ChineseRuralDog extends Animal{
    // 是否注射狂犬疫苗
    private boolean isVaccineInjected;

    @Override
    public void setAnimalPrice(double animalPrice) {
        this.animalPrice=animalPrice;
    }
    public void setAnimalCost(double animalCost){
        this.animalCost=animalCost;
    }

    @Override
    public double getAnimalPrice() {
        return  animalPrice;
    }
    @Override
    public double getAnimalCost(){
        return animalPrice;
    }
    @Override
    public String toString() {
        return ("动物名："+animalName+
                "动物年龄："+animalAge+
                "动物性别："+animalGender+
                "动物价格："+animalPrice+
                "动物成本："+animalCost);
    }
    public ChineseRuralDog(String animalName,int animalAge,String animalGender,double animalPrice,double animalCost){
        super(animalName,animalAge,animalGender,animalPrice,animalCost);
    }
}