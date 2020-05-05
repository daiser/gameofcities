package com.kvitkov.gameofcities;

import org.junit.Test;

import static org.junit.Assert.*;

public class DbDictionaryTest {
    @Test
    public void contains() {
        DbDictionary dbd = new DbDictionary();
        assertTrue(dbd.contains("МОСКВА"));
        assertFalse(dbd.contains("МАГНИТОГОРСК"));
    }

    @Test
    public void save() {
        DbDictionary dbd = new DbDictionary();
        dbd.save("ЖИТОМИР");
        dbd.save("ЖИТОМИР");
    }
}
