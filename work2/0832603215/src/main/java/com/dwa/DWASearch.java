package com.dwa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DWASearch {

    private static DivingEvent readEvent(String path)
            throws IOException {

        File file = new File(path);

        if (!file.isFile() || !file.canRead()) {
            throw new IOException("JSON 文件不存在或无法读取：" + path);
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            DivingEvent event =
                    mapper.readValue(file, DivingEvent.class);

            if (event == null || event.getHeats() == null) {
                throw new IOException("JSON 缺少必要的heats数据：" + path);
            }

            return event;

        } catch (JsonProcessingException e) {
            throw new IOException("JSON 格式错误或数据结构不匹配：" + path, e);
        }
    }

    private static String getResultFile(String event) {

        return switch (event) {
            case "women 3m springboard" -> "data/results/women_3m_springboard.json";
            case "women 3m synchronised" -> "data/results/women_3m_synchronised.json";
            case "women 10m platform" -> "data/results/women_10m_platform.json";
            case "women 10m synchronised" -> "data/results/women_10m_synchronised.json";
            case "men 3m springboard" -> "data/results/men_3m_springboard.json";
            case "men 3m synchronised" -> "data/results/men_3m_synchronised.json";
            case "men 10m platform" -> "data/results/men_10m_platform.json";
            case "men 10m synchronised" -> "data/results/men_10m_synchronised.json";
            default -> null;
        };
    }


    public static void main(String[] args) {
        int exitCode = run(args);

        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }


    static void validatePlayers(List<Country> countries)
            throws IOException {

        if (countries == null) {
            throw new IOException("players.json 数据为空");
        }

        for (Country country : countries) {

            if (country == null
                    || country.getCountryName() == null
                    || country.getCountryName().isBlank()
                    || country.getParticipations() == null) {
                throw new IOException("players.json 缺少国家必要字段");
            }

            for (Participation participation : country.getParticipations()) {

                if (participation == null
                        || participation.getFirstName() == null
                        || participation.getFirstName().isBlank()
                        || participation.getLastName() == null
                        || participation.getLastName().isBlank()
                        || participation.getGender().equals("Unknown")) {
                    throw new IOException("players.json 缺少运动员必要字段");
                }
            }
        }
    }
    static int run(String[] args) {

        if (args.length != 2) {
            System.err.println("Error: 参数数量错误！");
            System.err.println(
                    "用法：java -jar DWASearch.jar input.txt output.txt"
            );
            return 1;
        }

        Path inputPath = Paths.get(args[0]);
        Path outputPath = Paths.get(args[1]);

        if (!Files.isRegularFile(inputPath)
                || !Files.isReadable(inputPath)) {
            System.err.println("Error: 输入文件不存在或无法读取！");
            return 1;
        }

        try {
            Path parent = outputPath.toAbsolutePath().getParent();

            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (
                    BufferedReader reader =
                            Files.newBufferedReader(inputPath);

                    BufferedWriter writer =
                            Files.newBufferedWriter(outputPath)
            ) {
                ObjectMapper mapper = new ObjectMapper();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                System.out.println("读取到命令：" + line);
                if (line.equals("players")) {
                    System.out.println("执行球员查询");
                    File playersFile = new File("data/players.json");

                    if (!playersFile.isFile() || !playersFile.canRead()) {
                        throw new IOException("players.json 不存在或无法读取");
                    }

                    List<Country> countries;

                    try {
                        countries = mapper.readValue(
                                playersFile,
                                new TypeReference<List<Country>>() {}
                        );

                        if (countries == null) {
                            throw new IOException("players.json 数据为空");
                        }

                    } catch (JsonProcessingException e) {
                        throw new IOException("players.json 格式错误", e);
                    }

                    validatePlayers(countries);
                    List<Player> players = new ArrayList<>();

                    for (Country country : countries) {

                        for (Participation participation : country.getParticipations()) {
                            Player player = new Player(
                                    participation.getFirstName(),
                                    participation.getLastName(),
                                    participation.getGender().equals("Male") ? 0 : 1,
                                    country.getCountryName()
                            );

                            players.add(player);

                        }
                    }

                    players.sort(
                            Comparator.comparing(Player::getCountry)
                                    .thenComparing(Player::getLastName)
                    );

                    for (Player player : players) {
                        writer.write("Full Name:" + player.getLastName() + " " + player.getFirstName());
                        writer.newLine();
                        writer.write("Gender:" + player.getGender());
                        writer.newLine();
                        writer.write("Country:" + player.getCountry());
                        writer.newLine();
                        writer.write("-----");
                        writer.newLine();
                    }
                } else if (line.equals("result")) {
                    writer.write("N/A");
                    writer.newLine();
                    writer.write("-----");
                    writer.newLine();
                } else if (line.startsWith("result ")) {

                    String eventName = line.substring(7).trim();

                    String resultPath = getResultFile(eventName);

                    if (resultPath == null) {
                        writer.write("N/A");
                        writer.newLine();
                        writer.write("-----");
                        writer.newLine();
                        continue;
                    }

                    DivingEvent event = readEvent(resultPath);

                    for (Heat heat : event.getHeats()) {

                        if ("Finals".equals(heat.getName())) {

                            for (AthleteResult result : heat.getResults()) {

                                writer.write("Full Name: " + result.getFullName());
                                writer.newLine();
                                writer.write("Rank: " + result.getRank());
                                writer.newLine();
                                writer.write("Score: ");
                                for (int i = 0; i < result.getDives().size(); i++) {

                                    BigDecimal points = result.getDives().get(i).getDivePoints();

                                    writer.write(
                                            points.setScale(2, RoundingMode.HALF_UP).toPlainString()
                                    );

                                    if (i < result.getDives().size() - 1) {
                                        writer.write(" + ");
                                    }
                                }

                                writer.write(" = " + result.calculateTotalPoints());
                                writer.newLine();
                                writer.write("-----");
                                writer.newLine();
                            }
                        }
                    }
                } else {
                    writer.write("Error");
                    writer.newLine();
                    writer.write("-----");
                    writer.newLine();
                }
            }
        }
             return 0;

        } catch (NoSuchFileException e) {
            System.err.println("Error: 文件不存在：" + e.getMessage());
            return 1;

        } catch (AccessDeniedException e) {
            System.err.println("Error: 文件访问权限不足：" + e.getMessage());
            return 1;

        } catch (IOException e) {
            System.err.println("Error: 文件读取或写入失败：" + e.getMessage());
            return 1;
        }
    }
}



