public class ChineseRuralDog extends Animal {
    protected boolean isVaccineInjected;

    public ChineseRuralDog(String theName, int theAge, String theGender, boolean theIsVaccineInjected) {
        super(theName, theAge, theGender, 100);
        isVaccineInjected = theIsVaccineInjected;
    }

    public void setVaccineInjected(boolean theIsVaccineInjected) {
        isVaccineInjected = theIsVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseRuralDog {\n" +
                "\nname : " + '"' + name + '"' +
                "\nage: " + age +
                "\nisVaccineInjected" + isVaccineInjected +
                "\nprice: " + price +
                "\n}";
    }
}
