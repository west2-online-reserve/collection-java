import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
public class Tool {
    public static void writeing(String str) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\develop\\java_text\\basic_code\\DWA\\src\\output", true));
        String[] split = str.split("-");
        bw.write("Full Name:   " + split[0] + split[1]);
        bw.newLine();
        if (split[2].equals("0") ) {
            bw.write("Gender:   male");
        } else {
            bw.write("Gender:   female");
        }
        bw.newLine();
        bw.write("Country" + split[3]);
        bw.newLine();
        bw.write("-----");
        bw.newLine();
        bw.close();
    }
    public static void writeingContest(String str, double totalPoints) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\develop\\java_text\\basic_code\\DWA\\src\\output", true));
        String[] split = str.split(",");
        String[] splitFullname = split[0].split("=");
        String fullname = splitFullname[1];
        if (fullname.contains("/")) {
            String fullname1 = fullname.split("/")[0];
            String fullname2 = fullname.split("/")[1];
            if (fullname.split("/")[0].charAt(1) > fullname.split("/")[1].charAt(1)) {
                fullname = fullname2 + " & " + fullname1;
            }else {
                fullname = fullname1 + " & " + fullname2;
            }
        }
        bw.write("full name:  " + fullname);
        bw.newLine();
        String[] splitRank = split[1].split("=");
        bw.write("rank:  " + splitRank[1]);
        bw.newLine();
        String[] splitPoint = split[2].split("=");
        bw.write("Score: " + splitPoint[1].substring(2, splitPoint[1].length()));
        bw.write(" +" + split[3]);
        bw.write(" +" + split[4]);
        bw.write(" +" + split[5]);
        if(split.length>7){
            bw.write(" +" + split[6]);
            bw.write(" +" + split[7].substring(1, split[7].length()-2));
        }else {
            bw.write(" +" + split[6].substring(1, split[6].length()-2));
        }
        bw.write("=" + totalPoints);
        bw.newLine();
        bw.write("-----");
        bw.newLine();
        bw.close();
    }
    public static void writeingContestDetailed(Contest[] contests, double[] point) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\develop\\java_text\\basic_code\\DWA\\src\\output", true));
        String fullname = contests[0].getFullName();
        if (fullname.contains("/")) {
            String fullname1 = fullname.split("/")[0];
            String fullname2 = fullname.split("/")[1];
            if (fullname.split("/")[0].charAt(0) > fullname.split("/")[1].charAt(1)) {
                fullname = fullname2 + " & " + fullname1;
            }else {
                fullname = fullname1 + " & " + fullname2;
            }
        }
        bw.write("full name: " + fullname);
        bw.newLine();
        String rankSimifinal;
        String rankFianl;
        if (contests[1] != null) {
            rankSimifinal = String.valueOf(contests[1].getRank());
        } else {
            rankSimifinal = "*";
        }
        if (contests[2] != null) {
            rankFianl = String.valueOf(contests[2].getRank());
        } else {
            rankFianl = "*";
        }
        int rankPreliminary = contests[0].getRank();
        bw.write("rank: " + rankPreliminary + " | " + rankSimifinal + " | " + rankFianl);
        bw.newLine();
        String[] str0 = contests[0].toString().split("=");
        String[] split = str0[3].split(",");
        bw.write("Preliminary Score: ");
        bw.write(split[0].substring(2, split[0].length()));
        bw.write(" +" + split[1]);
        bw.write(" +" + split[2]);
        bw.write(" +" + split[3]);
        if (split.length > 5) {
            bw.write(" +" + split[4]);
            bw.write(" +" + split[5].substring(0, split[5].length() - 2));
        } else {
            bw.write(" +" + split[4].substring(0, split[4].length() - 2));
        }
        bw.write(" =" + point[0]);
        bw.newLine();
        if (contests[1] != null) {
            String[] str1 = contests[1].toString().split("=");
            String[] split1 = str1[3].split(",");
            bw.write("Semifinal Score: ");
            bw.write(split1[0].substring(2, split1[0].length()));
            bw.write(" +" + split1[1]);
            bw.write(" +" + split1[2]);
            bw.write(" +" + split1[3]);
            if (split1.length > 5) {
                bw.write(" +" + split1[4]);
                bw.write(" +" + split1[5].substring(0, split1[5].length() - 2));
            } else {
                bw.write(" +" + split1[4].substring(0, split1[4].length() - 2));
            }
            bw.write(" =" + point[1]);
            bw.newLine();
        } else {
            bw.write("Semifinal Score: * ");
            bw.newLine();
        }
        if (contests[2] != null) {
            String[] str1 = contests[2].toString().split("=");
            String[] split1 = str1[3].split(",");
            bw.write("Final Score: ");
            bw.write(split1[0].substring(2, split1[0].length()));
            bw.write(" +" + split1[1]);
            bw.write(" +" + split1[2]);
            bw.write(" +" + split1[3]);
            if (split1.length > 5) {
                bw.write(" +" + split1[4]);
                bw.write(" +" + split1[5].substring(0, split1[5].length() - 2));
            } else {
                bw.write(" +" + split1[4].substring(0, split1[4].length() - 2));
            }
            bw.write(" =" + point[2]);
            bw.newLine();
        } else {
            bw.write("Final Score: * ");
            bw.newLine();
        }
        bw.write("----------");
        bw.newLine();
        bw.close();
    }
    public static void writeingContestSynchronised(Contest[] contests, double[] point) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\develop\\java_text\\basic_code\\DWA\\src\\output", true));
        String fullname = contests[0].getFullName();
        String fullname1 = fullname.split("/")[0];
        String fullname2 = fullname.split("/")[1];
        if (fullname.split("/")[0].charAt(0) > fullname.split("/")[1].charAt(1)) {
            fullname = fullname2 + " & " + fullname1;
        }else {
            fullname = fullname1 + " & " + fullname2;
        }
        bw.write("full name: " + fullname);
        bw.newLine();
        int rank = contests[0].getRank();
        bw.write("rank:   * | * | " + rank);
        bw.newLine();
        String[] str = contests[0].toString().split("=");
        String[] split1 = str[3].split(",");
        bw .write("Preliminary Score:  *");
        bw.newLine();
        bw.write("Semifinal Score: *");
        bw.newLine();
        bw.write("Final Scores: ");
        bw.write(split1[0].substring(2, split1[0].length()));
        bw.write(" +" + split1[1]);
        bw.write(" +" + split1[2]);
        bw.write(" +" + split1[3]);
        if (split1.length > 5) {
            bw.write(" +" + split1[4]);
            bw.write(" +" + split1[5].substring(0, split1[5].length() - 2));
        } else {
            bw.write(" +" + split1[4].substring(0, split1[4].length() - 2));
        }
        bw.write(" =" + point[0]);
        bw.newLine();
        bw.write("-----");
        bw.newLine();
        bw.close();
    }
    public static void writeError_1() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\develop\\java_text\\basic_code\\DWA\\src\\output", true));
        bw.write("error");
        bw.newLine();
        bw.write("-----");
        bw.newLine();
        bw.close();
    }
    public static void writeError_2() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\develop\\java_text\\basic_code\\DWA\\src\\output", true));
        bw.write("N/A");
        bw.newLine();
        bw.write("-----");
        bw.newLine();
        bw.close();
    }
}