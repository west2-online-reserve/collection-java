package bonus.weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

import bonus.domain.City;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 此类用于从公用api中获得city对象
 * @author 1293978818
 */
public class CityInformationFromInternet {

    /**
     * 此方法根据城市姓名从公用api返回城市对象
     * @param 城市名称
     * @return 
     */
    public City getCity(String cityName){
        try {

            /*获得json字符串 */
            String urlsString = "https://geoapi.qweather.com/v2/city/lookup?key=e30ea101b31942deaa7839d295a4a3bb&location=" +  URLEncoder.encode(cityName, "UTF-8");
            URL url = new URL(urlsString);
            HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();
            InputStream is = httpUrlConnection.getInputStream();
            GZIPInputStream gzipInputStream =new GZIPInputStream(is);
            StringBuilder res=new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                res.append(line);
            }
            String result = res.toString();

            /*将json字符串转换为 */
            JSONObject jsonObject = JSONObject.fromObject(result);

            /* 如果连接未成功，返回null*/
            int responseCode = jsonObject.getInt("code");
            if(responseCode != HttpURLConnection.HTTP_OK){
                return null;
            }

            JSONArray jsonArray = jsonObject.getJSONArray("location");
            JSONObject cityJsonObject = JSONObject.fromObject(jsonArray.get(0).toString());

            City city = new City();
            city.setCityId(cityJsonObject.getInt("id"));
            city.setCityName(cityName);
            city.setLatitude(cityJsonObject.getDouble("lat"));
            city.setLocation(cityJsonObject.getString("country") + "-" + cityJsonObject.getString("adm1") + "-" + cityJsonObject.getString("adm2"));
            city.setLongitude(cityJsonObject.getDouble("lon"));

            return city;


        } catch (Exception e) {
            return null;
        }
    }
}
