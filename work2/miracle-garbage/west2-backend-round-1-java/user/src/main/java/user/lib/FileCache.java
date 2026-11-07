package user.lib;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

// 缓存已打开文件对象
public class FileCache {
    private final Map<Path, BufferedWriter> cache;

    public FileCache() {
        this.cache = new HashMap<>();
    }

    public void loadFile(String filename) {
        /*
            根据给到的文件名，构造合法的Path添加到cache
            先判断路径是否正确，不正确抛出异常
            路径正确后：
                文件已经存在：
                    直接构造加入cache，append模式
                文件不存在：
                    创建文件并加入cache，append模式

            注意同步访问问题，只判断文件存在性而不访问占用文件

            code is generate with ai DS
         */
        // 路径合法化交给 createLegalPath，返回 null 表示路径不合法
        Path path = createLegalPath(filename);
        if (path == null) {
            throw new IllegalArgumentException("文件路径不合法：" + filename);
        }

        // 同步访问：同一文件只打开一次，并避免并发下重复创建
        // TODO 实际上并没有实现并发运行指令
        synchronized (cache) {
            try {
                // 只判断存在性，不通过打开文件的方式去测试，避免占用文件
                if (Files.notExists(path)) {
                    Files.createFile(path);
                }

                // append模式打开，已缓存过则直接复用
                if (!cache.containsKey(path)) {
                    cache.put(path, Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                                                                StandardOpenOption.CREATE,
                                                                StandardOpenOption.APPEND));
                }
            } catch (IOException e) {
                throw new UncheckedIOException("打开文件失败：" + path, e);
            }
        }
    }

    // 合法的Path构建返回对应对象，不合法返回空
    public Path createLegalPath(String filename) {
        if (filename == null || filename.isBlank()) {
            return null;
        }

        Path path;
        try {
            // 构造合法Path：相对路径统一转成绝对路径，并消去 "." 和 ".."
            path = Path.of(filename).toAbsolutePath().normalize();
        } catch (InvalidPathException e) {
            // 路径字符串本身非法
            return null;
        }

        // 上级目录必须存在，否则路径不正确
        Path parent = path.getParent();
        if (parent == null || !Files.isDirectory(parent)) {
            return null;
        }

        // 路径已存在时必须指向普通文件，目录等一律视为不合法
        if (Files.exists(path) && !Files.isRegularFile(path)) {
            return null;
        }

        return path;
    }

    // 获取对应文件的Writer，尚未打开时先打开并缓存
    public BufferedWriter createFileWriter(String filename) {
        Path path = createLegalPath(filename);
        if (path == null) {
            throw new IllegalArgumentException("文件路径不合法：" + filename);
        }

        synchronized (cache) {
            // 池中没有对应文件则先打开，loadFile 与这里用的是同一个 key
            if (!cache.containsKey(path)) {
                loadFile(filename);
            }
            return cache.get(path);
        }
    }

    // 关闭map中所有文件对象
    public void clear() {

        // 先关闭全部Writer：close 内部会先flush，缓冲区内容不会丢
        UncheckedIOException closeFailure = null;
        for (Map.Entry<Path, BufferedWriter> entry : cache.entrySet()) {
            try {
                entry.getValue().close();
            } catch (IOException e) {
                // 单个文件关闭失败不能影响其余文件继续关闭，先记录再统一向上抛
                if (closeFailure == null) {
                    closeFailure = new UncheckedIOException("关闭文件失败：" + entry.getKey(), e);
                } else {
                    closeFailure.addSuppressed(e);
                }
            }
        }
        // 无论是否全部关闭成功，都不能继续持有已失效的Writer
        cache.clear();
        if (closeFailure != null) {
            throw closeFailure;
        }

    }

}
