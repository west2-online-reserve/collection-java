import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class DWASearchTest {
    // 写入输入文件
    private void writeInputFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"))) {
            writer.write(content);
        }
    }
    // 读取输出文件内容
    private String readOutputFile() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // 命令行参数数量错误
    @Test
    void testInvalidArgsCount() throws IOException {
        DWASearch.main(new String[]{"input.txt"});
        assertTrue(readOutputFile().equals(""));
    }

    // 空输入文件
    @Test
    void testEmptyInput() throws IOException {
        writeInputFile("");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        assertTrue(readOutputFile().equals(""));
    }

    // 有效players命令
    @Test
    void testValidPlayersCommand() throws IOException {
        writeInputFile("players");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertFalse(output.contains("Error"));
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Gender:"));
        assertTrue(output.contains("Country:"));
    }

    // 有效result命令（不带detail）
    @Test
    void testValidResultWithoutDetail() throws IOException {
        writeInputFile("result women 10m platform");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertFalse(output.contains("Error"));
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Rank:"));
        assertTrue(output.contains("Score:"));
    }

    //有效result命令（带detail）
    @Test
    void testValidResultWithDetail() throws IOException {
        writeInputFile("result women 10m platform detail");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertFalse(output.contains("Error"));
        assertTrue(output.contains("Full Name:"));
        assertTrue(output.contains("Rank:"));
        assertTrue(output.contains("Preliminary Score:"));
        assertTrue(output.contains("Semifinal Score:"));
        assertTrue(output.contains("Final Score:"));
    }

    // 无效指令
    @Test
    void testInvalidCommand() throws IOException {
        writeInputFile("invalidCommand");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertTrue(output.contains("Error"));
    }

    // result格式错误
    @Test
    void testInvalidResultFormat() throws IOException {
        writeInputFile("result women invalidEvent");
        DWASearch.main(new String[]{"input.txt", "output.txt"});
        String output = readOutputFile();
        assertTrue(output.contains("N/A"));
    }

    @BeforeEach
    void tearDown() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt"))) {
            bw.write("");
        }
    }
}
