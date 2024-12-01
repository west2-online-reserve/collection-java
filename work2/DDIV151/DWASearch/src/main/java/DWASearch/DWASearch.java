package DWASearch;

import DWA.Athlete;
import DWA.DetailedResult;
import DWA.ProcessedAthlete;
import DWA.Result;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class DWASearch {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar DWASearch.DWASearch <path to input file> <path to output file>");
            System.exit(0);
        }
        //接收用户输入的参数(文件名)
        File inputTxt = new File(args[0]);
        File outputTxt = new File(args[1]);
        if (!DWASFiles.ifExists(inputTxt, outputTxt)) {
            System.out.println("File Not Found");
        }
        //读取文件
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputTxt, true))) {
            ArrayList<Integer> orders = DWASFiles.readFileForOrders(inputTxt);
            //运行到这里,cmd的运行指令合法  输入文档存在且解析成功
            Map<Integer, String> FileMap = DWASFiles.getNumOfOrder();
            //输出
            printByOrders(FileMap, orders, outputTxt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printByOrders(Map<Integer, String> fileMap, ArrayList<Integer> orders, File output) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(output, true));
            for (int i : orders) {
                if (i == 0) {
                    bw.write("Error" + "\n-----\n");
                    bw.flush();
                } else if (i == -1) {
                    bw.write("N/A" + "\n-----\n");
                    bw.flush();
                } else if (i == 1) {
                    String name = "athletes.json";
                    String jsonStr = DWASearch.getResourcesStr(name);
                    ArrayList<Athlete> athletes = new ArrayList<>(JSON.parseArray(jsonStr, ProcessedAthlete.class));
                    for (Athlete athlete : athletes) {
                        bw.write(athlete.toString() + "\n-----\n");
                        bw.flush();
                    }
                } else {
                    //detailed
                    if (i < 0) {
                        String name = fileMap.get(-i);
                        String jsonStr = DWASearch.getResourcesStr(name);
                        ArrayList<Result> results = new ArrayList<>(JSON.parseArray(jsonStr, Result.class));
                        ArrayList<DetailedResult> detailedResults = new ArrayList<>();
                        for1:
                        for (Result result : results) {
                            for (DetailedResult resD : detailedResults) {
                                if (resD.getName().equals(result.getAthleteFullName())) {
                                    continue for1;
                                }
                            }
                            detailedResults.add(new DetailedResult(result, results));
                        }
                        results.clear();//失去利用价值的下场
                        Collections.sort(detailedResults);
                        for (DetailedResult resD : detailedResults) {
                            bw.write(resD.toString() + "\n-----\n");
                            bw.flush();
                        }

                    }
                    //普通
                    else {
                        String name = fileMap.get(i);
                        String jsonStr = DWASearch.getResourcesStr(name);
                        ArrayList<Result> results = new ArrayList<>(JSON.parseArray(jsonStr, Result.class));
                        for (Result result : results) {
                            if (!result.getName().equals("Finals") && !result.getName().equals("Final")) {
                                break;
                            }
                            bw.write(result + "\n-----\n");
                            bw.flush();
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String getResourcesStr(String name) throws IOException {
        String jsonStr;
        try (InputStream is = DWASearch.class.getClassLoader().getResourceAsStream(name); BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            jsonStr = builder.toString();
        }
        return jsonStr;
    }

}

