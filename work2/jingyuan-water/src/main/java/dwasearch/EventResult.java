package dwasearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 一名（双人项目为一对）选手在某个项目决赛中的成绩。
 *
 * <p>{@code dives} 与 {@code total} 用 String 保存：输出时再交给 BigDecimal 格式化，
 * 避免二进制浮点误差。
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResult {

    /** 名次：1、2、3 ... */
    public int rank;

    /** 姓名。单人项目为 "CHEN Jia"，双人项目为 "CHEN Jia / CHEN Yiwen" */
    public String fullName;

    /** 国家三字代码，例如 "CHN" */
    public String countryCode;

    /** 各跳得分，按出场顺序排列，例如 ["73.50", "74.40", ...] */
    public List<String> dives;

    /** 总分，例如 "374.40" */
    public String total;
}
