package animal;

/**
 * @author MR.瑜
 */
public class Cat extends Animal {
    protected String colour;
    public Cat(String name,int age,String gender,double price,String colour){
        super(name,age,gender,200);
        this.colour=colour;
    }

    @Override
    public String toString() {
            return  "Cat{ " + "\n" +
                    "NAME: " + name + "\n" +
                    "Age： " + age + "\n"+
                    "Gender: "+ gender +"\n"+
                    "Price: "+ price+"\n"+
                    "Colour: " +colour +"\n";
    }
}
