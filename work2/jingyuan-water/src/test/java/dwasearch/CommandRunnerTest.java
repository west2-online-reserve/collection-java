package dwasearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandRunnerTest {

    /** 每个测试构造独立的 runner */
    private CommandRunner newRunner() throws Exception {
        return new CommandRunner(DataLoader.load());
    }

    @Test
    void playersCommandStartsWithColeEllie() throws Exception {
        CommandRunner runner = newRunner();
        String out = runner.runLine("players");

        assertTrue(out.startsWith(
                "Full Name:COLE Ellie\nGender:Female\nCountry:Australia\n-----\n"));
    }

    @Test
    void playersAreSortedByCountryThenLastName() throws Exception {
        CommandRunner runner = newRunner();
        String out = runner.runLine("players");

        // Australia 有 6 人，姓氏顺序 COLE -> KEENEY -> KOLOI -> MERCIECA -> PUCKERIDGE -> ROUSSEAU
        assertTrue(out.contains("Full Name:COLE Ellie"));
        assertTrue(out.indexOf("COLE Ellie") < out.indexOf("KEENEY Maddison"));
        assertTrue(out.indexOf("KEENEY Maddison") < out.indexOf("KOLOI Alysha"));
        assertTrue(out.indexOf("KOLOI Alysha") < out.indexOf("MERCIECA Jonah"));
        assertTrue(out.indexOf("MERCIECA Jonah") < out.indexOf("PUCKERIDGE Milly"));
        assertTrue(out.indexOf("PUCKERIDGE Milly") < out.indexOf("ROUSSEAU Cassiel"));
    }

    @Test
    void unknownCommandReturnsError() throws Exception {
        CommandRunner runner = newRunner();

        assertEquals("Error\n-----\n", runner.runLine("player"));
        assertEquals("Error\n-----\n", runner.runLine("Players"));
        assertEquals("Error\n-----\n", runner.runLine("players extra"));
        assertEquals("Error\n-----\n", runner.runLine("resultwomen 3m springboard"));
    }

    @Test
    void badEventNameReturnsNA() throws Exception {
        CommandRunner runner = newRunner();

        assertEquals("N/A\n-----\n", runner.runLine("result"));
        assertEquals("N/A\n-----\n", runner.runLine("result sss"));
        assertEquals("N/A\n-----\n", runner.runLine("result women 10m springboard"));
        assertEquals("N/A\n-----\n", runner.runLine("result women 3m springboard details"));
    }

    @Test
    void firstResultMatchesSpec() throws Exception {
        CommandRunner runner = newRunner();
        String out = runner.runLine("result women 3m springboard");

        assertTrue(out.startsWith(
                "Full Name:CHEN Jia\nRank:1\n"
                        + "Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40\n-----\n"));
    }

    @Test
    void everyRecordEndsWithSeparator() throws Exception {
        CommandRunner runner = newRunner();
        String out = runner.runLine("result women 3m springboard");

        assertTrue(out.endsWith("-----\n"), "最后一条后面也必须有 -----");
    }

    @Test
    void repeatedCommandOutputsTwice() throws Exception {
        CommandRunner runner = newRunner();

        String first = runner.runLine("result women 3m springboard");
        String second = runner.runLine("result women 3m springboard");

        assertEquals(first, second, "重复命令要输出两次，不能去重");
    }

    @Test
    void windowsCarriageReturnIsHandled() throws Exception {
        CommandRunner runner = newRunner();

        // Windows 文本文件行尾为 CRLF，readLine 可能留下 \r
        assertEquals("Error\n-----\n", runner.runLine("player\r"));
        assertTrue(runner.runLine("players\r").startsWith("Full Name:COLE Ellie"));
    }

    @Test
    void blankLineIsIgnored() throws Exception {
        CommandRunner runner = newRunner();

        // 空行按"忽略"处理，该设计决定见 README
        assertEquals("", runner.runLine(""));
        assertEquals("", runner.runLine("   "));
    }
}
