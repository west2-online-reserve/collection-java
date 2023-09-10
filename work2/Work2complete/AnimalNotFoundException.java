package Work2complete;

public class AnimalNotFoundException extends RuntimeException{

    Customer c;
    int a;

    public AnimalNotFoundException(int a,Customer c){
        this.a =a;
        this.c = c;
    }

    public String toString(){
        return "对不起"+c.getName()+"没动物卖了";
    }


}
