package CongWork2;

public class ChineseDog extends Animals{
    boolean isVaccineInjected;

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    public ChineseDog(String name, int age, String gender, double price, boolean isVaccineInjected){
        super(name,age,gender,price);
        this.isVaccineInjected=isVaccineInjected;
    }

    @Override
    public String toString() {
        return "宠物名："+getName()+" "+"年龄："+getAge()+" "+"性别："+getGender()+"售价："+getPrice()+" "+"注射疫苗情况"+isVaccineInjected;
    }
}
