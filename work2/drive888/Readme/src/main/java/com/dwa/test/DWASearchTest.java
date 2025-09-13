package com.dwa.test;


import com.dwa.DWASearch;
import org.testng.annotations.Test;

import java.io.IOException;

public class DWASearchTest {

    @Test
    public void main() throws IOException {
        DWASearch.main(new String[]{"input,txt"});
    }
}