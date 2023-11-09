/**
 * 此类用于运用正则表达式判断邮件是否合格
 * @author 1293978818
 */
public class Bonus2 {
    public static void main(String[] args) {

        String emailregex = "[\\w]+[@][\\w&&[^_]]+(\\.[a-z]{2,3})+";

        //以下为测试样例
        System.out.println("1293978818@qq.com".matches(emailregex));  //true
        System.out.println("1293978818qq.com".matches(emailregex));   //false
        System.out.println("1293978818@q_q.com".matches(emailregex));//false
        System.out.println("1293978818qq".matches(emailregex));//false
        System.out.println("1293978818@qq.com.cn".matches(emailregex));//true
    }
}
