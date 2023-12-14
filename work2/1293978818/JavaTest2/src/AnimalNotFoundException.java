/**
 * 没找到动物异常
 * @author 1293978818
 */
public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message){
        super(message);
    }
}
