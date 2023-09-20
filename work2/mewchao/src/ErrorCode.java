/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: ErrorCode
 * @description（类描述）: 异常枚举类定义 封装错误信息和错误码
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-20
 */
public enum ErrorCode {
    INSUFFICIENT_BALANCE(400, "宠物店的余额不足!"),
    NOTFOUND_ANIMAL(501, "找不到动物!");

    private final int code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
