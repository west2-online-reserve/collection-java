package animalShop;

public class ChineseRuralDog extends Animal{
    private boolean isVaccineInjected;
    public ChineseRuralDog(String name, int age, String sex,boolean isVaccineInjected) {
        super(name,age,sex,100.0);
        this.isVaccineInjected=isVaccineInjected;
    }

    @Override
    public String toString(){
        return "中华田园犬" +
                "名字："+name+"\n"+
                "年龄："+age+"\n"+
                "性别："+sex+"\n"+
                "价格："+price+"\n"+
                "是否注射狂犬病疫苗："+isVaccineInjected+"\n";
    }
}
