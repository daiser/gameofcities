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
        assertEquals("МА", word.meaning);
    }


    @Test
    public void signatureCompare() {
        Word w1 = new Word("МОСКВА");
        Word w2 = new Word("МАСЛОВКА");

        assertEquals(w1.meaning, w2.meaning);
        assertEquals(w1.signature, w2.signature);
        assertNotEquals(w1, w2);
    }
}
