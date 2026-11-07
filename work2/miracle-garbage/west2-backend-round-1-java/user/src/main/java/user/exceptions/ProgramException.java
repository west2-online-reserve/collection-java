package user.exceptions;

public class ProgramException extends RuntimeException {
    // 出现此异常,程序终止

    public ProgramException(String message) {
        super(message);
    }
}
