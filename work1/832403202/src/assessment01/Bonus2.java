package assessment01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bonus2 {
    // 邮箱格式正则表达式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*\\.[a-zA-Z]{2,63}$";

    // 预编译正则表达式（提升性能）
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * 判断邮箱格式是否合法
     * @param email 待验证的邮箱字符串
     * @return 合法返回true，否则返回false（null直接返回false）
     */
    public static boolean isValidEmail(String email) {
        // 处理null输入
        if (email == null) {
            return false;
        }
        // 匹配正则表达式
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // 测试方法
    public static void main(String[] args) {
        // 测试案例：合法邮箱
        String[] validEmails = {
                "test@example.com",
                "user.name+tag@domain.co.uk",
                "user-name_123@sub.domain.cn",
                "a.b+c@x.y.z"
        };
        // 测试案例：非法邮箱
        String[] invalidEmails = {
                "invalid-email", // 缺少@
                "invalid@.com", // 域名以.开头
                ".user@example.com", // 本地部分以.开头
                "user@domain..com", // 域名包含连续.
                "user@domain.c", // 顶级域名长度不足（仅1个字符）
                "user#name@example.com", // 本地部分包含非法字符#
                "user@-domain.com" // 子域名以-开头
        };

        // 验证合法邮箱
        System.out.println("合法邮箱测试：");
        for (String email : validEmails) {
            System.out.println(email + " -> " + isValidEmail(email));
        }

        // 验证非法邮箱
        System.out.println("\n非法邮箱测试：");
        for (String email : invalidEmails) {
            System.out.println(email + " -> " + isValidEmail(email));
        }
    }
}
