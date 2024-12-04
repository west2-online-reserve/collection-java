package DWA_Search;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DWASearchTest {

    //正确测试,可以正常输出到output中
    @Test
    public void testDWA() throws IOException{

        DWASearch.main(new String[]{"input.txt","output.txt"});

    }
    //参数不正确,返回提醒,直接return
    @Test
    public void testDWAError1_1() throws IOException{

        DWASearch.main(new String[]{"input.txt"});

    }
    //参数不正确,返回提醒,直接return
    @Test
    public void testDWAError1_2() throws IOException{

        DWASearch.main(new String[]{"input"});

    }
    //参数不正确,返回提醒,直接return
    @Test
    public void testDWAError1_3() throws IOException{

        DWASearch.main(new String[]{"input.txt","output.txt","other.txt"});

    }
    //输入不存在的输出文件,会创建put文件,然后输入数据
    @Test
    public void testDWAError2_1() throws IOException{

        DWASearch.main(new String[]{"input.txt","put"});

    }

    //输入不存在的输出文件,会创建put.txt文件,然后输入数据
    @Test
    public void testDWAError2_2() throws IOException{

        DWASearch.main(new String[]{"input.txt","put.txt"});

    }

}
