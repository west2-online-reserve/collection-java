package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    class DWASearchTest {

        @org.junit.jupiter.api.Test
        void main() {
            DWASearch.main(new String[]{"input.txt","output.txt"});
        }
        @Test
        //错误输入
        void text1(){
            DWASearch.main(new String[]{"input","output.txt"});
        }
        @Test
        //错误输出
        void text2(){
            DWASearch.main(new String[]{"input.txt","output"});
        }
        @Test
        //输入输出都错
        void text3(){
            DWASearch.main(new String[]{"input","output"});
        }
        @Test
        //输入参数个数少
        void text4(){
            DWASearch.main(new String[]{"input.txt"});
        }
        @Test
        //参数格式错误
        void text5(){
            DWASearch.main(new String[]{"Input","output.txt"});
        }
        @Test
        void text6(){
            DWASearch.main(new String[]{"input.txt","Output"});
        }
        @Test
        void textdisdisplayNA(){
            DWASearch.displayNA("FIS");
        }
        @Test
        void textdisplayError(){
            DWASearch.displayError("fis");
        }
        @Test
        void textclearFile(){
            DWASearch.clearFile("output.txt");
        }
    }