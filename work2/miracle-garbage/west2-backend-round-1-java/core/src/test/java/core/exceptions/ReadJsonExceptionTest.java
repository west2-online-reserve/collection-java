package core.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ReadJsonExceptionTest {

    @Test
    void exceptionHierarchyKeepsMessages() {
        ReadJsonException base = new ReadJsonException("base");
        DataPathException dataPath = new DataPathException("data");
        PathResolveException pathResolve = new PathResolveException("path");

        assertEquals("base", base.getMessage());
        assertEquals("data", dataPath.getMessage());
        assertEquals("path", pathResolve.getMessage());
        assertInstanceOf(ReadJsonException.class, dataPath);
        assertInstanceOf(ReadJsonException.class, pathResolve);
    }
}
