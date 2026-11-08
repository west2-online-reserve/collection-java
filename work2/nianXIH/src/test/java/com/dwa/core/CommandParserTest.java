package com.dwa.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @Test
    void testPlayersCommand() {
        CommandParser.ParsedCommand cmd = CommandParser.parse("players");
        assertEquals(CommandParser.CommandType.PLAYERS, cmd.getType());
    }

    @Test
    void testResultValidEvent() {
        CommandParser.ParsedCommand cmd = CommandParser.parse("result women 3m springboard");
        assertEquals(CommandParser.CommandType.RESULT, cmd.getType());
        assertEquals("result women 3m springboard", cmd.getEventKey());
    }

    @Test
    void testResultMen10mPlatform() {
        CommandParser.ParsedCommand cmd = CommandParser.parse("result men 10m platform");
        assertEquals(CommandParser.CommandType.RESULT, cmd.getType());
    }

    @Test
    void testUnknownCommandReturnsError() {
        assertEquals(CommandParser.CommandType.ERROR, CommandParser.parse("player").getType());
        assertEquals(CommandParser.CommandType.ERROR, CommandParser.parse("Players").getType());
        assertEquals(CommandParser.CommandType.ERROR, CommandParser.parse("PLAYERS").getType());
        assertEquals(CommandParser.CommandType.ERROR, CommandParser.parse("").getType());
        assertEquals(CommandParser.CommandType.ERROR, CommandParser.parse(null).getType());
    }

    @Test
    void testResultWithoutSpaceReturnsError() {
        assertEquals(CommandParser.CommandType.ERROR,
                CommandParser.parse("resultwomen 3m springboard").getType());
    }

    @Test
    void testInvalidEventReturnsNA() {
        assertEquals(CommandParser.CommandType.NA,
                CommandParser.parse("result sss").getType());
        assertEquals(CommandParser.CommandType.NA,
                CommandParser.parse("result women 10m springboard").getType());
        assertEquals(CommandParser.CommandType.NA,
                CommandParser.parse("result detail").getType());
    }

    @Test
    void testResultWithExtraCharsReturnsNA() {
        assertEquals(CommandParser.CommandType.NA,
                CommandParser.parse("result women 3m springboard details").getType());
        assertEquals(CommandParser.CommandType.NA,
                CommandParser.parse("result men 10m     synchronised").getType());
    }
}
