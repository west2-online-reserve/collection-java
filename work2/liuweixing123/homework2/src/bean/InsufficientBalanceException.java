package bean;
//宠物店买宠物余额不足异常类
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException() {
    }

    public void showProblem(double balance,Animal animal) {
        if (animal instanceof ChineseRuralDog d) {
            System.out.println("余额还剩"+balance+" 中华田园犬的价格为100元还差"+(d.getPrice()-balance)+"元购买");
        } else if(animal instanceof Cat c){
            System.out.println("余额还剩"+balance+" 小猫的价格为200元还差"+(c.getPrice()-balance)+"元购买");
        }
    }
}
