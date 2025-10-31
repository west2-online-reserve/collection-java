import com.github.lpx.email_demo.Email;

public class test {
    public static void main(String[] args) {
        System.out.println(Email.CheckEmail("aq@1dsade_fdf.cyyb"));
        System.out.println(Email.CheckEmail(".aq@1dsade_fdf.cyb"));
        System.out.println(Email.CheckEmail("a...q@1dsade_fdf.yyb"));
        System.out.println(Email.CheckEmail("aq@@1dsade_fdf.cyb"));
        System.out.println(Email.CheckEmail("aq@1dsa_fdf.c"));
        System.out.println(Email.CheckEmail("attq.@1dsade_fdf.cyyb"));
    }
}
