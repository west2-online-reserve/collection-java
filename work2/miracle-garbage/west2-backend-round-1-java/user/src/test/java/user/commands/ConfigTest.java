package user.commands;

import core.data.JsonData;
import org.junit.jupiter.api.Test;
import user.exceptions.ParamException;
import user.lib.FileCache;
import user.support.TestFixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    private final Config config = new Config();

    @Test
    void hasRegisteredName() {
        assertEquals("config", config.getName());
    }

    @Test
    void rejectsMissingUnsupportedAndIncompleteOptions() {
        JsonData data = TestFixtures.data();
        FileCache fileCache = new FileCache();

        assertThrows(ParamException.class, () -> config.execute(data, TestFixtures.param(), fileCache));
        assertThrows(
                ParamException.class,
                () -> config.execute(data, TestFixtures.param("--unknown", "value"), fileCache)
        );
        assertThrows(
                ParamException.class,
                () -> config.execute(data, TestFixtures.param("--setpath"), fileCache)
        );
        assertThrows(
                ParamException.class,
                () -> config.execute(data, TestFixtures.param("--setpath", "a", "b"), fileCache)
        );
        assertThrows(
                ParamException.class,
                () -> config.execute(
                        data,
                        TestFixtures.param("--setpath-status", "invalid"),
                        fileCache
                )
        );
    }

    @Test
    void validatesAllOptionsBeforeApplyingAnyChange() {
        JsonData data = TestFixtures.data();

        assertThrows(
                ParamException.class,
                () -> config.execute(
                        data,
                        TestFixtures.param(
                                "--setpath-status", "default",
                                "--setpath-status", "invalid"
                        ),
                        new FileCache()
                )
        );
    }

    @Test
    void terminalAndFileOutputAreUnsupported() {
        assertThrows(UnsupportedOperationException.class, () -> config.displayOnTerminal(new Object()));
        assertThrows(
                UnsupportedOperationException.class,
                () -> config.writeToFile(new Object(), new FileCache(), "unused.txt")
        );
    }
}
