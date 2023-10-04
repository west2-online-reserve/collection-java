package shaoxiawjc;

public class InsufficientBalanceException extends RuntimeException{


    String name;
    double money;

    public InsufficientBalanceException (String name,double money){
        this.money = money;
        this.name = name;
    }

    public String toString(){
        return "余额为"+money+"不足以购买"+name;
    }

}
