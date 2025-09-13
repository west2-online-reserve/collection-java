public class ChineseRuralDog extends Animal{

    private boolean isVaccineInjected;
    
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
