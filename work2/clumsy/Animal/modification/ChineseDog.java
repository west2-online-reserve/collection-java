package modification;

public class ChineseDog extends Animal {
    public ChineseDog(String name, int age, String gender){
        super(name, age, gender,100);
    }
    private boolean isVaccineInjected;
    public ChineseDog() {}

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "中华田园犬：名字是"+name+"\n" +
                "年龄是"+age+"\n" +
                "性别是"+gender+"\n" +
                "售卖价格是"+price*2+"\n" +
                "注射疫苗"+isVaccineInjected+"\n" +
                "__________________";
    }

    @Override//重载方法来区分进价和售价
    public String toString(String mi) {
        return "中华田园犬：名字是"+name+"\n" +
                "年龄是"+age+"\n" +
                "性别是"+gender+"\n" +
                "进货价格是"+price+"\n" +
                "注射疫苗"+isVaccineInjected+"\n" +
                "__________________";
    }
}
