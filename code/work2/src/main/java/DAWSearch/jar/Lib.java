package DAWSearch.jar;

import java.io.*;
import java.util.Collections;
import java.util.List;
import Data.AthleteInfoProcess.*;
import sun.java2d.pipe.BufferedRenderPipe;

public class Lib implements FunctionDeclaration {

    //输出所有运动员的基本信息
    public void displayAllPlayersInfo(String filePath){
        List<Athletes> athletes;
        athletes=new ProcessAthletes().parser();
        Collections.sort(athletes,new myComparator());
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\java\\code\\work2\\src\\main\\java\\Data\\TestTxt\\output.txt",true));
            for  (Athletes athlete:athletes){
                bw.write("Full Name:"+athlete.getFullName());
                bw.newLine();
                bw.write("Gender:"+athlete.getGender());
                bw.newLine();
                bw.write("Country:"+athlete.getCountryName());
                bw.newLine();
                bw.write("-----");
                bw.newLine();
            }
            bw.close();
            System.out.println("成功将Athletes.json写入output.txt！！！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //输出每个比赛的结果
    public void displayFinalResults(String filePath){
        List<Athletes> athletes;
        try {
            athletes=new ProcessResult().parser(filePath);
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\java\\code\\work2\\src\\main\\java\\Data\\TestTxt\\output.txt",true));
            for   (Athletes athlete:athletes){
                bw.write("Full Name:"+athlete.getFullName());
                bw.newLine();
                bw.write("Rank:"+athlete.getRank());
                bw.newLine();
                bw.write("Score:");
                String[] divePoints=athlete.getDivePoints();
                int i=0;
                for (String divePoint:divePoints) {
                    i++;
                    if (i<=5)
                        bw.write(divePoint+"+");
                    else
                        bw.write(divePoint+"=");
                }
                bw.write(athlete.getFinalScore());
            }
            for (Athletes athlete:athletes){
                System.out.println(athlete.getFinalScore());
            }
            bw.close();
            System.out.println("成功将Men 1m Springboard.json写入output.txt！！！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void inputCommand(String command) {
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\java\\code\\work2\\src\\main\\java\\Data\\TestTxt\\input.txt",true));
            bw.write(command);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("成功写入input.txt");
    }

    //判断是否是正确的指令
    public boolean isValidCommand(String command) {

        return false;
    }
}
