package src;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import src.Lib.CommandStringProcess;
import src.Lib.DisplayInformation;
import src.Lib.FileReadAndWrite;
import src.Lib.CommandStringProcess;

public class DWASearchTest {

    String inputFile = "src/Data/input.txt",outputFile = "src/Data/output.txt";
    @Test
    public void testDwaSearch() throws IOException {
        CommandStringProcess processCommand = new CommandStringProcess();
        String[] command = CommandStringProcess.commandInput(inputFile);
        for(String c: command){
            int result = processCommand.commandDistinguish(c);
            try {
                DisplayInformation.displayInfo(result,outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        /*
            当output.txt文件内容为空
            input.txt文件内容为

            playersss
            result
            result aaa
            result men 1m springboard details

            players
            result men 1m springboard
            result men 3m springboard
            result men 10m platform
            result men 3m synchronised detail
            result men 10m synchronised detail

            程序执行input.txt文件内容后，输出到output.txt文件中，再进行比较
         */
        assertEquals(
                "Error\n" +
                        "-----\n" +
                        "N/A\n" +
                        "-----\n" +
                        "N/A\n" +
                        "-----\n" +
                        "N/A\n" +
                        "-----\n" +
                        "Full Name:HART Alexander\n" +
                        "Gender:Male\n" +
                        "Country:Austria\n" +
                        "-----\n" +
                        "Full Name:LOTFI Dariush\n" +
                        "Gender:Male\n" +
                        "Country:Austria\n" +
                        "-----\n" +
                        "Full Name:SCHALLER Nikolaj \n" +
                        "Gender:Male\n" +
                        "Country:Austria\n" +
                        "-----\n" +
                        "Full Name:WILSON Aimee \n" +
                        "Gender:Female\n" +
                        "Country:Canada\n" +
                        "-----\n" +
                        "Full Name:JASMIN Amelie\n" +
                        "Gender:Female\n" +
                        "Country:Canada\n" +
                        "-----\n" +
                        "Full Name:TESSIER Benjamin\n" +
                        "Gender:Male\n" +
                        "Country:Canada\n" +
                        "-----\n" +
                        "Full Name:DICK Elaena\n" +
                        "Gender:Female\n" +
                        "Country:Canada\n" +
                        "-----\n" +
                        "Full Name:BELANGER Eloise\n" +
                        "Gender:Female\n" +
                        "Country:Canada\n" +
                        "-----\n" +
                        "Full Name:FUNG Katelyn\n" +
                        "Gender:Female\n" +
                        "Country:Canada\n" +
                        "-----\n" +
                        "Full Name:ERLAM Margo\n" +
                        "Gender:Female\n" +
                        "Country:Canada\n" +
                        "-----\n" +
                        "Full Name:ABRAMOWICZ Tazman\n" +
                        "Gender:Male\n" +
                        "Country:Canada\n" +
                        "-----\n" +
                        "Full Name:SANTIAGO Dominique \n" +
                        "Gender:Female\n" +
                        "Country:Czechia\n" +
                        "-----\n" +
                        "Full Name:JELINKOVA Tereza\n" +
                        "Gender:Female\n" +
                        "Country:Czechia\n" +
                        "-----\n" +
                        "Full Name:TSULUKIDZE Giorgi \n" +
                        "Gender:Male\n" +
                        "Country:Georgia\n" +
                        "-----\n" +
                        "Full Name:SAKANDELIDZE Irakli\n" +
                        "Gender:Male\n" +
                        "Country:Georgia\n" +
                        "-----\n" +
                        "Full Name:SHANIDZE Mariam\n" +
                        "Gender:Female\n" +
                        "Country:Georgia\n" +
                        "-----\n" +
                        "Full Name:SHARIA Tekle\n" +
                        "Gender:Female\n" +
                        "Country:Georgia\n" +
                        "-----\n" +
                        "Full Name:ONIKASHVILI Tornike\n" +
                        "Gender:Male\n" +
                        "Country:Georgia\n" +
                        "-----\n" +
                        "Full Name:LUBE Alexander\n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:COORDES Carolina\n" +
                        "Gender:Female\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:WASSEN Christina\n" +
                        "Gender:Female\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:WASSEN Elena\n" +
                        "Gender:Female\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:PRENZYNA Espen\n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:EIKERMANN Jaden\n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:ROTHER Jana\n" +
                        "Gender:Female\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:MULLER Jette\n" +
                        "Gender:Female\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:RUDIGER Lars\n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:HENTSCHEL Lena\n" +
                        "Gender:Female\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:AVILA Luis\n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:WESEMANN Moritz \n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:ROSLER Ole\n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:PFEIF Pauline\n" +
                        "Gender:Female\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:OETTINGHAUS Saskia\n" +
                        "Gender:Female\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:BARTHEL Timo\n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:WALDSTEINER Tom\n" +
                        "Gender:Male\n" +
                        "Country:Germany\n" +
                        "-----\n" +
                        "Full Name:ROLLINSON Amy \n" +
                        "Gender:Female\n" +
                        "Country:Great Britain\n" +
                        "-----\n" +
                        "Full Name:BENT Desharne\n" +
                        "Gender:Female\n" +
                        "Country:Great Britain\n" +
                        "-----\n" +
                        "Full Name:MCCABE Euan\n" +
                        "Gender:Male\n" +
                        "Country:Great Britain\n" +
                        "-----\n" +
                        "Full Name:HEATLY James\n" +
                        "Gender:Male\n" +
                        "Country:Great Britain\n" +
                        "-----\n" +
                        "Full Name:HOULDEN Jordan\n" +
                        "Gender:Male\n" +
                        "Country:Great Britain\n" +
                        "-----\n" +
                        "Full Name:BOND Maisie\n" +
                        "Gender:Female\n" +
                        "Country:Great Britain\n" +
                        "-----\n" +
                        "Full Name:DIXON Matthew\n" +
                        "Gender:Male\n" +
                        "Country:Great Britain\n" +
                        "-----\n" +
                        "Full Name:LEE Robbie\n" +
                        "Gender:Male\n" +
                        "Country:Great Britain\n" +
                        "-----\n" +
                        "Full Name:TSIRIKOS Athanasios \n" +
                        "Gender:Male\n" +
                        "Country:Greece\n" +
                        "-----\n" +
                        "Full Name:VAN Celine \n" +
                        "Gender:Female\n" +
                        "Country:Netherlands\n" +
                        "-----\n" +
                        "Full Name:PRAASTERINK Else\n" +
                        "Gender:Female\n" +
                        "Country:Netherlands\n" +
                        "-----\n" +
                        "Full Name:JANSEN Inge\n" +
                        "Gender:Female\n" +
                        "Country:Netherlands\n" +
                        "-----\n" +
                        "Full Name:BLAZOWSKA Aleksandra\n" +
                        "Gender:Female\n" +
                        "Country:Poland\n" +
                        "-----\n" +
                        "Full Name:RZESZUTEK Andrzej\n" +
                        "Gender:Male\n" +
                        "Country:Poland\n" +
                        "-----\n" +
                        "Full Name:JACHIM Filip\n" +
                        "Gender:Male\n" +
                        "Country:Poland\n" +
                        "-----\n" +
                        "Full Name:LESIAK Kacper\n" +
                        "Gender:Male\n" +
                        "Country:Poland\n" +
                        "-----\n" +
                        "Full Name:SKRZEK Kaja \n" +
                        "Gender:Female\n" +
                        "Country:Poland\n" +
                        "-----\n" +
                        "Full Name:LUKASZEWICZ Robert\n" +
                        "Gender:Male\n" +
                        "Country:Poland\n" +
                        "-----\n" +
                        "Full Name:CHOI Gangin\n" +
                        "Gender:Male\n" +
                        "Country:Republic of Korea\n" +
                        "-----\n" +
                        "Full Name:KIM Gyeongbin\n" +
                        "Gender:Male\n" +
                        "Country:Republic of Korea\n" +
                        "-----\n" +
                        "Full Name:KANG Jiho\n" +
                        "Gender:Male\n" +
                        "Country:Republic of Korea\n" +
                        "-----\n" +
                        "Full Name:HAN Jiwoo\n" +
                        "Gender:Female\n" +
                        "Country:Republic of Korea\n" +
                        "-----\n" +
                        "Full Name:OH Sooyeon \n" +
                        "Gender:Female\n" +
                        "Country:Republic of Korea\n" +
                        "-----\n" +
                        "Full Name:BAEK Sunjin\n" +
                        "Gender:Female\n" +
                        "Country:Republic of Korea\n" +
                        "-----\n" +
                        "Full Name:LEE Wonseop\n" +
                        "Gender:Male\n" +
                        "Country:Republic of Korea\n" +
                        "-----\n" +
                        "Full Name:LEE Yejoo\n" +
                        "Gender:Female\n" +
                        "Country:Republic of Korea\n" +
                        "-----\n" +
                        "Full Name:MUSCALU Nicoleta \n" +
                        "Gender:Female\n" +
                        "Country:Romania\n" +
                        "-----\n" +
                        "Full Name:ABADIA Adrian\n" +
                        "Gender:Male\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:CARVAJAL Ana\n" +
                        "Gender:Female\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:CAMACHO Carlos\n" +
                        "Gender:Male\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:RODRIGUEZ Jorge\n" +
                        "Gender:Male\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:PAPWORTH Maria\n" +
                        "Gender:Female\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:LINAN Max\n" +
                        "Gender:Male\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:GARCIA Nicolas\n" +
                        "Gender:Male\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:VELAZQUEZ Rocio \n" +
                        "Gender:Female\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:ANTOLINO Valeria\n" +
                        "Gender:Female\n" +
                        "Country:Spain\n" +
                        "-----\n" +
                        "Full Name:LUNDIN Amanda \n" +
                        "Gender:Female\n" +
                        "Country:Sweden\n" +
                        "-----\n" +
                        "Full Name:DUTOIT Guillaume\n" +
                        "Gender:Male\n" +
                        "Country:Switzerland\n" +
                        "-----\n" +
                        "Full Name:SUCKOW Jonathan \n" +
                        "Gender:Male\n" +
                        "Country:Switzerland\n" +
                        "-----\n" +
                        "Full Name:COQUOZ Madeline\n" +
                        "Gender:Female\n" +
                        "Country:Switzerland\n" +
                        "-----\n" +
                        "Full Name:PYSMENSKA Anna \n" +
                        "Gender:Female\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:CHYZHOVSKYI Bohdan\n" +
                        "Gender:Male\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:AVANESOV Danylo\n" +
                        "Gender:Male\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:KONOVALOV Danylo\n" +
                        "Gender:Male\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:AZAROV Kyrylo\n" +
                        "Gender:Male\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:HRYTSENKO Mark\n" +
                        "Gender:Male\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:OLIFERCHYK Stanislav\n" +
                        "Gender:Male\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:KESAR Viktoriya\n" +
                        "Gender:Female\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:NAUMENKO Yevhen\n" +
                        "Gender:Male\n" +
                        "Country:Ukraine\n" +
                        "-----\n" +
                        "Full Name:MYALIN Igor\n" +
                        "Gender:Male\n" +
                        "Country:Uzbekistan\n" +
                        "-----\n" +
                        "Full Name:KACHANOV Vyacheslav\n" +
                        "Gender:Male\n" +
                        "Country:Uzbekistan\n" +
                        "-----\n" +
                        "Full Name:DIXON Matthew\n" +
                        "Rank:1\n" +
                        "Score:58.50 + 61.50 + 58.50 + 67.50 + 57.60 + 69.75 = 373.35\n" +
                        "-----\n" +
                        "Full Name:HOULDEN Jordan\n" +
                        "Rank:2\n" +
                        "Score:57.80 + 52.80 + 48.00 + 67.50 + 63.00 + 62.40 = 351.50\n" +
                        "-----\n" +
                        "Full Name:ABRAMOWICZ Tazman\n" +
                        "Rank:3\n" +
                        "Score:54.60 + 66.00 + 44.85 + 60.45 + 59.20 + 57.00 = 342.10\n" +
                        "-----\n" +
                        "Full Name:CHYZHOVSKYI Bohdan\n" +
                        "Rank:4\n" +
                        "Score:54.25 + 54.00 + 50.60 + 63.00 + 65.60 + 53.30 = 340.75\n" +
                        "-----\n" +
                        "Full Name:OLIFERCHYK Stanislav\n" +
                        "Rank:5\n" +
                        "Score:66.65 + 63.00 + 36.00 + 40.50 + 54.00 + 62.40 = 322.55\n" +
                        "-----\n" +
                        "Full Name:CHOI Gangin\n" +
                        "Rank:6\n" +
                        "Score:57.00 + 56.00 + 51.15 + 49.50 + 39.00 + 57.00 = 309.65\n" +
                        "-----\n" +
                        "Full Name:LOTFI Dariush\n" +
                        "Rank:7\n" +
                        "Score:51.00 + 54.00 + 37.50 + 52.50 + 60.45 + 49.40 = 304.85\n" +
                        "-----\n" +
                        "Full Name:ONIKASHVILI Tornike\n" +
                        "Rank:8\n" +
                        "Score:65.10 + 43.50 + 50.70 + 43.70 + 27.00 + 50.70 = 280.70\n" +
                        "-----\n" +
                        "Full Name:SAKANDELIDZE Irakli\n" +
                        "Rank:9\n" +
                        "Score:46.80 + 43.40 + 43.50 + 39.00 + 40.50 + 36.40 = 249.60\n" +
                        "-----\n" +
                        "Full Name:LEE Wonseop\n" +
                        "Rank:10\n" +
                        "Score:42.90 + 27.00 + 37.50 + 55.50 + 31.50 + 49.60 = 244.00\n" +
                        "-----\n" +
                        "Full Name:HOULDEN Jordan Christopher\n" +
                        "Rank:1\n" +
                        "Score:76.50 + 76.50 + 75.25 + 68.25 + 86.40 + 89.30 = 472.20\n" +
                        "-----\n" +
                        "Full Name:DUTOIT Guillaume\n" +
                        "Rank:2\n" +
                        "Score:67.50 + 65.10 + 89.30 + 60.00 + 64.50 + 71.40 = 417.80\n" +
                        "-----\n" +
                        "Full Name:ABADIA Adrian\n" +
                        "Rank:3\n" +
                        "Score:58.50 + 74.80 + 69.75 + 63.00 + 69.00 + 73.50 = 408.55\n" +
                        "-----\n" +
                        "Full Name:GARCIA BOISSIER Nicolas\n" +
                        "Rank:4\n" +
                        "Score:67.50 + 69.75 + 55.50 + 61.50 + 71.75 + 76.50 = 402.50\n" +
                        "-----\n" +
                        "Full Name:KACHANOV Vyacheslav\n" +
                        "Rank:5\n" +
                        "Score:66.00 + 66.30 + 64.80 + 62.00 + 63.00 + 66.50 = 388.60\n" +
                        "-----\n" +
                        "Full Name:SCHALLER Nikolaj\n" +
                        "Rank:6\n" +
                        "Score:63.00 + 65.10 + 54.00 + 58.50 + 73.50 + 61.20 = 375.30\n" +
                        "-----\n" +
                        "Full Name:HART Alexander\n" +
                        "Rank:7\n" +
                        "Score:61.50 + 65.10 + 57.00 + 54.00 + 68.00 + 64.35 = 369.95\n" +
                        "-----\n" +
                        "Full Name:LUBE Alexander\n" +
                        "Rank:8\n" +
                        "Score:51.00 + 73.10 + 68.25 + 56.10 + 47.50 + 70.00 = 365.95\n" +
                        "-----\n" +
                        "Full Name:AZAROV Kyrylo\n" +
                        "Rank:9\n" +
                        "Score:58.50 + 69.75 + 71.40 + 69.00 + 27.00 + 68.25 = 363.90\n" +
                        "-----\n" +
                        "Full Name:SUCKOW Jonathan\n" +
                        "Rank:10\n" +
                        "Score:66.00 + 66.30 + 58.50 + 63.00 + 70.00 + 39.90 = 363.70\n" +
                        "-----\n" +
                        "Full Name:DIXON Matthew\n" +
                        "Rank:11\n" +
                        "Score:68.20 + 54.40 + 61.20 + 57.75 + 68.40 + 48.60 = 358.55\n" +
                        "-----\n" +
                        "Full Name:RUDIGER Lars\n" +
                        "Rank:12\n" +
                        "Score:52.50 + 38.50 + 21.00 + 0.00 + 0.00 + 0.00 = 112.00\n" +
                        "-----\n" +
                        "Full Name:TESSIER Benjamin\n" +
                        "Rank:1\n" +
                        "Score:67.20 + 88.40 + 67.50 + 84.15 + 74.25 + 84.60 = 466.10\n" +
                        "-----\n" +
                        "Full Name:LEE Robbie\n" +
                        "Rank:2\n" +
                        "Score:76.80 + 75.20 + 69.30 + 76.50 + 77.40 + 86.95 = 462.15\n" +
                        "-----\n" +
                        "Full Name:EIKERMANN GREGORCHUK Jaden\n" +
                        "Rank:3\n" +
                        "Score:76.80 + 75.85 + 79.20 + 81.00 + 79.90 + 68.40 = 461.15\n" +
                        "-----\n" +
                        "Full Name:HRYTSENKO Mark\n" +
                        "Rank:4\n" +
                        "Score:66.00 + 81.60 + 70.00 + 84.15 + 65.60 + 67.20 = 434.55\n" +
                        "-----\n" +
                        "Full Name:MYALIN Igor\n" +
                        "Rank:5\n" +
                        "Score:72.00 + 57.80 + 86.40 + 64.35 + 72.00 + 62.40 = 414.95\n" +
                        "-----\n" +
                        "Full Name:NAUMENKO Yevhen\n" +
                        "Rank:6\n" +
                        "Score:75.20 + 70.95 + 73.10 + 72.00 + 57.60 + 56.00 = 404.85\n" +
                        "-----\n" +
                        "Full Name:MCCABE Euan\n" +
                        "Rank:7\n" +
                        "Score:73.60 + 60.80 + 68.40 + 45.90 + 77.70 + 73.80 = 400.20\n" +
                        "-----\n" +
                        "Full Name:CHOI Gangin\n" +
                        "Rank:8\n" +
                        "Score:72.00 + 69.30 + 55.50 + 64.35 + 62.90 + 69.30 = 393.35\n" +
                        "-----\n" +
                        "Full Name:AVILA SANCHEZ Luis\n" +
                        "Rank:9\n" +
                        "Score:67.20 + 39.60 + 40.80 + 70.40 + 72.15 + 75.60 = 365.75\n" +
                        "-----\n" +
                        "Full Name:LOTFI Dariush\n" +
                        "Rank:10\n" +
                        "Score:60.80 + 57.60 + 59.40 + 54.00 + 68.00 + 62.40 = 362.20\n" +
                        "-----\n" +
                        "Full Name:CAMACHO DEL HOYO\n" +
                        "Rank:11\n" +
                        "Score:76.50 + 50.40 + 29.70 + 40.00 + 62.40 + 72.00 = 331.00\n" +
                        "-----\n" +
                        "Full Name:RODRIGUEZ LEDESMA Jorge\n" +
                        "Rank:12\n" +
                        "Score:38.40 + 57.75 + 79.20 + 59.50 + 42.55 + 48.60 = 326.00\n" +
                        "-----\n" +
                        "Full Name:LUBE Alexander & WESEMANN Moritz\n" +
                        "Rank:* | * | 1\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:47.40 + 46.80 + 79.80 + 70.35 + 78.54 + 78.66 = 401.55\n" +
                        "-----\n" +
                        "Full Name:RUDIGER Lars & BARTHEL Timo\n" +
                        "Rank:* | * | 2\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:49.80 + 49.80 + 72.45 + 66.15 + 74.46 + 68.40 = 381.06\n" +
                        "-----\n" +
                        "Full Name:ABADIA Adrian & GARCIA BOISSIER Nicolas\n" +
                        "Rank:* | * | 3\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:47.40 + 46.20 + 72.00 + 64.17 + 64.05 + 76.50 = 370.32\n" +
                        "-----\n" +
                        "Full Name:RZESZUTEK Andrzej & LESIAK Kacper\n" +
                        "Rank:* | * | 4\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:49.80 + 48.60 + 67.89 + 66.30 + 70.38 + 63.84 = 366.81\n" +
                        "-----\n" +
                        "Full Name:DUTOIT Guillaume & SUCKOW Jonathan\n" +
                        "Rank:* | * | 5\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:45.00 + 46.80 + 67.50 + 71.61 + 62.22 + 67.26 = 360.39\n" +
                        "-----\n" +
                        "Full Name:HART Alexander & SCHALLER Nikolaj\n" +
                        "Rank:* | * | 6\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:45.60 + 45.00 + 56.70 + 63.90 + 60.45 + 64.26 = 335.91\n" +
                        "-----\n" +
                        "Full Name:MYALIN Igor & KACHANOV Vyacheslav\n" +
                        "Rank:* | * | 7\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:46.20 + 40.20 + 66.60 + 64.17 + 52.20 + 52.02 = 321.39\n" +
                        "-----\n" +
                        "Full Name:CHOI Gangin & LEE Wonseop\n" +
                        "Rank:* | * | 8\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:43.80 + 37.80 + 59.52 + 56.70 + 58.50 + 53.10 = 309.42\n" +
                        "-----\n" +
                        "Full Name:PRENZYNA Espen & ROSLER Ole Johannes\n" +
                        "Rank:* | * | 1\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:52.20 + 51.60 + 74.88 + 72.27 + 69.36 + 73.92 = 394.23\n" +
                        "-----\n" +
                        "Full Name:EIKERMANN GREGORCHUK Jaden Shiloh & BARTHEL Timo\n" +
                        "Rank:* | * | 2\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:50.40 + 50.40 + 72.96 + 74.25 + 71.40 + 72.96 = 392.37\n" +
                        "-----\n" +
                        "Full Name:JACHIM Filip & LUKASZEWICZ Robert\n" +
                        "Rank:* | * | 3\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:44.40 + 46.20 + 61.20 + 72.00 + 75.24 + 65.28 = 364.32\n" +
                        "-----\n" +
                        "Full Name:AVANESOV Danylo & HRYTSENKO Mark\n" +
                        "Rank:* | * | 4\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:48.00 + 48.00 + 68.40 + 78.72 + 54.45 + 64.32 = 361.89\n" +
                        "-----\n" +
                        "Full Name:CAMACHO DEL HOYO Carlos & LINAN Max\n" +
                        "Rank:* | * | 5\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:50.40 + 45.00 + 68.40 + 63.36 + 60.39 + 72.96 = 360.51\n" +
                        "-----\n" +
                        "Full Name:AVILA SANCHEZ Luis Carlo & WALDSTEINER Tom\n" +
                        "Rank:* | * | 6\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:48.60 + 45.00 + 60.48 + 73.92 + 54.45 + 59.40 = 341.85\n" +
                        "-----\n" +
                        "Full Name:KIM Gyeongbin & KANG Jiho\n" +
                        "Rank:* | * | 7\n" +
                        "Preliminary Score:*\n" +
                        "Semifinal Score:*\n" +
                        "Final Score:43.80 + 42.60 + 53.10 + 54.72 + 68.31 + 59.16 = 321.69\n" +
                        "-----\n",
                FileReadAndWrite.readFile(outputFile));
    }
}