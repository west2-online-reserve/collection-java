import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DWASearchCommandTest {
    private static String normalize(String s) {
        return s.replace("\r\n", "\n").replace("\r", "\n");
    }

    // 把命令文本写入临时输入文件，跑一遍 run，返回规范化后的输出
    private String runCommands(String inputText, Path dir) throws Exception {
        Path in = dir.resolve("input.txt");
        Path out = dir.resolve("output.txt");
        Files.writeString(in, inputText);
        int code = DWASearch.run(new String[]{in.toString(), out.toString()});
        assertEquals(0, code, "合法输入应返回退出码 0");
        return normalize(Files.readString(out));
    }

    // ---------- 命令处理 ----------

    @Test
    void unknownCommand_printsError(@TempDir Path dir) throws Exception {
        assertEquals("Error\n-----\n", runCommands("foobar\n", dir));
    }

    @Test
    void resultWithoutSpace_printsError(@TempDir Path dir) throws Exception {
        // 题目：result 后接空格或行尾才识别为 result，否则是未知命令 → Error
        assertEquals("Error\n-----\n", runCommands("resultwomen 3m springboard\n", dir));
    }

    @Test
    void missingContestName_printsNA(@TempDir Path dir) throws Exception {
        assertEquals("N/A\n-----\n", runCommands("result\n", dir));
    }

    @Test
    void wrongContestName_printsNA(@TempDir Path dir) throws Exception {
        assertEquals("N/A\n-----\n", runCommands("result men 3m springboardX\n", dir));
    }

    @Test
    void extraTrailingChars_printsNA(@TempDir Path dir) throws Exception {
        assertEquals("N/A\n-----\n", runCommands("result women 3m springboard details\n", dir));
    }

    @Test
    void repeatedCommand_outputsEachTime(@TempDir Path dir) throws Exception {
        String out = runCommands("result men 10m synchronised\nresult men 10m synchronised\n", dir);
        assertEquals(2, countOccurrences(out, "Full Name:ZHAO Renjie & YANG Zhihao\n"),
                "重复命令应各自输出一次结果");
    }

    @Test
    void multiLine_playersThenResult(@TempDir Path dir) throws Exception {
        String out = runCommands("players\nresult men 10m platform\n", dir);
        assertTrue(out.contains("Full Name:COLE Ellie\n"), "应包含 players 输出");
        assertTrue(out.contains("Full Name:BAI Yuming\n"), "应包含 result 输出");
        assertTrue(out.indexOf("Full Name:COLE Ellie") < out.indexOf("Full Name:BAI Yuming"),
                "players 输出应在 result 输出之前");
    }

    // ---------- 错误后继续 ----------

    @Test
    void errorThenContinue_illegalThenLegal(@TempDir Path dir) throws Exception {
        String out = runCommands("bogus\nresult men 10m synchronised\n", dir);
        assertTrue(out.startsWith("Error\n-----\n"), "非法命令应先输出 Error 块");
        assertTrue(out.contains("Full Name:ZHAO Renjie & YANG Zhihao"), "随后的合法命令应正常输出");
    }

    @Test
    void naThenContinue_missingThenLegal(@TempDir Path dir) throws Exception {
        String out = runCommands("result nope\nplayers\n", dir);
        assertTrue(out.startsWith("N/A\n-----\n"), "缺失项目名应先输出 N/A 块");
        assertTrue(out.contains("Full Name:COLE Ellie"), "后续 players 应正常输出");
    }

    // ---------- 边界与格式 ----------

    @Test
    void emptyFile_producesNoOutput(@TempDir Path dir) throws Exception {
        assertEquals("", runCommands("", dir), "空输入文件不应产生任何输出");
    }

    @Test
    void emptyLine_treatedAsError(@TempDir Path dir) throws Exception {
        // 约定：空行视为非法命令 → 输出 Error（本程序的选择，已固定为行为）
        String out = runCommands("players\n\nplayers\n", dir);
        assertTrue(out.contains("Error\n-----\n"), "空行应被当作未知命令输出 Error");
        assertEquals(2, countOccurrences(out, "Full Name:COLE Ellie\n"), "两条 players 都应生效");
    }

    @Test
    void caseSensitive_uppercaseRejected(@TempDir Path dir) throws Exception {
        assertEquals("Error\n-----\nError\n-----\n",
                runCommands("Players\nRESULT men 10m synchronised\n", dir),
                "命令区分大小写，大写应被判为未知命令");
    }

    @Test
    void extraSpaces_rejected(@TempDir Path dir) throws Exception {
        // 尾随空格、前导空格、项目名中间多空格都不匹配严格正则
        assertEquals("Error\n-----\nError\n-----\nN/A\n-----\n",
                runCommands("players \n result men 10m synchronised\nresult  men 10m synchronised\n", dir));
    }

    @Test
    void outputUsesSeparatorAndNewlines(@TempDir Path dir) throws Exception {
        String out = runCommands("result men 10m synchronised\n", dir);
        assertTrue(out.startsWith("Full Name:ZHAO Renjie & YANG Zhihao\n"), "第一名应首先输出");
        assertTrue(out.endsWith("-----\n"), "输出应以 ----- 分隔符换行结束");
    }

    @Test
    void outputUsesLfNotCrlf(@TempDir Path dir) throws Exception {
        Path in = dir.resolve("input.txt");
        Path out = dir.resolve("output.txt");
        Files.writeString(in, "result men 10m synchronised\n");
        DWASearch.run(new String[]{in.toString(), out.toString()});
        assertFalse(Files.readString(out).contains("\r"), "题目要求输出换行用 \\n，不应出现 \\r");
    }

    private static int countOccurrences(String s, String sub) {
        int n = 0, idx = 0;
        while ((idx = s.indexOf(sub, idx)) != -1) {
            n++;
            idx += sub.length();
        }
        return n;
    }
}
