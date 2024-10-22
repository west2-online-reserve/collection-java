package 恋与宠物店;

/**
 * 动物未找到异常类的创建
 *
 * @author xumostar
 * @date 2024/10/22
 */

class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException(String errorMessage){
        super(errorMessage);
    }
}
