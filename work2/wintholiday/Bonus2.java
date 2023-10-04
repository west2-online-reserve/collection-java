package work2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bonus2 {
    protected static final String EmailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EmailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String email1 = "111@163.com";
        boolean isValid = isValidEmail(email1);
        System.out.println(email1+"邮箱是否合法: " + isValid);
        String email2 = "111";
        boolean isValid2 = isValidEmail(email2);
        System.out.println(email2+"邮箱是否合法: " + isValid2);
    }
}
