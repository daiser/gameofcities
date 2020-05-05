package com.kvitkov.gameofcities.contracts;

public interface UsedWords extends Dictionary {
    boolean add(String word);

    void clear();
}
