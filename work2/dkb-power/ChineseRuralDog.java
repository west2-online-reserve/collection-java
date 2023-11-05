public class ChineseRuralDog extends Animal{
    private boolean isVaccineInjected;
    public final static String species = "中华田园犬";

    public ChineseRuralDog(String name, int age, String gender, double price, boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }


    @Override
    public String toString() {
        return "小狗姓名"+getName()+" 年龄"+getAge()+" 性别"+getGender()+" 价格"+getPrice()+" 疫苗注射情况" +isVaccineInjected;
    }
}
