package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainTest {
    @Test
    void athleteToString_male() {
        Athlete a = new Athlete("China", 0, "QUAN", "Hongchan");
        assertEquals("Full Name:QUAN Hongchan\nGender:Male\nCountry:China\n-----", a.toString());
    }

    @Test
    void athleteToString_female() {
        Athlete a = new Athlete("Australia", 1, "COLE", "Ellie");
        assertEquals("Full Name:COLE Ellie\nGender:Female\nCountry:Australia\n-----", a.toString());
    }

    @Test
    void contestGetScore_singleDive() {
        Contest c = new Contest("100.00", 1, "A", List.of("100.00"));
        assertEquals("100.00 = 100.00", c.getScore());
    }

    @Test
    void contestGetScore_multipleDives() {
        Contest c = new Contest("448.68", 1, "A & B",
                List.of("57.00", "49.80", "87.36", "91.80", "66.60", "96.12"));
        assertEquals("57.00 + 49.80 + 87.36 + 91.80 + 66.60 + 96.12 = 448.68", c.getScore());
    }

    @Test
    void contestGetScore_noDives() {
        Contest c = new Contest("0.00", 1, "A", new ArrayList<>());
        assertEquals("0.00", c.getScore());
    }

    @Test
    void contestToString_format() {
        Contest c = new Contest("448.68", 1, "ZHAO Renjie & YANG Zhihao",
                List.of("57.00", "49.80", "87.36", "91.80", "66.60", "96.12"));
        assertEquals("Full Name:ZHAO Renjie & YANG Zhihao\nRank:1\n"
                + "Score:57.00 + 49.80 + 87.36 + 91.80 + 66.60 + 96.12 = 448.68\n-----", c.toString());
    }
}
