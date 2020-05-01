package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.Dictionary;

import java.util.ArrayList;
import java.util.Iterator;

public class EmptyDictionary implements Dictionary {
    public boolean contains(String word) {
        return false;
    }


    public Iterator<String> iterator() {
        return new ArrayList<String>().iterator();
    }
}
