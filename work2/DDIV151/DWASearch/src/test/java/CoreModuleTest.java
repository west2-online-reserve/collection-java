import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CoreModuleTest {


    @Test
    void displayAllPlayersInfo() {
       new CoreModule().displayAllPlayersInfo();
    }

    @Test
    void displayResultsForEachEvent() {
        new CoreModule().displayResultsForEachEvent();
    }

    @Test
    void displayResult() {
        new CoreModule().displayResult("Men 1m Springboard");
    }

    @Test
    void displayDetailedResult() {
        new CoreModule().displayDetailedResult("Women 1m Springboard");
    }

    @Test
    void displayAllDetailedResults() {
        new CoreModule().displayAllDetailedResults();
    }
}
