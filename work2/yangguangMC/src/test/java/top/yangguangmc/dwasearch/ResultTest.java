package top.yangguangmc.dwasearch;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultTest {
    @Test
    void testCompareTo() {
        Result r1 = new Result("First Last", 1, List.of(new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("10.00")));
        Result r2 = new Result("First Last", 2, List.of(new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("9.99")));
        Result r3 = new Result("First Last", 3, List.of(new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("9.98")));
        List<Result> results = new ArrayList<>(3);
        results.add(r3);
        results.add(r2);
        results.add(r1);
        results.sort(null);
        assertEquals(r1, results.get(0));
        assertEquals(r2, results.get(1));
        assertEquals(r3, results.get(2));
    }

    @Test
    void testPrint() {
        IODataSet ioDataSet = new IODataSet();
        new Result("First Last", 114, List.of(new BigDecimal("1.00"), new BigDecimal("2.00"), new BigDecimal("3.00"), new BigDecimal("4.00"), new BigDecimal("5.00"))).print(ioDataSet);
        String str = ioDataSet.getOutputs();
        assertEquals("""
                Full Name:First Last
                Rank:114
                Score:1.00 + 2.00 + 3.00 + 4.00 + 5.00 = 15.00
                -----
                """, str);
    }
}
