package westwork2;

public class ChineseRuralDog extends Animal{
    private boolean vaccination;
    public ChineseRuralDog(String name,int age,String gender,double price,boolean vaccination) {
        super(name,age,gender,100);
        this.vaccination = vaccination;
    }
    public boolean isVaccination() {
        return vaccination;
    }
    public void setVaccination(boolean vaccination) {
        this.vaccination = vaccination;
    }

    @Override
    public String toString() {
        return  "ChineseRuralDog{ " + "\n" +
                "NAME: " + name + "\n" +
                "Age： " + age + "\n"+
                "Gender: "+ gender +"\n"+
                "Price: "+ price+"\n"+
                "Vaccination: "+ vaccination+"\n";
    }
}
