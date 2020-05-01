package com.kvitkov.gameofcities;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilityTest {
    @Test
    public void testLastLetter() {
        assertEquals('А', (char) Utility.getLastLetter("МОСКВА"));
        assertEquals('Н', (char) Utility.getLastLetter("КУРГАН"));
        assertEquals('Н', (char) Utility.getLastLetter("КАЗАНЬ"));
        assertEquals('Н', (char) Utility.getLastLetter("КАЗАНЬЬЬЬЬЬ"));
    }


    @Test(expected = IllegalStateException.class)
    public void testLastLetterInvalidInput() {
        Utility.getLastLetter("ЬЬЬЬЬЬЬЬЬ");
    }
}
