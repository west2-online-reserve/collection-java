package user.lib;

import org.junit.jupiter.api.Test;
import user.support.TestFixtures;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandParamTest {

    @Test
    void exposesArgumentsAndSize() {
        CommandParam param = TestFixtures.param("players", "-f", "output.txt");

        assertEquals("players", param.getFirstArg());
        assertEquals("-f", param.getArg(1));
        assertEquals("output.txt", param.getArg(2));
        assertEquals(3, param.size());
        assertFalse(param.isEmpty());
    }

    @Test
    void reportsEmptyParameterList() {
        CommandParam param = new CommandParam(new ArrayList<>());

        assertTrue(param.isEmpty());
        assertEquals(0, param.size());
    }

    @Test
    void createsSlicedCopiesUsingBeginAndEnd() {
        CommandParam original = TestFixtures.param("result", "men", "10m", "platform");

        CommandParam tail = new CommandParam(original, 1);
        CommandParam middle = new CommandParam(original, 1, 3);

        assertEquals(4, original.size());
        assertEquals("men", tail.getFirstArg());
        assertEquals(3, tail.size());
        assertEquals("men", middle.getFirstArg());
        assertEquals(2, middle.size());
    }

    @Test
    void slicesOptionsIntoIndependentParameterGroups() {
        CommandParam param = TestFixtures.param(
                "result", "men", "10m", "platform", "--detail", "-f", "output.txt"
        );

        ArrayList<CommandParam> slices = param.paramSlice();

        assertEquals(3, slices.size());
        assertEquals("result", slices.get(0).getFirstArg());
        assertEquals(4, slices.get(0).size());
        assertEquals("--detail", slices.get(1).getFirstArg());
        assertEquals(1, slices.get(1).size());
        assertEquals("-f", slices.get(2).getFirstArg());
        assertEquals("output.txt", slices.get(2).getArg(1));
        assertEquals(7, param.size());
    }

    @Test
    void returnsEmptySlicesForEmptyParameters() {
        CommandParam param = new CommandParam(new ArrayList<>());

        assertTrue(param.paramSlice().isEmpty());
    }
}
