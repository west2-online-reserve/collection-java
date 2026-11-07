package user.lib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import user.support.TestFixtures;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileCacheTest {

    @TempDir
    Path temporaryDirectory;

    @Test
    void validatesLegalAndIllegalPaths() throws Exception {
        Path existingFile = Files.writeString(temporaryDirectory.resolve("existing.txt"), "data");

        assertNull(new FileCache().createLegalPath(null));
        assertNull(new FileCache().createLegalPath(" "));
        assertNull(new FileCache().createLegalPath("\0"));
        assertNull(new FileCache().createLegalPath(temporaryDirectory.resolve("missing").resolve("a.txt").toString()));
        assertNull(new FileCache().createLegalPath(temporaryDirectory.toString()));
        assertEquals(existingFile.toAbsolutePath().normalize(),
                new FileCache().createLegalPath(existingFile.toString()));
    }

    @Test
    void writesNewUtf8FileAndFlushesOnClear() throws Exception {
        Path output = temporaryDirectory.resolve("output.txt");
        FileCache fileCache = new FileCache();
        BufferedWriter writer = fileCache.createFileWriter(output.toString());

        writer.write("中文内容\n");
        fileCache.clear();

        assertEquals("中文内容\n", TestFixtures.readFile(output));
    }

    @Test
    void appendsToExistingFileAndReusesCachedWriter() throws Exception {
        Path output = Files.writeString(temporaryDirectory.resolve("output.txt"), "first\n");
        FileCache fileCache = new FileCache();
        BufferedWriter firstWriter = fileCache.createFileWriter(output.toString());

        firstWriter.write("second\n");

        Path equivalentPath = temporaryDirectory.resolve(".").resolve("output.txt");
        BufferedWriter sameWriter = fileCache.createFileWriter(equivalentPath.toString());
        assertSame(firstWriter, sameWriter);
        fileCache.clear();

        assertEquals("first\nsecond\n", TestFixtures.readFile(output));
    }

    @Test
    void createsWriterDirectlyWhenFileWasNotLoaded() throws Exception {
        Path output = temporaryDirectory.resolve("direct.txt");
        FileCache fileCache = new FileCache();

        fileCache.loadFile(output.toString());
        fileCache.createFileWriter(output.toString()).write("loaded\n");
        fileCache.clear();

        assertEquals("loaded\n", TestFixtures.readFile(output));
    }

    @Test
    void rejectsInvalidFileNameWhenCreatingWriter() {
        FileCache fileCache = new FileCache();

        assertThrows(
                IllegalArgumentException.class,
                () -> fileCache.createFileWriter(temporaryDirectory.resolve("missing").resolve("x.txt").toString())
        );
        assertThrows(IllegalArgumentException.class, () -> fileCache.loadFile(" "));
    }

    @Test
    void clearIsSafeWhenNoFilesWereOpened() {
        new FileCache().clear();
    }
}
