class ChineseRuralDog extends Animal{
    // 是否注射狂犬疫苗
    private boolean isVaccineInjected;
    @Override
    public double getAnimalPrice() {
        return 100;
    }
    @Override
    public double getAnimalCost(){
        return 50;
    }
    @Override
    public String toString() {
        return ("动物名："+animalName+
                "动物年龄："+animalAge+
                "动物性别："+animalGender+
                "动物价格："+animalPrice+
                "动物成本："+animalCost);
    }
    public ChineseRuralDog(String animalName,int animalAge,String animalGender,double animalPrice,double animalCost,String animalspecies){
        super(animalName,animalAge,animalGender,animalPrice,animalCost,animalspecies);
    }
}