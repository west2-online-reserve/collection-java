package org.example;

import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CoreModuleTest {
    CoreModule coreModule=new CoreModule();
    @Test
    void textreadJsonFromData()throws IOException {
        String ans=coreModule.readJsonFromData("fis");
        assertEquals("not found file",ans);
    }
}