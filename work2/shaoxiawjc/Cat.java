package shaoxiawjc;

public class Cat extends Animal{

    public Cat(String n, int a, String s, double p){
        super(n,a,s,p);
    }



    public String toString(){
        return "name is "+name+
                "age is "+age+
                "sex is "+sex+
                "price is"+price;
    }




}
