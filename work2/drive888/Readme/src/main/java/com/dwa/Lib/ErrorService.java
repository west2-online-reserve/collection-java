package com.dwa.Lib;

import java.io.IOException;

public class ErrorService implements OutputService {
    @Override
    public String getOutputString(String fileName) throws IOException {
        return "Error\n-----\n";
    }
}
