/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: BaseException
 * @description（类描述）: 找不到动物异常类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class BaseException extends RuntimeException {
    protected int code;
    protected String message;

    public BaseException() {
    }

    public BaseException(String s) {
        super(s);
    }

    /**
     * 构造方法传入异常错误枚举类型 方便统一维护
     * @param errorCode
     */
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }
    /**
     * 输出错误类型
     * @param
     */
    public void printError(){
         System.out.println("错误码:"+code);
         System.out.println("错误信息:"+message);
    }

}

