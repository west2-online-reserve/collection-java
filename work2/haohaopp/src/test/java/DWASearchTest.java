import org.example.DWASearch;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DWASearchTest {
    @Test
    void test1()throws IOException {
        DWASearch.main(new String[]{"input.txt"});
    }
    @Test
    void test2()throws IOException {
        DWASearch.main(new String[]{});
    }
    @Test
    void test3()throws IOException {
        DWASearch.main(new String[]{"input.txt","output.txt"});
    }
    @Test
    void test4()throws IOException {
        DWASearch.main(new String[]{"input"});
    }
    @Test
    void test5()throws IOException {
        DWASearch.main(new String[]{"input.txt","output.txt","somethingelse.txt"});
    }
    @Test
    void test6()throws IOException {
        DWASearch.main(new String[]{"input.txt","badout.txt"});
    }
    @Test
    void test7()throws IOException {
        DWASearch.main(new String[]{"badinput.txt","output.txt"});
    }
}
