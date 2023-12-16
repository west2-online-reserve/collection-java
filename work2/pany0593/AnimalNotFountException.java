/**
 * 没找到动物异常，店内没有动物可买时抛出
 *
 * @author pany0593
 * @date 2023/11/5
 */
public class AnimalNotFountException extends RuntimeException {
    private String message;

    AnimalNotFountException(String message) {
        super(message);
        this.message = message;
    }
}
