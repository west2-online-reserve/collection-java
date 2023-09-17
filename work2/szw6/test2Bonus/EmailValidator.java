package szw.test2Bonus;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        // 使用正则表达式验证邮箱格式
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
    public static void main(String[] args) {
        String email1 = "test@example.com";
        String email2 = "invalid_email";

        System.out.println(email1 + " is valid: " + isValidEmail(email1));
        System.out.println(email2 + " is valid: " + isValidEmail(email2));
    }
}
