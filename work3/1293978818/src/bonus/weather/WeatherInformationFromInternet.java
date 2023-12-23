package bonus.weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import bonus.domain.Weather;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 此类用于从网络获取天气信息
 * @author 1293978818
 */
public class WeatherInformationFromInternet {

    /**
     * 此方法用于从网络获取指定城市id的天气信息
     * @param 要获取的城市id
     * @return 获取到的天气信息,若不存在则返回null
     */
    public ArrayList<Weather> getWeather(int cityId){
        String urlString = "https://devapi.qweather.com/v7/weather/3d?key=e30ea101b31942deaa7839d295a4a3bb&location=" + cityId;
        try {

            /*获得json字符串 */
            URL url = new URL(urlString);
            HttpURLConnection connection  = (HttpURLConnection)url.openConnection();
            InputStream is = connection.getInputStream();
            GZIPInputStream gzipInputStream =new GZIPInputStream(is);
            StringBuilder res=new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
            while((line = br.readLine()) != null){
                res.append(line);
            }
            String result = res.toString();

            /*解析json字符串 */
            ArrayList<Weather> weathers = new ArrayList<>();
            JSONObject jsonObject = JSONObject.fromObject(result);

            JSONArray daily = jsonObject.getJSONArray("daily");

            for(int i = 0; i < daily.size(); i++){
                Object object = daily.get(i);
                JSONObject now = JSONObject.fromObject(object.toString());
                Weather weather = new Weather();
                weather.setCityId(cityId);
                weather.setCode(i);
                weather.setFxDate(now.getString("fxDate"));
                weather.setTempMax(now.getInt("tempMax"));
                weather.setTempMin(now.getInt("tempMin"));
                weather.setTextDay(now.getString("textDay"));
                weathers.add(weather);
            }


            return weathers;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}