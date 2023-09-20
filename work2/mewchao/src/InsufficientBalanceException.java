/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: InsufficientBalanceException
 * @description（类描述）: 余额不足异常类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class InsufficientBalanceException extends BaseException{
    /**店铺余额**/
    private double balanced;
    /**动物的价格**/
    private double price;
    /**所欠的钱**/
    private double amount;
    public InsufficientBalanceException(double balanced,double price){
        super(ErrorCode.INSUFFICIENT_BALANCE);
        this.balanced=balanced;
        this.price=price;
        this.amount=price-balanced;
    }
    double getPrice(){
        return this.price;
    }
    double getBalanced(){
        return this.balanced;
    }
    /**
     * 输出详细的错误信息
     */
    public void printDetailedError(){
        this.printError();
        System.out.println("动物的价格为:"+price);
        System.out.println("店铺的余额为:"+balanced);
        System.out.println("购买动物还需要的钱为：:"+amount);
    }
}
