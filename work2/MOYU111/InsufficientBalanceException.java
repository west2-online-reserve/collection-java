public class InsufficientBalanceException extends Throwable {
    private String name;
    private double money;

    public InsufficientBalanceException (String name,double money){
        this.money = money;
        this.name = name;
    }
    @Override
    public String toString(){
        return "商店余额为"+money+"\n不足以购买"+name+"\n";
    }
}
