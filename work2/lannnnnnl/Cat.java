package westwork2;

public class Cat extends Animal {
    
    public Cat(String name,int age,String gender,double price){
        super(name,age,gender,200);
      
    }

    @Override
    public String toString() {
            return  "Cat{ " + "\n" +
                    "NAME: " + name + "\n" +
                    "Age： " + age + "\n"+
                    "Gender: "+ gender +"\n"+
                    "Price: "+ price+"\n";
    }
}