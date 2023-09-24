package szw.test2Bonus;
import java.util.regex.Pattern;
public class EmailValidator {

    public static boolean isValidEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }

    public static void main(String[] args) {
        String email1 = "test@example.com";
        String email2 = "invalid_email";
        String email3="15646485@qq.com";
        System.out.println(email1 + " is valid: " + isValidEmail(email1));
        System.out.println(email2 + " is valid: " + isValidEmail(email2));
        System.out.println(email3 + " is valid: " + isValidEmail(email3));
    }
}
