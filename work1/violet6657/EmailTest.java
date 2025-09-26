import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class  EmailTest{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String regex = "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+";
        Pattern pattern = Pattern.compile(regex);
        System.out.println("请输入邮箱");
        String email = sc.nextLine();
        Matcher matcher = pattern.matcher(email);
        System.out.println(matcher.matches());
    }
}