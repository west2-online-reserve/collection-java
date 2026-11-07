package user.app;

import org.junit.jupiter.api.Test;
import user.support.TestFixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApplicationAndMainTest {

    @Test
    void applicationStartsWithEmptyDataObject() {
        Application application = new Application();

        assertNotNull(application.getData());
        assertNull(application.getData().getCompetition());
    }

    @Test
    void commandRunReportsUnknownCommandAsError() {
        Application application = new Application();

        String output = TestFixtures.captureOutput(
                () -> Main.commandRun(application, new String[]{"unknown"})
        );

        assertEquals("Error\n", output);
    }

}
