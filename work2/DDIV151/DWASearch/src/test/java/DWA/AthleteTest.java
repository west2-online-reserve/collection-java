package DWA;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test
    void turnGenderString() {
        for (int i = 0; i < 2; i++) {
            switch (i) {
                case 0:assertEquals("Male",Athlete.turnGenderString(i));
                break;
                case 1:assertEquals("Female",Athlete.turnGenderString(i));
            }
        }
        Assert.assertEquals("Unknown",Athlete.turnGenderString(99));
    }

    @Test
    void testToString() {
        Athlete athlete = new Athlete("Dazhuang","Wang","Male","China");
        Assert.assertEquals("Full Name:Dazhuang Wang\nGender:Male\nCountry:China",athlete.toString());
    }
}
