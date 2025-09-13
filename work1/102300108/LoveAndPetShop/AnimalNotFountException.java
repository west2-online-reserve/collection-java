package LoveAndPetShop;

/**
 * 动物未找到异常类的创建
 *
 * @author xumostar
 * @date 2024/10/26
 */

class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException(String errorMessage){
        super(errorMessage);
    }
}
