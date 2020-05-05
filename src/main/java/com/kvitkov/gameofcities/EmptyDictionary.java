package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.AllWords;

import java.util.ArrayList;
import java.util.Iterator;

public class EmptyDictionary implements AllWords {
    public boolean contains(String word) {
        return false;
    }


    @SuppressWarnings("RedundantOperationOnEmptyContainer")
    public Iterator<String> iterator() {
        return new ArrayList<String>().iterator();
    }


    public void save(String newWord) {}
}
