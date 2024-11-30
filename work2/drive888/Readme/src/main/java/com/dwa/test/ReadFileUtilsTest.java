package com.dwa.test;


import com.dwa.Lib.ReadFileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ReadFileUtilsTest {

    @Test
    public void toPlayerList() {
        //正确的输入
        ReadFileUtils.toPlayerList("athletes.json");
        //错误的输入
        //ReadFileUtils.toPlayerList("athletess.json");

    }

    @Test
    public void getUserDetailMap() {
        //输入目前需要读取的文件
        //正确的输入
        ReadFileUtils.getUserDetailMap("women 1m springboard.json");
    }

    @Test
    public void toJsonArray() throws IOException {
        //正确的输入
        ReadFileUtils.toJsonArray("athletes.json");

    }

    @Test
    public void toJsonObject() throws IOException {
        //输入目前需要读取的文件
        //正确的输入
        ReadFileUtils.toJsonObject("women 1m springboard.json");
        //错误的输入
        //ReadFileUtils.toJsonObject("women 10m springboard.json");
    }

    @Test
    public void readFile() throws IOException {
        //正确的输入
        ReadFileUtils.readFile(new File("input.txt" ));
    }
}