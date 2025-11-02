package P;

public class Chinesedog extends Animals{
    private boolean isVaccineInjected;

    public  Chinesedog(String name,int age,String gender,boolean isVaccineInjected){
        super(name,age,gender,100);
        this.isVaccineInjected=isVaccineInjected;
    }
    public boolean isVaccineInjected(){
        return isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseDog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", monney=" + monney +
                ", isVaccineInjected=" + isVaccineInjected +
                '}';
    }
}
