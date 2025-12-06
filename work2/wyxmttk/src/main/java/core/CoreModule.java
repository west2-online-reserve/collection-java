package core;

import core.argument.*;
import crawler.DataCrawler;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class CoreModule {

    private String inputPath;
    private String outputPath;

    public static final String PLAYER="players";
    public static final String MEN="men";
    public static final String WOMEN="women";
    public static final String HEIGHT_PATTERN="^\\d+m$";
    public static final String DETAIL="detail";
    public static final String RESULT="result";


    public CoreModule(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void invoke(){
        Path input = Paths.get(inputPath);
        if(!Files.exists(input)){
            throw new NullPointerException("Input file does not exist");
        }
        List<String> args;
        try {
            args = Files.readAllLines(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Argument> argumentList = new ArrayList<>();
        for(String arg : args){
            Argument argument = parseStringArg(arg);
            if(argument!=null){
                argumentList.add(argument);
            }
        }
        Arguments arguments = new Arguments(argumentList);
        DataCrawler.invoke(outputPath, arguments);
    }

    private Argument parseStringArg(String arg){
        if(arg==null || arg.isBlank()) return null;

        if(PLAYER.equals(arg)){
            return new PlayerInfoArgument();
        }

        String[] s = arg.split(" ");
        int length=s.length;
        if(!RESULT.equals(s[0])) return new ErrorArgument();
        if(length<4) return new InvalidArgument();
        String gender = s[1];
        String height = s[2];
        String sport = s[3];
        String detail=null;
        if(length == 5){
            detail = s[4];
        }else if(length > 5){
            return new InvalidArgument();
        }

        //validate args
        if(!(MEN.equals(gender) || WOMEN.equals(gender))) return new InvalidArgument();
        if(height==null || !(height.matches(HEIGHT_PATTERN))) return new InvalidArgument();
        if(sport==null || Arrays.binarySearch(Arguments.SPORTS,sport)<0) return new InvalidArgument();
        if(detail!=null && !DETAIL.equals(detail)) return new InvalidArgument();

        int heightArg = Integer.parseInt(height.substring(0, height.length() - 1));
        ResultArgument resultArgument = new ResultArgument();
        resultArgument.setGender(gender);
        resultArgument.setHeight(heightArg);
        resultArgument.setSport(sport);
        if(detail!=null){
            resultArgument.setDetail(true);
        }
        return resultArgument;
    }

    public static CoreModule build(String[] args){
        if(args.length < 2){
            throw new RuntimeException("程序启动要求参数input.txt及output.txt");
        }
        String inputPath = args[0];
        String outputPath = args[1];
        return new CoreModule(inputPath, outputPath);
    }
}
