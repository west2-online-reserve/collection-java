# The Introduction Of My Project 
## *一.程序设计思路*
### 1.从网页中爬取数据
![如图所示](images/json.png)
### 2.数据解析与处理
![](images/1.jpg)
![](images/2.jpg)
![](images/3.jpg)
## *二.接口设计与实现过程*
### 1.接口设计
#### 1.1 CoreInterface接口
````java
public interface CoreInterface {
    //显示所有选手的信息
    public void showPlayerInfo(String fileName);
    //显示所有项目的结果(简略)
    public void showProjectResult(String fileName);
    //显示所有项目的结果(详细)
    public void showProjectResultDetail(String fileName);

}
````
#### 1.2 ParserInterface接口
````java
public interface ParseInterface {
    //解析运动员信息json文件
    public void parserPlayerInfo(String fileName);
    //parser所有项目的决赛结果（简略）
    public void parserResult(String fileName);
    //parser所有项目的结果(详细）
    public void parserResultDetail(String fileName);

}
````
### 2.接口实现过程(在Core类中接入CoreInterface接口和ParserInterface接口)
#### 2.1 CoreInterface接口实现（三个方法）
````java
//显示所有选手的信息
    public void showPlayerInfo(String fileName){
        File file = new File(fileName);
        parserPlayerInfo(fileName);
    }
    //显示所有项目的结果(简略)
    public void showProjectResult(String fileName){
        File file = new File(fileName);
        parserResult(fileName);
    }
    //显示所有项目的结果(详细)
    public void showProjectResultDetail(String fileName){
        File file = new File(fileName);
        parserResultDetail(fileName);
    }
````
#### 2.2 ParserInterface接口实现（三个方法，但是在Core中运用了Player内嵌类）
````java
//解析运动员信息json文件
//解析运动员信息json文件
public void parserPlayerInfo(String fileName) {
    File file = new File(fileName);
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Athlete> athletes = new ArrayList<>();
    try( BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\java\\code\\ParserDate\\src\\main\\java\\Output.txt",true));){
        JsonNode rootNode = objectMapper.readTree(file);
        for (JsonNode node : rootNode) {
            String country = node.get("CountryName").asText();
            JsonNode participationNode = node.get("Participations");
            if (participationNode!=null && participationNode.isArray()) {
                for (JsonNode participation : participationNode) {
                    int gender = participation.get("Gender").asInt();
                    String preferredLastName = participation.get("PreferredLastName").asText();
                    String preferredFirstName = participation.get("PreferredFirstName").asText();
                    athletes.add(new Athlete((preferredLastName+" "+preferredFirstName),gender,country));
                }
            }
        }
        Collections.sort(athletes,new Comparator<Athlete>(){
            @Override
            public int compare(Athlete a1, Athlete a2) {
                int countryCompare = a1.getcountry().compareTo(a2.getcountry());
                //先以国家首字母排序
                if (countryCompare!=0)
                    return countryCompare;
                return a1.getFullName().compareTo(a2.getFullName());
            }
        });
        for (Athlete athlete : athletes){
            bw.write("FullName:" +  athlete.getFullName() + "\nGender:" + (athlete.getGender()==0 ? "MALE" : "FEMALE") + "\nCountry:" + athlete.getcountry() + "\n");
            bw.write("-----\n");
        }
    } catch (Exception e){
        e.printStackTrace();
    }
}

