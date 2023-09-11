package shaoxiawjc;

public class Dog extends Animal{
   boolean isVaccineInjected = true;

   public  Dog(String n, int a, String s, double p){
       super(n,a,s,p);
   }

    public String toString(){
        return "name is "+name+
                "age is "+age+
                "sex is "+sex+
                "price is"+price+
                "isVaccineInjected?"+isVaccineInjected;
    }

}
