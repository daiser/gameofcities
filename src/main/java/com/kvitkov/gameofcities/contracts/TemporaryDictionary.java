package com.kvitkov.gameofcities.contracts;

public interface TemporaryDictionary extends Dictionary {
    boolean add(String word);

    void clear();
}