//parser所有项目的决赛结果（简略）
public void parserResult(String fileName) {
    File file = new File(fileName);
    ObjectMapper objectMapper = new ObjectMapper();
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Output.txt", true))) {
        JsonNode rootNode = objectMapper.readTree(file);
        JsonNode heatsNode = rootNode.get("Heats");
        if (heatsNode!=null && heatsNode.isArray()) {
            for (JsonNode heat : heatsNode) {
                String heatName = heat.get("Name").asText();
                if (heatName.equals("Final")){
                    JsonNode resultNode = heat.get("Results");
                    if  (resultNode!=null && resultNode.isArray()) {
                        for (JsonNode result : resultNode) {
                            String totalPoints = result.get("TotalPoints").asText();
                            String fullName = result.get("FullName").asText();
                            String rank = result.get("Rank").asText();
                            ArrayList<String> divePoints = new ArrayList<>();
                            JsonNode diveNode = result.get("Dives");
                            if (diveNode!=null && diveNode.isArray()) {
                                for (JsonNode dive : diveNode) {
                                    divePoints.add(dive.get("DivePoints").asText());
                                }
                            }
                            bw.write("FullName:"+fullName+"\nRank:"+rank+"\n");
                            for (int i=0;i<divePoints.size();i++) {
                                bw.write(divePoints.get(i) + "+");
                                if (i==divePoints.size()-1)
                                    bw.write(divePoints.get(i) + "=");
                            }
                            bw.write(totalPoints + "\n" + "-----\n");
                        }
                    }
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

//parser所有项目的结果(详细）
public void parserResultDetail(String fileName) {
    File file = new File(fileName);
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Player> athletes = new ArrayList<>();
    Map<String,Player> playerMap = new HashMap<>();
    String fullName;
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Output.txt", true))) {
        JsonNode rootNode = objectMapper.readTree(file);
        JsonNode heatsNode = rootNode.get("Heats");
        if (heatsNode!=null && heatsNode.isArray()) {
            for (JsonNode heat : heatsNode) {
                //表示比赛的名字
                String heatName = heat.get("Name").asText();
                JsonNode resultsNode = heat.get("Results");
                if (resultsNode!=null && resultsNode.isArray()) {
                    for (JsonNode result : resultsNode) {
                        String name = result.get("FullName").asText();
                        //给双人项目组的名字进行排序
                        if (name.contains("/")){
                            String[] parts = name.split("/");
                            Arrays.sort(parts,Comparator.comparing(s->{
                                String[] names = s.split(" ");
                                return names.length>1 ? names[0] : s;
                            }));
                            fullName = String.join(" & ",parts);
                        } else {
                            fullName = name;
                        }
                        //检查是否已经存在该运动员（们）
                        Player player = playerMap.get(fullName);
                        if (player==null) {
                            player = new Player();
                            player.fullName = fullName;
                            player.ranks = new String[]{"*","*","*"};
                            player.preliminaryScore = "*";
                            player.semifinalScore = "*";
                            player.finalScore = "*";
                        }
                        //获取排名和分别对应的总分数
                        String rank = result.get("Rank").asText();
                        if (heatName.equals("Final")) {
                            player.ranks[2]=rank;
                            player.finalScore = result.get("TotalPoints").asText();
                            JsonNode divesNode = result.get("Dives");
                            if (divesNode!=null && divesNode.isArray()) {
                                int i = 0;
                                for (JsonNode dive : divesNode) {
                                    player.divesScores3[i] = "*";
                                    if (dive.get("DivePoints").asText()!=null) {
                                        player.divesScores3[i] = dive.get("DivePoints").asText();
                                    }
                                    i++;
                                }
                            }
                        } else if (heatName.equals("Semifinal")) {
                            player.ranks[1]=rank;
                            player.semifinalScore = result.get("TotalPoints").asText();
                            JsonNode divesNode = result.get("Dives");
                            if (divesNode!=null && divesNode.isArray()) {
                                int i = 0;
                                for (JsonNode dive : divesNode) {
                                    player.divesScores2[i] = "*";
                                    if (dive.get("DivePoints").asText()!=null) {
                                        player.divesScores2[i] = dive.get("DivePoints").asText();
                                    }
                                    i++;
                                }
                            }
                        }  else if (heatName.equals("Preliminary")) {
                            player.ranks[0]=rank;
                            player.preliminaryScore = result.get("TotalPoints").asText();
                            JsonNode divesNode = result.get("Dives");
                            if (divesNode!=null && divesNode.isArray()) {
                                int i = 0;
                                for (JsonNode dive : divesNode) {
                                    player.divesScores1[i] = "*";
                                    if (dive.get("DivePoints").asText()!=null) {
                                        player.divesScores1[i] = dive.get("DivePoints").asText();
                                    }
                                    i++;
                                }
                            }
                        }
                        playerMap.put(fullName,player);
                    }
                }
            }
            athletes.addAll(playerMap.values());
            //排序
            Collections.sort(athletes,new Comparator<Player>(){
                @Override
                public int compare(Player a1,Player a2){
                    if (a1.ranks[0].equals("*")) {
                        return 1;  // 带*的排在后面
                    }
                    if (a2.ranks[0].equals("*")) {
                        return -1;  // 不带*的排在前面
                    }
                    int p1 = Integer.parseInt(a1.ranks[0]);
                    int p2 = Integer.parseInt(a2.ranks[0]);
                    return Integer.compare(p1, p2);
                }
            });
        }
        //输入结果
        for (Player player : athletes) {
            bw.write("FullName:" + player.fullName + "\n");
            bw.write("Rank:" + player.ranks[0] + " | " + player.ranks[1] + " | " + player.ranks[2] + "\n");
            bw.write("Final Score:"+player.toString(player.divesScores3) + player.finalScore + "\n");
            bw.write("Semifinal Score:"+player.toString(player.divesScores2) + player.semifinalScore + "\n");
            bw.write("Preliminary Score:"+player.toString(player.divesScores1) + player.preliminaryScore + "\n");
            bw.write("-----\n");
        }
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
````
## *三.性能改进*
### 1.耗时的原因
#### 1.1过多使用IO流（BufferedWriter类）
#### 1.2用jackson解析json文件，导致运行时间过长。
#### 20次的运行时间为：![](images/4.png)
### 2.优化方案：可以使用StringBuilder，将内容写入到内存中，然后一次性写入到文件中。
#### 优化后20次的运行时间为：![](images/5.png)
## *四.单元测试展示*
````java
class CoreTest {

    private Core core;
    private final String TEST_OUTPUT_PATH = "D:\\java\\code\\ParserDate\\src\\main\\java\\Output.txt"; // 与Core中输出路径一致
    private final String TEST_PLAYER_JSON = "D:\\java\\code\\ParserDate\\src\\main\\java\\Data\\athletes.json"; // 测试用运动员JSON
    private final String TEST_RESULT_JSON = "D:\\java\\code\\ParserDate\\src\\main\\java\\Data\\Men 1m SpringBoard.json"; // 测试用结果JSON

    private static String readFile(String filename) throws IOException {
        if (!Files.exists(Paths.get(filename))){
            fail("文件未打开" + filename);
        }
        return new String(Files.readAllBytes(Paths.get(filename)));
    }


    @Test
    void parserPlayerInfo() {
        core.parserPlayerInfo(TEST_PLAYER_JSON);
        //读取文件内容
        try {
            String content = CoreTest.readFile(TEST_OUTPUT_PATH);
            assertTrue(content.contains("FullName:HART Alexander"));
            assertTrue(content.contains("Gender:MALE"));
            assertTrue(content.contains("Country:Austria"));
            assertTrue(content.contains("-----"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parserResult() {
        core.parserResult(TEST_RESULT_JSON);
        try {
            String content = CoreTest.readFile(TEST_OUTPUT_PATH);
            int num1_player = content.indexOf("FullName:DIXON Matthew");
            int num2_player = content.indexOf("FullName:HOULDEN Jordan Christopher");
            assertTrue(num1_player<num2_player);
            assertTrue(content.contains("Rank:1"));
            assertTrue(content.contains("58.50+61.50+58.50+67.50+57.60+69.75+69.75=373.35"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parserResultDetail() {
        core.parserResultDetail(TEST_RESULT_JSON);
        try {
            String content = CoreTest.readFile(TEST_OUTPUT_PATH);
            int num1_player = content.indexOf("FullName:HOULDEN Jordan Christopher");
            int num2_player = content.indexOf("DIXON Matthew");
            assertTrue(num1_player<num2_player);
            assertTrue(content.contains("Rank:1 | * | 2"));
            assertTrue(content.contains("Final Score:351.50"));
            assertTrue(content.contains("Semifinal Score:*"));
            assertTrue(content.contains("Preliminary Score:368.10"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
````
## *五.异常处理说明*
### 1.在Core的parserPlayerInfo（）方法中使用了try-with-resources语句，该语句会自动关闭文件流，避免出现异常时未关闭的问题。
### 2.在Core的parserResult（）方法中，使用了BufferedReader类读取文件，该类也会自动关闭文件流，避免出现异常时未关闭的问题。
### 3.在Core的parserResultDetail（）方法中，使用了BufferedReader类读取文件，该类也会自动关闭文件流，避免出现异常时未关闭的问题。