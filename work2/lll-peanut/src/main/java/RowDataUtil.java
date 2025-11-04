import java.io.*;

public class RowDataUtil {


    /*
        作用： 由于url从网站上拷贝太慢了，所以手动把json复制到rowData中，并用该方法把rowData中的json全部变成一行存储到rowData1中
     */
    public static void processRowData() throws IOException {
        InputStream resourceAsStream = RowDataUtil.class.getClassLoader().getResourceAsStream("rowData/athletes.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        bufferedReader.close();
        FileWriter fileWriter = new FileWriter("D:\\westTwo\\westTowcheck2\\src\\main\\resources\\rowData1\\athletes.txt");
        fileWriter.write(builder.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
