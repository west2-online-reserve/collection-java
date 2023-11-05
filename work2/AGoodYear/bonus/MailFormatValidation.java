/**
 * 使用正则表达式判断邮箱格式是否合法
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public class MailFormatValidation {

    public static void main(String[] args) {
        MailFormatValidation m = new MailFormatValidation();
        m.formatValidation("12345678@163.com");
        m.formatValidation("abcdefg@126.com");
        m.formatValidation("a1b2C3D4@qq.com");
        m.formatValidation("T5s65@fzu.edu.cn");
        m.formatValidation("www.baidu.com");
        m.formatValidation("www@11.");
    }

    public void formatValidation(String mail) {
        // 最后的“\\.?[a-zA-Z]*”用来防止邮箱域名为多段，如fzu.edu.cn
        boolean legal = mail.matches("[\\w]+@[\\w]+\\.[a-zA-Z]+\\.?[a-zA-Z]*");
        if (legal) {
            System.out.println("邮箱格式合法");
        } else {
            System.out.println("邮箱格式非法");
        }
    }
}
