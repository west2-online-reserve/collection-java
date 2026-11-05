package com.dwasearch;

import com.dwasearch.command.CommandProcessor;
import com.dwasearch.model.CompetitionData;
import com.dwasearch.model.EventResult;
import com.dwasearch.model.FinalResult;
import com.dwasearch.model.Player;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandProcessorTest {

    private CompetitionData sampleData() {
        Player cole = new Player("Ellie", "COLE", "Female", "Australia");
        Player keeney = new Player("Maddison", "KEENEY", "Female", "Australia");

        FinalResult first = new FinalResult(
                "CHEN Jia",
                1,
                List.of(new BigDecimal("73.50"), new BigDecimal("74.40")),
                new BigDecimal("147.90")
        );

        FinalResult second = new FinalResult(
                "CHEN Yiwen",
                2,
                List.of(new BigDecimal("69.00"), new BigDecimal("72.00")),
                new BigDecimal("141.00")
        );

        EventResult event = new EventResult(
                "women 3m springboard",
                List.of(second, first)
        );

        return new CompetitionData(
                List.of(keeney, cole),
                List.of(event)
        );
    }

    @Test
    void shouldProcessPlayersCommand() {
        String actual = new CommandProcessor().process("players", sampleData());

        String expected =
                "Full Name:COLE Ellie\n" +
                        "Gender:Female\n" +
                        "Country:Australia\n" +
                        "-----\n" +
                        "Full Name:KEENEY Maddison\n" +
                        "Gender:Female\n" +
                        "Country:Australia\n" +
                        "-----\n";

        assertEquals(expected, actual);
    }

    @Test
    void shouldProcessResultCommand() {
        String actual = new CommandProcessor()
                .process("result women 3m springboard", sampleData());

        String expected =
                "Full Name:CHEN Jia\n" +
                        "Rank:1\n" +
                        "Score:73.50 + 74.40 = 147.90\n" +
                        "-----\n" +
                        "Full Name:CHEN Yiwen\n" +
                        "Rank:2\n" +
                        "Score:69.00 + 72.00 = 141.00\n" +
                        "-----\n";

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnErrorForUnknownCommands() {
        CommandProcessor processor = new CommandProcessor();
        CompetitionData data = sampleData();

        assertEquals("Error\n-----\n", processor.process("player", data));
        assertEquals("Error\n-----\n", processor.process("Players", data));
        assertEquals("Error\n-----\n", processor.process("players extra", data));
        assertEquals("Error\n-----\n", processor.process("resultwomen 3m springboard", data));
    }

    @Test
    void shouldReturnNaForInvalidResultCommands() {
        CommandProcessor processor = new CommandProcessor();
        CompetitionData data = sampleData();

        assertEquals("N/A\n-----\n", processor.process("result", data));
        assertEquals("N/A\n-----\n", processor.process("result sss", data));
        assertEquals("N/A\n-----\n", processor.process("result women 10m springboard", data));
        assertEquals("N/A\n-----\n", processor.process("result women 3m springboard details", data));
        assertEquals("N/A\n-----\n", processor.process("result men 10m     synchronised", data));
    }

    @Test
    void shouldIgnoreBlankLines() {
        CommandProcessor processor = new CommandProcessor();
        CompetitionData data = sampleData();

        assertEquals("", processor.process("", data));
        assertEquals("", processor.process("   ", data));
    }
}