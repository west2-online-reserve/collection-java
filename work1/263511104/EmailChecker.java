package work1;

import java.util.Scanner;

public class EmailChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String email = new String(input.nextLine());
        System.out.println("Email is valid: " + isValidEmail(email));

    }
    public static boolean isValidEmail(String email){
        //邮箱至少六位+@+至少一位字母/数字+.+至少一位字母/数字
        String regex = "[a-zA-Z0-9_&*$]{6,}@[a-zA-Z0-9]{1,}\\.[a-zA-Z0-9]{1,}";
        return email.matches(regex);
    }
}
