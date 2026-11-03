package com.west2online.dwasearch.model;

/**
 * 选手（运动员）。
 *
 * 对应 data.json 顶层 players 数组中的一个元素，例如：
 *   { "country": "Australia", "lastName": "COLE", "firstName": "Ellie", "gender": "Female" }
 *
 * 注意：这个类只负责「装数据」，不负责「输出格式」。
 * 输出的那几行（Full Name / Gender / Country）由查询模块负责拼装。
 */
public class Athlete {

    private String country;   // 国籍，如 "Australia"
    private String lastName;  // 姓，如 "COLE"（对应接口的 PreferredLastName）
    private String firstName; // 名，如 "Ellie"（对应接口的 PreferredFirstName）
    private String gender;    // 性别，取值 "Male" 或 "Female"

    // 无参构造方法：Jackson 把 JSON 转成对象时需要它
    public Athlete() {
    }

    // 全参构造方法：抓取脚本和测试里手动创建对象时用
    public Athlete(String country, String lastName, String firstName, String gender) {
        this.country = country;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
