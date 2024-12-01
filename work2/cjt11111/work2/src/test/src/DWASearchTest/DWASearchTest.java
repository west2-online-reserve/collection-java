package DWASearchTest;

import DWA_Search.DWASearch;
import org.junit.Test;

import java.io.IOException;

    public class DWASearchTest {
        @Test
        public void testMain()throws IOException {
            long start=System.currentTimeMillis();
            DWASearch.main(new String[]{"input.txt", "output.txt"});
            long end=System.currentTimeMillis();
            System.out.println("耗时："+(end-start)+" ms");
        }
        //只输入一个且输入错误
        @Test
        public void test2()throws IOException{
            DWASearch.main(new String[]{"input"});
        }
        //只输入一个且输入正确
        @Test
        public void test3()throws IOException{
            DWASearch.main(new String[]{"input.txt"});
        }
        //只输入一个且输入正确
        @Test
        public void test4()throws IOException{
            DWASearch.main(new String[]{"output.txt"});
        }
        //正确输入两个
        @Test
        public void test5()throws IOException{
            DWASearch.main(new String[]{"input.txt","output.txt"});
        }
        //错误输入两个（input错误，output错误）
        @Test
        public void test6()throws IOException {
            DWASearch.main(new String[]{"input","output"});
        }
        //错误输入两个（input正确，output错误）
        @Test
        public void test7()throws IOException{
            DWASearch.main(new String[]{"input.txt","output"});
        }
        //错误输入两个（input错误，output正确）
        @Test
        public void test8()throws IOException{
            DWASearch.main(new String[]{"input","output.txt"});
        }
        //错误输入参数（input正确，output正确，其他输入）
        @Test
        public void test9()throws IOException{
            DWASearch.main(new String[]{"input.txt","output.txt","others.txt"});
        }//能正确写入文件，不报错


        //错误输入参数（input错误，output错误，其他输入）
        @Test
        public void test10()throws IOException{
            DWASearch.main(new String[]{"input","output","others.txt"});
        }
    }


