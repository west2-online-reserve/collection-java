package dwasearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 选手信息，字段与 data.json 中的 players 元素一一对应。
 *
 * <p>{@code ignoreUnknown = true}：JSON 中多出的字段（如 personId）直接忽略，不报错。
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    /** 全名，例如 "COLE Ellie"（官方数据为姓氏在前、大写） */
    public String fullName;

    /** 姓氏，例如 "COLE"；players 排序的次要关键字 */
    public String lastName;

    /** 名字，例如 "Ellie" */
    public String firstName;

    /** 性别："Female" 或 "Male" */
    public String gender;

    /** 国家全称，例如 "Australia"；players 排序的首要关键字 */
    public String country;

    /** 国家三字代码，例如 "AUS" */
    public String countryCode;
}
