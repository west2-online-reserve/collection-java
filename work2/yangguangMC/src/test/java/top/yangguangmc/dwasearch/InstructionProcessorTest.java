package top.yangguangmc.dwasearch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionProcessorTest {
    private static DWADataSet dwaDataSet;
    private IODataSet ioDataSet;

    @BeforeAll
    static void load() throws IOException {
        dwaDataSet = DWADataSet.load(resource -> DWADataSet.class.getResourceAsStream("/test" + resource));
    }

    @BeforeEach
    void setup() {
        ioDataSet = new IODataSet();
    }

    @Test
    void emptyInstructions() {
        ioDataSet.addInstruction("");
        ioDataSet.addInstruction(" ");
        ioDataSet.addInstruction("   \t ");
        new InstructionProcessor(dwaDataSet).process(ioDataSet);
        String out = ioDataSet.getOutputs();
        assertEquals("", out);
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "player|Error",
            "Players|Error",
            "players extra|Error",
            "resultwomen 3m springboard|Error",
            "result|N/A",
            "result sss|N/A",
            "result women 10m springboard|N/A",
            "result women 3m springboard details|N/A",
            "result men 10m     synchronised|N/A",
            "result |N/A",
            "result   women 3m springboard|N/A",
            "  players |Error"
    }, ignoreLeadingAndTrailingWhitespace = false)
    void invalidCommands(String command, String expected) {
        assertEquals(expected + "\n-----\n", process(command));
    }

    private String process(String command) {
        ioDataSet.addInstruction(command);
        new InstructionProcessor(dwaDataSet).process(ioDataSet);
        return ioDataSet.getOutputs();
    }

    @Test
    void repeatedCommands() {
        ioDataSet.addInstruction("players");
        ioDataSet.addInstruction("players");
        new InstructionProcessor(dwaDataSet).process(ioDataSet);
        String outputs = ioDataSet.getOutputs();
        assertEquals("""
                Full Name:LAST NAME1 First Name1
                Gender:Male
                Country:Country Name1
                -----
                Full Name:LAST NAME2 First Name2
                Gender:Female
                Country:Country Name1
                -----
                Full Name:LAST NAME3 First Name3
                Gender:Male
                Country:Country Name2
                -----
                Full Name:LAST NAME1 First Name1
                Gender:Male
                Country:Country Name1
                -----
                Full Name:LAST NAME2 First Name2
                Gender:Female
                Country:Country Name1
                -----
                Full Name:LAST NAME3 First Name3
                Gender:Male
                Country:Country Name2
                -----
                """, outputs);
    }

    @Test
    void errorBetweenCommands() {
        ioDataSet.addInstruction("players");
        ioDataSet.addInstruction("player");
        ioDataSet.addInstruction("players");
        new InstructionProcessor(dwaDataSet).process(ioDataSet);
        String outputs = ioDataSet.getOutputs();
        assertEquals("""
                Full Name:LAST NAME1 First Name1
                Gender:Male
                Country:Country Name1
                -----
                Full Name:LAST NAME2 First Name2
                Gender:Female
                Country:Country Name1
                -----
                Full Name:LAST NAME3 First Name3
                Gender:Male
                Country:Country Name2
                -----
                Error
                -----
                Full Name:LAST NAME1 First Name1
                Gender:Male
                Country:Country Name1
                -----
                Full Name:LAST NAME2 First Name2
                Gender:Female
                Country:Country Name1
                -----
                Full Name:LAST NAME3 First Name3
                Gender:Male
                Country:Country Name2
                -----
                """, outputs);
    }
}