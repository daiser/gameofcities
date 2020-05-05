package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.UsedWords;

import java.util.HashSet;

public class TeDi extends HashSet<String> implements UsedWords {
    public boolean contains(String word) {
        return super.contains(word);
    }
}
