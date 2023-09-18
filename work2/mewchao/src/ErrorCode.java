public enum ErrorCode {
    /**
     * 错误码类
     */
    NOT_LOGIN(300, "用户未登录"),
    TOKEN_ERROR(301, "无效的TOKEN"),
    AUTH_ERROR(302, "没有权限"),
    PARAM_ERROR(400, "参数错误"),
    BIZ_ERROR(500, "业务异常"),
    BIZ_ERROR_OTHER(501, "业务异常:%s"),
    UNKNOWN_ERROR(600, "未知错误"),

    /**
     * 用户中心业务异常
     */
    USER_NOT_EXIST(1000, "用户不存在"),
    PASSWORD_ERROR(1001, "密码错误"),

    /**
     * 博客中心业务异常
     */
    ARTICLE_NOT_EXIST(2000, "文章不存在"),
    ;

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
