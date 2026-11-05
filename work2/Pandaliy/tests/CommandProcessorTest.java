import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 命令处理测试：用内存流验证多行命令、非法输入与输出格式。
class CommandProcessorTest {

    @Test
    void playersCommandPrintsAllPlayerBlocks() throws IOException {
        String output = runCommands("players\n");

        assertTrue(output.startsWith(
                "Full Name:COLE Ellie\nGender:Female\nCountry:Australia\n-----\n"));
        assertTrue(output.endsWith("-----\n"));
        assertFalse(output.contains("\r"));
    }

    @Test
    void resultCommandPrintsEventScores() throws IOException {
        String output = runCommands("result men 10m platform\n");

        assertTrue(output.contains(
                "Full Name:BAI Yuming\n"
                        + "Rank:1\n"
                        + "Score:81.60 + 86.40 + 77.00 + 91.80 + 104.40 + 101.75 = 542.95\n"
                        + "-----\n"));
    }

    @Test
    void multipleCommandsAreConcatenatedWithoutExtraBlankLines() throws IOException {
        String output = runCommands("players\nresult women 3m springboard\n");

        assertTrue(output.startsWith("Full Name:COLE Ellie\n"));
        assertTrue(output.contains("Full Name:CHEN Jia\nRank:1\n"));
        assertFalse(output.contains("-----\n\n"));
    }

    @Test
    void repeatedCommandsProduceRepeatedResults() throws IOException {
        String output = runCommands(
                "result men 10m platform\nresult men 10m platform\n");

        assertEquals(2, count(output, "Full Name:BAI Yuming"));
    }

    @Test
    void blankLinesAreSkipped() throws IOException {
        String output = runCommands("\n   \t\nplayers\n  \n");

        assertTrue(output.startsWith("Full Name:COLE Ellie\n"));
        assertFalse(output.contains("Error"));
    }

    @Test
    void unknownCommandsPrintError() throws IOException {
        assertEquals("Error\n-----\n", runCommands("player\n"));
        assertEquals("Error\n-----\n", runCommands("Players\n"));
        assertEquals("Error\n-----\n", runCommands("players extra\n"));
        assertEquals("Error\n-----\nError\n-----\n", runCommands("player\nplayer\n"));
    }

    @Test
    void unknownEventPrintsNotAvailable() throws IOException {
        assertEquals("N/A\n-----\n", runCommands("result men 10m board\n"));
        assertEquals("N/A\n-----\n", runCommands("result WOMEN 3m springboard\n"));
    }

    @Test
    void bareOrIncompleteResultCommandPrintsNotAvailable() throws IOException {
        // 能识别为 result（后面是空格或行尾），但项目缺失或不在列表内，输出 N/A
        assertEquals("N/A\n-----\n", runCommands("result\n"));
        assertEquals("N/A\n-----\n", runCommands("result \n"));
        assertEquals("N/A\n-----\n", runCommands("result sss\n"));
    }

    @Test
    void unrecognizedResultLikeCommandsPrintError() throws IOException {
        // 只有完整的 "result" 词才算 result 命令，其余前缀一律 Error
        assertEquals("Error\n-----\n", runCommands("resultat\n"));
        assertEquals("Error\n-----\n", runCommands("resultfoo men 10m platform\n"));
    }

    @Test
    void commandsAreCaseSensitive() throws IOException {
        assertEquals("Error\n-----\n", runCommands("RESULT men 10m platform\n"));
    }

    @Test
    void extraSpacesInsideEventNamePrintNotAvailable() throws IOException {
        // 项目名称中的空格不能随意增加：多一个空格就不再是合法命令
        assertEquals("N/A\n-----\n", runCommands("result men 10m     synchronised\n"));
        assertEquals("N/A\n-----\n", runCommands("result   men 10m platform\n"));
        assertEquals("N/A\n-----\n", runCommands("result women 3m  springboard\n"));
    }

    @Test
    void surroundingWhitespaceIsIgnored() throws IOException {
        // 首尾空白可以容忍，只有命令内部的多余空格才会导致 N/A
        assertEquals(runCommands("players\n"), runCommands("  players  \n"));
        assertEquals(
                runCommands("result men 10m platform\n"),
                runCommands("\tresult men 10m platform\t\n"));
    }

    @Test
    void invalidCommandDoesNotStopFollowingValidCommand() throws IOException {
        String output = runCommands("badcmd\nresult women 3m springboard\n");

        assertTrue(output.startsWith("Error\n-----\n"));
        assertTrue(output.contains("Full Name:CHEN Jia\nRank:1\n"));
    }

    @Test
    void emptyInputProducesNothing() throws IOException {
        assertEquals("", runCommands(""));
        assertEquals("", runCommands("\n\n \t\n"));
    }

    private static String runCommands(String commands) throws IOException {
        StringWriter buffer = new StringWriter();
        PrintWriter writer = new PrintWriter(buffer, true);
        BufferedReader reader = new BufferedReader(new StringReader(commands));
        new CommandProcessor(writer).run(reader);
        writer.flush();
        return buffer.toString();
    }

    private static int count(String text, String needle) {
        int occurrences = 0;
        int from = 0;
        while ((from = text.indexOf(needle, from)) != -1) {
            occurrences++;
            from += needle.length();
        }
        return occurrences;
    }
}
