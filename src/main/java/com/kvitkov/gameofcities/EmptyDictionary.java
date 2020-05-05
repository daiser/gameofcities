package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.AvailableWords;

import java.util.ArrayList;
import java.util.Iterator;

public class EmptyDictionary implements AvailableWords {
    public boolean contains(String word) {
        return false;
    }


    @SuppressWarnings("RedundantOperationOnEmptyContainer")
    public Iterator<String> iterator() {
        return new ArrayList<String>().iterator();
    }


    public void save(String newWord) {}
}
