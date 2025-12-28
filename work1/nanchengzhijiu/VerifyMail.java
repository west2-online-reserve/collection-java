import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyMail {
    public static void main(String[] args) {
        if (verifyMail("1638420547@qq.com")) {
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }
    }

    private static boolean verifyMail(String mail) {
        // Java 正则表达式不需要斜杠分隔符
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(pattern, mail);
    }
}