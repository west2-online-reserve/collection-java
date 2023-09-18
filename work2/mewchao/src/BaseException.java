/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: BaseException
 * @description（类描述）: 找不到动物异常类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class BaseException extends RuntimeException {
    private int code;
    private String message;

    public BaseException() {
    }

    public BaseException(String s) {
        super(s);
    }

    /**
     * 可以直接抛出的异常
     * @param errorCode
     */
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 可以对message进行扩展
     * @param errorCode
     * @param message
     */
    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
        this.message = String.format(errorCode.getMessage(), message);
    }
}

