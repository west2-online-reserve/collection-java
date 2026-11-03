package com.dwa.service;

import com.dwa.model.DivingResult;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ResultService {
    // 8个合法项目名称
    private static final Set<String> VALID_EVENTS = new HashSet<>(Arrays.asList(
        "women 3m springboard",
        "women 10m platform",
        "women 3m synchronised",
        "women 10m synchronised",
        "men 3m springboard",
        "men 10m platform",
        "men 3m synchronised",
        "men 10m synchronised"
    ));

    public static void printResults(List<DivingResult> results, String eventName) {
        // 1.异常处理
        if (!VALID_EVENTS.contains(eventName)) {
            System.out.println("N/A");
            System.out.println("----");
            return; // 结束该方法 后续操作不会执行
        }

        // 2. for-each循环遍历，并按 Rank 升序排序
        List<DivingResult> filtered = new ArrayList<>();
        for (DivingResult r : results) {
            if (eventName.equals(r.event)) {
                filtered.add(r);
            }
        }
        filtered.sort(Comparator.comparingInt(r -> r.rank));//根据整型属性进行排序，默认升序。r -> r.rank 提取排序的键，DivingResult对象的rank字段。

        // 3. 计算总分并格式化输出
        for (DivingResult r : filtered) {
            System.out.println("Full Name:" + r.diverName);
            System.out.println("Rank:" + r.rank);

            // 用 BigDecimal 避免浮点数精度丢失
            BigDecimal total = BigDecimal.ZERO;//表示数字0的常量，初始化
            List<String> scoreStrings = new ArrayList<>();//保存运动员每一跳的分数
            
            for (Double score : r.scores) {
                // 将 double 转为字符串再转 BigDecimal
                BigDecimal curScore = new BigDecimal(String.valueOf(score));
                total = total.add(curScore);
                scoreStrings.add(String.valueOf(score)); //用toString时null会抛出NullPointerException
            }
            
            // 拼接字符串
            String detailStr = String.join(" + ", scoreStrings);
            // 强制保留两位小数
            String finalScore = total.setScale(2, RoundingMode.HALF_UP).toString();//setScale设置保留几位小数RoundingMode舍入模式，HALF_UP标准四舍五入

            // 格式输出
            System.out.println("Score:" + detailStr + " = " + finalScore);
            System.out.println("----");
        }
    }
}