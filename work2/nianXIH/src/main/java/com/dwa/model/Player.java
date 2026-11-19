// 选手数据模型 —— 对应 data.json 中 players 数组的每个元素，包含 fullName（全名）、gender（性别）、country（国籍）、countryCode（国籍代码）四个字段；
// 需使用 Jackson @JsonIgnoreProperties 注解忽略未知字段，并提供 getLastName() 方法提取姓氏用于排序。
package com.dwa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    private String fullName;
    private String gender;
    private String country;
    private String countryCode;

    public Player() {}

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    /**
     * 提取选手的 Last Name（姓氏）。
     * 数据格式为 "LASTNAME Firstname" 或 "COMPOUND LASTNAME Firstname"，
     * 姓氏部分全部大写，名字部分首字母大写。找到第一个非全大写的词即可确定姓氏边界。
     */
    public String getLastName() {
        if (fullName == null) return "";
        String[] words = fullName.split(" ");
        StringBuilder lastName = new StringBuilder();
        for (String word : words) {
            if (word.equals(word.toUpperCase()) && !word.isEmpty()) {
                if (lastName.length() > 0) lastName.append(' ');
                lastName.append(word);
            } else {
                break;
            }
        }
        return lastName.length() > 0 ? lastName.toString() : fullName;
    }
}
