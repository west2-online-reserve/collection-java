import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalTest {
    @Test
    void 五个分数求和(){
        List<BigDecimal> temp = new ArrayList<>();
        temp.add(new BigDecimal("73.50"));
        temp.add(new BigDecimal("74.40"));
        temp.add(new BigDecimal("76.50"));
        temp.add(new BigDecimal("72.00"));
        temp.add(new BigDecimal("78.00"));

        assertEquals(new BigDecimal("374.40"), BigDecimalDEMO.sum(temp));
    }

    @Test
    void 单元素(){
        List<BigDecimal> temp = new ArrayList<>();
        temp.add(new BigDecimal("1.276324"));

        assertEquals(new BigDecimal("1.28"), BigDecimalDEMO.sum(temp));
    }

    @Test
    void 空列表(){
        List<BigDecimal> temp = new ArrayList<>();

        assertEquals(new BigDecimal("0.00"), BigDecimalDEMO.sum(temp));
    }
}
