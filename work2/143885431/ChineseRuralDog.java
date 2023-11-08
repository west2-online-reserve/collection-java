public class ChineseRuralDog extends Animal{

    protected boolean isVaccineInjected;
    //变量:是否注射狂犬病疫苗
    public ChineseRuralDog(){
    }
    public ChineseRuralDog(String name,int age,String gender,boolean isVaccineInjected){
        super(name, age, gender,100.);
        this.isVaccineInjected = isVaccineInjected;
    }


    public void setIsVaccineInjected(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }
    public boolean grtIsVaccineInjected() {
        return isVaccineInjected;
    }

    //父类toString的实现
    @Override
    public String toString() {
        return "ChineseRuralDog{" +
                "name:" + getName() +
                ",age:" + getAge() +
                ",gender:" + getGender() +
                ",price:" +getPrice() +
                ",isVaccineInjected:" + isVaccineInjected +
                '}';
    }
}
