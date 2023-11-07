import java.util.Scanner;
public class EmailValidity {
    static boolean judge(String mailAddress) {
        /*由于不同类型电子邮箱的用户名要求、域名不同，此函数被设计为仅从以下方面审查邮箱的合法性：
         * 是否符合邮箱通用格式“username@domain.com”
         * 用户名是否仅包含数字、字母和下划线
         * 用户名长度是否在6~16个字符之间
         * 域名是否仅包含小写字母或数字
         * 域名是否以“.com”结尾（教育邮箱等其他类型邮箱将被视为非法）
         */
        return mailAddress.matches("\\w{6,16}@[a-z0-9]+\\.com");
    }
    public static void main(String[] argv) {
        Scanner scanner=new Scanner(System.in);
        String mailAddress=scanner.nextLine();
        if(judge(mailAddress)) {
            System.out.println("Valid email address!");
        }
        else {
            System.out.println("Invalid email address!");
        }
        scanner.close();
    }
}
