import org.example.Athlete;
import org.example.Conpetiton;
import org.example.ConpetitonDetial;
import org.example.CoreModule;
import org.junit.jupiter.api.Test;

public class CoreModuleTest {
    @Test
    void testAthletes(){
        Athlete[] athletes=CoreModule.getAthlete("src/main/resources/athletes.json");
    }
    @Test
    void  testConpetiton(){
        Conpetiton[] conpetitons=CoreModule.getConpetition("src/main/resources/Men 1m Springboard.json");
    }
    @Test
    void  testConpetitonDetail(){
        ConpetitonDetial[] conpetitonDetials=CoreModule.getConpetitionDetial(("src/main/resources/Men 1m Springboard.json"));
    }

}
