package com.src.Lib;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class DisplayInformationTest {
    //women 10m synchronised.txt 文件内容最短，所以我将他作为测试用例
    @Test
    //这是测试非detail的显示信息
    public void testDisplayInformation() throws IOException {
        try {
            DisplayInformation.displaySingleMatch("women 10m synchronised.txt",25,"output1.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals("" +
                        "Full Name:WASSEN Christina & WASSEN Elena\n" +
                        "Rank:1\n" +
                        "Score:49.20 + 43.20 + 67.20 + 61.20 + 69.12 = 289.92\n" +
                        "-----\n" +
                        "Full Name:CARVAJAL Ana & ANTOLINO Valeria\n" +
                        "Rank:2\n" +
                        "Score:42.60 + 40.20 + 61.20 + 56.55 + 54.72 = 255.27\n" +
                        "-----\n" +
                        "Full Name:PFEIF Pauline Alexandra & COORDES Carolina\n" +
                        "Rank:99\n" +
                        "Score:46.20 + 46.80 + 55.68 + 66.24 + 57.60 = 272.52\n" +
                        "-----\n",
                FileReadAndWrite.readFile("output1.txt"));
    }
    @Test
    public void testDisplayPartialPlayerInformation() throws IOException {
        try {
            DisplayInformation.displayAllAthletes("output2.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //我把全部运动员的信息都输出到了output2.txt文件中
        String content=FileReadAndWrite.readFile("output2.txt");
        //这里的getStringBeforeNthCharacter方法的作用是找到第8个'\n'字符之前的字符串(包括第8个\n)，并返回
        content= StringProcessor.getStringBeforeNthCharacter(content, '\n',8);
        //根据我自己的allAthlete.txt文档内容，第8个\n前面的字符串应该有2个运动员的信息，所以我只比较前2个运动员信息是否正确输出输出
        assertEquals("" +
                "Full Name:HART Alexander\n" +
                "Gender:Male\n" +
                "Country:Austria\n" +
                "-----\n" +
                "Full Name:LOTFI Dariush\n" +
                "Gender:Male\n" +
                "Country:Austria\n" +
                "-----\n",content);
    }
}
//下面是StringProcessor.java文件的内容，作用是截取字符串的第n个特定字符之前的字符串，包括第n个特定的字符一起作返回
public class StringProcessor {

    public static String getStringBeforeNthCharacter(String input, char targetChar, int n) {
        int count = 0;
        int index = -1;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == targetChar) {
                count++;
                if (count == n) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1) {
            return ""; // 如果没有找到第n个特定字符，返回空字符串
        }

        return input.substring(0, index)+"\n"; // 返回第n个特定字符前的字符串
    }
}
