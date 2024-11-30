package com.dwa.Lib;

import java.io.IOException;

public class NAService implements OutputService {

    @Override
    public String getOutputString(String fileName) throws IOException {
        return "\nN/A\n-----\n";
    }
}
