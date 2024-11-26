import DWA.*;
import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        /*File f = new File("src/main/resources/DWAData/Men 10m Synchronised.json");
        String str = FileUtils.readFileToString(f, "utf-8");
        ArrayList<Result> e = DWASearch.DWASFiles.parseResultsList("Men 10m Synchronised.json",str);
        for(Result r : e){
            System.out.println(JSON.toJSONString(r));
        }*/
        //DWASearch.DWASFiles f = new DWASearch.DWASFiles(new File("input.txt"), new File("output.txt"));
        File file = new File("src/main/resources/DWASearchData/Men 10m Synchronised.json");
        String jsonStr = FileUtils.readFileToString(file, "utf-8");
        ArrayList<Result> r = new ArrayList<>(JSON.parseArray(jsonStr, Result.class));
        for (Result r1 : r) {
            System.out.println(r1.getName());
        }
    }

}

