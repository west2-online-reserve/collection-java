package 恋与宠物店;
/**
 * 余额不足异常类的创建
 *
 * @author xumostar
 * @date 2024/10/22
 */
class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String errorMessage){
        super(errorMessage);
    }
}