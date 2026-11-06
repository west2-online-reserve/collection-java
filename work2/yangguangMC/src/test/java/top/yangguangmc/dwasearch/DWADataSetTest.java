package top.yangguangmc.dwasearch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.exc.JsonNodeException;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DWADataSetTest {
    private static InputStream missingFinalsStream;
    private static InputStream invalidSyntaxStream;
    private static InputStream invalidTypeStream;

    @BeforeAll
    static void setUp() {
        missingFinalsStream = new ByteArrayInputStream("""
                {
                  "Heats": [
                    {
                      "Name": "Semifinals"
                    },
                    {
                      "Name": "Head-To-Head"
                    }
                  ]
                }
                """.getBytes(StandardCharsets.UTF_8));
        invalidSyntaxStream = new ByteArrayInputStream("""
                [ {
                  "CountryName" : "Australia",
                  "CountryCode" : "AUS",
                  "Participations" : [ {
                    "ResultId" : 1769867,
                    "Gender" : 1,
                    "PreferredLastName" : "COLE",
                    "PreferredFirstName" : "Ellie",
                    "ScoreboardPhotoId" : "3e06283f-063f-470b-b46b-a317b81a4dd4",
                    "Sports" : "DV",
                    "DOB" : "2006-09-16T00:00:00",
                    "NAT" : "Australia",
                    "Disciplines" : [ {
                      "DisciplineId" : "581a26af-8e97-427c-9adb-5fdd8f4fc9c1",
                      "Disc
                """.getBytes(StandardCharsets.UTF_8));
        invalidTypeStream = new ByteArrayInputStream("""
                {
                  "Heats": [
                    {
                      "Name": "Finals",
                      "Results": [
                        {
                          "FullName": "First Last",
                          "Rank": "1",
                          "Dives": [
                            {
                              "DivePoints": "0.00"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }
                """.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void exceptionsDuringLoad() {
        assertThrows(FileNotFoundException.class, () -> DWADataSet.load(resource -> null));
        assertThrows(DWADataSet.DataFormatException.class, () -> DWADataSet.load(resource -> {
            if (resource.contains("athletes")) {
                return DWADataSet.class.getResourceAsStream(resource);
            }
            return missingFinalsStream;
        }));
        assertThrows(StreamReadException.class, () -> DWADataSet.load(resource -> {
            if (resource.contains("athletes")) {
                return DWADataSet.class.getResourceAsStream(resource);
            }
            return invalidSyntaxStream;
        }));
        assertThrows(JsonNodeException.class, () -> DWADataSet.load(resource -> {
            if (resource.contains("athletes")) {
                return DWADataSet.class.getResourceAsStream(resource);
            }
            return invalidTypeStream;
        }));
    }
}