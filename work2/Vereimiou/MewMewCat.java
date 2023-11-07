public class MewMewCat extends Animal {
    private boolean isisVaccineInjected;
    public MewMewCat(String theName, int theAge, String theGender, boolean isisVaccineInjected) {
        super(theName, theAge, theGender, 200);
        this.isisVaccineInjected=isisVaccineInjected;
    }

    @Override
    public String toString() {
        return "MewMewCat {" +
                "\nname : " + '"' + name + '"' +
                "\nage: " + age +
                "\ngender: " + '"' + gender + '"' +
                "\nprice: " + price +
                "\n}";
    }
}
