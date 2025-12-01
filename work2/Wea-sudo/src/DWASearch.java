package org.example;

import org.example.lib.CoreModule;
import org.example.lib.Country;
import org.example.lib.Event;
import org.example.lib.HttpsCrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DWASearch {
    // 固定数据目录名（无需用户输入，程序内部指定）
    private static final String DATA_DIR = "data/";

    public static void main(String[] args) {
        String partcipationsUrl = "https://api.worldaquatics.com/fina/competitions/3337/athletes?gender=&countryId=";
        String disciplinesUrl = "https://api.worldaquatics.com/fina/competitions/3337/events";


        //校验命令行参数（必须传入 2 个文件名）
        if (args.length != 2) {
            System.err.println("用法：java -jar DWASearch.jar <输入文件名> <输出文件名>");
            System.exit(1);
        }

        //拼接data目录路径
        String inputFileName = "data/"+ args[0];
        String outputFileName ="data/"+ args[1];

        try {
            HttpsCrawler httpsCrawler = new HttpsCrawler();
            ArrayList<Country> countries = httpsCrawler.fetchArrayList(partcipationsUrl, Country.class);
            Event event = httpsCrawler.fetch(disciplinesUrl, Event.class);
            //获取jar包所在目录
            String jarDir = new File(DWASearch.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getParent();

            //拼相对路径到当前目录下
            File inputFilePath = new File(jarDir, inputFileName);
            File outputFilePath = new File(jarDir, outputFileName);

            if (!inputFilePath.exists()) {
                System.out.println("文件未找到: " + inputFilePath.getAbsolutePath());
                return;
            }
            if (!outputFilePath.exists()) {
                System.out.println("文件未找到: " + outputFilePath.getAbsolutePath());
                return;
            }

            CoreModule coreModule = new CoreModule(outputFilePath);


            //正则表达式匹配命令
            Pattern playersPattern = Pattern.compile("players");
            Pattern resultsPattern = Pattern.compile("^result ([a-zA-Z0-9 ]+)");
            Pattern resultsDetailPattern = Pattern.compile("^result ([a-zA-Z0-9 ]+) detail$");

            Scanner sc = new Scanner(inputFilePath);
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                boolean isCommandMatched = false;

                //匹配命令
                //匹配 "players"命令
                Matcher playersMatcher = playersPattern.matcher(command);
                if (playersMatcher.matches()) {
                    coreModule.displayAllPlayersInfo(countries); // 假设 countries 已提前初始化
                    isCommandMatched = true;
                }
                //匹配"result XXX details"命令
                else {
                    Matcher detailMatcher = resultsDetailPattern.matcher(command);
                    if (detailMatcher.matches()) {
                        String discipline = detailMatcher.group(1);
                        coreModule.displayResultsForEachEventDetail(event, discipline);
                        isCommandMatched = true;
                    }
                    //匹配"result XXX"命令
                    else {
                        Matcher resultMatcher = resultsPattern.matcher(command);
                        if (resultMatcher.matches()) {
                            String discipline = resultMatcher.group(1);
                            //System.out.println(discipline);
                            coreModule.displayResultsForEachEvent(event, discipline);
                            isCommandMatched = true;
                        }
                    }
                }
                if (!isCommandMatched) {
                    coreModule.writeWrongInstruction();
                }
            }

            System.out.println("写入成功！");
        } catch (IOException e) {
            System.err.println("文件读写异常: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("操作被中断: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("发生未知异常: " + e.getMessage());
            e.printStackTrace();
        }


    }
}