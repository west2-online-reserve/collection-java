package bonus.domain;


/**
 * 此类为天气对象
 * @author 1293978818
 */
public class Weather {
    private int cityId;
    private int code;
    private String fxDate;
    private int tempMin;
    private int tempMax;
    private String textDay;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getFxDate() {
        return fxDate;
    }

    public void setFxDate(String fxDate) {
        this.fxDate = fxDate;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public Weather() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Weather(int cityId, int code, String fxDate, int tempMin, int tempMax, String textDay) {
        this.cityId = cityId;
        this.code = code;
        this.fxDate = fxDate;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.textDay = textDay;
    }

    @Override
    public String toString() {
        String result = "";
        switch (code) {
            case 0:
                result = "今";
                break;
            case 1:
                result = "明";
                break;
            case 2:
                result = "后";
                break;
            default:
                break;
        }
        return result + "天（" + fxDate + "）的天气为" + textDay + "，" + tempMin + "~" + tempMax + "摄氏度";
    }
    

    
}
