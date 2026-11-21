package core.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.data.JsonData;
import core.exceptions.DataPathException;
import core.exceptions.PathResolveException;
import core.exceptions.ReadJsonException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.security.CodeSource;

// Reconstruction of AI DS
public class ReadData {
    // 存放数据包路径配置的文件名，与程序（打包成的 jar 或运行的 classes 目录）位于同一文件夹
    private static final String CONFIG_FILE = "config.json";
    // 没有可用配置时读取的默认数据包文件名，与程序位于同一文件夹
    private static final String DEFAULT_DATA_FILE = "data.json";
    // status 取值：使用默认目录读取数据包
    private static final int STATUS_DEFAULT = 0;
    // status 取值：使用 path 中记录的路径读取数据包
    private static final int STATUS_DESIGN = 1;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 获取数据包路径
    public static Path getDataPath() {
        /*
            在整个项目打包成jar时运行时实现如下效果：

            先读取jar包同一文件夹下的config.json文件：
                文件存在可读取：
                    获取status，path，接下来以此读取data
                文件不存在或不可读取：
                    之后读取data默认在jar同文件夹下读取data.jsons

            本函数只返回用于读取data的Path
         */

        // 配置文件和默认数据包都以程序所在目录为基准
        Path programDir = getProgramDir();
        DataPath dataPath = readConfig(programDir.resolve(CONFIG_FILE));

        // 配置不存在、不可读取或没有记录可用路径时，读取默认目录下的数据包
        if (dataPath == null
                || dataPath.getStatus() == STATUS_DEFAULT
                || dataPath.getPath() == null
                || dataPath.getPath().isBlank()) {
            return programDir.resolve(DEFAULT_DATA_FILE);
        }

        // 使用配置中记录的路径
        return toDataPath(dataPath.getPath());
    }

    // 读取到数据结构并返回
    public static JsonData readData() {
        try {
            Path path = getDataPath();
            return MAPPER.readValue(path.toFile(),JsonData.class);
        } catch (IOException ex) {
            throw new ReadJsonException("无法读取文件");
        }
    }

    /*
        根据path来修改内部资源文件config.json，使所存储的路径更新并将status设置为1
        判断路径是否合法，不合法抛出DataPathException
     */
    public static void changePath(String path) {
        // 校验路径是否合法：空路径、语法错误以及不存在的文件都视为非法路径
        Path dataPath = toDataPath(path);
        if (!Files.isRegularFile(dataPath)) {
            throw new DataPathException("数据包路径不存在或不是文件：" + path);
        }

        writeConfig(STATUS_DESIGN, dataPath.toString());
    }

    /*
        将配置中的 status 设置为 0，即读取默认目录：与 jar 文件同目录
     */
    public static void changeStatusDefault() {
        changeStatus(STATUS_DEFAULT);
    }

    /*
        将配置中的 status 设置为 1，即读取 path 中记录的路径
     */
    public static void changeStatusDesign() {
        changeStatus(STATUS_DESIGN);
    }

    // 修改配置中的 status，已记录的 path 保持不变
    private static void changeStatus(int status) {
        DataPath dataPath = readConfig(getProgramDir().resolve(CONFIG_FILE));
        writeConfig(status, dataPath == null ? null : dataPath.getPath());
    }

    /*
        读取配置文件，文件不存在或不可读取时返回 null
     */
    private static DataPath readConfig(Path configPath) {
        if (!Files.isRegularFile(configPath)) {
            return null;
        }

        try {
            return MAPPER.readValue(configPath.toFile(), DataPath.class);
        } catch (IOException ex) {
            return null;
        }
    }

    // 将 status 与 path 写入配置文件
    private static void writeConfig(int status, String path) {
        DataPath dataPath = new DataPath();
        dataPath.setPath(path);
        if (status == STATUS_DESIGN) {
            dataPath.setReadDesign(status);
        } else {
            dataPath.setReadDefault();
        }

        Path configPath = getProgramDir().resolve(CONFIG_FILE);
        try {
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(configPath.toFile(), dataPath);
        } catch (IOException ex) {
            throw new DataPathException("无法写入配置文件：" + configPath);
        }
    }

    // 将字符串路径转换为 Path，空路径或语法错误抛出 DataPathException
    private static Path toDataPath(String path) {
        if (path == null || path.isBlank()) {
            throw new DataPathException("数据包路径不能为空");
        }

        try {
            return Path.of(path.trim());
        } catch (InvalidPathException ex) {
            throw new DataPathException("数据包路径不合法：" + path);
        }
    }

    // 程序所在目录：打包成 jar 时为 jar 文件所在目录，未打包时为 classes 目录
    private static Path getProgramDir() {
        try {
            CodeSource codeSource = ReadData.class.getProtectionDomain().getCodeSource();
            if (codeSource == null) {
                throw new PathResolveException("无法读取文件");
            }

            Path location = Path.of(codeSource.getLocation().toURI());
            Path programDir = Files.isDirectory(location) ? location : location.getParent();
            if (programDir == null) {
                throw new PathResolveException("无法读取文件");
            }

            return programDir;
        } catch (URISyntaxException ex) {
            throw new PathResolveException("无法读取文件");
        }
    }

    static class DataPath {
        private int status; // 0 表示用默认目录：与 jar 文件同目录；1 表示使用 path 写入的目录
        private String path; // data.json 文件目录

        // setter getter
        public int getStatus() { return status; }
        public String getPath() { return path; }

        public void setPath(String path) {
            this.path = path;
        }

        public void setReadDefault() {
            this.status = 0;
        }

        public void setReadDesign(int status) {
            this.status = 1;
        }
    }
}
