package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.TemporaryDictionary;

import java.util.HashSet;

public class TeDi extends HashSet<String> implements TemporaryDictionary {
    public boolean contains(String word) {
        return super.contains(word);
    }
}
