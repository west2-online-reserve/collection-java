import Constant.DivingEventConstants;
import Constant.FileConstants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Deprecated {

        /*
        返回值：
     */
//    public List<String> readAllFilesInDir() {
//        List<String> contents = new ArrayList<>();
//        String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//
//        try (JarFile jar = new JarFile(jarPath)) {
//            Enumeration<JarEntry> entries = jar.entries();
//            while (entries.hasMoreElements()) {
//                JarEntry entry = entries.nextElement();
//                if (entry.getName().startsWith(fileName) && !entry.isDirectory()) {
//                    try (InputStream is = jar.getInputStream(entry);
//                         BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
//                        contents.add(reader.readLine());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return contents;
//    }

    public static void processArgs(String[] args) {
        if (args.length != 2) {
            throw new ArgumentsException(FileConstants.ARGS_ERROR);
        }

        File file = new File(args[0]);

        for (String arg : args) {
            if (!arg.endsWith(".txt")) {
                throw new ArgumentsException(FileConstants.SUFFIX_ERROR);
            }
        }

        if (!file.exists()) {
            throw new ArgumentsException(FileConstants.INPUT_FILE_NOT_EXIST);
        }

        Path outputArgPath = Paths.get(args[1]);
        String dest;
        if (outputArgPath.isAbsolute()) {
            dest = args[1];
        } else {
            dest = DivingEventConstants.OUTPUT_PATH + args[1];
        }

        if (!Files.exists(outputArgPath)) {
            System.out.println("即将创建新文件" + dest);
        } else {
            System.out.println("即将覆盖文件" + dest);
            Lib.outPrint("", dest, false);
        }

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            String content;
            while ((content = bufferedReader.readLine()) != null) {

                if (content.equals("players")) {
                    Lib.outputPlayers(dest);
                    continue;
                }

                if (!content.startsWith("result ")) {
                    Lib.outPrint(FileConstants.String_ERROR + FileConstants.DELIMITER, dest);
                    continue;
                }
                String subContent = content.substring("result ".length());
                if (subContent.endsWith(" detail")) {
                    subContent = subContent.substring(0, subContent.length() - " detail".length());
                }
                if (subContent.isEmpty()) {
                    Lib.outPrint(FileConstants.String_Wrong + FileConstants.DELIMITER, dest);
                    continue;
                }

                int commandType = content.endsWith(" detail") ? FileConstants.RESULT_DETAIL : FileConstants.RESULT;
                boolean eventExists = Lib.findEvents(subContent, dest, commandType);

                if (!eventExists) {
                    Lib.outPrint(FileConstants.String_Wrong + FileConstants.DELIMITER, dest);
                }
            }
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
