import main.java.DWASearch;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DWASearchTest {
    private  BufferedReader br;

    @BeforeEach
    void setUpBufferReaderAndWriter() throws IOException{
        File testInputFile = File.createTempFile("input", ".txt");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(testInputFile))){
            bw.write("DWASearchTest");
            bw.flush();
        }
        br = new BufferedReader(new FileReader(testInputFile));
        testInputFile.deleteOnExit();
    }
    //命令行参数处理测试
    @Test
    public void testArgsExceptionProcessor1(){
        String[] args = new String[1];
        args[0] = "DWASearchTest";
        assertFalse(DWASearch.argsExceptionProcessor(args));
    }
    @Test
    public void testArgsExceptionProcessor2(){
        String[] args = new String[2];
        args[0] = "src/test/resources/src/input.txt";
        args[1] = "src/test/resources/src/output.txt";
        assertFalse(DWASearch.argsExceptionProcessor(args));
    }
    @Test
    public void testArgsExceptionProcessor3(){
        String[] args = new String[2];
        args[0] = "src/test/resources/input.txt";
        args[1] = "src/test/resources/src/output.txt";
        assertFalse(DWASearch.argsExceptionProcessor(args));
    }
    @Test
    public void testArgsExceptionProcessor4(){
        String[] args = new String[2];
        args[0] = "src/test/resources/input.txt";
        args[1] = "src/test/resources/output.txt";
        assertTrue(DWASearch.argsExceptionProcessor(args));
    }
    //文件读取测试
    @Test
    public void testReadInputFile1(){
        DWASearch.setBr(br);
        String testContent = DWASearch.readInputFile();
        assertEquals("DWASearchTest", testContent);
    }
    @AfterEach
    void closeBufferReaderWriter() throws IOException{
        br.close();
    }

    @Test
    public void testMain1() throws IOException{
        String[] args = new String[2];
        args[0] = "src/test/resources/input.txt";
        args[1] = "src/test/resources/output.txt";
        DWASearch.main(args);
        //一次性读取output.txt所有内容
        String testContent = readFileToString(args[1]);
        assertEquals(3, countStringOccurrences(testContent, "Error"));
        assertEquals(5, countStringOccurrences(testContent, "N/A"));
        assertEquals(86, countStringOccurrences(testContent, "Country:"));
        assertEquals(7, countStringOccurrences(testContent, "&"));
        assertTrue(countStringOccurrences(testContent, "Preliminary Score:") >= 14);
        assertTrue(testContent.contains("Semifinal Score:*"));
    }
    private String readFileToString(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }
    //字符串计数查找方法，测试main方法时用于大致检查输出的内容是否符合预期
    private int countStringOccurrences(String text, String target) {
        if (text.isEmpty() || target.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        // 循环查找目标字符串，直到找不到为止
        while ((index = text.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        return count;
    }
}
