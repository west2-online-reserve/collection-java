package work1;

public class ChineseVillageDogs extends Animal{
    private double price=100.0;
    private  boolean isVaccineInjected;
    public double getPrice() {
        return price;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }


    public ChineseVillageDogs(String name, int age, String sex,  boolean isVaccineInjected) {
        super(name, age, sex);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "宠物名："+getName()+" "+"年龄："+getAge()+" "+"性别："+getSex()+"价格："+getPrice()+" "+"注射疫苗情况: "+isVaccineInjected;
    }


}
