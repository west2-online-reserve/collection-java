import DWA.Athlete;
import DWA.DetailedResult;
import DWA.Result;
import DWASearch.DWASFiles;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CoreModule implements DWASearch.CoreModule {
    File data = new File("src/main/resources/DWAData");

    @Override
    public void setData(String path) {
        File data = new File(path);
        if (!data.exists() || !data.isDirectory())
            data.mkdirs();
        this.data = data;
    }


    @Override
    public void displayAllPlayersInfo() {
        File[] files = data.listFiles();
        boolean flag = true;
        for (File file : files) {
            if (file.getName().equals("athletes.json")) {
                flag = false;
                try {
                    String jsonStr = FileUtils.readFileToString(file);
                    ArrayList<Athlete> athlete = DWASFiles.parseAthletesList(jsonStr);
                    for (Athlete a : athlete) {
                        System.out.println(a.toString() + "\n-----");
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read athletes.json" + "\n" + file.getAbsoluteFile(), e);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to parse athletes.json" + "\n" + file.getAbsoluteFile(), e);
                }
                break;
            }
        }
        if (flag) {
            throw new RuntimeException("Failed to find athletes.json" + "\n" + data.getAbsoluteFile());
        }

    }

    @Override
    public void displayResultsForEachEvent() {
        File[] files = data.listFiles();
        for (File file : files) {
            if (file.getName().equals("athletes.json"))
                continue;
            StringBuilder sb = new StringBuilder(file.getName());
            sb.delete(sb.length() - 5, sb.length());
            System.out.println("\n\n=====" + sb + ": ");
            try {
                ArrayList<Result> results = DWASFiles.parseResultsList(FileUtils.readFileToString(file));
                for (Result r : results) {
                    System.out.println(r.toString());
                    System.out.println("-----");
                }
                System.out.println("\n=====");
            } catch (Exception e) {
                throw new RuntimeException("Failed to read " + file.getName() + "\n" + file.getAbsoluteFile(), e);
            }
        }
    }

    @Override
    public void displayResult(String name) {
        try {
            ArrayList<Result> results = getResultsForEvent(name);
            for (Result r : results) {
                if (!r.getName().equals("Finals") && !r.getName().equals("Final"))
                    break;
                System.out.println(r);
                System.out.println("-----");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void displayDetailedResult(String name) {
        try {
            ArrayList<DetailedResult> results = getDetailedResultsForEvent(name);
            Collections.sort(results);
            for (DetailedResult r : results) {
                System.out.println(r.toString());
                System.out.println("-----");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void displayAllDetailedResults() {
        File[] files = data.listFiles();
        for (File file : files) {
            if (file.getName().equals("athletes.json"))
                continue;
            StringBuilder sb = new StringBuilder(file.getName());
            sb.delete(sb.length() - 5, sb.length());
            ArrayList<DetailedResult> results = getDetailedResultsForEvent(sb.toString());
            Collections.sort(results);
            System.out.println("\n=====\n" + sb + ": ");
            for (DetailedResult r : results) {
                System.out.println(r.toString());
                System.out.println("-----");
            }
            System.out.println("\n=====");
        }
    }


    @Override
    public ArrayList<Result> getResultsForEvent(String eventName) {
        File[] files = data.listFiles();
        ArrayList<Result> results = new ArrayList<>();
        for (File file : files) {
            if (file.getName().equals(eventName + ".json")) {
                try {
                    results = DWASFiles.parseResultsList(FileUtils.readFileToString(file));
                } catch (Exception e) {
                    throw new RuntimeException("Failed to read " + file.getName() + "\n" + file.getAbsoluteFile(), e);
                }
                break;
            }
        }
        if (results.isEmpty()) {
            throw new RuntimeException("No results found for event " + eventName);
        }
        return results;
    }

    @Override
    public ArrayList<DetailedResult> getDetailedResultsForEvent(String eventName) {
        File[] files = data.listFiles();
        ArrayList<Result> results = new ArrayList<>();
        ArrayList<DetailedResult> detailedResults = new ArrayList<>();
        for (File file : files) {
            if (file.getName().equals(eventName + ".json")) {
                try {
                    results = DWASFiles.parseResultsList(FileUtils.readFileToString(file));
                    for (Result r : results) {
                        detailedResults.add(new DetailedResult(r, results));
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read " + file.getName() + "\n" + file.getAbsoluteFile(), e);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to parse " + file.getName() + "\n" + file.getAbsoluteFile(), e);
                }
                break;
            }
        }
        if (detailedResults.isEmpty()) {
            throw new RuntimeException("No results found for event " + eventName);
        }
        return detailedResults;
    }

}
