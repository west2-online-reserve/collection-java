package util;
import java.util.Random;
public class EmailUtil {
    //判断邮箱格式
    public static boolean judgeEmailFormat(String email){
        String regex = "@fzu\\.edu\\.cn$";
        if (email.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }
    //生成验证码
    public static String createVerificationCode(){
        String result=new String();
        Random random = new Random();
        for(int i=0;i<6;i++){
            int randomNumber = random.nextInt(10); // 生成0到9之间的随机整数
            result=result+randomNumber;
        }
        return result;
    }
    //测试
    public static void main(String[] args){
        String result=createVerificationCode();
        System.out.println(result);
    }
}