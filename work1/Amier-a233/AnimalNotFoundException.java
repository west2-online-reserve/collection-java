public class AnimalNotFoundException extends RuntimeException {
    // 无参构造方法
    public AnimalNotFoundException() {
        super();
    }

    // 带错误信息的构造方法
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
