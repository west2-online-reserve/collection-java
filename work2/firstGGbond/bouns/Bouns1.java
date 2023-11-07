package bouns;

public class Bouns1 {
    public static boolean isEmail(String  email){
        String pattern ="[\\da-zA-Z_]+@[\\da-zA-Z]+\\.[\\da-zA-Z]+";
        return email.matches(pattern);
    }
    public static void main(String[] args) {
        System.out.println(isEmail("1294956375@qq.com"));
        System.out.println(isEmail("73sad55sf@com"));
        System.out.println(isEmail("73sad55sf@1.com"));
        System.out.println(isEmail("fulan1294956375@gmail.com"));
        System.out.println(isEmail("fu___@gmail.com"));
    }
}
