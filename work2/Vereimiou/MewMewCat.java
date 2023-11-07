public class MewMewCat extends Animal {
    public MewMewCat(String theName, int theAge, String theGender) {
        super(theName, theAge, theGender, 200);
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
