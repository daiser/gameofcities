package com.kvitkov.gameofcities;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {
    @Test
    public void create() {
        Word word = new Word("МОСКВА");
        assertEquals("МОСКВА", word.value);
        assertEquals('М', word.first);
        assertEquals('А', word.last);
    }


    @Test
    public void signatureCompare() {
        Word w1 = new Word("МОСКВА");
        Word w2 = new Word("МАСЛОВКА");

        assertEquals(w1.code, w2.code);
        assertNotEquals(w1, w2);
    }


    @Test
    public void testLastLetter() {
        assertEquals('А', (char) Word.getLastLetter("МОСКВА"));
        assertEquals('Н', (char) Word.getLastLetter("КУРГАН"));
        assertEquals('Н', (char) Word.getLastLetter("КАЗАНЬ"));
        assertEquals('Н', (char) Word.getLastLetter("КАЗАНЬЬЬЬЬЬ"));
    }


    @Test(expected = IllegalStateException.class)
    public void testLastLetterInvalidInput() {
        Word.getLastLetter("ЬЬЬЬЬЬЬЬЬ");
    }
}
