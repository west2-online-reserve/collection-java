import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResultFormatter {
    // 把输出接进out里面
    public static String formatResult(String[] r){
        // 用split进行拆分，以,为依据
        String[] scores = r[2].split(",");
        BigDecimal sum = BigDecimal.ZERO;  // 这里不能用空指针，得赋值为0，不然会NPE
        StringBuilder sb = new StringBuilder();
        // 把scores里面的分数转为BigDecimal在进行计算
        for (int i = 0; i < scores.length; i++) {
            sum = sum.add(new BigDecimal(scores[i]));
        }
        // 这里得重新引用，不能直接sum.setScale
        sum = sum.setScale(2, RoundingMode.HALF_UP);    // 和的展示形态

        // 前面两个元素
        sb.append("Full Name:").append(r[0]).append("\nRank:")
                .append(r[1]).append("\nScore:");

        // 这是scores部分
        for (int j = 0; j < scores.length; j++) {
            if (j > 0) sb.append(" + ");
            sb.append(scores[j]);
        }

        // 后面两个元素加分割线,这里的数据得用自己算的，就是sum
        sb.append(" = ").append(sum).append("\n-----\n");
        return sb.toString();
    }
}
