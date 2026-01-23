package org.example.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;
import java.util.ArrayList;

public class CoreModule implements OutputModule{

    private final File outputFilePath;


    public CoreModule(File output) {
        this.outputFilePath = output;
    }

    //未知的指令，写入"Error"
    @Override
    public void writeWrongInstruction() {
        try (FileWriter writer = new FileWriter(outputFilePath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            bufferedWriter.write("Error");
            bufferedWriter.newLine();
            bufferedWriter.write("-----");
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayAllPlayersInfo(ArrayList<Country> countries) throws InterruptedException {

        try (FileWriter writer = new FileWriter(outputFilePath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            //写入内容所有运动员信息
            for (Country country : countries) {

                for (Participation participation : country.getParticipations()) {
                    bufferedWriter.write("Full Name:" + participation.getPreferredLastName() +
                            " " + participation.getPreferredFirstName()
                    );
                    bufferedWriter.newLine();
                    bufferedWriter.write("Gender:" + (participation.getGender() ? "Female" : "Male"));
                    bufferedWriter.newLine();
                    bufferedWriter.write("Country:" + country.getCountryName());
                    bufferedWriter.newLine();
                    bufferedWriter.write("-----");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void displayResultsForEachEvent(Event event, String disciplineName) throws InterruptedException {
        StringBuilder disciplineUrl = new StringBuilder("https://api.worldaquatics.com/fina/events/");


        try (FileWriter writer = new FileWriter(outputFilePath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            boolean isExistDiscipline = false;

            for (Sport sport : event.getSports()) {

                for (Discipline discipline : sport.getDisciplines()) {
                    if (discipline.getDisciplineName().toLowerCase().equals(disciplineName)) {
                        //转入该项目详细信息的api网页
                        disciplineUrl.append(discipline.getId());
                        isExistDiscipline = true;
                        break;
                    }
                }
            }

            //不存在项目
            if (!isExistDiscipline) {

                bufferedWriter.write("N/A");
                bufferedWriter.newLine();
                bufferedWriter.write("-----");
                bufferedWriter.newLine();
                return;

            }

            HttpsCrawler httpsCrawler = new HttpsCrawler();
            Discipline discipline = httpsCrawler.fetch(disciplineUrl.toString(), Discipline.class);


            Heat heat = new Heat();
            for (Heat disciplineHeat : discipline.getHeats()) {
                if (disciplineHeat.getName().equals("Final")) {
                    heat = disciplineHeat;
                    break;
                }
            }

            //写入该项目的成绩
            for (Result result : heat.getResults()) {
                bufferedWriter.write("Full Name:" + result.getFullName().replace("/", "&"));
                bufferedWriter.newLine();
                bufferedWriter.write("Rank:" + result.getRank());
                bufferedWriter.newLine();
                bufferedWriter.write("Score:");
                for (int i = 0; i < result.getDives().size(); i++) {
                    Dive dive = result.getDives().get(i);
                    bufferedWriter.write(String.format("%.2f", dive.getDivePoints()));
                    if (i == result.getDives().size() - 1) {
                        bufferedWriter.write(" = ");
                    } else {
                        bufferedWriter.write(" + ");
                    }
                }
                bufferedWriter.write(String.format("%.2f", result.getTotalPoints()));
                bufferedWriter.newLine();
                bufferedWriter.write("-----");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void displayResultsForEachEventDetail(Event event, String disciplineName) throws InterruptedException {
        StringBuilder disciplineUrl = new StringBuilder("https://api.worldaquatics.com/fina/events/");

        try {
            FileWriter writer = new FileWriter(outputFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            boolean isExistDiscipline = false;
            for (Sport sport : event.getSports()) {

                for (Discipline discipline : sport.getDisciplines()) {
                    if (discipline.getDisciplineName().toLowerCase().equals(disciplineName)) {
                        //转入该项目详细信息的api网页
                        disciplineUrl.append(discipline.getId());
                        isExistDiscipline = true;
                        break;
                    }
                }
            }

            //未找到该项目，写入"N/A"
            if (!isExistDiscipline) {

                bufferedWriter.write("N/A");
                bufferedWriter.newLine();
                bufferedWriter.write("-----");
                bufferedWriter.newLine();
                return;
            }

            HttpsCrawler httpsCrawler = new HttpsCrawler();
            Discipline discipline = httpsCrawler.fetch(disciplineUrl.toString(), Discipline.class);


            Map<String, TotalResult> map = new TreeMap<>();


            ArrayList<Heat> heats = discipline.getHeats();

            for (Heat heat : heats) {

                for (Result result : heat.getResults()) {
                    if (map.containsKey(result.getFullName())) {
                        map.get(result.getFullName()).setResult(result, heat.getName());
                    } else {
                        TotalResult totalResult = new TotalResult();
                        totalResult.setResult(result, heat.getName());
                        map.put(result.getFullName(), totalResult);
                    }

                }


            }

            //写入该项目的成绩
            List<Map.Entry<String, TotalResult>> list = new ArrayList<>(map.entrySet());
            list.sort((a, b) -> a.getValue().getFirstRankValue() - b.getValue().getFirstRankValue());
            for (Map.Entry<String, TotalResult> e : list) {
                bufferedWriter.write("Full Name:" + e.getKey().replace("/", "&"));
                bufferedWriter.newLine();
                bufferedWriter.write(e.getValue().toString());
                bufferedWriter.newLine();
                bufferedWriter.write("-----");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
