import java.util.regex.Pattern;

public class Bonus2 {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);

        boolean isValid = pattern.matcher(email).matches();

        return isValid;
    }

    public static void main(String[] args) {
        String[] emails = {"2487889191@qq.com", "44341212wa.wand@qq.com", "user123@example", "user@qq..com", "user@-.163.com"};

        for (String email : emails) {
            boolean isValid = isValidEmail(email);
            System.out.println(isValid);
        }
    }
}
