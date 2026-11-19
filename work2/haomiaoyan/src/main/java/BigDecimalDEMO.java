import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalDEMO {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);

        List<BigDecimal> num =new ArrayList<>();
        num.add(new BigDecimal("73.50"));
        num.add(new BigDecimal("74.40"));
        num.add(new BigDecimal("76.50"));
        num.add(new BigDecimal("72.00"));
        num.add(new BigDecimal("78.00"));

        System.out.println(sum(num));
    }

    // 进行求和的运算
    public static BigDecimal sum(List<BigDecimal> scores){
        BigDecimal temp = BigDecimal.ZERO;
        for (BigDecimal price : scores){
            temp = temp.add(price);
        }

        BigDecimal result = temp.setScale(2, RoundingMode.HALF_UP);
        return result;
    }
}
