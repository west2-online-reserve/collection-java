package com.dwa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DWASearchTest {

    @TempDir
    Path tempDir;

    private Path createInput(String content) throws IOException {
        Path input = tempDir.resolve("input.txt");
        Files.writeString(input, content);
        return input;
    }

    private String execute(String commands) throws IOException {
        Path input = createInput(commands);
        Path output = tempDir.resolve("output.txt");

        int exitCode = DWASearch.run(new String[]{
                input.toString(),
                output.toString()
        });

        assertEquals(0, exitCode);
        assertTrue(Files.exists(output));

        return Files.readString(output);
    }

    @Test
    void testNoArguments() {
        assertNotEquals(0, DWASearch.run(new String[]{}));
    }

    @Test
    void testOneArgument() {
        assertNotEquals(0,
                DWASearch.run(new String[]{"input.txt"}));
    }

    @Test
    void testTooManyArguments() {
        assertNotEquals(0,
                DWASearch.run(new String[]{
                        "a.txt", "b.txt", "c.txt"
                }));
    }

    @Test
    void testInputFileNotFound() {
        Path input = tempDir.resolve("missing.txt");
        Path output = tempDir.resolve("output.txt");

        int exitCode = DWASearch.run(new String[]{
                input.toString(),
                output.toString()
        });

        assertNotEquals(0, exitCode);
    }

    @Test
    void testEmptyInputFile() throws IOException {
        String output = execute("");

        assertEquals("", output);
    }

    @Test
    void testBlankLines() throws IOException {
        String output = execute("\n\n   \n");

        assertEquals("", output);
    }

    @Test
    void testUnknownCommand() throws IOException {
        String output = execute("hello");

        assertTrue(output.contains("Error"));
    }

    @Test
    void testResultWithoutArgument() throws IOException {
        String output = execute("result");

        assertTrue(output.contains("N/A"));
    }

    @Test
    void testInvalidEvent() throws IOException {
        String output = execute("result unknown event");

        assertTrue(output.contains("N/A"));
    }

    @Test
    void testPlayersCommand() throws IOException {
        String output = execute("players");

        assertFalse(output.isEmpty());

        assertTrue(output.contains("Australia")
                || output.contains("China"));

        assertTrue(output.contains("-----"));
    }

    @Test
    void testMenSpringboardResult() throws IOException {
        String output = execute("result men 3m springboard");

        assertTrue(output.contains("Full Name"));
        assertTrue(output.contains("Rank"));
        assertTrue(output.contains("Score"));
        assertTrue(output.contains("-----"));
    }

    @Test
    void testResultScoreFormat() throws IOException {
        String output = execute("result men 3m springboard");

        Pattern pattern = Pattern.compile(
                "\\d+\\.\\d{2}"
        );

        assertTrue(pattern.matcher(output).find());
    }

    @Test
    void testResultTotalScoreFormat() throws IOException {
        String output = execute("result men 3m springboard");

        assertTrue(output.matches(
                "(?s).* = \\d+\\.\\d{2}.*"
        ));
    }


    @Test
    void testMultipleCommands() throws IOException {
        String output = execute(
                "players\n" +
                        "result men 3m springboard\n" +
                        "result\n"
        );

        assertTrue(output.contains("Full Name"));
        assertTrue(output.contains("N/A"));
    }
    @Test
    void testRepeatedCommands() throws IOException {
        String output = execute(
                "result men 3m springboard\n" +
                        "result men 3m springboard"
        );

        int first = output.indexOf("Full Name");

        int second = output.indexOf(
                "Full Name", first + 1
        );

        assertTrue(first >= 0);
        assertTrue(second > first);
    }
    @Test
    void testExtraSpaces() throws IOException {
        String output = execute(
                "   result men 3m springboard   "
        );

        assertTrue(output.contains("Full Name"));
    }

    @Test
    void testContinueAfterInvalidCommand() throws IOException {
        String output = execute(
                "hello\n" +
                        "result men 3m springboard"
        );

        assertTrue(output.contains("Error"));
        assertTrue(output.contains("Full Name"));
    }

    @Test
    void testOutputFileCreated() throws IOException {
        Path input = createInput("result");

        Path output = tempDir.resolve("result.txt");

        int exitCode = DWASearch.run(new String[]{
                input.toString(),
                output.toString()
        });

        assertEquals(0, exitCode);
        assertTrue(Files.exists(output));
        assertFalse(Files.readString(output).isEmpty());
    }

    @Test
    void testValidatePlayersRejectsNullList() {
        IOException exception = assertThrows(
                IOException.class,
                () -> DWASearch.validatePlayers(null)
        );

        assertTrue(exception.getMessage().contains("数据为空"));
    }

    @Test
    void testValidatePlayersRejectsMissingCountryName() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Country country = mapper.readValue(
                "{\"Participations\":[]}",
                Country.class
        );

        List<Country> countries = List.of(country);

        IOException exception = assertThrows(
                IOException.class,
                () -> DWASearch.validatePlayers(countries)
        );

        assertTrue(exception.getMessage().contains("国家必要字段"));
    }

    @Test
    void testValidatePlayersRejectsMissingAthleteName() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Country country = mapper.readValue(
                """
                {
                    "CountryName": "China",
                    "Participations": [
                        {
                            "LastName": "CHEN",
                            "Gender": 1
                        }
                    ]
                }
                """,
                Country.class
        );

        List<Country> countries = List.of(country);

        IOException exception = assertThrows(
                IOException.class,
                () -> DWASearch.validatePlayers(countries)
        );

        assertTrue(exception.getMessage().contains("运动员必要字段"));
    }

    @Test
    void testValidatePlayersRejectsMissingGender() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Country country = mapper.readValue(
                """
                {
                    "CountryName": "China",
                    "Participations": [
                        {
                            "FirstName": "Jia",
                            "LastName": "CHEN"
                        }
                    ]
                }
                """,
                Country.class
        );

        List<Country> countries = List.of(country);

        IOException exception = assertThrows(
                IOException.class,
                () -> DWASearch.validatePlayers(countries)
        );

        assertTrue(exception.getMessage().contains("运动员必要字段"));
    }

    @Test
    void testReadEventRejectsMissingFile() throws Exception {
        java.lang.reflect.Method method =
                DWASearch.class.getDeclaredMethod(
                        "readEvent", String.class
                );

        method.setAccessible(true);

        java.lang.reflect.InvocationTargetException exception =
                assertThrows(
                        java.lang.reflect.InvocationTargetException.class,
                        () -> method.invoke(
                                null,
                                "this-file-does-not-exist.json"
                        )
                );

        assertTrue(
                exception.getCause() instanceof IOException
        );

        assertTrue(
                exception.getCause().getMessage()
                        .contains("JSON 文件不存在或无法读取")
        );
    }

    @Test
    void testReadEventRejectsInvalidJson() throws Exception {
        Path file = tempDir.resolve("invalid.json");

        Files.writeString(file, "{ invalid json }");

        java.lang.reflect.Method method =
                DWASearch.class.getDeclaredMethod(
                        "readEvent", String.class
                );

        method.setAccessible(true);

        java.lang.reflect.InvocationTargetException exception =
                assertThrows(
                        java.lang.reflect.InvocationTargetException.class,
                        () -> method.invoke(null, file.toString())
                );

        assertTrue(exception.getCause() instanceof IOException);

        assertTrue(
                exception.getCause().getMessage()
                        .contains("JSON 格式错误或数据结构不匹配")
        );
    }

    @Test
    void testReadEventRejectsMissingHeats() throws Exception {
        Path file = tempDir.resolve("missing-heats.json");

        Files.writeString(file, "{}");

        java.lang.reflect.Method method =
                DWASearch.class.getDeclaredMethod(
                        "readEvent", String.class
                );

        method.setAccessible(true);

        java.lang.reflect.InvocationTargetException exception =
                assertThrows(
                        java.lang.reflect.InvocationTargetException.class,
                        () -> method.invoke(null, file.toString())
                );

        assertTrue(exception.getCause() instanceof IOException);

        assertTrue(
                exception.getCause().getMessage()
                        .contains("JSON 缺少必要的heats数据")
        );
    }
    @ParameterizedTest
    @CsvSource({
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    })
    void testAllEightEvents(String eventName) throws IOException {

        String output = execute("result " + eventName);

        assertFalse(output.isEmpty());
        assertTrue(output.contains("Full Name"));
        assertTrue(output.contains("Rank"));
        assertTrue(output.contains("Score"));

    }
}