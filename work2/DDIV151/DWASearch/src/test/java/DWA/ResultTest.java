package DWA;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    void testToString() {
        System.out.println(new Result("123","123","123",new ArrayList<Double>(),0,2).toString());
    }

    @Test
    void parsePointsString() {
        ArrayList<Double> points = new ArrayList<>();
        points.add(1.0);
        points.add(2.0);
        points.add(3.0);
        points.add(4.0);
        points.add(5.0);
        System.out.println(new Result().parsePointsString(points));
    }
}
