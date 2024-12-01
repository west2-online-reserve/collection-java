package DWASearch;

import DWA.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DWASFiles {
    //这个类用来处理数据, 帮助实现jar包功能
    //以下是指定的参数
    private File outputFile;//输出结果文件
    private File inputFile;//输入命令文件
    private static ArrayList<Integer> orders = new ArrayList<>();//命令列表
    //这是原数据库
    private final File[] resultsJsons = new File("src/main/resources/DWAData").listFiles();
    //这是新数据库
    private File data = new File("src/main/resources/DWASearchData");
    //以下是命令和读取文件名的对照
    public static final Map<Integer, String> NumOfOrder = Map.of(
            111, "Men 1m Springboard.json",
            113, "Men 3m Springboard.json",
            133, "Men 3m Synchronised.json",
            120, "Men 10m Platform.json",
            130, "Men 10m Synchronised.json",
            211, "Women 1m Springboard.json",
            213, "Women 3m Springboard.json",
            233, "Women 3m Synchronised.json",
            220, "Women 10m Platform.json",
            230, "Women 10m Synchronised.json"

    );

    public static Map<Integer, String> getNumOfOrder() {
        return NumOfOrder;
    }

    public File getData() {
        return data;
    }

    public void setData(File data) {
        this.data = data;
    }

    public File[] getResultsJsons() {
        return resultsJsons;
    }

    //初始化数据库
    private void getReady() throws RuntimeException {
        //初始化具体指:
        // 1. 准备一个放处理过数据的文件夹(data)
        try {
            // 2.指定目录不存在则创建
            if (!data.exists()) {
                if (!data.mkdirs())
                    throw new RuntimeException("指定文件夹不存在,且尝试创建该文件夹失败");
            }
            // 3.解析原数据并丢进文件夹
            prepareResults(data);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void prepareAthletes(File ath) throws Exception {
        String athName = "athletes.json";
        //以字符串读取原json
        String jsonStr = getJsonStr(athName);
        ArrayList<Athlete> athletes = parseAthletesList(jsonStr);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ath.getPath() + "/athletes.json"))) {
            writer.write(JSONArray.toJSONString(athletes));
        }
    }

    public static ArrayList<Athlete> parseAthletesList(String jsonStr) throws Exception {
        ArrayList<Athlete> athletes = new ArrayList<>();
        //fastjson嵌套数组的反序列化我还没搞懂(涉及到什么泛型, 时间紧任务重, 先摆)(序列化也不懂
        //于是, 尝试把每个发生嵌套的数组当作字符串读取再次反序列化
        //所以因为每种不同结构要重新写而变得很复杂(将就用吧
        //如下:
        //1. parseArray()把最外层序列化(内层数组Participations[]当作字符串)
        ArrayList<Country> countries = new ArrayList<>(JSON.parseArray(jsonStr, Country.class));
        //2. 循环来处理内层数组
        for (Country country : countries) {
            //2.1 反序列化读取到的内嵌数组字符串(每个国家元素都要单独反序列化):
            ArrayList<Athlete> athParticipations = new ArrayList<>(JSON.parseArray(country.getAthletes().toString(), Athlete.class));
            //2.2 把反序列化的列表加回去:
            for (Athlete athlete : athParticipations) {
                //保存上一层反序列化国家元素获取到的国家名
                athlete.setCountry(country.getCountryName());
                //加回去
                athletes.add(athlete);
            }
        }
        //最后排序
        Collections.sort(athletes);
        return athletes;
    }


    private void prepareResults(File dir) throws Exception {
        //这个方法的作用是提取扒下来的json的关键信息, 保存在某个文件夹(res)
        if (resultsJsons == null) {
            //扒下来的数据没了
            throw new IOException("应读取的DWAData不存在");
        }
        //逐个读取每个项目文件
        for (File r : resultsJsons) {
            if (r.getName().equals("athletes.json")) {
                //运动员json与比赛结果json结构不同, 所以单独写了一个方法
                prepareAthletes(dir);
                continue;
            }
            //这是输出处理结果的File
            File resultOutput = new File(dir.getPath() + "/" + r.getName());//放在指定的dir下
            resultOutput.createNewFile();
            String jsonStr = getJsonStr(r.getName());
            ArrayList<Result> results = parseResultsList(jsonStr);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultOutput))) {
                writer.write(JSONArray.toJSONString(results));
            }
        }
    }

    public static ArrayList<Result> parseResultsList(String jsonStr) throws Exception {
        //正如parseAthletesList()注释, 以下代码和该方法原理其实是一样的
        Event event = JSON.parseObject(jsonStr, Event.class);
        ArrayList<Heat> heats = new ArrayList<>(JSON.parseArray(event.getHeats(), Heat.class));
        //嵌套了好几种, 专门写个方法来剥洋葱
        ArrayList<Result> results = new ArrayList<>();
        for (Heat heat : heats) {
            results.addAll(getResultsFromHeats(heat));
        }
        return results;
    }

    private String getJsonStr(String resourcesName) throws IOException {
        String jsonStr = FileUtils.readFileToString(new File("src/main/resources/DWAData/" + resourcesName));
        return jsonStr;
    }

    private static ArrayList<Result> getResultsFromHeats(Heat heat) {
        ArrayList<Result> results = new ArrayList<>();
        results = new ArrayList<>(JSON.parseArray(heat.getResults(), Result.class));
        for (Result result : results) {
            ArrayList<Dive> dives = new ArrayList<>(JSON.parseArray(result.getDivesJson(), Dive.class));
            for (Dive dive : dives) {
                result.getDives().add(dive.getDivePoints());
                result.setDivesJson(null);
                result.setName(heat.getName());
            }
        }
        return results;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }


    public ArrayList<Integer> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Integer> orders) {
        DWASFiles.orders = orders;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }


    public DWASFiles(File inputFile, File outputFile) throws IOException {
        if (!ifTxt(inputFile.getName(), outputFile.getName()))
            throw new InputError("文件格式错误, 请使用txt文件");
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        try {
            //读取命令
            readFileForOrders(inputFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (!outputFile.exists()||!outputFile.isFile()) {
            if (!outputFile.createNewFile())
                throw new InputError("选择的输出文档不存在, 且创建失败");
        }
        getReady();
    }

    public DWASFiles(File inputFile, File outputFile, File data) throws IOException {
        //指定data的版本
        if (!ifTxt(inputFile.getName(), outputFile.getName()))
            throw new InputError("文件格式错误, 请使用txt文件");
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        try {
            readFileForOrders(inputFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (!outputFile.exists()||!outputFile.isFile()) {
            if (!outputFile.createNewFile())
                throw new InputError("选择的输出文档不存在, 且创建失败");
        }
        //在这里
        this.data = data;
        getReady();
    }


    public static boolean ifExists(File... file) {
        boolean exists = true;
        for (File f : file) {
            if (!f.exists()) {
                exists = false;
                break;
            }
        }
        return exists;
    }

    public static boolean ifTxt(String... name) {
        for (String name1 : name) {
            if (!name1.matches(".*\\b\\.txt")) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> readFileForOrders(File inputFile) throws Exception {
        if (!ifExists(inputFile)||!inputFile.isFile()) {
            throw new InputError("输入文档为空");
        }
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            while ((line = reader.readLine()) != null) {
                orders.add(getNumOrders(line));
            }
            if (orders.isEmpty()) {
                throw new InputError("没有读取到有效命令");
            }
        }
        return orders;
    }

    public static int getNumOrders(String inputLine) {
        //特殊情况,直接用1记录
        if (inputLine.equals("players")) {
            return 1;
        }
        //其它情况
        int numOrders = 0;//numOrders默认 0 ,表示读取到了无法识别的指令(Error)
        //用正则来获取每部分命令
        Pattern p = Pattern.compile("^result (men|women) (1m springboard|3m springboard|10m platform|3m synchronised|10m synchronised)( detail|.*|)");
        Matcher m = p.matcher(inputLine);
        //先看是不是有脏东西跟在result后面
        if (inputLine.matches("result .*") && !inputLine.matches(".*(1m springboard|3m springboard|10m platform|3m synchronised|10m synchronised|detail)"))
            return -1;//有脏东西就结束
        //如果命令格式正确
        if (m.matches()) {
            //以下是检测性别
            if (m.group(1).equals("men"))
                numOrders += 100;
            else numOrders += 200;
            //以下是检测项目
            String content = m.group(2);
            switch (content) {
                case "1m springboard" -> numOrders += 11;
                case "3m springboard" -> numOrders += 13;
                case "10m platform" -> numOrders += 20;
                case "3m synchronised" -> numOrders += 33;
                default ->
                    //(10m synchronised)
                        numOrders += 30;
            }
            //以下是看是否detail
            if (m.group(3).equals(" detail"))
                numOrders = -numOrders;//取相反数来记录
        }
        return numOrders;
    }

}
