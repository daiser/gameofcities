package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.GameWordsSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;

public class EmptyDictionary implements GameWordsSet {
    public boolean contains(Word word) {
        return false;
    }


    public Iterator<Word> iterator() {
        return Collections.emptyIterator();
    }


    public void save(@NotNull Word newWord) {}
}
