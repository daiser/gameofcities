package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class FileDictionary implements Dictionary {
    private final String fileName;
    private final HashSet<String> words;


    public FileDictionary(String fileName) throws IOException {
        this.fileName = fileName;
        this.words = new HashSet<String>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        for (String line; (line = br.readLine()) != null; ) {
            String cleanLine = Utility.cleanUp(line);
            if (cleanLine.length() == 0) continue;
            words.add(cleanLine);
        }
        br.close();
    }


    public boolean contains(String word) {
        return words.contains(word);
    }


    public Iterator<String> iterator() {
        return words.iterator();
    }
}
