/**
 * @author sjy176
 */
public class AnimalNotFountException extends RuntimeException{

    Customer c;
    int a;

    public AnimalNotFountException(int a, Customer c){
        this.a =a;
        this.c = c;
    }
    
    @Override
    public String toString(){
        return "不好意思"+c.getName()+"，动物卖完了"+"\n";
    }


}
