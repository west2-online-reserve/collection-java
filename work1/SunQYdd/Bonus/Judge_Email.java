package Bonus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Judge_Email {

    public static boolean email_judge(String email) {
        // 上网了解到邮箱可以出现的字符
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
