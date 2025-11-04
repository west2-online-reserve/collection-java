//import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class MyTest {


    public final static String PLAYER_OUTPUT = "D:\\westTwo\\westTowcheck2\\src\\main\\resources\\TestData\\Player.txt";

    public final static String TEST_OUTPUT = "D:\\westTwo\\westTowcheck2\\src\\main\\resources\\TestData\\test.txt";

    public final static String INPUT = "D:\\westTwo\\westTowcheck2\\out\\artifacts\\westTowcheck2_jar\\input.txt";
    @Test
    public void playerTest(){
        Lib.outputPlayers(PLAYER_OUTPUT);
    }

    @Test
    public void test() {
        String[] args = new String[]{INPUT, TEST_OUTPUT};
        Deprecated.processArgs(args);
    }

    @Test
    public void test2() throws IOException {
        RowDataUtil.processRowData();
    }
}
