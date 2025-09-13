package Data.AthleteInfoProcess;

import DAWSearch.jar.Athletes;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessAthletes {
    //解析Athletes.json文件
    public List<Athletes> parser() throws RuntimeException {
        List<Athletes> athletes = new ArrayList<>();
        //创建jsonFactory来处理j大型son文件
        JsonFactory jsonFactory = new JsonFactory();
        //指定json文件绝对路径
        File jsonFile = new File("D:\\java\\code\\work2\\src\\main\\java\\Data\\AthleteInfo\\Athletes.json");
        try (JsonParser jsonParser = jsonFactory.createParser(jsonFile)){
            //检测json文件是否以数组开始
            if (jsonParser.nextToken() !=  JsonToken.START_ARRAY) {
                throw new JsonParseException(jsonParser, "JSON array expected");
            }
            //遍历数组中的每个对象
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                //检查当前是否是一个对象开始
                if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                    //处理单个对象
                    processCountryObject(jsonParser,athletes);
                }
            }
        } catch (JsonParseException e) {
            throw new RuntimeException("JSON parsing error"+e.getMessage(),e);
        } catch (IOException e) {
            throw new RuntimeException("IO error"+e.getMessage(),e);
        }
        return athletes;
    }
    //processCountryObject的定义
    private void processCountryObject(JsonParser jsonParser,List<Athletes> athletes) throws IOException {
        String countryName = null;
        String countryCode = null;
        //遍历对象的每个字段
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            jsonParser.nextToken();
            switch (fieldname) {
                case "CountryName":
                    countryName = jsonParser.getText();
                    break;
                case "CountryCode":
                    countryCode = jsonParser.getText();
                    break;
                case "Participations":
                    //处理Participations数组
                    processParticipationsArray(jsonParser,athletes,countryName,countryCode);
                    break;
                default:
                    //忽略未知字段
                    jsonParser.skipChildren();
            }
        }
    }
    //processParticipationsArray的定义
    private void processParticipationsArray(JsonParser jsonParser,List<Athletes> athletes,String countryName,String countryCode) throws IOException {
        //检查是否是一个对象开头
        if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
            throw new JsonParseException(jsonParser, "Expected content to be an array");
        }
        //遍历数组中的每个对象
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            //检查当前是否是一个对象的开始
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                processParticipationObject(jsonParser,athletes,countryName,countryCode);
            }
        }
    }
    //processParticipationObject的定义
    private void processParticipationObject(JsonParser jsonParser,List<Athletes> athletes,String countryName,String countryCode) throws IOException {
        String preferredLastName=null;
        String preferredFirstName=null;
        int gender=0;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            //根据字段提取值
            switch (fieldName) {
                case "PreferredLastName":
                    preferredLastName = jsonParser.getText();
                    break;
                case "PreferredFirstName":
                    preferredFirstName = jsonParser.getText();
                    break;
                case "Gender":
                    gender = jsonParser.getIntValue();
                    break;
                case "Disciplines":
                    processDisciplinesArray(jsonParser,athletes,countryName,countryCode,preferredLastName,preferredFirstName,gender);
                    break;
                default:
                    jsonParser.skipChildren();
            }
        }

    }
    //processDisciplinesArray的定义
    private void processDisciplinesArray(JsonParser jsonParser,List<Athletes> athletes,String countryName,String countryCode,String preferredLastName,String preferredFirstName,int gender) throws IOException {
        if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
            throw new JsonParseException(jsonParser, "JSON array expected");
        }
        /*while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                processDisciplineObject(jsonParser,athletes,countryName,countryCode,preferredLastName,preferredFirstName,gender);
            }
        }*/
        Athletes athlete = new Athletes(countryName,countryCode,preferredLastName,preferredFirstName,gender);
        athletes.add(athlete);
    }
    //processDisciplineObject的定义
    /*private void processDisciplineObject(JsonParser jsonParser,List<Athletes> athletes,String countryName,String countryCode,String preferredLastName,String preferredFirstName,int gender) throws IOException {
        String disciplineName = null;
        String phaseName = null;

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            jsonParser.nextToken();
            switch (fieldname) {
                case "DisciplineName":
                    disciplineName = jsonParser.getText();
                    break;
                case "PhaseName":
                    phaseName = jsonParser.getText();
                    break;
                default:
                    jsonParser.skipChildren();
            }
        }
    }*/
}
