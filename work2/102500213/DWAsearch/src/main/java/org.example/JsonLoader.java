package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonLoader {
    private final ObjectMapper mapper = new ObjectMapper();

    public Data load(Path dataFile) throws JsonProcessingException, IOException {
        String dataStr = Files.readString(dataFile);
        return mapper.readValue(dataStr, Data.class);
    }
}
