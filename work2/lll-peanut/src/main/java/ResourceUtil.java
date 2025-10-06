import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceUtil {

    public static final String fileName = "rowData1";
    /*
        返回值： 相关项目的json信息
        作用： 利用类加载器读取resources里的json文本，并将其用BufferedReader的readLine()方法转化为字符串将其返回。
        好处是在打成jar包的时候不会出现找不到文件的问题。
     */
    public String readFile(String resourcePath)  {
        resourcePath = fileName + "/" + resourcePath;
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
