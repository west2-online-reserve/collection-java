package Bonus;

public class RegularExpression {
    public static void main(String[] args) {
        System.out.println(checkEmailAddress("114514@qq.com"));
    }
    public static boolean checkEmailAddress(String email) {
        String regex="^[a-zA-Z0-9_+&*-]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$";
        String regex2 = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";//这个？

        return email.matches(regex);
    }
}
