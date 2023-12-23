package bonus.domain;
/**
 * 此类为城市信息类
 * @author 1293978818
 */
public class City {

    private int cityId;
    private String cityName;
    private double latitude;
    private double longitude;
    private String location;
    public int getCityId() {
        return cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public City() {
    }
    public City(int cityId, String cityName, double latitude, double longitude, String location) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }
    @Override
    public String toString() {
        return cityName + "\t\t" + longitude + "\t" + latitude + "\t" + location;
    }

    
}
