package crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.argument.*;
import crawler.parser.PlayerInfoParser;
import crawler.parser.ResultInfoDetailParser;
import crawler.parser.ResultInfoParser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DataCrawler {

    public static final String SEPARATOR="\n-----\n";

    public static final String playerInfoPath="https://api.worldaquatics.com/fina/competitions/3337/athletes?gender=&countryId=";

    public static final String resultPathPrefix="https://api.worldaquatics.com/fina/events/";

    public static final Map<ResultArgument,String> resultPathSufixMap=new HashMap<>();
    static {
        resultPathSufixMap.put(new ResultArgument("women",1,"springboard"),"108c795d-5e4f-4dc6-acea-0bc70bfd1928");
        resultPathSufixMap.put(new ResultArgument("women",3,"springboard"),"7e6bbfb2-54c8-4083-845e-ca7fc6932d7f");
        resultPathSufixMap.put(new ResultArgument("women",10,"platform"),"7b2f14f7-5e6b-44e0-8bb4-f78591e9545b");
        resultPathSufixMap.put(new ResultArgument("women",3,"synchronised"),"d545954e-8e8a-4841-8ab4-a5ea0e188380");
        resultPathSufixMap.put(new ResultArgument("women",10,"synchronised"),"f73a2ff2-96c1-4075-8a69-21d513aebfa9");
        resultPathSufixMap.put(new ResultArgument("men",1,"springboard"),"732633c2-7fcd-40f3-b3bc-203d8eec161c");
        resultPathSufixMap.put(new ResultArgument("men",3,"springboard"),"e85aed35-be49-4251-9558-a4b651f57266");
        resultPathSufixMap.put(new ResultArgument("men",10,"platform"),"26f2e7ae-fc1a-4a5e-9653-858c1dc9e95f");
        resultPathSufixMap.put(new ResultArgument("men",3,"synchronised"),"6119565c-9b6a-429a-ac0d-183a7fd37e55");
        resultPathSufixMap.put(new ResultArgument("men",10,"synchronised"),"96662b5e-200e-4f05-9565-154456cd21e4");
    }


    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static final PlayerInfoParser playerInfoParser=new PlayerInfoParser();
    public static final ResultInfoParser resultInfoParser=new ResultInfoParser();
    public static final ResultInfoDetailParser resultInfoDetailParser=new ResultInfoDetailParser();

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static void invoke(String outputPath, Arguments arguments){
        Path path = Paths.get(outputPath);
        try(BufferedWriter bW=Files.newBufferedWriter(path)){
            for(Argument argument:arguments.getArguments()){
                if(argument instanceof ErrorArgument){
                    try {
                        bW.write("Error"+SEPARATOR);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    continue;
                }

                if(argument instanceof InvalidArgument){
                    try {
                        bW.write("N/A"+SEPARATOR);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(argument instanceof PlayerInfoArgument){
                    String rawData=crawlData(playerInfoPath);
                    String playerInfo=parseData(argument,rawData);
                    bW.write(playerInfo);
                }

                if(argument instanceof ResultArgument){
                    String suffix = resultPathSufixMap.get(argument);
                    if(suffix==null){
                        bW.write("N/A"+SEPARATOR);
                        continue;
                    }
                    String rawData=crawlData(resultPathPrefix+suffix);
                    String resultInfo=parseData(argument,rawData);
                    bW.write(resultInfo);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String crawlData(String url){
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response;
        try {
             response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

    public static String parseData(Argument argument, String jsonContent){
        if(argument instanceof PlayerInfoArgument){
            return playerInfoParser.parse(jsonContent);
        }
        if(argument instanceof ResultArgument resultArgument){
            if(resultArgument.isDetail()){
                return resultInfoDetailParser.parse(jsonContent);
            }
            return resultInfoParser.parse(jsonContent);
        }
        return null;
    }

}
