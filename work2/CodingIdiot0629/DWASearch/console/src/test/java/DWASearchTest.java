import org.junit.jupiter.api.Test;

import java.io.PrintStream;

public class DWASearchTest {
    //传null
    @Test
    void test1() {
        DWASearch.main(null);
    }

    //参数数量不对,需要两个参数
    @Test
    void test2() {
        DWASearch.main(new String[]{""});
        DWASearch.main(new String[]{"input.txt"});
        DWASearch.main(new String[]{"output.txt"});
        DWASearch.main(new String[]{"input.txt", "output.txt", "666"});
    }

    //传入的文件不是文本文件名(.txt)
    @Test
    void test3() {
        DWASearch.main(new String[]{"input.tx", "output.txt"});
        DWASearch.main(new String[]{"input.txt", "output.tx"});
        DWASearch.main(new String[]{"input.tx", "output.tx"});
    }

    //传入的输入文件不存在
    @Test
    void test4() {
        DWASearch.main(new String[]{"nailong.txt", "output.txt"});
    }

    //传入的输出文件不存在
    @Test
    void test5() {
        DWASearch.main(new String[]{"input.txt", "hajimi.txt"});
    }

    //测试players
    @Test
    void test6() {
        try (PrintStream ps = new PrintStream("input.txt")) {
            ps.println("players");
            DWASearch.main(new String[]{"input.txt", "output.txt"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试错误的players,输出Error
    @Test
    void test7() {
        try (PrintStream ps = new PrintStream("input.txt")) {
            ps.println("Players");
            ps.println("player");
            ps.println("play");
            DWASearch.main(new String[]{"input.txt", "output.txt"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试正确的 result
    @Test
    void test8() {
        PrintStream ps = null;
        try {
            ps = new PrintStream("input.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ps.println("result men 1m springboard");
        ps.println("result men 3m springboard");
        ps.println("result men 3m synchronised");
        ps.println("result men 10m platform");
        ps.println("result men 10m synchronised");
        ps.println("result mixed 3m & 10m team");
        ps.println("result mixed 3m synchronised");
        ps.println("result mixed 10m synchronised");
        ps.println("result women 1m springboard");
        ps.println("result women 3m springboard");
        ps.println("result women 3m synchronised");
        ps.println("result women 10m platform");
        ps.println("result women 10m synchronised");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
    }

    //测试正确的 result detail
    @Test
    void test9() {
        PrintStream ps = null;
        try {
            ps = new PrintStream("input.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ps.println("result men 1m springboard detail");
        ps.println("result men 3m springboard detail");
        ps.println("result men 3m synchronised detail");
        ps.println("result men 10m platform detail");
        ps.println("result men 10m synchronised detail");
        ps.println("result mixed 3m & 10m team detail");
        ps.println("result mixed 3m synchronised detail");
        ps.println("result mixed 10m synchronised detail");
        ps.println("result women 1m springboard detail");
        ps.println("result women 3m springboard detail");
        ps.println("result women 3m synchronised detail");
        ps.println("result women 10m platform detail");
        ps.println("result women 10m synchronised detail");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
    }

    //测试输出Error的情况
    @Test
    void test10() {
        PrintStream ps = null;
        try {
            ps = new PrintStream("input.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ps.println("resultmen 1m springboard");
        ps.println("result women 10m platformdetail");
        ps.println("detail result men 1m springboard");
        ps.println("result detail men 3m springboard");
        ps.println("resulted men 3m synchronised");
        ps.println("result men 10m platform detail ");
        ps.println("result men 10m synchronised detail hhh");
        ps.println("result mixed 3m & 10m team details");
        ps.println("results mixed 3m synchronised details");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
    }

    //测试输出N/A的情况
    @Test
    void test11() {
        PrintStream ps = null;
        try {
            ps = new PrintStream("input.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ps.println("result men 3m springboard          ");
        ps.println("result men 3m synchronised hahaha");
        ps.println("result        10m platform");
        ps.println("result men       10m synchronised");
        ps.println("result mixed 3m   &    10m team");
        ps.println("result men 3m synchronised      detail");
        ps.println("result     men 10m platform detail");
        ps.println("result men 100m synchronised detail");
        ps.println("result mixed 3m & 10m teams detail");
        ps.println("result mixed 3m  synchronised detail");
        ps.println("result   mixed 10m synchronised detail");
        ps.println("result wwomen 1m springboard detail");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
    }
}
