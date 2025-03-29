package Bonus_02;

import java.util.regex.Pattern;

public class Test {
    // 正则表达式用于验证邮箱格式
    private static final String EMAIL_REGEX = "^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    // 编译正则表达式为模式
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static void main(String[] args) {
        // 测试案例
        String[] testEmails = {
                "test@example.com",    // 合法
                "user.name@domain.co", // 合法
                "user@sub.domain.com", // 合法
                "invalid-email@",      // 非法
                "user@.com",           // 非法
                "user@domain",         // 非法
                "@domain.com"          // 非法
        };

        for (String email : testEmails) {
            System.out.println(email + " : " + isValidEmail(email));
        }
    }
}
