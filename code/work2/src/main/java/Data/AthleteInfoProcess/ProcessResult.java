package Data.AthleteInfoProcess;

import DAWSearch.jar.Athletes;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
public class ProcessResult {
    //解析json数据文件
    public List<Athletes> parser(String filePath) throws RuntimeException, IOException {
        List<Athletes> athletes = new ArrayList<>();
        JsonFactory jsonFactory = new JsonFactory();
        File jsonFile = new File(filePath);
        try (JsonParser jsonParser = jsonFactory.createParser(jsonFile)){
            /*while (jsonParser.nextToken() != null) {
                String fieldName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                switch (fieldName) {
                    case "Heats":
                        processFinal(jsonParser,athletes);
                        break;
                    default:
                        jsonParser.skipChildren();
                }
            }*/
            if (jsonParser.nextToken() == JsonToken.START_OBJECT) {
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    switch (fieldName) {
                        case "Heats":
                            processFinal(jsonParser,athletes);
                        default:
                            jsonParser.skipChildren();
                    }
                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return athletes;
    }

    //定义一个processFinal的方法
    public void processFinal(JsonParser jsonParser,List<Athletes> athletes) throws RuntimeException, IOException {
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            if (jsonParser.nextToken() == JsonToken.START_OBJECT) {
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    if ("Results".equals(fieldName)) {
                        processResultsArray(jsonParser,athletes);
                    }
                    else
                    {
                        jsonParser.skipChildren();
                    }
                }
            }
        }
    }

    //定义一个processResultsArray的方法
    public void processResultsArray(JsonParser jsonParser,List<Athletes> athletes) throws IOException {
        if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
            throw new JsonParseException(jsonParser, "Expected START_ARRAY");
        }
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                processResultsObject(jsonParser,athletes);
            }
        }
    }

    //定义一个processResultsObject的方法
    public void processResultsObject(JsonParser jsonParser,List<Athletes> athletes) throws IOException {
        String totalPoints = null;
        String[] divePoints = new String[6];
        int rank = 0;
        String fullName= null;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            switch (fieldName) {
                case "TotalPoints":
                    totalPoints = jsonParser.getText();
                    break;
                case "Dives":
                    divePoints=processDivesArray(jsonParser);
                    break;
                case "Rank":
                    rank=jsonParser.getIntValue();
                    break;
                case "FullName":
                    fullName=jsonParser.getText();
                    break;
                default:
                    jsonParser.skipChildren();
            }
        }
        Athletes athlete = new Athletes(rank,totalPoints,divePoints,fullName);
        athletes.add(athlete);
    }

    //定义一个processDivesArray的方法
    public String[] processDivesArray(JsonParser jsonParser) throws IOException {
        if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
            throw new JsonParseException(jsonParser, "Expected START_ARRAY");
        }
        String[] divePoints = new String[6];
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                divePoints= processDivesObject(jsonParser);
            }
        }
        return divePoints;
    }

    //定义一个processDivesObject的方法
    public String[] processDivesObject(JsonParser jsonParser) throws IOException {
        String[] divePoints=new String[6];
        int i=0;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT){
            String fieldName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            switch (fieldName) {
                case "DivePoints":
                    divePoints[i]=jsonParser.getText();
                    break;
                default:
                    jsonParser.skipChildren();
            }
        }
        return divePoints;
    }
}
