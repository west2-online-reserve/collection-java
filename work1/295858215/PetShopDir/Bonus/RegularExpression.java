package Bonus;

public class RegularExpression {
    public static void main(String[] args) {
        System.out.println(checkEmailAddress("295858215@qq.com"));
    }
    public static boolean checkEmailAddress(String email) {
        String regex="^[a-zA-Z0-9_+&*-]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$";
        return email.matches(regex);
    }
}
