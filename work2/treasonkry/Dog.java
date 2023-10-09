public class Dog extends Animal{
    protected boolean isVaccineInjected;

    public boolean getVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        this.isVaccineInjected = vaccineInjected;
    }

    public double getPrice(){
        return 100;
    }






    public String toString(){
        return "Dog:"+"\n"+"name:"+name+"\n"+"age:"+age+"\n"+"sex"+sex+"\n"+"price:100"+"\n"+"isVaccineInjected:"+isVaccineInjected;
    }
    

}
