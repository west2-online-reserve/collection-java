public class ChineseRuralDog extends Animal{
    private boolean isVaccineInjected;
    public ChineseRuralDog(String name, int age, double price, boolean isVaccineInjected){
        super(name, age, price);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseRuralDog{"+
                "name='"+name+'\''+
                ",age='"+age+'\''+
                ",price='"+price+'\''+
                ",isVaccineInjected='"+isVaccineInjected+"'}";
    }
}

