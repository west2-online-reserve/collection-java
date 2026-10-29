package com.example.taskdive;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AthleteOutputTest {
    @Test
    public void PlayerOutputTest(){
        Athlete athlete = new Athlete();
        athlete.setCountryName("Australia");
        athlete.setPreferredFirstName("Ellie");
        athlete.setPreferredLastName("COLE");
        athlete.setGender(1);
        OutputClass.OutputAthlete(athlete);

        PrintStream printStream = System.out; // 标准控制台输出流

        // 字符串缓冲，重定向输出
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        OutputClass.OutputAthlete(athlete);

        System.setOut(printStream); // 重要收尾工作

        String output = byteArrayOutputStream.toString();

        assertTrue(output.contains("Full Name: COLE Ellie"), "姓名格式不对");
        assertTrue(output.contains("Gender: Female"),"性别输出不对");
        assertTrue(output.contains("Country: Australia"),"国家输出不对");
        assertTrue(output.contains("----"),"缺少分割线");
    }
}
