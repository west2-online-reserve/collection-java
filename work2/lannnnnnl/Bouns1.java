package westwork2;

public class Bouns1 {
    public static boolean isEmail(String  email){
        String pattern ="[\\da-zA-Z_]+@[\\da-zA-Z]+\\.[\\da-zA-Z]+";
        return email.matches(pattern);
    }
    public static void main(String[] args) {
        System.out.println(isEmail("129495637500@qq.com"));
        System.out.println(isEmail("7384sad55sf@com"));
        System.out.println(isEmail("7384sad55sf@1.com"));
        System.out.println(isEmail("lan1294956375@gmail.com"));
        System.out.println(isEmail("fzu___@gmail.com"));
    }
}