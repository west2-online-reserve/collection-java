package DWASearchTest;

import DWA.Athlete;
import DWA.Result;
import DWASearch.DWASFiles;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class DWASFilesTest {
    @Test
    void prepareResults() {
        try {
            //顺便测一下构造方法(里面也用到prepareResults
            new DWASFiles(new File("src/test/resources/input.txt"), new File("src/test/resources/output.txt"), new File("src/test/resources"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    public static void clear() {
        File file[] = new File("src/test/resources").listFiles();
        for (File file1 : file) {
            if(file1.getName().endsWith(".txt")) {
                continue;
            }
            file1.delete();
        }
    }

    @Test
    void parseAthletesList() {
        File f = new File("src/main/resources/DWAData/athletes.json");
        ArrayList<Athlete> athletes = new ArrayList<>();
        try {
            athletes = DWASFiles.parseAthletesList(FileUtils.readFileToString(f));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(2236, athletes.size());
        System.out.println(athletes);
    }

    @Test
    void parseResultsList() {
        File f = new File("src/main/resources/DWAData/Men 3m Synchronised.json");
        ArrayList<Result> results = new ArrayList<>();
        try {
            results = DWASFiles.parseResultsList(FileUtils.readFileToString(f));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(27, results.size());
        System.out.println(results);
    }

    @Test
    void readFileForOrders(){
        File f = new File("src/test/resources/input.txt");
        ArrayList<Integer> orders = new ArrayList<>();
        try {
            orders = DWASFiles.readFileForOrders(f);
            if(orders.isEmpty())
                throw new RuntimeException("No orders found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }

    @Test
    void ifExists() {
        boolean if1 = DWASFiles.ifExists(new File("src/main/resources/DWAData/athletes.json"));
        boolean if2 = DWASFiles.ifExists(new File("114514"), new File("3278t"));
        Assertions.assertTrue(if1);
        Assertions.assertFalse(if2);
    }

    @Test
    void ifTxt() {
        boolean if1 = DWASFiles.ifTxt("189,txt");
        boolean if2 = DWASFiles.ifTxt("189.txt");
        Assertions.assertFalse(if1);
        Assertions.assertTrue(if2);
    }
}
