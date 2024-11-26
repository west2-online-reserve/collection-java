import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}
