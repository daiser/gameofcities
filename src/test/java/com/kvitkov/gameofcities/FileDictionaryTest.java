package com.kvitkov.gameofcities;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FileDictionaryTest {
    @Test
    public void save() {
        try {
            File tmpFile = new File(System.getenv("TMP") + "\\goc_fdsave_test.txt");
            tmpFile.createNewFile();

            FileDictionary d1 = new FileDictionary(tmpFile.getAbsolutePath());
            d1.save("ВАЛЕРА");
            FileDictionary d2 = new FileDictionary(tmpFile.getAbsolutePath());
            assertTrue(d2.contains("ВАЛЕРА"));

            tmpFile.delete();
        } catch (IOException iox) {
            throw new RuntimeException("Test failed", iox);
        }
    }
}
