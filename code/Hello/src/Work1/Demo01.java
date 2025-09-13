package Work1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("Hello\\fos.txt");
        fos.write(97);
    }
}
