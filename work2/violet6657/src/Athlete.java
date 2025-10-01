public class Athlete {
    private String fullName;
    private String gender;
    private String country;

    Athlete(String fullName, String gender, String country) {
        this.fullName = fullName;
        this.gender = gender;
        this.country = country;
    }

    public Athlete() {
    }

    /**
     * 获取
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public String toString() {
        return "{"+fullName.charAt(0) + gender.charAt(0) +country.charAt(0) + "}";
    }
}