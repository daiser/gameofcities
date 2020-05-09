package com.kvitkov.gameofcities;

import org.junit.Test;

import static org.junit.Assert.*;

public class DbDictionaryTest {
    @Test
    public void contains() {
        DbDictionary dbd = new DbDictionary();
        assertTrue(dbd.contains(new Word("МОСКВА")));
        assertFalse(dbd.contains(new Word("МАГНИТОГОРСК")));
    }


    @Test
    public void save() {
        DbDictionary dbd = new DbDictionary();
        dbd.save(new Word("ЖИТОМИР"));
        dbd.save(new Word("ЖИТОМИР"));
    }
}
