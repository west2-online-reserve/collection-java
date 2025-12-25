import java.util.Scanner;
import java.util.regex.Pattern;

public class Regex {
    //正则学习书籍：https://github.com/ziishaned/learn-regex/tree/master/translations
    //合法邮箱格式:本地部分+@+域名部分+.+顶级域名

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入待检测的邮箱");
        String email = sc.nextLine();
        if(isVaildEmail(email)){
            System.out.println("该邮箱格式合格");
        }
        else{
            System.out.println("该邮箱格式不合格");
        }
    }
    //开头部分。匹配 1 个或多个字母、数字、_、- 或 +。保证了邮箱不会以.开头。同时确保不会出现连续的‘.’号
    public static boolean isVaildEmail(String email){
        String regex= "^[a-zA-Z0-9_\\-\\+]+(\\.[a-zA-Z0-9_\\-\\+]+)*@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex,email);
    }
}
