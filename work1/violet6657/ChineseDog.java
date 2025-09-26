public class ChineseDog extends Animal {
    protected boolean isVaccineInjected;
    static final double CHINESEDOG_PURCHASE_PRICE=100;
    public ChineseDog(){}

    @Override
    public String toString() {
        String vaccineName;
        if(isVaccineInjected){
            vaccineName="已打狂犬疫苗";
        } else {
            vaccineName="未打狂犬疫苗";
        }
        return("该中华田园犬名为"+getName()+"  年龄:"+getAge()
                +"  性别为"+getGender()+"  体重为"+getWeight()+"  价格为"+getPrice()+vaccineName);
    }

    public ChineseDog(String name,int age,boolean isVaccineInjected,int weight,String gender,double price){
        super(name,age,gender,price, weight);
        this.isVaccineInjected=isVaccineInjected;
    }
}
