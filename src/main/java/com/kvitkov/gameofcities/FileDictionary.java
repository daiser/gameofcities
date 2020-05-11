package com.kvitkov.gameofcities;

import com.kvitkov.gameofcities.contracts.GameWordsSet;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class FileDictionary implements GameWordsSet {
    private final String fileName;
    private final HashSet<Word> words;


    public FileDictionary(String fileName) throws IOException {
        this.fileName = fileName;
        this.words = new HashSet<>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        for (String line; (line = br.readLine()) != null; ) {
            String cleanLine = Utility.cleanUp(line);
            if (cleanLine.length() == 0) continue;
            words.add(new Word(cleanLine));
        }
        br.close();
    }


    public boolean contains(Word word) {
        return words.contains(word);
    }


    public Iterator<Word> iterator() {
        return words.iterator();
    }


    public void save(@NotNull Word newWord) {
        if (contains(newWord)) return;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
            bw.write(newWord.value);
            bw.newLine();
            bw.close();
        } catch (IOException iox) {
            throw new RuntimeException("Не удалось сохранить слово", iox);
        }
    }
}
