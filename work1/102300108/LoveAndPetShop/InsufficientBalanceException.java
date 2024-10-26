package LoveAndPetShop;

/**
 * 余额不足异常类的创建
 *
 * @author xumostar
 * @date 2024/10/26
 */
class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String errorMessage){
        super(errorMessage);
    }
}