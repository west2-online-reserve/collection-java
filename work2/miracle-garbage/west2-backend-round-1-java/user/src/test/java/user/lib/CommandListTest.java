package user.lib;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandListTest {

    private final CommandList commandList = new CommandList();

    @Test
    void registersAllSupportedCommandsAndExtensions() {
        assertEquals(Set.of("players", "result", "config"), commandList.getCommandMap().keySet());
        assertEquals(List.of(".txt"), commandList.getExtensions());
    }

    @Test
    void acceptsKnownCommandsAndTxtFiles() {
        assertTrue(commandList.isLegal("players"));
        assertTrue(commandList.isLegal("result"));
        assertTrue(commandList.isLegal("config"));
        assertTrue(commandList.isLegal("input.txt"));
        assertTrue(commandList.isLegal("C:\\data\\input.txt"));
    }

    @Test
    void rejectsNullOrEmptyAndUnsupportedArguments() {
        assertFalse(commandList.isLegal(null));
        assertFalse(commandList.isLegal(""));
        assertFalse(commandList.isLegal("unknown"));
        assertFalse(commandList.isLegal("input.md"));
        assertFalse(commandList.isLegal("input.TXT"));
    }

    @Test
    void distinguishesCommandsFromFilesByDot() {
        assertTrue(commandList.isCommand("players"));
        assertFalse(commandList.isCommand("input.txt"));
        assertFalse(commandList.isFile("players"));
        assertTrue(commandList.isFile("input.txt"));
    }
}
