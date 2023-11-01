public class dog extends Animal{
    private boolean isVaccineInjected;
    public dog(String name,int age,String sex,String species,boolean isVaccineInjected) {
        super(name,age,sex,100,species);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "\nname:"+name+" age:"+age+" sex:"+sex+" price:"+100+" species: "+species+" isVaccineInjected:"+isVaccineInjected;

   }

}
