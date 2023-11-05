public class Bonus {
    public static void main(String[] args) {
        // 测试交替输出数组元素的方法
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        AlternateArrayPrinter printer = new AlternateArrayPrinter(arr1, arr2);
        printer.printAlternately();

        // 测试邮箱格式验证方法
        String email = "test@example.com";
        boolean isValid = EmailValidator.isValidEmail(email);
        System.out.println("\nEmail " + email + " is valid: " + isValid);
    }
}
