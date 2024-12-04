package DWASearch;

import Core.CoreModule;
import File_IO.FileIO;

public class DWASearch {
    public static void main(String[] args) {

        String result[] = {"result women 1m springboard", "result women 3m springboard", "result women 10m platform", "result women 3m synchronised", "result women 10m synchronised", "result men 1m springboard", "result men 3m springboard", "result men 10m platform", "result men 3m synchronised", "result men 10m synchronised"};
        String detailResult[] = new String[10];
        for (int i = 0; i < detailResult.length; i++) {
            StringBuilder stringBuilder=new StringBuilder(result[i]);
            stringBuilder.append(" detail");
            detailResult[i]=stringBuilder.toString();
        }

        CoreModule coreModule = new CoreModule();
        String input = args[0];
        String output = args[1];
        FileIO fileIO = new FileIO(input, output);

        fileIO.deleteFile();

        String str;

        while ((str = fileIO.fileRead()) != null) {
            if (str.equals("players")) {
                coreModule.displayAllPlayersInfo(fileIO);
            } else if (str.startsWith("result ")) {
                boolean flag=false;
                for (int i = 0; i < result.length; i++) {
                    if (str.equals(result[i])){
                        coreModule.displayResultsForEachEvent(str.substring(7),fileIO);
                        flag=true;
                    }
                }
                for (int i = 0; i < detailResult.length; i++) {
                    if (str.equals(detailResult[i])){
                        coreModule.displayResultsForEachEventDetail(str.substring(7),fileIO);
                        flag=true;
                    }
                }
                if (!flag){
                    fileIO.fileWrite("N/A\n-----");
                }

            } else {
                fileIO.fileWrite("Error\n-----");
            }
        }
    }
}
