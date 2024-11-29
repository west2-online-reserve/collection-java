package DWA_Search;
import DWA_Search.DWASearch;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DWASearchTest {


    @Test
    public void test1()throws IOException{
        //正确的参数输入
        DWASearch.main(new String[]{"input.txt","output.txt"});
    }
    @Test
    public void test2()throws IOException{
        //错误的参数个数输入
        DWASearch.main(new String[]{"input.txt"});
    }
    @Test
    public void test3()throws IOException{
        //错误的参数个数输入
        DWASearch.main(new String[]{"output.txt"});
    }
    @Test
    public void test4()throws IOException{
        //错误的参数格式输入
        DWASearch.main(new String[]{"output"});
    }
    @Test
    public void test5()throws IOException{
        //错误的参数格式输入
        DWASearch.main(new String[]{"input.txt","Output.txt"});
    }
    @Test
    public void test6()throws IOException{
        //错误的参数格式输入
        DWASearch.main(new String[]{"Input.txt","output.txt"});
    }
        @Test
        public void test7()throws IOException{
            //错误的参数个数输入
            DWASearch.main(new String[]{"input.txt","output.txt,other.txt"});
        }
    @Test
    public void test8()throws IOException{
        //错误的参数格式输入
        DWASearch.main(new String[]{"input.txt","output"});
    }
    @Test
    public void test9()throws IOException{
        //错误的参数格式输入
        DWASearch.main(new String[]{"input","output.txt"});
    }
    @Test
    public void test10()throws IOException{
        //错误的参数个数和格式输入
        DWASearch.main(new String[]{"input.txt","output.txt,other"});
    }

}