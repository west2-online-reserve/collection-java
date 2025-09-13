package com.src.Lib;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

public class CreateAthleteListTest {

    // 测试正常路径
    @Test
    public void testCreatePlayerList_HappyPath() throws IOException {
        //"men 10m synchronised.txt"文件里面只有7组运动员的数据，所以列表长度应该为7
        String fileName = "men 10m synchronised.txt";

        // 调用方法
        List<Athlete> athleteList = CreateAthleteList.createPlayerlist(fileName);


        assertNotNull(athleteList);
        assertEquals(7, athleteList.size());
        //列表中第一组运动员的数据就是我的Data文件中男子10m同步赛文件的最开头的的运动员数据
        assertEquals("Full Name:EIKERMANN GREGORCHUK Jaden Shiloh & BARTHEL Timo", athleteList.get(0).getFullName());
        assertEquals("Final Score:50.40 + 50.40 + 72.96 + 74.25 + 71.40 + 72.96 = 392.37", athleteList.get(0).getFinalScore());
    }

    // 测试空文件情况
    @Test
    public void testCreatePlayerList_EmptyFile() throws IOException {
        String fileName = "empty.txt"; // 这是一个不存在的文件

        List<Athlete> athleteList = CreateAthleteList.createPlayerlist(fileName);

        assertNotNull(athleteList);
        assertTrue(athleteList.isEmpty()); // 应该返回空列表
    }
}
