public class Test {
    public static void main(String[] args) {
        String testEmail1 = "testValid@example001.com";
        String testEmail2 = "test_InvalidEmail@.com";
        String testEmail3 = "001testInvalidEmail@.com";

        EmailValidator emailValidator = new EmailValidator();

        boolean isValid1 = emailValidator.isValidEmail(testEmail1);
        boolean isValid2 = emailValidator.isValidEmail(testEmail2);
        boolean isValid3 = emailValidator.isValidEmail(testEmail3);

        System.out.println(testEmail1+  "  Legitimacy: " +isValid1);
        System.out.println(testEmail2 + "  Legitimacy: " + isValid2);
        System.out.println(testEmail3 + "  Legitimacy: " + isValid3);
    }
}
