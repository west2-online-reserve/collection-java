package Bonus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class verifyEmail {
    // 邮箱验证正则表达式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

    // 编译正则表达式
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // 验证邮箱地址的方法
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // 测试示例
    public static void main(String[] args) {
        String[] testEmails = {
                "user@example.com",          // 基本格式
                "user.name@example.com",     // 包含点
                "user+tag@example.com",      // 包含加号
                "user-123@example.co.uk",    // 包含数字和连字符，多级域名
                "user_name@example.io",      // 包含下划线
                ".user@example.com",         // 非法（以点开头）
                "user.@example.com",         // 非法（以点结尾）
                "user@.example.com",         // 非法（域名以点开头）
                "user@example",              // 非法（缺少顶级域名）
                "user@-example.com"          // 非法（域名以连字符开头）
        };

        for (String email : testEmails) {
            System.out.printf("邮箱: %-25s  有效: %b%n", email, isValidEmail(email));
        }
    }
}
