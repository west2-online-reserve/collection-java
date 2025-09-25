package test;
import JDBC.JdbcUtils;
import java.util.Scanner;

public class TestExecuteUpdate {
    public static void main(String[] args) {
        String sql = null;
        Scanner sc = new Scanner(System.in);

        Object[] params = null;

        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
            sql = sc.nextLine();
            if (sql.equals("exit"))
                break;
            System.out.println("Enter parameters:");
            params = new Object[2];
            //第一个
            if (sc.hasNextLine()){
                params[0] = sc.nextLine();
            }
            //第二个
            if (sc.hasNextLine()){
                params[1] = sc.nextLine();
            }
            int i= JdbcUtils.executeUpdate(sql,params);
            if (i > 0)
                System.out.println("Update success!");
        }
        sc.close();
    }
}
