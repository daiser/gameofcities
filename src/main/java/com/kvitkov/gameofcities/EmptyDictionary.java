package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.AllWords;

import java.util.Collections;
import java.util.Iterator;

public class EmptyDictionary implements AllWords {
    public boolean contains(String word) {
        return false;
    }


    public Iterator<String> iterator() {
        return Collections.emptyIterator();
    }


    public void save(String newWord) {}
}
