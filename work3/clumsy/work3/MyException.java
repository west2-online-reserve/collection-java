package JDBC.orderManagemaentSystem;

/**
 * 自定义异常
 *
 * @Author 31445
 * @Date 2023/11/23
 */
public class MyException extends Exception{
    public MyException() {
    }
    public MyException(String s){
        super(s);
    }
}
