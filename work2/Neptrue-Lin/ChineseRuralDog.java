public class ChineseRuralDog extends Animal {
    /*
     * [Oxford dictionary] inject: drive or force (a liquid, especially a drug or vaccine) into a person or animal's body with a syringe or similar device.
     * Commonly-used expressions: inject <sth.> into <sb.>/<sth.> & inject <sb.>/<sth.> with <sth.>
     * i.e. Insulin was injected into the muscle.
     * Instead of saying "The dog has been injected vaccine.", you might use this expression: "The dog has been vaccinated."
     */
    //BTW, cats are also required to be regularly vaccinated against rabies.
    protected boolean isVaccinated;

    public ChineseRuralDog(String name, int age, boolean sex, boolean isVaccinated) {
        super(name, age, sex, 100);
        this.setVaccinated(isVaccinated);
    }

    public boolean isVaccinated() {
        return this.isVaccinated;
    }

    public void setVaccinated(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    @Override
    public String toString() {
        return "This is Chinese rural dog! " +
                "Age: " + this.getAge() + ", " +
                "Sex: " + this.getSex() + ", " +
                "Is Vaccinated: " + this.isVaccinated() + ", " +
                "Price: " + this.getPrice();
    }
}
