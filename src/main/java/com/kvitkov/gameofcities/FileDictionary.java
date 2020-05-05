package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.AvailableWords;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class FileDictionary implements AvailableWords {
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


    public void save(String newWord) {
        if (contains(newWord)) return;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
            bw.write(newWord);
            bw.newLine();
            bw.close();
        } catch (IOException iox) {
            throw new RuntimeException("Не удалось сохранить слово", iox);
        }
    }
}
