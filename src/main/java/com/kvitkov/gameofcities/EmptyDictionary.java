package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.AllWords;

import java.util.Collections;
import java.util.Iterator;

public class EmptyDictionary implements AllWords {
    public boolean contains(Word word) {
        return false;
    }


    public Iterator<Word> iterator() {
        return Collections.emptyIterator();
    }


    public void save(Word newWord) {}
}
