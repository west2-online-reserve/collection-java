package core;

import DWA_Search.DWASearch;
import org.junit.jupiter.api.Test;

import java.io.*;

public class CoreTest {

    private static String input = "input.txt";
    private static String output = "output.txt";

    //测试player函数的输出,正常
    @Test
    public void testOutputPlayer() throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter(input));
        BufferedReader br = new BufferedReader(new FileReader(output));
        bw.write("players\n");
        bw.close();
        DWASearch.main(new String[]{input,output});
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
    }

    //测试outputContest函数的输出,正常
    @Test
    public void testOutputContest_1() throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter(input));
        BufferedReader br = new BufferedReader(new FileReader(output));
        bw.write("result women 1m springboard\n");
        bw.close();
        DWASearch.main(new String[]{input,output});
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
    }

    //测试带有空格的输入指令,测试outputContest函数的输出,正常
    @Test
    public void testOutputContest_2() throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter(input));
        BufferedReader br = new BufferedReader(new FileReader(output));
        bw.write("result men 10m     synchronised\n");
        bw.close();
        DWASearch.main(new String[]{input,output});
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
    }

    //测试outputContestDetailed函数的输出,正常
    @Test
    public void testOutputContestDetailed() throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter(input));
        BufferedReader br = new BufferedReader(new FileReader(output));
        bw.write("result women 10m platform detail\n");
        bw.close();
        DWASearch.main(new String[]{input,output});
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
    }

    //测试一系列不正常的输入指令
    @Test
    public void testError() throws IOException{

        BufferedWriter bw = new BufferedWriter(new FileWriter(input));
        BufferedReader br = new BufferedReader(new FileReader(output));
        bw.write("player\n" +
                "Players\n" +
                "resultwomen 1m springboard\n" +
                "result women 10m springboard\n" +
                "result sss\n" +
                "result detail\n");
        bw.close();
        DWASearch.main(new String[]{input,output});
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
    }



}
