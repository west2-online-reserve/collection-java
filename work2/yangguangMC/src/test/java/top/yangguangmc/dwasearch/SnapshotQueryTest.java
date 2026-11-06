package top.yangguangmc.dwasearch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SnapshotQueryTest {
    private static DWADataSet dwaDataSet;

    @BeforeAll
    static void load() throws IOException {
        dwaDataSet = DWADataSet.load(DWADataSet.class::getResourceAsStream);
    }

    @Test
    void playersMatchSnapshot() {
        IODataSet ioDataSet = new IODataSet();
        ioDataSet.addInstruction("players");
        new InstructionProcessor(dwaDataSet).process(ioDataSet);
        String out = ioDataSet.getOutputs();
        assertTrue(out.startsWith("""
                Full Name:COLE Ellie
                Gender:Female
                Country:Australia
                -----
                """));
        assertEquals(89L, out.lines().filter("-----"::equals).count());
    }

    @Test
    void women3mSpringboardUsesFinals() {
        IODataSet ioDataSet = new IODataSet();
        ioDataSet.addInstruction("result women 3m springboard");
        new InstructionProcessor(dwaDataSet).process(ioDataSet);
        String out = ioDataSet.getOutputs();
        assertTrue(out.startsWith("""
                Full Name:CHEN Jia
                Rank:1
                Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40
                -----
                """));
        assertEquals(4L, out.lines().filter("-----"::equals).count());
        assertFalse(out.contains("GARCIA SIXTOS Maria Fernanda"));
    }
}