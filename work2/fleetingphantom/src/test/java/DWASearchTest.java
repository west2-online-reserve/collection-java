import DWASearch.DWASearch;
import org.junit.Test;

public class DWASearchTest {
    @Test
    public void test1(){
        //测试无参数
        try {
            DWASearch.main(new String[]{});
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void test2(){
        //测试参数为空
        try {
            DWASearch.main(new String[]{" "," "});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        //测试参数错误(无前txt)
        try {
            DWASearch.main(new String[]{"input","output.txt"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        //测试参数错误(无后txt)
        try {
            DWASearch.main(new String[]{"input.txt","output"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        //测试参数正确
        try {
            DWASearch.main(new String[]{"input.txt","output.txt"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test6(){
        //测试参数错误(少参数)
        try {
            DWASearch.main(new String[]{"input.txt"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test7(){
        //测试参数错误(少参数)
        try {
            DWASearch.main(new String[]{"output.txt"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test8(){
        //测试参数错误(多参数)
        try {
            DWASearch.main(new String[]{"input.txt","output.txt","aaa.txt"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test9(){
        //测试参数错误(多参数)
        try {
            DWASearch.main(new String[]{"input.txt","output.txt","aaa.txt","bbb.txt"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test10(){
        //测试参数错误
        try {
            DWASearch.main(new String[]{"input.txt","output.t"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
