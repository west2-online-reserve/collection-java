package java_work2;

/**
 * AnimalNotFoundException异常处理类
 *
 * @author formrc
 * @date 2023/10/25
 */
class AnimalNotFoundException extends  RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}