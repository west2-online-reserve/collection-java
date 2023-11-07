import java.util.regex.Pattern;

public class EmailCheck {
    public static boolean checkEmail(String email){
        return Pattern.matches("^[A-Za-z0-9]+([-._][A-Za-z0-9]+)*@[A-Za-z0-9]+(-[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6}|[A-Za-z]{2,4}\\.[A-Za-z]{2,3})$",email);
    }
    public static void main(String[] args){
        String email1="12345678@qq.com";
        if (checkEmail(email1)){
            System.out.println("邮箱 "+email1+" 格式正确");
        }
        else {
            System.out.println("邮箱 "+email1+" 格式错误");
        }
        String email2="1";
        if (checkEmail(email2)){
            System.out.println("邮箱 "+email2+" 格式正确");
        }
        else {
            System.out.println("邮箱 "+email2+" 格式错误");
        }
        String email3="";
        if (checkEmail(email3)){
            System.out.println("邮箱 "+email3+" 格式正确");
        }
        else {
            System.out.println("邮箱 "+email3+" 格式错误");
        }
    }
}
